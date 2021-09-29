package com.cocos.develop.libcos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.cocos.develop.libcos.databinding.ActivityMainBinding
import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.impl.UserRoomRepoImpl
import com.cocos.develop.libcos.ui.profile.Contract
import com.cocos.develop.libcos.ui.profile.UserProfilePresenter
import com.cocos.develop.libcos.utils.getErrorByCode
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: Contract.Presenter = UserProfilePresenter(UserRoomRepoImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onAttach(this)
        initView()
        //setContentView(R.layout.activity_main)
    }

    private fun initView() {
        binding.fab.setOnClickListener {
            presenter.onSave(gatherUser())
        }
        binding.organizationEditText.addTextChangedListener {
            presenter.onChangeOrganization(it.toString())
        }

    }

    private fun gatherUser(): UserProfile {
        return UserProfile(
            binding.emailEditText.text.toString(),
            binding.firstNameEditText.text.toString(),
            binding.secondNameEditText.text.toString(),
            binding.ageEditText.text.toString().toInt(),
            binding.country.toString(),
            binding.organizationEditText.text.toString(),
            "",
            binding.resumeEditText.text.toString(),
            binding.phoneEditText.text.toString()
        )
    }

    override fun setState(state: Contract.ViewState) {
        binding.container.isVisible = false
        binding.progressBar.isVisible = false
        binding.doneImage.isVisible = false

        when (state) {
            Contract.ViewState.IDLE ->
                binding.container.isVisible = true
            Contract.ViewState.LOADING -> {
                binding.container.isVisible = true
                binding.progressBar.isVisible = true
            }
            Contract.ViewState.ERROR -> {
                binding.container.isVisible = true
                binding.progressBar.isVisible = true
                Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
            }
            Contract.ViewState.SUCCESS -> {
                binding.container.isVisible = true
                binding.doneImage.isVisible = true
            }
        }

    }

    override fun setUser(user: UserProfile) {
        binding.firstNameEditText.setText(user.firstName)
        binding.secondNameEditText.setText(user.secondName)
        binding.ageEditText.setText(user.age.toString())
        binding.resumeEditText.setText(user.resume)
        binding.organizationEditText.setText(user.organization)
    }

    override fun setOrganizationError(errorCode: Int) {
        binding.organizationEditText.error = getErrorByCode(errorCode)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
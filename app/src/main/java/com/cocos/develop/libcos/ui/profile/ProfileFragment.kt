package com.cocos.develop.libcos.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.libcos.R
import com.cocos.develop.libcos.databinding.FragmentProfileBinding
import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.utils.*
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ProfileFragment : MvpAppCompatFragment() , ProfileContract.View {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val presenter by moxyPresenter {UserProfilePresenter(requireActivity().app.userRepo)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.fab.setOnClickListener {
            presenter.onSave(gatherUser())
        }
        binding.organizationEditText.addTextChangedListener {
            presenter.onChangeOrganization(it.toString())
        }

        binding.emailEditText.addTextChangedListener {
            presenter.onChangeEmail(it.toString())
        }
    }

    private fun gatherUser(): UserProfile {
        return UserProfile(
            binding.emailEditText.parsToString(),
            binding.firstNameEditText.parsToString(),
            binding.secondNameEditText.parsToString(),
            binding.ageEditText.parsToInt(),
            binding.country.toString(),
            binding.organizationEditText.parsToString(),
            "",
            binding.resumeEditText.parsToString(),
            binding.phoneEditText.parsToString()
        )
    }

    override fun setState(state: AppState) {
        binding.profileBlank.isVisible = false
        binding.progressBar.isVisible = false
        binding.doneImage.isVisible = false

        when (state) {
            AppState.IDLE ->
                binding.profileBlank.isVisible = true
            AppState.LOADING -> {
                binding.profileBlank.isVisible = true
                binding.progressBar.isVisible = true
            }
            AppState.ERROR -> {
                binding.profileBlank.isVisible = true
                binding.progressBar.isVisible = true
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
            }
           AppState.SUCCESS -> {
                binding.profileBlank.isVisible = true
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

    override fun setOrganizationError(errorCode: ErrorCode) {
        binding.organizationEditText.error = getErrorByCode(errorCode, getString(R.string.organization))
    }

    override fun setEmailError(errorCode: ErrorCode) {
        binding.emailEditText.error = getErrorByCode(errorCode, getString(R.string.email))
    }

}
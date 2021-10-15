package com.cocos.develop.libcos.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.libcos.R
import com.cocos.develop.libcos.databinding.FragmentLoginBinding
import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.utils.*
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.*

class LoginFragment : MvpAppCompatFragment(), LoginContract.View {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val presenter by moxyPresenter { LoginPresenter(requireActivity().app.loginRepo)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        binding.username.addTextChangedListener {
            presenter.onChangeEmail(it.toString())
        }
        initButtons()
    }

    private fun initButtons() {
        binding.singInButton.setOnClickListener {
            presenter.onSingInClick(binding.username.parsToString(), binding.password.parsToString())
        }
        binding.register.setOnClickListener {
            presenter.onRegisterClick(gatherLogin())
        }
        binding.forgottenPassword.setOnClickListener {
            presenter.onForgetPassClick(gatherLogin())
        }
    }

    private fun gatherLogin(): LoginEntity {
        return LoginEntity(
            UUID.randomUUID().toString(),
            binding.username.parsToString(),
            binding.password.parsToString()
        )
    }
    override fun setState(state: AppState) {
        binding.loginBlank.isVisible = false
        binding.loading.isVisible = false

        when (state) {
            AppState.IDLE ->
                binding.loginBlank.isVisible = true
            AppState.LOADING -> {
                binding.loginBlank.isVisible = true
                binding.loading.isVisible = true
            }
            AppState.ERROR -> {
                binding.loginBlank.isVisible = true
                binding.loading.isVisible = true
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
            }
            AppState.SUCCESS -> {
                binding.loginBlank.isVisible = true
            }
        }

    }

    override fun setEmailError(errorCode: ErrorCode) {
        binding.username.error = getErrorByCode(errorCode, getString(R.string.email))
    }

    override fun openProfileScreen(login:LoginEntity) {
        navigateTo(R.id.profile_fragment)
    }

    override fun openPasswordScreen(login:LoginEntity) {
        navigateTo(R.id.password_fragment)
    }

    override fun checkLogin(result: String) {
        Snackbar.make(binding.root, result, Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateTo(target: Int) =
        Navigation.findNavController(requireActivity(), R.id.container).also {
            it.navigate(target)
        }
}
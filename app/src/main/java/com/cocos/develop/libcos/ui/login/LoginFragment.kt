package com.cocos.develop.libcos.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.libcos.R
import com.cocos.develop.libcos.databinding.FragmentLoginBinding
import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState
import com.cocos.develop.libcos.utils.getErrorByCode
import com.cocos.develop.libcos.utils.parsToString
import com.google.android.material.snackbar.Snackbar
import java.util.*

class LoginFragment : Fragment(R.layout.fragment_login), LoginContract.View {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private var presenter: LoginContract.Presenter = LoginPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        initView()
    }

    private fun initView() {

        binding.username.addTextChangedListener {
            presenter.onChangeEmail(it.toString())
        }
        binding.singInButton.setOnClickListener {
            presenter.onSingInClick(gatherLogin())
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
    override fun setState(state: ViewState) {
        binding.loginBlank.isVisible = false
        binding.loading.isVisible = false

        when (state) {
            ViewState.IDLE ->
                binding.loginBlank.isVisible = true
            ViewState.LOADING -> {
                binding.loginBlank.isVisible = true
                binding.loading.isVisible = true
            }
            ViewState.ERROR -> {
                binding.loginBlank.isVisible = true
                binding.loading.isVisible = true
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
            }
            ViewState.SUCCESS -> {
                binding.loginBlank.isVisible = true
                Snackbar.make(binding.root, getString(R.string.welcome), Snackbar.LENGTH_SHORT).show()
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

    private fun navigateTo(target: Int) =
        Navigation.findNavController(requireActivity(), R.id.container).also {
            it.navigate(target)
        }
}
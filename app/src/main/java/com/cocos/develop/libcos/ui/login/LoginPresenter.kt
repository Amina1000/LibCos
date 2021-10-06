package com.cocos.develop.libcos.ui.login

import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.domain.LoginRepo
import com.cocos.develop.libcos.domain.ResultState
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.AppState
import com.cocos.develop.libcos.utils.checkCurrentEmail

/**
 * homework com.cocos.develop.libcos.ui.login
 *
 * @author Amina
 * 29.09.2021
 */
class LoginPresenter(private val loginRepo: LoginRepo) : LoginContract.Presenter() {

     override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(AppState.IDLE)
    }

    override fun onChangeEmail(email: String) {
        if (!checkCurrentEmail(email)) {
            viewState.setEmailError(ErrorCode.TYPE_ERROR)
            viewState.setState(AppState.ERROR)
        }
    }

    override fun onSingInClick(username: String, password: String) {
        val result = loginRepo.checkLogin(username, password)
        viewState.setState(AppState.LOADING)

        if (result is ResultState.Success) {
            result.data?.let { loginEntity ->
                viewState.setState(AppState.SUCCESS)
                viewState.openProfileScreen(loginEntity) } ?: viewState.setState(AppState.ERROR)
        } else {
            viewState.setState(AppState.ERROR)
        }
        viewState.checkLogin(result.toString())
    }


    override fun onRegisterClick(login: LoginEntity) {
        viewState.openProfileScreen(login)
    }

    override fun onForgetPassClick(login: LoginEntity) {
        viewState.openPasswordScreen(login)
    }

}
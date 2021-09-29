package com.cocos.develop.libcos.ui.login

import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.ui.profile.ProfileContract
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState

/**
 * homework com.cocos.develop.libcos.ui.login
 *
 * @author Amina
 * 29.09.2021
 */
class LoginPresenter:LoginContract.Presenter {

    private var view: LoginContract.View?= null

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        view.setState(ViewState.IDLE)
    }

    override fun onDetach() {
        view = null
    }

    override fun onChangeEmail(email: String) {
        view?.setEmailError(ErrorCode.NOT_FOUND)
        view?.setState(ViewState.LOADING)
    }

    override fun onSingInClick(login: LoginEntity) {
        view?.setState(ViewState.SUCCESS)
    }

    override fun onRegisterClick(login: LoginEntity) {
        view?.openProfileScreen(login)
    }

    override fun onForgetPassClick(login: LoginEntity) {
        view?.openPasswordScreen(login)
    }

}
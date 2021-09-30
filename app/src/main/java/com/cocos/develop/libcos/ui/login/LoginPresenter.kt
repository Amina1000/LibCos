package com.cocos.develop.libcos.ui.login

import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.domain.LoginRepo
import com.cocos.develop.libcos.domain.ResultState
import com.cocos.develop.libcos.domain.UserRepo
import com.cocos.develop.libcos.ui.profile.ProfileContract
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState
import com.cocos.develop.libcos.utils.checkCurrentEmail

/**
 * homework com.cocos.develop.libcos.ui.login
 *
 * @author Amina
 * 29.09.2021
 */
class LoginPresenter(private val loginRepo: LoginRepo) : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private var loginEntity: LoginEntity? = null

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        view.setState(ViewState.IDLE)
    }

    override fun onDetach() {
        view = null
    }

    override fun onChangeEmail(email: String) {
        if (!checkCurrentEmail(email)) {
            view?.setEmailError(ErrorCode.TYPE_ERROR)
            view?.setState(ViewState.ERROR)
        }
    }

    override fun onSingInClick(username: String, password: String) {
        val result = loginRepo.checkLogin(username, password)
        view?.setState(ViewState.LOADING)

        if (result is ResultState.Success) {
            loginEntity = result.data
            if (loginEntity!= null){
                view?.setState(ViewState.SUCCESS)
                view?.openProfileScreen(loginEntity!!)
            }else
                view?.setState(ViewState.ERROR)
        } else {
            view?.setState(ViewState.ERROR)
        }
        view?.checkLogin(result.toString())
    }


    override fun onRegisterClick(login: LoginEntity) {
        view?.openProfileScreen(login)
    }

    override fun onForgetPassClick(login: LoginEntity) {
        view?.openPasswordScreen(login)
    }

}
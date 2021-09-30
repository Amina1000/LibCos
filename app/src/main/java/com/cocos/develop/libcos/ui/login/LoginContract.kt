package com.cocos.develop.libcos.ui.login

import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState

/**
 * homework com.cocos.develop.libcos.ui.login
 *
 * @author Amina
 * 29.09.2021
 */
class LoginContract {

    interface View{
        fun setState(state: ViewState)
        fun setEmailError(errorCode:ErrorCode)
        fun openProfileScreen(login: LoginEntity)
        fun openPasswordScreen(login: LoginEntity)
        fun checkLogin(result :String)
    }

    interface Presenter{
        fun onAttach(view:View)
        fun onDetach()
        fun onChangeEmail(email: String)
        fun onSingInClick(username:String, password:String)
        fun onRegisterClick(login: LoginEntity)
        fun onForgetPassClick(login: LoginEntity)
    }
}
package com.cocos.develop.libcos.ui.login

import com.cocos.develop.libcos.domain.LoginEntity
import com.cocos.develop.libcos.utils.AppState
import com.cocos.develop.libcos.utils.ErrorCode
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

/**
 * homework com.cocos.develop.libcos.ui.login
 *
 * @author Amina
 * 29.09.2021
 */
class LoginContract {

    interface View:MvpView{
        @AddToEndSingle
        fun setState(state: AppState)
        @AddToEndSingle
        fun setEmailError(errorCode:ErrorCode)
        @AddToEndSingle
        fun checkLogin(result :String)
        @Skip
        fun openProfileScreen(login: LoginEntity)
        @Skip
        fun openPasswordScreen(login: LoginEntity)

    }

    abstract class Presenter:MvpPresenter<View>(){
        abstract fun onChangeEmail(email: String)
        abstract fun onSingInClick(username:String, password:String)
        abstract fun onRegisterClick(login: LoginEntity)
        abstract fun onForgetPassClick(login: LoginEntity)
    }
}
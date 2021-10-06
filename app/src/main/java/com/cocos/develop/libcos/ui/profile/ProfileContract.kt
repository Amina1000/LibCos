package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.utils.AppState
import com.cocos.develop.libcos.utils.ErrorCode
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */
class ProfileContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View: MvpView {
        fun setState(state: AppState)
        fun setUser(user:UserProfile)
        fun setOrganizationError(errorCode:ErrorCode)
        fun setEmailError(errorCode:ErrorCode)

    }

    abstract class Presenter: MvpPresenter<View>(){
        abstract fun onChangeEmail(email: String)
        abstract fun onSave(user:UserProfile)
        abstract fun onChangeOrganization(organization: String)
    }

}
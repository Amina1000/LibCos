package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.domain.UserRepo
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.AppState
import com.cocos.develop.libcos.utils.checkCurrentEmail

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */

class UserProfilePresenter(private val userRepo: UserRepo): ProfileContract.Presenter() {

    private var view: ProfileContract.View?= null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(AppState.IDLE)
    }

    override fun onChangeEmail(email: String) {
        if (!checkCurrentEmail(email)){
            view?.setEmailError(ErrorCode.TYPE_ERROR)
            view?.setState(AppState.ERROR)
        }
    }

    override fun onSave(user: UserProfile) {
        view?.setState(AppState.LOADING)
        userRepo.saveUser(user)
        view?.setState(AppState.SUCCESS)
    }

    override fun onChangeOrganization(organization: String) {
        if (userRepo.getOrganization()!=organization){
            view?.setOrganizationError(ErrorCode.NOT_FOUND)
            view?.setState(AppState.ERROR)
        }
    }

}
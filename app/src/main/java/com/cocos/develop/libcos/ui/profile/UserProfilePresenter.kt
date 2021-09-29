package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.domain.UserRepo
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */

class UserProfilePresenter(private val userRepo: UserRepo): ProfileContract.Presenter {

    private var view: ProfileContract.View?= null
    private var userProfile: UserProfile? = null

    override fun onAttach(view:ProfileContract.View) {
        this.view = view
        view.setState(ViewState.IDLE)
        userProfile?.let {
            view.setUser(it)
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun onSave(user: UserProfile) {
       view?.setState(ViewState.SUCCESS)
    }

    override fun onChangeOrganization(organization: String) {
        view?.setOrganizationError(ErrorCode.SERVICE_UNAVAILABLE)
        view?.setState(ViewState.LOADING)
    }
}
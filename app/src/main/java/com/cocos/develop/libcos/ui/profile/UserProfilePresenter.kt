package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.domain.UserRepo

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */

class UserProfilePresenter(private val userRepo: UserRepo): Contract.Presenter {

    private var view: Contract.View?= null
    private var userProfile: UserProfile? = null

    override fun onAttach(view:Contract.View) {
        this.view = view
        view.setState(Contract.ViewState.IDLE)
        userProfile?.let {
            view.setUser(it)
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun onSave(user: UserProfile) {
       view?.setState(Contract.ViewState.SUCCESS)
    }

    override fun onChangeOrganization(organization: String) {
        view?.setOrganizationError(0)
        view?.setState(Contract.ViewState.LOADING)
    }
}
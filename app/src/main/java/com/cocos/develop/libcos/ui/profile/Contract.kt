package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */
class Contract {

    enum class ViewState{
        IDLE, LOADING, SUCCESS, ERROR
    }

    interface View{
        fun setState(state: ViewState)
        fun setUser(user:UserProfile)
        fun setOrganizationError(errorCode:Int)
    }

    interface Presenter{
        fun onAttach(view:View)
        fun onDetach()

        fun onSave(user:UserProfile)
        fun onChangeOrganization(organization: String)
    }

}
package com.cocos.develop.libcos.ui.profile

import com.cocos.develop.libcos.domain.UserProfile
import com.cocos.develop.libcos.utils.ErrorCode
import com.cocos.develop.libcos.utils.ViewState

/**
 * homework com.cocos.develop.libcos.ui
 *
 * @author Amina
 * 28.09.2021
 */
class ProfileContract {

    interface View{
        fun setState(state: ViewState)
        fun setUser(user:UserProfile)
        fun setOrganizationError(errorCode:ErrorCode)
    }

    interface Presenter{
        fun onAttach(view:View)
        fun onDetach()

        fun onSave(user:UserProfile)
        fun onChangeOrganization(organization: String)
    }

}
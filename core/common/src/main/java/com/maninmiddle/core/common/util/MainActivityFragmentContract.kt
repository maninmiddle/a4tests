package com.maninmiddle.core.common.util

import androidx.fragment.app.Fragment

interface MainActivityFragmentContract {
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true)
}
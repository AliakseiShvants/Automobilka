package by.shvants.avtomobilka.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    val navController: NavController
        get() = (requireActivity() as MainActivity).getNavController()
}
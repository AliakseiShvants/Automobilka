package by.shvants.avtomobilka.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

//    protected abstract val layoutId: Int
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }

    abstract fun hideProgress()

    abstract fun showProgress()
}
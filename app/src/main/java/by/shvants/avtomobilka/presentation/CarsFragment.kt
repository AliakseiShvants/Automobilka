package by.shvants.avtomobilka.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.fragmentViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.base.BaseFragment
import by.shvants.avtomobilka.databinding.FragmentCarsBinding
import by.shvants.avtomobilka.presentation.state.VisibilityState
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class CarsFragment : BaseFragment(R.layout.fragment_cars), KoinComponent {

    private val carsViewModel: CarsViewModel by viewModels()

    private val binding: FragmentCarsBinding by viewBinding()

    private val TAG = "CarsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(carsViewModel) {
            progressState.observe(viewLifecycleOwner) {
                when(it) {
                    VisibilityState.Show -> showProgress()
                    VisibilityState.Hide -> hideProgress()
                }
            }
        }
    }

    override fun hideProgress() {
        binding.progressBar.isGone = true
    }

    override fun showProgress() {
        binding.progressBar.isVisible = true
    }
}
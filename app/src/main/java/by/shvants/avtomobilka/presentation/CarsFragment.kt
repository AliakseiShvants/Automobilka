package by.shvants.avtomobilka.presentation

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import by.kirich1409.viewbindingdelegate.fragmentViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.base.BaseFragment
import by.shvants.avtomobilka.base.MainActivity
import by.shvants.avtomobilka.databinding.FragmentCarsBinding
import by.shvants.avtomobilka.presentation.adapter.CarsAdapter
import by.shvants.avtomobilka.presentation.state.VisibilityState
import by.shvants.avtomobilka.utils.json
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class CarsFragment : BaseFragment(R.layout.fragment_cars), KoinComponent {

    private val carsViewModel: CarsViewModel by viewModels()
    private val binding: FragmentCarsBinding by viewBinding()

    private lateinit var carsAdapter: CarsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            carsAdapter = CarsAdapter(
                listener = this@CarsFragment::onEventClickListener,
                onNextPage = {
                    carsViewModel.fetchCarsFromRemote()
                }
            )

            with(carsViewModel) {
                progressState.observe(viewLifecycleOwner) {
                    when(it) {
                        VisibilityState.Show -> progressBar.isVisible = true
                        VisibilityState.Hide -> progressBar.isGone = true
                    }
                }
                carsList.observe(viewLifecycleOwner) { list ->
                    when {
                        list == null -> {
                            tvError.isGone = true
                            rvCars.isGone = true
                        }
                        list.isNotEmpty() -> {
                            tvError.isGone = true

                            with(rvCars) {
                                isVisible = true
                                adapter = carsAdapter.apply {
                                    cars = list
                                }
                            }

                            rvCars.scrollToPosition(
                                (rvCars.adapter as? CarsAdapter)?.position ?: 0
                            )
                        }
                        else -> {
                            tvError.isVisible = true
                            rvCars.isGone = true
                        }
                    }
                }
            }
        }
    }

    private fun onEventClickListener(event: CarsEvent) {
        when(event) {
            is OnItemClick -> navController.navigate(
                R.id.carsFragment_to_carDetailsFragment,
                Bundle(1).apply {
                    putString("car", event.car.json())
                }
            )
        }
    }
}
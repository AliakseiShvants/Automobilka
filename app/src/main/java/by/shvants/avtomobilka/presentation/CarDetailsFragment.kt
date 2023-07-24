package by.shvants.avtomobilka.presentation

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import by.kirich1409.viewbindingdelegate.viewBinding
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.base.BaseFragment
import by.shvants.avtomobilka.databinding.FragmentCarDetailsBinding
import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.presentation.adapter.CarImageAdapter
import by.shvants.avtomobilka.presentation.adapter.PostsAdapter
import by.shvants.avtomobilka.presentation.state.VisibilityState
import by.shvants.avtomobilka.utils.dpToPx
import by.shvants.avtomobilka.utils.fromJson
import coil.load
import org.koin.core.component.KoinComponent

class CarDetailsFragment : BaseFragment(R.layout.fragment_car_details), KoinComponent {

    private val carDetailsViewModel: CarDetailsViewModel by viewModels()
    private val binding: FragmentCarDetailsBinding by viewBinding()
//    private val args by navArgs<CarDetailsFragmentArgs>()

    //    private lateinit var carsAdapter: CarsAdapter
    private var car: Car? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        car = arguments?.getString("car")?.fromJson<Car>()

        car?.let {
            carDetailsViewModel.fetchPostsFromRemote(it.id)
        }

        with(binding) {
            progressBar.isGone = car != null
            tvError.isGone = car != null

            with(rvCarImages) {
                adapter = CarImageAdapter(car?.images.orEmpty())
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                isNestedScrollingEnabled = false
                addItemDecoration(
                    object : RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(
                            outRect: Rect,
                            view: View,
                            parent: RecyclerView,
                            state: RecyclerView.State
                        ) {
                            outRect.left = view.context.dpToPx(4)
                            outRect.right = view.context.dpToPx(4)

                            if (parent.getChildAdapterPosition(view) == 0) {
                                outRect.left = view.context.dpToPx(8)
                            }

                            if (parent.getChildAdapterPosition(view) == parent.childCount - 1) {
                                outRect.right = view.context.dpToPx(8)
                            }
                        }
                    }
                )

                PagerSnapHelper().attachToRecyclerView(this)
            }

            with(carDetailsViewModel) {
                progressState.observe(viewLifecycleOwner) {
                    when (it) {
                        VisibilityState.Show -> progressBar.isVisible = true
                        VisibilityState.Hide -> progressBar.isGone = true
                    }
                }
                user.observe(viewLifecycleOwner) { user ->
                    when {
                        user != null -> {
                            ivAvatar.load(user.url)
                            tvName.text = user.username
                        }

                        else -> {

                        }
                    }
                }
                posts.observe(viewLifecycleOwner) { posts ->
                    when {
                        posts == null && progressState.value == VisibilityState.Hide -> {
                            rvPosts.isGone = true
                            tvError.isVisible = true
                        }

                        posts?.isEmpty() == true -> {
                            rvPosts.isGone = true
                            tvError.isVisible = true
                            tvError.text = "Постов нет"
                        }

                        else -> {
                            tvError.isGone = true
                            rvPosts.isVisible = posts?.isNotEmpty() == true

                            with(rvPosts) {
                                adapter = PostsAdapter {
                                    fetchPostsFromRemote(car?.id ?: 0)
                                }.apply {
                                    list = posts.orEmpty()
                                }
                            }
                        }
                    }
                }
            }
        }

        Log.d("Car", car.toString())

//                carsList.observe(viewLifecycleOwner) { list ->
//                    when {
//                        list == null -> {
//                            tvError.isGone = true
//                            rvCars.isGone = true
//                        }
//                        list.isNotEmpty() -> {
//                            tvError.isGone = true
//
//                            with(rvCars) {
//                                isVisible = true
//                                adapter = carsAdapter.apply {
//                                    cars = list
//                                }
//                            }
//
//                            rvCars.scrollToPosition(
//                                (rvCars.adapter as? CarsAdapter)?.position ?: 0
//                            )
//                        }
//                        else -> {
//                            tvError.isVisible = true
//                            rvCars.isGone = true
//                        }
//                    }
//                }
//            }
//        }
    }


}
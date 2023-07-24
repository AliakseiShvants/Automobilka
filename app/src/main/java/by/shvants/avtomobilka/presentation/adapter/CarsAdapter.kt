package by.shvants.avtomobilka.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.databinding.ListItemCarBinding
import by.shvants.avtomobilka.presentation.CarsEventListener
import by.shvants.avtomobilka.presentation.OnItemClick
import coil.load
import kotlin.properties.Delegates

class CarsAdapter(
    private val listener: CarsEventListener,
    private val onNextPage: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUtilAdapter {

    var position = 0
        private set

    var cars: List<Car> by Delegates.observable(emptyList()) { prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewHolder(
        ListItemCarBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_car, parent, false)
        )
    )

    override fun getItemCount() = cars.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            cars.size -> {
                this.position = position - 2
                onNextPage()
            }

            else -> (holder as CarViewHolder).bind(cars[position])
        }
    }

    inner class CarViewHolder(
        private val binding: ListItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            with(binding) {
                ivCar.load(car.image)
                tvCar.text = car.name

                root.setOnClickListener {
                    listener(OnItemClick(car))
                }
            }
        }
    }
}
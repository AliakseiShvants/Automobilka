package by.shvants.avtomobilka.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.databinding.ListItemCarBinding
import by.shvants.avtomobilka.databinding.ListItemCarDetailsImgBinding
import by.shvants.avtomobilka.domain.Image
import by.shvants.avtomobilka.presentation.CarsEventListener
import by.shvants.avtomobilka.presentation.OnItemClick
import coil.load
import kotlin.properties.Delegates

class CarImageAdapter(
    private val images: List<Image>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUtilAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarImageHolder(
        ListItemCarDetailsImgBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_car_details_img, parent, false)
        )
    )

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CarImageHolder).bind(images[position])
    }

    inner class CarImageHolder(
        private val binding: ListItemCarDetailsImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            with(binding) {
                ivCarDetailImg.load(image.url)
            }
        }
    }
}
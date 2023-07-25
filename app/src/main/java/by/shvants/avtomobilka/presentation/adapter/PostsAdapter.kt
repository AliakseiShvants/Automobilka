package by.shvants.avtomobilka.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.shvants.avtomobilka.R
import by.shvants.avtomobilka.databinding.ListItemPostBinding
import by.shvants.avtomobilka.domain.Post
import coil.load
import kotlin.properties.Delegates

class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUtilAdapter {

    var list: List<Post> by Delegates.observable(emptyList()) { prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        ListItemPostBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(list[position])
    }

    inner class PostViewHolder(
        private val binding: ListItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(binding) {
                ivPostAvatar.load(post.author?.url)

                ivPostCar.isVisible = post.img.isNotEmpty()
                ivPostCar.load(post.img)

                tvPostUsername.text = post.author?.username
                tvDate.text = post.created_at
                tvPostTitle.text = post.text
            }
        }
    }
}
package com.merkost.github_api.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.merkost.github_api.R
import com.merkost.github_api.databinding.ItemUserBinding
import com.merkost.github_api.model.entity.users.UsersSearchResult
import com.merkost.github_api.ui.Arguments
import com.merkost.github_api.ui.MainFragmentDirections

class UsersSearchResultAdapter : RecyclerView.Adapter<UsersSearchResultAdapter.SearchResultViewHolder>() {

    private var resultUsers: List<UsersSearchResult> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        holder.bind(resultUsers[position])
    }

    override fun getItemCount(): Int {
        return resultUsers.size
    }

    fun updateResults(resultUsers: List<UsersSearchResult>) {
        this.resultUsers = resultUsers
        notifyDataSetChanged()
    }

    class SearchResultViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(usersSearchResult: UsersSearchResult) {
            binding.tvLogin.text = usersSearchResult.login
            binding.ivAvatar.load(usersSearchResult.avatar_url)
            binding.root.setOnClickListener {
                Toast.makeText(itemView.context, usersSearchResult.login, Toast.LENGTH_SHORT).show()
                val action = MainFragmentDirections.actionMainFragmentToUserDetailsFragment(usersSearchResult)
                findNavController(it).navigate(action)
                //it.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }
}

package com.merkost.github_api.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.merkost.github_api.databinding.ItemRepositoryBinding
import com.merkost.github_api.model.entity.repositories.RepositoriesSearchResult

class ReposSearchResultAdapter : RecyclerView.Adapter<ReposSearchResultAdapter.SearchResultViewHolder>() {

    private var resultRepositories: List<RepositoriesSearchResult> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        val binding: ItemRepositoryBinding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        holder.bind(resultRepositories[position])
    }

    override fun getItemCount(): Int {
        return resultRepositories.size
    }

    fun updateResults(resultRepositories: List<RepositoriesSearchResult>) {
        this.resultRepositories = resultRepositories
        notifyDataSetChanged()
    }

    class SearchResultViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoriesSearchResult: RepositoriesSearchResult) {
            binding.repositoryName.text = repositoriesSearchResult.fullName
            binding.repositoryName.setOnClickListener {
                Toast.makeText(itemView.context, repositoriesSearchResult.fullName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

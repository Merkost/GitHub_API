package com.merkost.github_api.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.merkost.github_api.R
import com.merkost.github_api.databinding.FragmentMainBinding
import com.merkost.github_api.model.entity.repositories.RepositoriesSearchResponse
import com.merkost.github_api.model.entity.users.UsersSearchResponse
import com.merkost.github_api.ui.adapters.ReposSearchResultAdapter
import com.merkost.github_api.ui.adapters.UsersSearchResultAdapter
import com.merkost.github_api.viewmodels.MainViewModel
import com.merkost.github_api.viewmodels.viewstates.BaseViewState
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.get
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope
import java.util.*

class MainFragment : Fragment(), AndroidScopeComponent {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override val scope: Scope by fragmentScope()
    private val mainViewModel: MainViewModel = get()

    private val adapter = UsersSearchResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeOnViewModel()
        setButtons()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun setButtons() {
        binding.buttonSearch.setOnClickListener {
            if (binding.searchEditText.text.isNotEmpty()) {
                mainViewModel.searchFor(binding.searchEditText.text.toString())
            } else Toast.makeText(this.context, "Вы ничего не ввели!", Toast.LENGTH_SHORT).show()
        }
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    private fun subscribeOnViewModel() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {
                when (it) {
                    is BaseViewState.Success<*> -> {
                        setViews(isLoading = false)

                        if (it.data!= null) {

                            onSuccess(it.data as UsersSearchResponse)
                        }
                    }
                    is BaseViewState.Loading -> {
                        setViews(isLoading = true)
                    }
                    is BaseViewState.Error -> {
                        setViews(isLoading = false)

                    }
                }
            }
        }
    }

    private fun setViews(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonSearch.isClickable = false
            binding.totalCountTextView.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.buttonSearch.isClickable = true
        }

    }

    private fun onSuccess(usersSearchResponse: UsersSearchResponse) {
        usersSearchResponse.items.let {
            adapter.updateResults(it)
            binding.totalCountTextView.text = it.size.toString()
            with(binding.totalCountTextView) {
                visibility = View.VISIBLE
                text = String.format(
                    Locale.getDefault(),
                    getString(R.string.results_count),
                    it.size
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
package com.merkost.github_api.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.merkost.github_api.R
import com.merkost.github_api.databinding.FragmentUserDetailsBinding
import com.merkost.github_api.model.entity.repositories.RepositoriesSearchResponse
import com.merkost.github_api.model.entity.user_repos.UserReposItem
import com.merkost.github_api.model.entity.user_repos.UserReposResponse
import com.merkost.github_api.model.entity.users.UsersSearchResponse
import com.merkost.github_api.model.entity.users.UsersSearchResult
import com.merkost.github_api.ui.adapters.ReposSearchResultAdapter
import com.merkost.github_api.ui.adapters.UsersSearchResultAdapter
import com.merkost.github_api.viewmodels.MainViewModel
import com.merkost.github_api.viewmodels.UserDetailsViewModel
import com.merkost.github_api.viewmodels.viewstates.BaseViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.util.*

class UserDetailsFragment : Fragment(), AndroidScopeComponent {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val adapter = ReposSearchResultAdapter()

    override val scope: Scope by fragmentScope()
    private val userDetailsViewModel: UserDetailsViewModel = get()

    private var _binding: FragmentUserDetailsBinding? = null

    lateinit var user: UsersSearchResult

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = args.user
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userImg.load(user.avatar_url)
        binding.tvLogin.text = user.login
        binding.recyclerView.adapter = adapter

        subscribeToViewModel()
        userDetailsViewModel.getUserRepos(user.login)
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {

            userDetailsViewModel.uiState.collect {
                when (it) {
                    is BaseViewState.Success<*> -> {
                        setViews(isLoading = false)

                        if (it.data!= null) {
                            onSuccess(it.data as List<UserReposItem>)
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
            binding.totalCountTextView.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun onSuccess(userRepos: List<UserReposItem>) {
        userRepos.let {
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
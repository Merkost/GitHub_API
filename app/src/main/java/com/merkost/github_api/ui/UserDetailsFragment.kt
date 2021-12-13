package com.merkost.github_api.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.merkost.github_api.databinding.FragmentUserDetailsBinding
import com.merkost.github_api.model.entity.users.UsersSearchResult
import com.merkost.github_api.ui.adapters.ReposSearchResultAdapter
import com.merkost.github_api.ui.adapters.UsersSearchResultAdapter

class UserDetailsFragment : Fragment() {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val adapter = ReposSearchResultAdapter()

    private var _binding: FragmentUserDetailsBinding? = null

    lateinit var user: UsersSearchResult

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        setViews()
    }

    private fun setViews() {
        binding.userImg.load(user.avatar_url)
        binding.tvLogin.text = user.login
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
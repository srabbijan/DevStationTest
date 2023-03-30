package com.example.devstationtest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.devstationtest.R
import com.example.devstationtest.adapter.UserListAdapter
import com.example.devstationtest.api.Status
import com.example.devstationtest.databinding.FragmentUserListBinding
import com.example.devstationtest.interfaces.UserListItemClick
import com.example.devstationtest.model.Results
import com.example.devstationtest.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() , UserListItemClick{
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UserListAdapter
    var userList: MutableList<Results> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onCreate: 1" )
        adapter = UserListAdapter(userList,this)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getUsers()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("TAG", "onCreate: 2" )
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "onCreate: 3" )
        binding.recycler.adapter = adapter
        subscribeObserver()
        binding.btnReload.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.getUsers()
            }
            subscribeObserver()
        }
    }

    private fun subscribeObserver() {
        viewModel.getUsers.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    Log.e("Response", "loading")
                }

                Status.SUCCESS -> {
                    Log.e("Response", "SUCCESS")
                    binding.progressCircular.visibility = View.GONE
                    val res = it.data
                    if (!res?.results.isNullOrEmpty()) {
                        binding.recycler.adapter = UserListAdapter(res?.results!!,this)
                    }
                }

                Status.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    Log.e("Response", "ERROR${it.message}")
                }
            }
        }
    }

    override fun itemClick(data: Results) {

        val bundle = bundleOf(
            "name" to "${data.name?.title} ${data.name?.first} ${data.name?.last}",
            "image" to data.picture?.large,
            "city" to data.location?.city,
            "state" to data.location?.state,
            "country" to data.location?.country,
            "phone" to data.phone,
            "email" to data.email
        )
        findNavController().navigate(R.id.action_userListFragment_to_userDetailsFragment,bundle)

    }

}
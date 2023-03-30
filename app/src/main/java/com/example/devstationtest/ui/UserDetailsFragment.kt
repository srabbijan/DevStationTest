package com.example.devstationtest.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.devstationtest.R
import com.example.devstationtest.databinding.FragmentUserDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(arguments?.getString("image")).placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder).into(binding.profileImage)
        binding.userName.text = arguments?.getString("name")
        binding.emailTv.text = arguments?.getString("email")
        binding.phoneTv.text = arguments?.getString("phone")
        binding.City.text = arguments?.getString("city")
        binding.State.text = arguments?.getString("state")
        binding.Country.text = arguments?.getString("country")


        binding.clickEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val data: Uri = Uri.parse(
                "mailto:${arguments?.getString("email")}?subject=&body="
            )
            intent.data = data
            startActivity(intent)
        }
        binding.toolbarFixedTitle.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}
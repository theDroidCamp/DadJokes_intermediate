package com.noelon.dadjokes_intermediate.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.noelon.dadjokes_intermediate.R
import com.noelon.dadjokes_intermediate.databinding.FragmentSignUpBinding
import com.noelon.dadjokes_intermediate.models.ResultWrapper
import com.noelon.dadjokes_intermediate.ui.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.viewModel = signUpViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpViewModel.errorString.observe(viewLifecycleOwner, {
           Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
            //Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        signUpViewModel.signUpResponse.observe(viewLifecycleOwner, {
            if (it is ResultWrapper.Success) {
                //ToDo: Go to login or main view
                Toast.makeText(context, "Account created", Toast.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}
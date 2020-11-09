package com.noelon.dadjokes_intermediate.ui.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.noelon.dadjokes_intermediate.R
import com.noelon.dadjokes_intermediate.databinding.FragmentSignInBinding
import com.noelon.dadjokes_intermediate.models.ResultWrapper
import com.noelon.dadjokes_intermediate.ui.signup.SignUpFragment

class SignInFragment : Fragment() {
    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(
            inflater, container, false
        )
        binding.signInViewModel = signInViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        signInViewModel.errorString.observe(viewLifecycleOwner, {
            Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
        })

        signInViewModel.signInResponse.observe(viewLifecycleOwner, {
            if (it is ResultWrapper.Success) {
//                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
//                findNavController().navigate(action)
                findNavController().navigate(R.id.action_signInFragment_to_jokesFragment)
            } else {
                Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })
        binding.navToSignUp.setOnClickListener {
            initUi()
        }
    }
    private fun initUi(){
        /*val registerFragment = SignUpFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, registerFragment)
            .addToBackStack(null)
            .commit()*/
//        val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

}
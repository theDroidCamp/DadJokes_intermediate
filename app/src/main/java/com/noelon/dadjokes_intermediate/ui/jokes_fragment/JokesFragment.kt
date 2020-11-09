package com.noelon.dadjokes_intermediate.ui.jokes_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noelon.dadjokes_intermediate.databinding.FragmentJokesBinding
import com.noelon.dadjokes_intermediate.ui.jokes_fragment.each_joke_item.EachJokeItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import timber.log.Timber

class JokesFragment : Fragment() {

    private val viewModel: JokesViewModel by lazy { ViewModelProvider(this, JokesViewModelFactory(requireContext()))[JokesViewModel::class.java] }

    private lateinit var binding: FragmentJokesBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokesBinding.inflate(inflater)

        viewModel.mapOfJokes.observe(viewLifecycleOwner, {
            val likedJokes = viewModel.likedJokes.value
            it?.values?.forEach { eachJoke ->
                Timber.d("EachJoke in observe function is $eachJoke")
                groupAdapter.add(EachJokeItem(eachJoke, likedJokes?.contains(eachJoke) ?: false))
            }
            Timber.d("Size of groupAdapter is ${groupAdapter.itemCount}")
        })

        binding.jokesRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val lastVisibleItem =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    Timber.d("lastVisibleItem is $lastVisibleItem and groupAdapter.itemCount is ${groupAdapter.itemCount}")

                    if (lastVisibleItem >= groupAdapter.itemCount - 8) viewModel.getTenJokes()
                }
            })
        }

        return binding.root
    }
}
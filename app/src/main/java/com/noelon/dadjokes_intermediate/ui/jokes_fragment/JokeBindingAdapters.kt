package com.noelon.dadjokes_intermediate.ui.jokes_fragment

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noelon.dadjokes_intermediate.ui.jokes_fragment.each_joke_item.EachJokeItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import timber.log.Timber

//@BindingAdapter("addViewModel", "addCustomLifecycleOwner")
//fun RecyclerView.addJokesToRecyclerView(
//    viewModel: JokesViewModel,
//    viewLifecycleOwner: LifecycleOwner
//) {
//    val likedJokes = viewModel.likedJokes
//    val mapOfJokes = viewModel.mapOfJokes
//
//    layoutManager = LinearLayoutManager(context)
//
//    val groupAdapter = GroupAdapter<GroupieViewHolder>()
//    adapter = groupAdapter
//
//    mapOfJokes.observe(viewLifecycleOwner, {
//
//        val likedJokesIds = arrayListOf<Int?>()
//        likedJokes.value?.forEach { likedJoke ->
//            likedJokesIds.add(likedJoke?.id)
//        }
//
//        it?.values?.forEach { joke ->
//            groupAdapter.add(EachJokeItem(joke, joke.id in likedJokesIds))
//        }
//
//    })
//
//    addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            val lastVisibleItem =
//                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
//            Timber.d("lastVisibleItem is $lastVisibleItem and groupAdapter.itemCount is ${groupAdapter.itemCount}")
//
//            if (lastVisibleItem >= groupAdapter.itemCount - 8) viewModel.getTenJokes()
//        }
//    })
//}
package com.noelon.dadjokes_intermediate.ui.jokes_fragment.each_joke_item

import com.noelon.dadjokes_intermediate.R
import com.noelon.dadjokes_intermediate.database.JokeDataBase
import com.noelon.dadjokes_intermediate.databinding.EachJokeItemLayoutBinding
import com.noelon.dadjokes_intermediate.network.jokes.Joke
import com.xwray.groupie.databinding.BindableItem
import timber.log.Timber

class EachJokeItem(private val joke: Joke, private var isLiked: Boolean = false) :
    BindableItem<EachJokeItemLayoutBinding>() {
    override fun bind(binding: EachJokeItemLayoutBinding, position: Int) {
        binding.joke = joke

        binding.likeBtn.apply {
            setImageResource(if (isLiked) R.drawable.ic_star_black else R.drawable.ic_star_outline_black)

            setOnClickListener {
                val jokeDataBaseDao = JokeDataBase.getInstance(context)?.jokeDao
                try {
                    isLiked = when (isLiked) {
                        true -> {
                            removeLikedJokeFromRoom(jokeDataBaseDao, joke)
                            false
                        }
                        false -> {
                            addLikedJokeToRoom(jokeDataBaseDao, joke)
                            true
                        }

                    }
                } catch (e: Exception) {
                    Timber.e(e)
                } finally {
                    setImageResource(if (isLiked) R.drawable.ic_star_black else R.drawable.ic_star_outline_black)
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.each_joke_item_layout
}
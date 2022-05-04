package com.example.encoratask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.encoratask.R
import com.example.encoratask.viewmodel.CharacterViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.imageview.ShapeableImageView

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set title to an empty string, it's going to be updated with the character's name
        // once the character loads
        val toolbar = activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbar?.title = null

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                id = it.getInt(ARG_ITEM_ID)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        val toolbar = activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        val imageView = rootView.findViewById<ShapeableImageView>(R.id.item_image)
        val speciesView = rootView.findViewById<TextView>(R.id.item_species)
        val typeView = rootView.findViewById<TextView>(R.id.item_type)
        val genderView = rootView.findViewById<TextView>(R.id.item_gender)
        val statusView = rootView.findViewById<TextView>(R.id.item_status)

        // Show the dummy content as text in a TextView.
        id?.let {
            viewModel.refreshCharacter(it)
            viewModel.characterLiveData.observe(this) { response ->
                if (response == null) {
                    Toast.makeText(context, "Error on network call", Toast.LENGTH_SHORT)
                        .show()
                    return@observe
                }

                toolbar?.title = response.name
                speciesView.text = response.species
                typeView.text = response.type
                genderView.text = response.gender
                statusView.text = response.status

                Glide.with(this@ItemDetailFragment)
                    .load(response.image)
                    .into(imageView)
            }
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
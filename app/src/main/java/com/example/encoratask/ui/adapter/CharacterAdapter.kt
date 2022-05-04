package com.example.encoratask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.encoratask.data.entity.Character
import com.example.encoratask.databinding.ItemListContentBinding

class CharacterAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(view: View?, characterId: Int)
        fun onLastCharacterVisible()
    }

    private val items = ArrayList<Character>()

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemListContentBinding =
            ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
        if (position >= items.size - 1) listener.onLastCharacterVisible()
    }
}

class CharacterViewHolder(
    private val itemBinding: ItemListContentBinding,
    private val listener: CharacterAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {
    private lateinit var character: Character

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Character) {
        this.character = item
        itemBinding.itemName.text = item.name

        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.itemImage)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(v, character.id)
    }
}


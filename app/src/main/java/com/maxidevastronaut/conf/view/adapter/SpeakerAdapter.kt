package com.maxidevastronaut.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maxidevastronaut.conf.R
import com.maxidevastronaut.conf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){
    var listSpeaker = ArrayList<Speaker>()
    // 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker, parent, false))
    // 3
    override fun getItemCount() = listSpeaker.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val speaker = listSpeaker[position]
            holder.tvSpeakerName.text = speaker.name
            holder.tvSpeakerWork.text = speaker.workplace
            //usamos libreria Glide para obtener imagen de otras redes
            Glide.with(holder.itemView.context)
                .load(speaker.image)//cargamos
                .apply(RequestOptions.circleCropTransform())//aplanamos y tranformamos
                .into(holder.ivSpeakerImage)//donde? dentro de nuestro holder

            holder.itemView.setOnClickListener {
                speakerListener.onSpeakerClicked(speaker, position)
            }
    }
    // 5
    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }
    // 2
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerWork)
    }
}
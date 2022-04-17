package com.example.musicapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.MusicList
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.model.SongResponse
import com.squareup.picasso.Picasso

class MusicAdapter(private val dataSet: MusicList,
                   private val openDetails: (MusicResponse) -> Unit)
    : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(){

    class MusicViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view){
        private val albumArt: ImageView =
            view.findViewById(R.id.iv_album_art)
        private val songTitle : TextView =
            view.findViewById(R.id.tv_song_name)
        private val artistName: TextView =
            view.findViewById(R.id.tv_artist)
        private val songPrice: TextView =
            view.findViewById(R.id.tv_price)


        fun onBind(dataItem: MusicResponse,
                   openDetails: (MusicResponse) -> Unit){
            songTitle.text = dataItem.trackName
            artistName.text = dataItem.artistName
            songPrice.text = "$"+dataItem.trackPrice.toString()
            Picasso.get().load(dataItem.artworkUrl60).into(albumArt)

            view.setOnClickListener {
                openDetails(dataItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.music_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
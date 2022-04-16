package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MusicList: ArrayList<MusicResponse>()

@Parcelize
data class MusicResponse(
    val trackName: String,
    val artworkUrl60: String,
    val artistName: String,
    val trackPrice: String,
    val previewUrl: String
): Parcelable

data class SongResponse(
    val resultCount: Int,
    val results:MusicList
)

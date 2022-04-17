package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MusicList: ArrayList<ResponseItem>()

@Parcelize
data class ResponseItem(
    val trackName: String,
    val artworkUrl60: String,
    val artistName: String,
    val trackPrice: String,
    val previewUrl: String
): Parcelable

data class JsonResponse(
    val resultCount: Int,
    val results:MusicList
)

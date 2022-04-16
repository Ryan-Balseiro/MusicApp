package com.example.musicapp.model.remote

//import com.example.movietest.common.END_POINT
import com.example.musicapp.common.BASE_URL
import com.example.musicapp.common.END_POINT_CLASSIC
import com.example.musicapp.common.END_POINT_POP
import com.example.musicapp.common.END_POINT_ROCK
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.model.MusicList
import com.example.musicapp.model.SongResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicService {
    @GET(END_POINT_ROCK)
    fun getMusic(): Call<SongResponse>// Call<List<MusicResponse>

    @GET(END_POINT_CLASSIC)
    fun getMusicClassic(): Call<SongResponse>// Call<List<MusicResponse>

    @GET(END_POINT_POP)
    fun getMusicPop(): Call<SongResponse>// Call<List<MusicResponse>

    companion object{
        fun initRetrofit(): MusicService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicService::class.java)
        }
    }
}
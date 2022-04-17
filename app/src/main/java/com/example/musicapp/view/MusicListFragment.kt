package com.example.musicapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicapp.R
import com.example.musicapp.model.SongResponse
import com.example.musicapp.model.remote.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

private const val TAG = "MusicListFragment"
class MusicListFragment: Fragment() {

    private lateinit var musicList: RecyclerView
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(
            R.layout.music_list_fragment_layout,
            container,
            false
        )
        initViews(view)
        getMusic()

        return view
    }

    private fun initViews(view: View) {
        musicList = view.findViewById(R.id.music_list)
        musicList.layoutManager = LinearLayoutManager(context)

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            Toast.makeText(activity, "Refreshed!", Toast.LENGTH_SHORT).show()
            if (swipeRefreshLayout.isRefreshing){
                TimeUnit.SECONDS.sleep(1L)
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun getMusic() {
        MusicService.initRetrofit().getMusic()
            .enqueue(
                object : Callback<SongResponse> {
                    override fun onResponse(
                        call: Call<SongResponse>,
                        response: Response<SongResponse>
                    ) {
                        Log.d(TAG, "onResponse: $response")
                        if (response.isSuccessful) {
                            updateAdapter(response.body())
                        } else
                            showError(response.message())
                    }

                    override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        showError(t.message ?: "Unknown error")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: SongResponse?) {
        body?.let {
            it.results.let { MusicList ->
                adapter = MusicAdapter(MusicList) { MusicResponse ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(MusicResponse.previewUrl))
                    intent.setDataAndType(Uri.parse(MusicResponse.previewUrl), "video/mp4")
                    activity?.startActivity(intent)
                }
                musicList.adapter = adapter
            }
        } ?: showError("No response from server")
    }
}
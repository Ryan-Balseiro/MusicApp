package com.example.musicapp

import android.R.attr.data
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicapp.view.MusicListCFragment
import com.example.musicapp.view.MusicListFragment
import com.example.musicapp.view.MusicListPFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MusicListFragment())
            .commit()

        val tabController: TabLayout = findViewById(R.id.tl_footer)
        tabController.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT).show()
                if (position == 1){
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MusicListCFragment())
                        .commit()
                }
                else if (position == 2){
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MusicListPFragment())
                        .commit()
                }
                else{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MusicListFragment())
                        .commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }//end onCreate

}
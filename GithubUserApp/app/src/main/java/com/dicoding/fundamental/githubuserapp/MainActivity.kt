package com.dicoding.fundamental.githubuserapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamental.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var title: String = "Github User"
    private val list = ArrayList<GithubUser>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = title

        binding.rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<GithubUser>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany= resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)

            val listUser = ArrayList<GithubUser>()
            for (i in dataName.indices) {
                val user = GithubUser(
                    dataName[i],
                    dataUsername[i],
                    dataAvatar.getResourceId(i, -1),
                    dataFollowers[i],
                    dataFollowing[i],
                    dataRepository[i],
                    dataCompany[i],
                    dataLocation[i]
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.adapter = listUserAdapter
    }
}
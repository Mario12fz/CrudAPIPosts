package com.example.crudapipost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudapipost.databinding.ActivityMainBinding
import com.example.crudapipost.models.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostsAdapter
    private var listPosts = mutableListOf<Posts>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPosts()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = PostsAdapter(listPosts)
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        binding.rvPost.adapter = adapter
    }

    private fun getPosts() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://jsonplaceholder.typicode.com/")
        val endpoint = retrofitClient.create(APIService::class.java)
        val callback = endpoint.getPosts()
        callback.enqueue(object: Callback<List<Posts>>{
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                listPosts.clear()
                response.body()?.forEach(){
                    val posts = Posts(it.userId,it.userId, it.title,it.body)
                    listPosts.add(posts)
                }
                adapter.notifyDataSetChanged()
            }
        })

    }

}
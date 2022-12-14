package com.sabdio.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabdio.myposts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }
    
    fun fetchPosts(){
        val apiclient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiclient.getPosts()
        
        
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response
            : Response<List<Post>>) {
                if (response.isSuccessful){
                    var post = response.body()!!
//                    if (post=null){
//                        displayposts(post)
//                    }
                    Toast.makeText(baseContext, "fetched ${post.size} posts", Toast.LENGTH_SHORT).show()

                    var adapter = PostRvAdapter(post)
                    Log.d("TAG",post.toString())
                    binding.rvPosts.adapter = adapter
                    binding.rvPosts.layoutManager= LinearLayoutManager(baseContext)
            }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()

            }

        })
    }
//    fun displayposts(postsList: List<Post>){
//        binding.rvPosts
//    }
}


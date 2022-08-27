package com.sabdio.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabdio.myposts.databinding.ActivityCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentBinding
    var postId =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchpPostById()
        fetchComments()
//        setupToolbar()
    }
    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }
    fun fetchpPostById(){
        var apiClient =ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)


        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body

                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_SHORT).show()

            }
        })

}
//    fun setupToolbar(){
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//    }
    fun fetchComments(){
        var apiClient =ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)

        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    var Commentss  = response.body()
                    binding.rvComments.adapter= Commentss?.let { CommentsRVAdapter(it) }
                    binding.rvComments.layoutManager=LinearLayoutManager(baseContext)

//
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_SHORT).show()
            }

        })


    }
//    fun displayComments(commentList:List<Comment>){
//        val commentsAdapter=CommentsRVAdapter(commentList)
//        binding.rvComments.layoutManager=LinearLayoutManager(this)
//        binding.rvComments.adapter=commentsAdapter
//    }


}
//?: emptyList()
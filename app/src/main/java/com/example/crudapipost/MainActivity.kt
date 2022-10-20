package com.example.crudapipost

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudapipost.databinding.ActivityMainBinding
import com.example.crudapipost.databinding.CreatePostsDialogBinding
import com.example.crudapipost.models.Posts

class MainActivity : AppCompatActivity() {


    private lateinit var  binding: ActivityMainBinding
    private lateinit var bindingDialog : CreatePostsDialogBinding
    private lateinit var adapter: PostsAdapter
    private lateinit var vm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[HomeViewModel::class.java]
        initRecyclerView()
        vm.getAllPosts()

        vm.postModelListData?.observe(this, Observer {
            if (it != null){
                binding.rvPost.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<Posts>)
            }else{
                Toast.makeText(this, "Something went worng",Toast.LENGTH_SHORT).show()
            }
        })

        binding.fabAddPost.setOnClickListener{
            showCreatePostsDialog()
        }
    }
    private fun initRecyclerView(){
        adapter = PostsAdapter(this)
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        binding.rvPost.adapter = adapter
    }

    fun onItemDeleted(posts: Posts, position: Int) {
        TODO("Not yet implemented")
    }


    fun showCreatePostsDialog() {
        val dialog = Dialog(this)
        bindingDialog = CreatePostsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDialog.root)

        var title = ""
        var body = ""
        bindingDialog.btnSavePosts.setOnClickListener {
            title = bindingDialog.edtPostsTitle.text.toString().trim()
            body = bindingDialog.edtPostsBody.text.toString().trim()

            if(title.isNotEmpty() && body.isNotEmpty()){
                val posts =  Posts()
                posts.userId = 0
                posts.title = title
                posts.body = body
                vm.createPostsLiveData?.observe(this, Observer {
                    if(it != null){
                        adapter.addData(posts)
                        binding.rvPost.smoothScrollToPosition(0)
                    }else{
                        Toast.makeText(this, "Cannot create posts at the moment", Toast.LENGTH_SHORT).show()
                    }
                    dialog.cancel()
                })
            }else{
                Toast.makeText(this, "Please fill data carefully!", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

}
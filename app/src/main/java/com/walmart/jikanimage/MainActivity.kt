package com.walmart.jikanimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.walmart.jikanimage.databinding.ActivityMainBinding
import com.walmart.jikanimage.repository.entities.Response
import com.walmart.jikanimage.view.JekinImageListAdapter
import com.walmart.jikanimage.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * First screen activity
 */
class MainActivity : AppCompatActivity() {
    private val adapter = JekinImageListAdapter()           //Adapter
    private val viewModel by viewModel<ImagesViewModel>()   //ViewModel
    private var query = ""                                  //Search keyword

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflate views using view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageList.adapter = adapter

        //Add observer to viewmodel livedata
        viewModel.images.observe(this) {
            when {
                it.success() -> {
                    adapter.submitList(it.data)
                }
                else -> {
                    adapter.submitList(ArrayList<Response.ImageData>())

                    //Log error message
                    Log.w("ImagesViewModel", it.error.toString())
                }
            }
        }

        //Initially load default images
        viewModel.loadImages("")
    }

    /**
     * Create menu and define menu search action
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(query != newText) {
                    query = newText ?: ""
                    viewModel.loadImages(query)
                }

                return true
            }
        })
        return true
    }
}
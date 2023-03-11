package com.example.appmvvmkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmvvmkotlin.R
import com.example.appmvvmkotlin.databinding.ActivityMainBinding
import com.example.appmvvmkotlin.model.LoadingState
import com.example.appmvvmkotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = OrderAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initializeUI()
        initializeObservers()

        viewModel.onViewReady()
    }
    private fun initializeUI() {
        binding.rvOrder.adapter=adapter
        binding.rvOrder.layoutManager=LinearLayoutManager(this)

        binding.edtSearch.doOnTextChanged { text, start, before, count ->
            viewModel.onSearchQuery(text.toString())
        }
    }
    private fun initializeObservers(){
        viewModel.loadingStateLiveData.observe(this){
            onLoadingStateChanged(it)
        }
        viewModel.ordersLiveData.observe(this){
            adapter.updateOrders(it)
        }
    }
    private fun onLoadingStateChanged(state:LoadingState){
        binding.edtSearch.visibility = if (state == LoadingState.LOADED) View.VISIBLE else View.GONE
        binding.rvOrder.visibility = if (state == LoadingState.LOADED) View.VISIBLE else View.GONE
        binding.txtError.visibility = if (state == LoadingState.ERROR) View.VISIBLE else View.GONE
        binding.pbLoading.visibility = if (state == LoadingState.LOADING) View.VISIBLE else View.GONE
    }
}
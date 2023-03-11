package com.example.shifttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shifttest.adapter.BinAdapter
import com.example.shifttest.data.BinInfoModel
import com.example.shifttest.data.BinRepository
import com.example.shifttest.databinding.ActivityMainBinding
import com.example.shifttest.db.BinItem
import com.example.shifttest.domain.BinInfo
import com.example.shifttest.presentation.BinSearchViewModel
import com.example.shifttest.presentation.SearchState
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = BinRepository()
    private val adapter = BinAdapter()
    private var isUserRequest = false
    private var lastBinNum: Long = 0
    private val binItemsList = ArrayList<BinItem>()


    private val viewModel: BinSearchViewModel by viewModels {
        viewModelFactory {
            initializer {
                BinSearchViewModel(repository)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.state.observe(this, ::handleState)
        initRecyclerView()
        setBtnListener()
        turnOffNightMode()
    }

    private fun initRecyclerView() {
        adapter.setOnItemClickListener {
            isUserRequest = false
            viewModel.loadData(binNum = it)
        }
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun turnOffNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setBtnListener() {
        binding.searchBtn.setOnClickListener {
            isUserRequest = true
            try {
                val binNum = binding.editBin.text.toString().toLong()
                lastBinNum = binNum
                viewModel.loadData(binNum)
            } catch (ex: java.lang.NumberFormatException) {
                Log.e(TAG, ex.message.orEmpty())
                showErrorMsg(ex.message.orEmpty())
            }
        }
    }


    private fun handleState(state: SearchState) {
        when (state) {
            SearchState.Initial -> Unit
            SearchState.Loading -> Unit
            is SearchState.Content -> {
                handleBinInfoModel(state.binInfoModel)
            }
            is SearchState.Error -> showErrorMsg(state.text)
        }
    }

    private fun handleBinInfoModel(binInfoModel: BinInfoModel) {
        val binInfo = BinInfo(lastBinNum, binInfoModel)
        startInfoActivity(binInfo)
        if (isUserRequest) {
            val binItem = BinItem(
                binNum = lastBinNum,
                searchDate = getDateAndTime()
            )
            adapter.addItem(binItem)
            binItemsList.add(binItem)
        }
    }

    private fun showErrorMsg(msg: String) {
        Toast.makeText(
            this@MainActivity,
            "${getString(R.string.error)}: $msg",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun getDateAndTime(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = Date()
        return formatter.format(date)
    }

    private fun startInfoActivity(binInfo: BinInfo) {
        val intent = Intent(this@MainActivity, InfoActivity::class.java).apply {
            putExtra(BIN_INFO, binInfo)
        }
        startActivity(intent)
    }


}
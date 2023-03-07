package com.example.shifttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shifttest.adapter.BinAdapter
import com.example.shifttest.data.BinInfo
import com.example.shifttest.data.BinRepository
import com.example.shifttest.databinding.ActivityMainBinding
import com.example.shifttest.presentation.InfoActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = BinRepository()
    private val adapter = BinAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        setBtnListener()
        turnOffNightMode()
    }

    private fun setRecyclerView(){
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun turnOffNightMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setBtnListener() {
        binding.searchBtn.setOnClickListener {
            val binNum = binding.editBin.text.toString().toInt()
            lateinit var binInfo: BinInfo
            lifecycleScope.launch {
                binInfo = repository.getByNum(binNum)
                adapter.addItem(binInfo)
                startInfoActivity(binInfo)
            }
        }
    }

    private fun startInfoActivity(binInfo: BinInfo) {
        val intent = Intent(this@MainActivity, InfoActivity::class.java).apply {
            putExtra(BIN_INFO, binInfo)
        }
        startActivity(intent)
    }


}
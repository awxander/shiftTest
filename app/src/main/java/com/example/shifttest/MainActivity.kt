package com.example.shifttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.shifttest.data.BinRepository
import com.example.shifttest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val repository = BinRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBtnListener()
    }

    private fun setBtnListener(){
        binding.run{
            searchBtn.setOnClickListener {
                val binNum = editBin.text.toString().toInt()
                lifecycleScope.launch {
                    val binInfo = repository.getByNum(binNum)
                    textView2.text = binInfo.scheme
                }
            }
        }
    }


}
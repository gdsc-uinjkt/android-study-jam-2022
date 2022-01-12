package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diceroll.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object{
        const val RESULT_EXTRA = "result"
    }

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntExtra(RESULT_EXTRA, 0)

        setDiceResult(result)
    }

    private fun setDiceResult(result: Int) {
        binding.tvDiceResult.text = result.toString()
    }
}
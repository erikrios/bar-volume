package io.erikrios.github.barvolume

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.barvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        savedInstanceState?.let {
            binding.tvResult.text = it.getString(STATE_RESULT)
        }

        binding.btnCalculate.setOnClickListener {
            val length = binding.etLength.text.toString().trim()
            val width = binding.etWidth.text.toString().trim()
            val height = binding.etHeight.text.toString().trim()

            val isValidInput = isValid(length, width, height)

            if (isValidInput) {
                val volume = length.toDouble() * width.toDouble() * height.toDouble()
                binding.tvResult.text = volume.toString()
            }
        }
    }

    private fun isValid(length: String, width: String, height: String): Boolean {
        var isValid = true

        when {
            length.isEmpty() -> {
                isValid = false
                binding.etLength.error = getString(R.string.not_empty_error)
            }
            width.isEmpty() -> {
                isValid = false
                binding.etWidth.error = getString(R.string.not_empty_error)
            }
            height.isEmpty() -> {
                isValid = false
                binding.etHeight.error = getString(R.string.not_empty_error)
            }
        }

        return isValid
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }
}
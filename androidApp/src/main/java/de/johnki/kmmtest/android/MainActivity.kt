package de.johnki.kmmtest.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import de.johnki.kmmtest.GetIssUseCase
import de.johnki.kmmtest.UpdateIssUseCase
import de.johnki.kmmtest.android.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getUseCase: GetIssUseCase
    @Inject
    lateinit var updateUseCase: UpdateIssUseCase
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

        getUseCase.getIssFlow().asLiveData().observe(this) { iss ->
            iss?.let {
                binding.id.text = it.id.toString()
                binding.latitude.text = it.latitude.toString()
                binding.longitude.text = it.longitude.toString()
                binding.altitude.text = it.altitude
                binding.velocity.text = it.velocity
                binding.visibility.text = it.visibility
                val date = Date(it.timestamp * 1000)
                val formatter =
                    SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
                binding.timestamp.text = formatter.format(date)
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            updateUseCase.updateIss()
            binding.swiperefresh.isRefreshing = false;
        }
    }
}

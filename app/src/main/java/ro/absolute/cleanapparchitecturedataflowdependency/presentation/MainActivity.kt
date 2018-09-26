package ro.absolute.cleanapparchitecturedataflowdependency.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.data_fields
import org.koin.android.architecture.ext.viewModel
import ro.absolute.cleanapparchitecturedataflowdependency.R
import ro.absolute.cleanapparchitecturedataflowdependency.presentation.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private val dataViewModel by viewModel<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataViewModel.data.observe(
                this,
                Observer { data ->
                    data?.let {
                        data_fields.text = data.toString()
                    }
                }
        )
    }
}

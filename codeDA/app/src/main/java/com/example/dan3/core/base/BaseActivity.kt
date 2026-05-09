package com.example.dan3.core.base

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity : ComponentActivity() {
    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected inline fun <reified  T : BaseViewModel> obseverViewModel (
        viewModel : T,
        crossinline onEvent : (UiEvent) -> Unit
    ) {
        lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                onEvent(event)
            }
        }
    }
}
package com.application.codelab2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _screenName = mutableStateOf("Home")
    var screenName = _screenName
}
package com.example.realtimelocation.ui.activity

import androidx.lifecycle.ViewModel
import com.example.realtimelocation.data.tabs.BottomTabs
import com.example.realtimelocation.data.tabs.GetBottomTabs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    getBottomTabs: GetBottomTabs,
) : ViewModel() {
    private val mutableTabs = MutableStateFlow<List<BottomTabs>>(emptyList())

    val tabs: StateFlow<List<BottomTabs>> = mutableTabs

    init {
        mutableTabs.tryEmit(getBottomTabs())
    }
}

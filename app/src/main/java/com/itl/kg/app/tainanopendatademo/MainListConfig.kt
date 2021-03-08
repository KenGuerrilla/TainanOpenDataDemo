package com.itl.kg.app.tainanopendatademo

import androidx.navigation.NavDirections


/**
 *
 *  需要自動產生NavDirections需要在Project gradle中的dependencies區塊新增
 *  classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
 *  並於App gradle plugins區塊新增androidx.navigation.safeargs.kotlin
 *
 *  詳細設定可參考：https://developer.android.com/guide/navigation/navigation-getting-started
 *
 */

object MainListConfig {

    val list = mutableListOf<MainListItem>()

    init {
        list.apply {
            add(MainListItem("Test item title", "Test item message", MainFragmentDirections.actionMainFragmentToParkingActivity()))
        }

    }
}

data class MainListItem(
    val title: String,
    val desc: String,
    val nav: NavDirections
)
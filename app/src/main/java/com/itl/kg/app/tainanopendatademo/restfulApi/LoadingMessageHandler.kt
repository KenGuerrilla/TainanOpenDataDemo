package com.itl.kg.app.tainanopendatademo.restfulApi

import androidx.fragment.app.FragmentManager
import com.itl.kg.app.tainanopendatademo.ui.dialog.LoadingDialog


class LoadingMessageHandler(
    private val fragmentManager: FragmentManager
) {

    companion object {
        const val TAG = "LoadingMessageHandler"
    }

    private val dialog: LoadingDialog = LoadingDialog()

    fun showLoadingView() {
        dialog.show(fragmentManager, TAG)
    }

    fun dismissLoadingView() {
        dialog.dismiss()
    }

}
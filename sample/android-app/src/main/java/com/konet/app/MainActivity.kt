/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.konet.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.konet.sample.library.viewmodel.TestViewModel
import dev.icerock.moko.mvvm.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val refreshButton: Button = findViewById(R.id.refreshButton)

        val viewModel = getViewModel { TestViewModel() }

        viewModel.users.ld().observe(this, Observer { url ->
            textView.text = "Users loaded: ${url?.data?.size}"
        })

        refreshButton.setOnClickListener {
            viewModel.onRefreshPressed()
        }
    }
}

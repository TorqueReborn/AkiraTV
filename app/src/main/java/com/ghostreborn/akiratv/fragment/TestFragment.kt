package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.update.GithubAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val testText: TextView = view.findViewById(R.id.test_text)
        CoroutineScope(Dispatchers.IO).launch {
            val test = GithubAPI().latestPackage()
            withContext(Dispatchers.Main) {
                testText.text = test
            }
        }
    }

}
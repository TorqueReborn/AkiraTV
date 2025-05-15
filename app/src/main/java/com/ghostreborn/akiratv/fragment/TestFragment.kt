package com.ghostreborn.akiratv.fragment

import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.os.Environment
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
import androidx.core.net.toUri

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
                val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val uri = test.toUri()
                val request = DownloadManager.Request(uri).apply {
                    setTitle("AkiraTV.apk")
                    setDescription("Downloading apk...")
                    setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "AkiraTV.apk")
                }
                downloadManager.enqueue(request)
            }
        }
    }

}
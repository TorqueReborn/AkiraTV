package com.ghostreborn.akiratv.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ghostreborn.akiratv.Constants
import com.ghostreborn.akiratv.KitsuAPI
import com.ghostreborn.akiratv.MainActivity
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.database.SavedEntry
import com.ghostreborn.akiratv.utils.AkiraUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment: Fragment() {

    lateinit var loginInfoConstraint: ConstraintLayout
    lateinit var loginConstraint: ConstraintLayout
    lateinit var loginProgress: ConstraintLayout
    private lateinit var userNameEdit: EditText
    private lateinit var userPassEdit: EditText
    private lateinit var loginProgressText: TextView
    lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginConstraint = view.findViewById(R.id.login_constraint)
        loginInfoConstraint = view.findViewById(R.id.login_info_constraint)
        loginProgress = view.findViewById(R.id.login_progress)
        loginProgressText = view.findViewById(R.id.login_progress_text)
        loginButton = view.findViewById(R.id.login_button)
        userNameEdit = view.findViewById(R.id.login_user)
        userPassEdit = view.findViewById(R.id.login_pass)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.login_info_continue_button).setOnClickListener{
            loginInfoConstraint.visibility = View.GONE
            loginConstraint.visibility = View.VISIBLE
        }
        loginButton.setOnClickListener{
            checkLogin()
        }
    }

    private fun checkLogin() {
        val userName = userNameEdit.text.toString()
        val userPass = userPassEdit.text.toString()
        if (userName.isNotEmpty() && userPass.isNotEmpty()) {
            lifecycleScope.launch {
                val loginResponse = KitsuAPI().login(userName, userPass)
                if (loginResponse == null) {
                    Toast.makeText(requireContext(), "Login failed, Signed Up?", Toast.LENGTH_SHORT)
                        .show()
                    return@launch
                }
                loginConstraint.visibility = View.GONE
                loginProgress.visibility = View.VISIBLE
                loginProgressText.text = "Getting User Info..."
                val userResponse = KitsuAPI().user(loginResponse.access_token)
                if (userResponse != null) {
                    requireContext().getSharedPreferences(Constants.SHARED_PREF, 0)
                        .edit()
                        .putString(Constants.PREF_USER_NAME, userResponse.data[0].attributes.name)
                        .putString(Constants.PREF_USER_ID, userResponse.data[0].id)
                        .putString(Constants.PREF_TOKEN, loginResponse.access_token)
                        .putString(Constants.PREF_REFRESH_TOKEN, loginResponse.refresh_token)
                        .apply()
                }
                loginProgressText.text = "Getting user's list..."
                val totalList = KitsuAPI().ids(userResponse!!.data[0].id, 0)
                val db = AkiraUtils().getDB(requireContext())
                withContext(Dispatchers.IO) {
                    for (i in 0 until totalList!!.data.size) {
                        val anime = KitsuAPI().anime(totalList.data[i].id)
                        withContext(Dispatchers.Main) {
                            loginProgressText.text =" Inserting ${anime?.data?.attributes?.canonicalTitle} to database..."
                        }
                        val entry = SavedEntry(
                            totalList.data[i].id,
                            totalList.data[i].attributes.progress,
                            totalList.data[i].attributes.status,
                            anime?.data?.id.toString(),
                            anime?.data?.attributes?.canonicalTitle.toString(),
                            anime?.data?.attributes?.posterImage?.large.toString()
                        )
                        db.savedEntryDao().insert(entry)
                    }
                }

                loginProgressText.text ="Login Completed!"

                requireContext().getSharedPreferences(Constants.SHARED_PREF, 0)
                    .edit()
                    .putBoolean(Constants.PREF_LOGGED_IN, true)
                    .apply()

                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}
package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ghostreborn.akiratv.KitsuAPI
import com.ghostreborn.akiratv.R
import kotlinx.coroutines.launch

class LoginFragment: Fragment() {

    lateinit var loginInfoConstraint: ConstraintLayout
    lateinit var loginConstraint: ConstraintLayout
    private lateinit var userNameEdit: EditText
    private lateinit var userPassEdit: EditText
    lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginConstraint = view.findViewById(R.id.login_constraint)
        loginInfoConstraint = view.findViewById(R.id.login_info_constraint)
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
            }
        }
    }
}
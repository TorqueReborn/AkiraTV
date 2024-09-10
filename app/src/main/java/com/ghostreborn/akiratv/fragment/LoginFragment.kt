package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.ghostreborn.akiratv.R

class LoginFragment: Fragment() {

    lateinit var loginInfoConstraint: ConstraintLayout
    lateinit var loginConstraint: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginConstraint = view.findViewById(R.id.login_constraint)
        loginInfoConstraint = view.findViewById(R.id.login_info_constraint)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.login_info_continue_button).setOnClickListener{
            loginInfoConstraint.visibility = View.GONE
            loginConstraint.visibility = View.VISIBLE
        }
    }
}
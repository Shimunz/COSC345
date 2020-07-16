package com.example.gossip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    /**
     * Creates the main menu for new users
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //To login Menu
        button_Menu_Login.setOnClickListener{
            val loginIntent = Intent(this, LoginMenu::class.java)
            startActivity(loginIntent)
        }

        //To Register Menu
        button_Menu_Register.setOnClickListener{
            val registerIntent = Intent(this, RegisterMenu::class.java)
            startActivity(registerIntent)
        }
    }

    /**
     * Checks to see if user is already logged in
     */
    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val intent = Intent(this, MessagesMenu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
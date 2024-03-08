package com.example.faiz

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var edemail: EditText
    lateinit var sdpassword1: EditText
    lateinit var tlogin: Button
    lateinit var tregister: TextView

    lateinit var prosesdialog: ProgressDialog
    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edemail = findViewById(R.id.edemail)
        sdpassword1 = findViewById(R.id.edpasswordmain)
        tlogin = findViewById(R.id.tvlogin)
        tregister = findViewById(R.id.tvdaftar)

        prosesdialog = ProgressDialog(this)
        prosesdialog.setTitle("LOGGING")
        prosesdialog.setMessage("SILAHKAN TUNGGU ...")

        tregister.setOnClickListener {
            startActivity(Intent(this, Daftar::class.java))
        }

        tlogin.setOnClickListener {
            if (edemail.text.isEmpty() || sdpassword1.text.isEmpty()) {
                Toast.makeText(this, "ISI EMAIL DAN PASSWORD ANDA !!!", Toast.LENGTH_SHORT).show()
            } else {
                prosesLogin()
            }

        }
    }

    private fun prosesLogin() {
        val email = edemail.text.toString()
        val password = sdpassword1.text.toString()

        prosesdialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                prosesdialog.dismiss()
            }
    }
}
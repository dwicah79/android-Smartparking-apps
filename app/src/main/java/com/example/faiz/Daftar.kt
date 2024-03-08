package com.example.faiz

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class Daftar : AppCompatActivity() {
    lateinit var ednama: EditText
    lateinit var edemail: EditText
    lateinit var edpasswordmain: EditText
    lateinit var edpasswordsec : EditText
    lateinit var sregist: Button
    lateinit var slogin: Button

    lateinit var prosesdialog: ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        ednama = findViewById(R.id.namapengguna)
        edemail = findViewById(R.id.emaill)
        edpasswordmain = findViewById(R.id.passwoordmain)
        edpasswordsec = findViewById(R.id.passwoordsec)
        sregist = findViewById(R.id.register)
        slogin = findViewById(R.id.logg)

        prosesdialog = ProgressDialog(this)
        prosesdialog.setTitle("Mendaftar")
        prosesdialog.setMessage("SILAHKAN TUNGGU ...")

        slogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        sregist.setOnClickListener {
            if (ednama.text.isNotEmpty() && edemail.text.isNotEmpty() && edpasswordmain.text.isNotEmpty()) {
                if (edpasswordmain.text.toString() == edpasswordsec.text.toString()) {
                    prosesDaftar()
                }else {
                    Toast.makeText(this, "KATA SANDI HARUS SAMA !!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "ISI SEMUA DATA !!!", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun prosesDaftar() {
        val emaill = edemail.text.toString()
        val namalengkap = ednama.text.toString()
        val passwordd = edpasswordmain.text.toString()


        prosesdialog.show()

        firebaseAuth.createUserWithEmailAndPassword(emaill, passwordd)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = namalengkap
                    }
                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnCompleteListener {
                            prosesdialog.dismiss()
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        .addOnFailureListener { error ->
                            Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
                        }

                } else {
                    prosesdialog.dismiss()
                }
            }
            .addOnFailureListener { error2 ->
                Toast.makeText(this, error2.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }
}
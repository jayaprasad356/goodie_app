package com.example.clintproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var sendOTPBtn : Button
    private lateinit var phoneNumberET : EditText
    private lateinit var phonenumber:EditText
    private lateinit var auth:FirebaseAuth
    private lateinit var number:String
    private lateinit var progressbar:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phonenumber=findViewById<EditText>(R.id.mobile_number)
        progressbar= ProgressDialog(this)
        progressbar.setMessage("Please wait...")
        auth= FirebaseAuth.getInstance()

        init()
        login.setOnClickListener {

            number = phoneNumberET.text.trim().toString()
            if (number.isNotEmpty()){
                if (number.length == 10){
                    number = "+91$number"
progressbar.show()
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                }else{
                    Toast.makeText(this , "Please Enter correct Number" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , "Please Enter Number" , Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun init(){

        sendOTPBtn = findViewById(R.id.login)
        phoneNumberET = findViewById(R.id.mobile_number)
        auth = FirebaseAuth.getInstance()
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this , "Authenticate Successfully" , Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
progressbar.dismiss()
            }
    }

    private fun sendToMain(){
        startActivity(Intent(this , MainActivity::class.java))
    }
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {


            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
          
            val intent = Intent(this@MainActivity , VerificationActivity::class.java)
            intent.putExtra("OTP" , verificationId)
            intent.putExtra("resendToken" , token)
            intent.putExtra("phoneNumber" , number)
            startActivity(intent)
            progressbar.dismiss()
        }
    }


    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            startActivity(Intent(this , MainActivity::class.java))
        }
    }
}
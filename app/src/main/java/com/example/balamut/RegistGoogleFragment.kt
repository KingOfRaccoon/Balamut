package com.example.balamut

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


class RegistGoogleFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var auth: FirebaseAuth

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var account: GoogleSignInAccount

    lateinit var txtName: TextView
    lateinit var txtEmail: TextView
    lateinit var txtId: TextView
    lateinit var but_google: SignInButton

    private val TAG = MainActivity::class.java.simpleName
    private val RC_SIGN_IN = 7

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_regist_google, container, false)

        but_google = view.findViewById(R.id.sign_in_button)

        txtName = view.findViewById(R.id.txtName)
        txtEmail = view.findViewById(R.id.txtEmail)
        txtId = view.findViewById(R.id.txtId)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
//                .Builder(requireContext())
//                .enableAutoManage(requireActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build()

        but_google.setSize(SignInButton.SIZE_STANDARD)
        but_google.setOnClickListener { signIn() }

        auth = FirebaseAuth.getInstance()

        return view
    }

    private fun signIn() {
        val singInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(singInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        auth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener {
            updateUI(null)
        }
        Log.d(TAG, "SignOut worked")
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCompleteListener{
            updateUI(null)
        }
        Log.d(TAG, "RevokeAccess worked")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount>? = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task?.isSuccessful == true){
                handleSignInResult(task)
                //account = task.getSignInAccount()
            }
            else {
                updateUI(null)
                Log.w(TAG, "Sign in faild")
            }
            /*try {
                account = task?.getResult(ApiException::class.java)!!
                Log.d(TAG, "OnActivityResult ${account.id} and ${account.email}")
            }
            catch (e: ApiException){
                Log.d(TAG, "OnActivityResult: $task, $e")
                updateUI(null)
            }*/
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>?) {
        Log.d(TAG, "handleSignInResult:" + completedTask!!.isSuccessful)
        try {
            account = completedTask.getResult(ApiException::class.java)!!
            updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
            updateUI(null)
        }
    }

    override fun onStart() {
        super.onStart()
        account = GoogleSignIn.getLastSignedInAccount(requireContext())!!

        if (account != null) {
            Log.d(TAG, "Got cached sign-in")
            var name: String = account.displayName!!
            var email : String = account.email!!
            var id : String = account.id!!
        } //else {
            //account.setResultCallback { googleSignInResult -> handleSignInResult(googleSignInResult) }
            //updateUI(null)
       // }
    }

    private fun updateUI(isSignedIn: GoogleSignInAccount?) {
        if (isSignedIn != null){
            txtEmail.setText(account.id)
            txtId.setText(account.id)
            txtName.setText(account.displayName)
        }
         else {
            Log.d(TAG, "updateUI ERROR")
        }
    }
}
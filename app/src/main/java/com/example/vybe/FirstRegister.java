package com.example.vybe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class FirstRegister extends AppCompatActivity {
    private Button googleSignIn;
    private GoogleSignInClient mGoogleSignInClient;
    private Button FacebookSignIn;
    private Button EmailSignIn;
    private FirebaseAuth firebaseAuth;
    private int RC_SIGN_IN = 1;
    private static final String TAG = "FacebookLogin";
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_register);

        googleSignIn = (Button) findViewById(R.id.google);
        FacebookSignIn = (Button) findViewById(R.id.buttonFacebookLogin);

        //Initializing Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Initializing Google SignUp
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
;
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
    private void signIn(){
        Intent signIn = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIn,RC_SIGN_IN);
    }
        });

        //Initializing Facebook SignUp
        callbackManager = CallbackManager.Factory.create();
        FacebookSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FirstRegister.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook onSuccess" + loginResult);
                        handleFacebookAcessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG,"facebook onCancel");

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG,"facebook on error" + error);

                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Google Sign Up
    if(requestCode ==RC_SIGN_IN)
      {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);

      }
      //Facebook Sign Up
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAcessToken(AccessToken token) {
        Log.d(TAG, "handlefacebookaccesstoken" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //If Sign Up is succesful update the UI.
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.d(TAG, "signInWIthCredential:failure", task.getException());
                            Toast.makeText(FirstRegister.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(FirstRegister.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(account);
        }
        catch (ApiException e){
            Toast.makeText(FirstRegister.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    Intent mainIntent = new Intent(FirstRegister.this, Home.class);
                    startService(mainIntent);
                }
            }, 5000);
        }
    }
    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
        //check if account is null
        if (account != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(FirstRegister.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        startActivity(new Intent(FirstRegister.this, Home.class));
                        finish();
                    }
                }
            });
        }
        else{
            Toast.makeText(FirstRegister.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();

            Toast.makeText(FirstRegister.this, personName + personEmail, Toast.LENGTH_SHORT).show();
        }

    }
}

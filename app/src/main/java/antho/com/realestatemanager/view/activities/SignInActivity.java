package antho.com.realestatemanager.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import antho.com.realestatemanager.MainActivity;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;
import butterknife.BindView;

public class SignInActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.password) EditText password;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.sign_up)
    TextView signUp;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "Firebase";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set click listeners
        signInButton.setOnClickListener(this);
        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void login()
    {
        mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user)
    {
        if (user != null)
        {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_in_button:
                login();
                break;
            case R.id.sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.recover_password:
                startActivity(new Intent(this, RecoverPasswordActivity.class));
        }
    }
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_sign_in;
    }
}

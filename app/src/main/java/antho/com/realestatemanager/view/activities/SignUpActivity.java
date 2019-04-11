package antho.com.realestatemanager.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import antho.com.realestatemanager.MainActivity;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;
import butterknife.BindView;

public class SignUpActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.sign_up_button)
    Button signInButton;
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
    private void signIn()
    {
        mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task ->
                {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }
    private void updateUI(FirebaseUser user)
    {
        if (user != null)
        {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_up_button:
                signIn();
                break;
        }
    }
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_sign_up;
    }
}

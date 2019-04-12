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
import antho.com.realestatemanager.helpers.Utilities;
import butterknife.BindView;
/** Sign up screen to create a new account **/
public class SignUpActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.password) EditText password;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.sign_up_button) Button signInButton;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "Firebase";
    // Set on click listener and get firebase instance
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        signInButton.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    // Logic to create a new user account
    private void signUp()
    {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        if (Utilities.isEmailValid(emailText))
        {
            if (Utilities.isPasswordValid(passwordText))
            {
                mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, task ->
                        {
                            if (task.isSuccessful())
                            {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                updateUI(user);
                            }
                            else
                            {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                showToast("Authentication failed");
                                updateUI(null);
                            }
                        });
            }
            else
            {
                showToast("Please type an 8 characters minimum password with at least one alphanumerical and one special character");
            }
        }
        else
        {
            showToast("Please type a valid email address");
        }
    }
    // Load main activity when sign up has been successfully done
    private void updateUI(FirebaseUser user)
    {
        if (user != null)
        {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        }
    }
    // On click listener for sign up button
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_up_button:
                signUp();
                break;
        }
    }
    // Return activity layout
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_sign_up;
    }
}

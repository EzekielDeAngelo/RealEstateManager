package antho.com.realestatemanager.view.activities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;
import butterknife.BindView;
/** Recover password screen to ask for a new password **/
public class RecoverPasswordActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.email) EditText email;
    @BindView(R.id.recover_password_button) Button recoverPasswordButton;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "Firebase";
    // Set on click listener and get firebase instance
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        recoverPasswordButton.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    // Logic to send an email with a link to recover password
    private void recoverPassword()
    {
        String emailAddress = email.getText().toString();
        mFirebaseAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        Log.d(TAG, "Email sent.");
                        showToast("A link to create a new password has been sent to " + emailAddress);
                    }
                });
    }
    // On click listener for recover password button
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.recover_password_button:
                recoverPassword();
                break;
        }
    }
    // Return activity layout
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_recover_password;
    }
}
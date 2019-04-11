package antho.com.realestatemanager.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import antho.com.realestatemanager.MainActivity;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;
import butterknife.BindView;

public class RecoverPasswordActivity extends BaseActivity implements View.OnClickListener
{

    @BindView(R.id.email) EditText email;
    @BindView(R.id.recover_password_button)
    Button recoverPasswordButton;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "Firebase";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set click listeners
        recoverPasswordButton.setOnClickListener(this);
        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    private void recoverPassword()
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user)
    {
        if (user != null)
        {
            startActivity(new Intent(RecoverPasswordActivity.this, MainActivity.class));
        }
    }
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
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_recover_password;
    }
}
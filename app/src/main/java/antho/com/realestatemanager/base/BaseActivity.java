package antho.com.realestatemanager.base;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Objects;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import antho.com.realestatemanager.R;
import butterknife.ButterKnife;

public abstract class BaseActivity  extends AppCompatActivity
{
    Toolbar toolbar;
    // Set layout and support action bar
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());

        configureToolbar();
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);
    }
    //
    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
    }

    // Set a return value as a layout resource reference
    @LayoutRes
    protected abstract int layoutRes();
    //

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    // Helper method to display a toast
    protected void showToast(String message)
    {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
package antho.com.realestatemanager.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import antho.com.realestatemanager.MainActivity;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;

public class AddActivity extends BaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutRes()
    {
        return R.layout.activity_add;
    }
}

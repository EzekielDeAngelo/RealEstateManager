package antho.com.realestatemanager;
/** Main activity **/
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import antho.com.realestatemanager.base.BaseActivity;
import antho.com.realestatemanager.view.activities.AddActivity;
import antho.com.realestatemanager.view.activities.SignInActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/** **/
public class MainActivity extends BaseActivity
{
    @BindView(R.id.filterIcon) ImageView filterIcon;
    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior sheetBehavior;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        // Set default username is anonymous.
        mUsername = ANONYMOUS;
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        if (mFirebaseUser == null)
        {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }

        filterIcon = coordinatorLayout.findViewById(R.id.filterIcon);
        LinearLayout contentLayout = coordinatorLayout.findViewById(R.id.contentLayout);

        sheetBehavior = BottomSheetBehavior.from(contentLayout);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHideable(false);//prevents the boottom sheet from completely hiding off the screen
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//initially state to fully expanded

        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFilters();
            }
        });
    }

    //
    private void toggleFilters(){
        if(sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
        {
            sheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        }
        else
        {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
    // Inflates app bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut();
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }
}

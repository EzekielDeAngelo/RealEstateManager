package antho.com.realestatemanager;
/** Main activity **/
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import antho.com.realestatemanager.base.BaseActivity;
import antho.com.realestatemanager.helpers.Utilities;
import antho.com.realestatemanager.view.fragments.MainFragment;
import butterknife.BindView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/** **/
public class MainActivity extends BaseActivity
{
    //@BindView(R.id.activity_main_activity_text_view_main) TextView textViewMain;
    //@BindView(R.id.minimum_price) TextView textViewQuantity;
    private static final String MAIN_VIEW_TAG = "MAIN_VIEW_TAG";
    FragmentManager fragmentManager;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        loadFragment();

    }
    private void loadFragment()
    {
        Fragment fragment = null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentByTag(MAIN_VIEW_TAG);
        if (fragment == null)
        {
            fragment = MainFragment.newInstance();
            fragmentTransaction.add(R.id.fragment_frame, fragment, MAIN_VIEW_TAG);
        }


        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }


    //
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_main;
    }
}

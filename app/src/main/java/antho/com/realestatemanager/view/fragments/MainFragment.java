package antho.com.realestatemanager.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseFragment;
import butterknife.BindView;

public class MainFragment extends BaseFragment
{
    @BindView(R.id.filterIcon)
    ImageView filterIcon;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    BottomSheetBehavior sheetBehavior;
    //
    public MainFragment() {}
    //
    public static MainFragment newInstance()
    {
        return new MainFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        coordinatorLayout = (CoordinatorLayout)inflater.inflate(R.layout.fragment_main, container, false);



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
        return coordinatorLayout;
    }

    private void toggleFilters(){
        if(sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            sheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        }
        else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    //
    @Override
    public int layoutRes()
    {
        return R.layout.activity_main;
    }
}

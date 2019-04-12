package antho.com.realestatemanager.view.activities;
import android.app.DatePickerDialog;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import antho.com.realestatemanager.R;
import antho.com.realestatemanager.base.BaseActivity;
import butterknife.BindView;

/** **/
public class AddActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, PlaceSelectionListener
{
    @BindView(R.id.price) EditText price;
    @BindView(R.id.description) EditText description;
    @BindView(R.id.surface) EditText surface;
    @BindView(R.id.room_spinner) Spinner rooms;
    @BindView(R.id.type_spinner) Spinner type;
    @BindView(R.id.agent_spinner) Spinner agent;
    @BindView(R.id.edit_button) Button editButton;
    @BindView(R.id.date) TextView date;
    private static final String TAG="prout";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String[] typeList = {"House", "Flat", "Duplex", "Penthouse"};
        setSpinner(typeList, type);
        String[] roomsList = {"1 Room", "2 Rooms", "3 Rooms","4 Rooms", "5 Rooms", "6 Rooms", "7 Or more"};
        setSpinner(roomsList, rooms);
        String[] agentList = {"1 Room", "2 Rooms", "3 Rooms","4 Rooms", "5 Rooms", "6 Rooms", "7 Or more"};
        setSpinner(agentList, agent);
        setDate();
// Initialize Places.
        Places.initialize(getApplicationContext(),"AIzaSyAuGzug9RNw6hQZvzetYY-VB5is8OLeS7w");

// Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

// Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    private void setDate()
    {
        final Calendar ca = Calendar.getInstance();
        int mYear = ca.get(Calendar.YEAR);
        int mMonth = ca.get(Calendar.MONTH) + 1;
        int mDay = ca.get(Calendar.DAY_OF_MONTH);
        String datee= mDay + "/" + mMonth + "/" + mYear;
        date.setText(datee);
        date.setOnClickListener(v ->
        {
            //isFromDateSelected = true;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);
        });
    }
    private void setSpinner(String[] data, Spinner spinner)
    {

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_selected_item, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddActivity.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    protected int layoutRes()
    {
        return R.layout.activity_add;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText(String.format(Locale.getDefault(), "%d/%d/%d", dayOfMonth, month + 1, year));
    }

    @Override
    public void onPlaceSelected(@NonNull Place place) {
// TODO: Get info about the selected place.
        Log.d(TAG, "Place: " + place.getName() + ", " + place.getId());
    }

    @Override
    public void onError(@NonNull Status status) {

    }
}

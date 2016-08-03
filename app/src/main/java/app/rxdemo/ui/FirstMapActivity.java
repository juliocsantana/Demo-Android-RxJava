package app.rxdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.rxdemo.R;
import app.rxdemo.ui.fragments.FirstMapFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
   @BindView(R.id.txtPhone)
    TextView txtPhone;
   @BindView(R.id.txtMonOpen)
    TextView txtMonOpen;
   @BindView(R.id.txtTueOpen)
    TextView txtTueOpen;
   @BindView(R.id.txtWedOpen)
    TextView txtWedOpen;
   @BindView(R.id.txtThuOpen)
    TextView txtThuOpen;
   @BindView(R.id.txtFriOpen)
    TextView txtFriOpen;
    @BindView(R.id.txtSatOpen)
    TextView txtSatOpen;
    @BindView(R.id.txtSunOpen)
    TextView txtSunOpen;
   @BindView(R.id.txtMonClose)
    TextView txtMonClose;
   @BindView(R.id.txtTueClose)
    TextView txtTueClose;
   @BindView(R.id.txtWedClose)
    TextView txtWedClose;
   @BindView(R.id.txtThuClose)
    TextView txtThuClose;
   @BindView(R.id.txtFriClose)
    TextView txtFriClose;
    @BindView(R.id.txtSatClose)
    TextView txtSatClose;
    @BindView(R.id.txtSunClose)
    TextView txtSunClose;
    private Intent intent;
    private Bundle bundle;
    private String name, phone;
    private float latitude, longitude;
    private FirstMapFragment mFirstMapFragment;
    private LatLng beerStoreMarker;
    private CameraPosition cameraPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_map);
        ButterKnife.bind(this);
        buildView();
    }

    public void buildView() {
        mFirstMapFragment = FirstMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.map_container, mFirstMapFragment).commit();
        mFirstMapFragment.getMapAsync(this);
        intent = getIntent();
        bundle = intent.getExtras();
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        txtName.setText(name);
        txtAddress.setText(bundle.getString("address"));
        txtPhone.setText(phone);
        latitude = bundle.getFloat("latitude");
        longitude = bundle.getFloat("longitude");
        txtMonOpen.setText(bundle.getString("monday_open"));
        txtTueOpen.setText(bundle.getString("tuesday_open"));
        txtWedOpen.setText(bundle.getString("wednesday_open"));
        txtThuOpen.setText(bundle.getString("thursday_open"));
        txtFriOpen.setText(bundle.getString("friday_open"));
        txtSatOpen.setText(bundle.getString("saturday_open"));
        txtSunOpen.setText(bundle.getString("sunday_open"));
        txtMonClose.setText(bundle.getString("monday_close"));
        txtTueClose.setText(bundle.getString("tuesday_close"));
        txtWedClose.setText(bundle.getString("wednesday_close"));
        txtThuClose.setText(bundle.getString("thursday_close"));
        txtFriClose.setText(bundle.getString("friday_close"));
        txtSatClose.setText(bundle.getString("saturday_close"));
        txtSunClose.setText(bundle.getString("sunday_close"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        beerStoreMarker = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions()
                .position(beerStoreMarker)
                .title(name));

        cameraPosition = CameraPosition.builder()
                .target(beerStoreMarker)
                .zoom(12)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

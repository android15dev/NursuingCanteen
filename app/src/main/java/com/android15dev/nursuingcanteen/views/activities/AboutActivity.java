package com.android15dev.nursuingcanteen.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.android15dev.nursuingcanteen.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private ImageView transparentImageView;
    private NestedScrollView content_about;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                mapFragment.getMapAsync(AboutActivity.this);
            }
        }, 1500);

        initUI();

    }

    private void initUI () {
        transparentImageView = (ImageView) findViewById(R.id.transparent_image);
        content_about = (NestedScrollView) findViewById(R.id.content_about);
        findViewById(R.id.img_call).setOnClickListener(this);
        findViewById(R.id.img_msg).setOnClickListener(this);
        findViewById(R.id.lay_call).setOnClickListener(this);
        findViewById(R.id.lay_whatsapp).setOnClickListener(this);
        findViewById(R.id.lay_email).setOnClickListener(this);
        findViewById(R.id.lay_location).setOnClickListener(this);

        transparentImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch (View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        content_about.requestDisallowInterceptTouchEvent(true);
                        return false;

                    case MotionEvent.ACTION_UP:
                        content_about.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        content_about.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady (GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(30.680351, 74.772518);
        mMap.addMarker(new MarkerOptions().position(sydney).title("My Place"));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(sydney, 17)));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.img_call:
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel:+919463092892"));
                startActivity(callIntent);
                break;
            case R.id.lay_call:
                Intent callIntent1 = new Intent(Intent.ACTION_VIEW);
                callIntent1.setData(Uri.parse("tel:+919463092892"));
                startActivity(callIntent1);
                break;
            case R.id.img_msg:
                Intent sms_intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:+919463092892"));
                startActivity(sms_intent);
                break;
            case R.id.lay_whatsapp:
                try {
                    Uri uri = Uri.parse("smsto:9463092892");
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    i.setPackage("com.whatsapp");
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lay_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:sahilg_anrd@krishnais.com"));
                startActivity(Intent.createChooser(emailIntent, "Email Via"));
                break;
            case R.id.lay_location:
                String uriString = "geo:30.680351,74.772518?q=" + Uri.encode("30.680351,74.772518(My Place)");
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
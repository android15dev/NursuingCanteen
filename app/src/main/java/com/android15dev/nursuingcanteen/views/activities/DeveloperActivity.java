package com.android15dev.nursuingcanteen.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android15dev.nursuingcanteen.R;

public class DeveloperActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.img_call:
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel:+919041490646"));
                startActivity(callIntent);
                break;
            case R.id.lay_call:
                Intent callIntent1 = new Intent(Intent.ACTION_VIEW);
                callIntent1.setData(Uri.parse("tel:+919041490646"));
                startActivity(callIntent1);
                break;
            case R.id.img_msg:
                Intent sms_intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:+919041490646"));
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

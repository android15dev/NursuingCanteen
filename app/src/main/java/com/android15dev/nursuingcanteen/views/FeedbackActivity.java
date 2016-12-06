package com.android15dev.nursuingcanteen.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android15dev.nursuingcanteen.R;
import com.android15dev.nursuingcanteen.controller.Utils;
import com.android15dev.nursuingcanteen.model.FeedbackProp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name, et_email, et_message;
    private FirebaseDatabase firebaseInstance;
    private DatabaseReference database;
    private ValueEventListener listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseInstance = FirebaseDatabase.getInstance();
        database = firebaseInstance.getReference("Feedback");

        initUI();
    }

    private void initUI() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_message = (EditText) findViewById(R.id.et_message);
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String message = et_message.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Utils.getInstance().showToast("Enter Name");
                } else if (TextUtils.isEmpty(email)) {
                    Utils.getInstance().showToast("Enter Email");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Utils.getInstance().showToast("Enter valid email");
                } else if (TextUtils.isEmpty(message)) {
                    Utils.getInstance().showToast("Enter Message");
                } else {
                    String key = database.push().getKey();
                    FeedbackProp prop = new FeedbackProp(name, email, message);
                    database.child(key).setValue(prop);
                    listner = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            onSuccess();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            databaseError.toException().printStackTrace();
                        }
                    };
                    database.addValueEventListener(listner);
                }
                break;
        }
    }

    private void onSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Thanks for your feedback.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        if (listner != null)
            database.removeEventListener(listner);
        super.onDestroy();
    }
}

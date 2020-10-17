package com.example.passangerapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SessionRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    ImageView imageView;
    Spinner sp1;
    EditText ed;
    Button b;
    DatabaseReference mDatabase;
    SessionRequestData sessionRequestData;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_session_request);
        sp1 = findViewById(R.id.spinner);
        sp1.setOnItemSelectedListener(this);
        ed = findViewById(R.id.descriptionz);
        b = findViewById(R.id.sessionsend);
        sessionRequestData = new SessionRequestData();


        /*reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ses = sp1.getSelectedItem().toString();
                String desc = ed.getText().toString();

                if(desc.equals("")){
                    Toast.makeText(SessionRequest.this, "Please Fill Feilds To Continue..!", Toast.LENGTH_SHORT).show();


                }else if(ses.equals("Select")){

                    Toast.makeText(SessionRequest.this, "Please Select Session Name..!", Toast.LENGTH_SHORT).show();

                }else{
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Session");

                    sessionRequestData = new SessionRequestData();

                    sessionRequestData.setSessionname(ses);
                    sessionRequestData.setSessdescription(desc);

                    mDatabase.push().setValue(sessionRequestData);
                    Toast.makeText(SessionRequest.this, "Data Saved Saucess", Toast.LENGTH_SHORT).show();
                    ed.setText("");


                }


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
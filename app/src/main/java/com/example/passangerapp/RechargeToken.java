package com.example.passangerapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RechargeToken extends AppCompatActivity {
    private EditText ed, ed1, ed2, ed3;
    private Button b;
    private DatabaseReference mDatabase;

    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_recharge_token);

        final Bundle extras = getIntent().getExtras();

        ed = findViewById(R.id.amountrecharge);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);

        b = findViewById(R.id.rechargebtn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardno = ed1.getText().toString();
                String expdate = ed2.getText().toString();
                String ccv = ed3.getText().toString();
                final String amount = ed.getText().toString();
                if (cardno.equals("") || expdate.equals("") || ccv.equals("") || amount.equals("")) {
                    Toast.makeText(RechargeToken.this, "Please Fill Feilds To Continue..!", Toast.LENGTH_SHORT).show();
                } else {
                    String id = extras.getString("pid");

                    Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Passanger");

                    Query checkInspector = mDatabase.orderByChild("pid").equalTo(id);

                    checkInspector.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String pid = null, amt = null;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    pid = ds.child("pid").getValue(String.class);
                                    amt = ds.child("amount").getValue(String.class);
                                }
                                double curAmt = Double.parseDouble(amt);

                                curAmt += Double.parseDouble(amount);
                                String newAmt = Double.toString(curAmt);

                                mDatabase.child(pid).child("amount").setValue(newAmt);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(RechargeToken.this, "Recharged Successfully!!!", Toast.LENGTH_SHORT).show();
                    ed.setText("");
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                }
            }
        });
    }
}
package com.example.passangerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passangerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button b1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.unamezfb);
        ed2 = findViewById(R.id.pwzfb);
        b1 = findViewById(R.id.loginbtnfb);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = ed1.getText().toString().trim();
                String pw = ed2.getText().toString().trim();


                if(uname.equals("") || pw.equals("")){

                    Toast.makeText(MainActivity.this, "Please Fill All Feilds To Continue..", Toast.LENGTH_LONG).show();
                }else if(pw.length() <6){
                    Toast.makeText(MainActivity.this, "Password Lenght is short..", Toast.LENGTH_LONG).show();


                }else{

                    isPassenger(uname,pw);
                    /*firebaseAuth.signInWithEmailAndPassword(uname, pw)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Login Sucess..!", Toast.LENGTH_LONG).show();
                                        Intent yy= new Intent(MainActivity.this,MainActivity2.class);
                                        startActivity(yy);

                                    } else {
                                        Toast.makeText(MainActivity.this, "Invalid Username Or Password", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });*/



                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void isPassenger(final String username, final String password) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Passanger");

        Query checkInspector = reference.orderByChild("pemail").equalTo(username);

        checkInspector.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String passwordFromDB = null,pidFromDB = null,pnameFromDB = null,amountFromDB = null;

                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        passwordFromDB = ds.child("password").getValue(String.class);
                        pidFromDB = ds.child("pid").getValue(String.class);
                        pnameFromDB = ds.child("pname").getValue(String.class);
                        amountFromDB = ds.child("amount").getValue(String.class);
                    }

                    //String passwordFromDB = dataSnapshot.child(username).child("password").getValue(String.class);
                    //PassengerData passengerData = dataSnapshot.getValue(PassengerData.class);

                    if(passwordFromDB.equals(password)){

                        //pidFromDB = dataSnapshot.child(username).child("pid").getValue(String.class);
                        //pnameFromDB = dataSnapshot.child(username).child("pname").getValue(String.class);
                        //amountFromDB = dataSnapshot.child(username).child("amount").getValue(String.class);

                        Intent home = new Intent(MainActivity.this, MainActivity2.class);
                        home.putExtra("pid",pidFromDB);
                        home.putExtra("pname",pnameFromDB);
                        home.putExtra("amount",amountFromDB);
                        startActivity(home);

                        Toast.makeText(getApplicationContext(),"Login Success..!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
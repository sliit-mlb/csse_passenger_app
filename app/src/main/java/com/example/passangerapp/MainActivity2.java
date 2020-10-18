package com.example.passangerapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE = 0.7f;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LinearLayout contecnt;

    private ImageView qrcode;
    private TextView amount;

    private String pid = null, balance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();

        pid = extras.getString("pid");
        balance = extras.getString("amount");

        contecnt = findViewById(R.id.contenzz);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        qrcode = findViewById(R.id.qr);
        amount = findViewById(R.id.amt);

        toolbar = findViewById(R.id.toolbar);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        amount.setText(extras.getString("amount"));
        String pid = extras.getString("pid");

        if (!pid.equals("")) {
            QRGEncoder qrgEncoder = new QRGEncoder(pid, null, QRGContents.Type.TEXT, 500);
            try {
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrcode.setImageBitmap(qrBits);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        animatenavigationdrawe();

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });

    }

    private void animatenavigationdrawe() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contecnt.setScaleX(offsetScale);
                contecnt.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contecnt.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contecnt.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_search:
                startActivity(new Intent(getApplicationContext(), SessionRequest.class));
                break;
            case R.id.nav_home:
                Intent rechargeToken = new Intent(getApplicationContext(), RechargeToken.class);
                rechargeToken.putExtra("pid", pid);
                startActivity(rechargeToken);
                break;
            case R.id.nav_cato:
                Intent myToken = new Intent(getApplicationContext(), MyToken.class);
                myToken.putExtra("pid", pid);
                myToken.putExtra("amount", balance);
                startActivity(myToken);
                break;
            case R.id.nav_skillz:
                Intent tripHistory = new Intent(getApplicationContext(), TipHistory.class);
                tripHistory.putExtra("pid", pid);
                startActivity(tripHistory);
                break;
            case R.id.nav_signout:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

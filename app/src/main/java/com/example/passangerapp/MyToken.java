package com.example.passangerapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MyToken extends AppCompatActivity {

    private ImageView qrView;
    private TextView amountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_token);

        qrView = findViewById(R.id.qrCode);
        amountView = findViewById(R.id.amount);

        Bundle extras = getIntent().getExtras();

        amountView.setText(extras.getString("amount"));
        String pid = extras.getString("pid");

        if (!pid.equals("")) {
            QRGEncoder qrgEncoder = new QRGEncoder(pid, null, QRGContents.Type.TEXT, 500);
            try {
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrView.setImageBitmap(qrBits);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
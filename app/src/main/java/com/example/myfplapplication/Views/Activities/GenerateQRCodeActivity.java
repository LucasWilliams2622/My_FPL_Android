package com.example.myfplapplication.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myfplapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQRCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);

        EditText edt_QR_code = findViewById(R.id.edt_QR_code);
        Button btn_generate_QRCode = findViewById(R.id.btn_generate_QRCode);
        ImageView iv_QRCode = findViewById(R.id.iv_QRCode);

        btn_generate_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try{
                        BitMatrix bitMatrix =multiFormatWriter.encode(edt_QR_code.getText().toString(), BarcodeFormat.QR_CODE,300,300);

                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        iv_QRCode.setImageBitmap(bitmap);
                    }catch (WriterException e){
                        throw  new RuntimeException(e);
                    }
            }
        });
    }
}
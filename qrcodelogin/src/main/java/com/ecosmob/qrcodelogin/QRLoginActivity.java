package com.ecosmob.qrcodelogin;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;


public class QRLoginActivity extends AppCompatActivity implements View.OnClickListener {

    public MenuItem menuItemSubmit;
    CompoundBarcodeView barcodeScannerView;
    Bundle savedInstanceState;
    private RelativeLayout relativeLayoutAddCodeBtn;
    private EditText editTextAddCode;
    private Button buttonAddCode;
    private CaptureManager capture;
    private String qrCode = "";
    private boolean checkItemVisib = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qr);
        this.savedInstanceState = savedInstanceState;
        initV();

        barcodeScannerView.setStatusText("");
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
        scanQrCode();
    }

    private void initV() {
        barcodeScannerView = (CompoundBarcodeView) findViewById(R.id.zxing_barcode_scanner);
        relativeLayoutAddCodeBtn = (RelativeLayout) findViewById(R.id.reltivelayout_addcode_btn);
        editTextAddCode = (EditText) findViewById(R.id.edtxt_addcode);

        buttonAddCode = (Button) findViewById(R.id.btn_addcode);
        buttonAddCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        barcodeScannerView.setVisibility(View.GONE);
        editTextAddCode.setVisibility(View.VISIBLE);
        buttonAddCode.setVisibility(View.GONE);
        editTextAddCode.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextAddCode, InputMethodManager.SHOW_IMPLICIT);
        checkItemVisib = true;
        invalidateOptionsMenu();

    }

    private void scanQrCode() {
        barcodeScannerView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Log.d("resGETString", "Scanned Barcode:@@ " + result.getText());
                qrCode = getValidQrCode(result.getText());
                setTitle(qrCode);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    private String getValidQrCode(String scannedCode) {
        String trimedCode = scannedCode.replaceAll("[^\\p{L}\\p{Nd}]+", "");
        if (trimedCode.contains("21")) {
            trimedCode = trimedCode.substring(trimedCode.indexOf("21"), trimedCode.length());
            trimedCode = trimedCode.replace("21", "");
            return trimedCode;
        } else {
            Toast.makeText(this, "Not valid", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }
}
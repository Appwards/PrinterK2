package com.example.emptyprinter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.emptyprinter.aidlutils.AidlUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AidlUtil.getInstance().connectPrinterService(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                int status = AidlUtil.getInstance().getPrinterStatus();
                Log.i("Printer","Printer status: " + status);
            }
        }, 3000);
    }
}
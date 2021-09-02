package com.example.emptyprinter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.emptyprinter.aidlutils.AidlUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AidlUtil.getInstance().connectPrinterService(this);
    }
}
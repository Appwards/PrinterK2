package com.example.emptyprinter.aidlutils;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface PrinterCallback {
    String getResult();
    void onReturnString(String result);
}

package com.example.emptyprinter.aidlutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.emptyprinter.aidlutils.bean.TableItem;

import java.util.LinkedList;

import woyou.aidlservice.jiuiv5.ExtPrinterService;
import woyou.aidlservice.jiuiv5.ICallback;

public class AidlUtil {
    private static final String SERVICE＿PACKAGE = "woyou.aidlservice.jiuiv5";
    private static final String SERVICE＿ACTION = "woyou.aidlservice.jiuiv5.ExtPrinterService";

    private ExtPrinterService printerService;
    private static AidlUtil mAidlUtil = new AidlUtil();
    private Context context;

    private AidlUtil() {
    }

    public static AidlUtil getInstance() {
        return mAidlUtil;
    }

    public void connectPrinterService(Context context) {
        this.context = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setPackage(SERVICE＿PACKAGE);
        intent.setAction(SERVICE＿ACTION);
        context.getApplicationContext().startService(intent);
        context.getApplicationContext().bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    public void disconnectPrinterService(Context context) {
        if (printerService != null) {
            context.getApplicationContext().unbindService(connService);
            printerService = null;
        }
    }

    public boolean isConnect() {
        return printerService != null;
    }

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("tryhard","aidl service is disconnected");
            printerService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("tryhard","aidl service is connected");
            printerService = ExtPrinterService.Stub.asInterface(service);
        }
    };

    public ICallback generateCB(final PrinterCallback printerCallback){
        return new ICallback.Stub(){
            @Override
            public void  onRunResult(boolean isSuccess, int code, String msg) throws RemoteException {
            }
        };
    }

    public void initPrinter() {
        if (printerService == null) {
            Toast.makeText(context,"Init",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.printerInit();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printQr(String data, int modulesize, int errorlevel) {
        if (printerService == null) {
            Toast.makeText(context,"QR",Toast.LENGTH_LONG).show();
            return;
        }

        try {
			printerService.setAlignMode(1);
            printerService.printQrCode(data, modulesize, errorlevel);
            printerService.lineWrap(1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
        if (printerService == null) {
            Toast.makeText(context,"Bar",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.printBarCode(data, symbology, height, width, textposition);
            printerService.lineWrap(1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printText(String content, int size, int alignment, boolean isBold, boolean isUnderLine) {
        if (printerService == null) {
            Toast.makeText(context,"Text",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            if (isBold) {
                printerService.sendRawData(ESCUtil.boldOn());
            } else {
                printerService.sendRawData(ESCUtil.boldOff());
            }

            if (isUnderLine) {
                printerService.sendRawData(ESCUtil.underlineWithOneDotWidthOn());
            } else {
                printerService.sendRawData(ESCUtil.underlineOff());
            }

            printerService.setAlignMode(alignment);
            printerService.setFontZoom(size, size);
            printerService.printText(content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setFontSize(int size) {
        try {
            printerService.setFontZoom(size, size);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setBold() {
        try {
            printerService.sendRawData(ESCUtil.boldOn());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unsetBold() {
        try {
            printerService.sendRawData(ESCUtil.boldOff());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setUnderline() {
        try {
            printerService.sendRawData(ESCUtil.underlineWithOneDotWidthOn());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unsetUnderline() {
        try {
            printerService.sendRawData(ESCUtil.underlineOff());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBitmap(Bitmap bitmap) {
        if (printerService == null) {
            Toast.makeText(context,"",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.setAlignMode(1);
            printerService.printBitmap(bitmap, 0);
            printerService.lineWrap(1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printTable(LinkedList<TableItem> list) {
        if (printerService == null) {
            Toast.makeText(context,"",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            for (TableItem tableItem : list) {
                printerService.setFontZoom(tableItem.getFontSize(), tableItem.getFontSize());
                if (tableItem.getIsBold()) {
                    setBold();
                }
                else {
                    unsetBold();
                }
                if (tableItem.getIsUnderline()) {
                    setUnderline();
                }
                else {
                    unsetUnderline();
                }
                printerService.printColumnsText(tableItem.getText(), tableItem.getWidth(), tableItem.getAlign());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printLines(int number){
        if (printerService == null) {
            Toast.makeText(context,"3Line",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.lineWrap(number);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRawData(byte[] data) {
        if (printerService == null) {
            Toast.makeText(context,"Raw",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.sendRawData(data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cutPaper() {
        if (printerService == null) {
            Toast.makeText(context,"Cut",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            printerService.cutPaper(0, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getPrinterStatus(){
        if(printerService == null){
            Toast.makeText(context,"Service disconnected",Toast.LENGTH_LONG).show();
            return -1;
        }

        int res;
        try {
            res = printerService.getPrinterStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
            res = -1;
        }
        return res;
    }
}

package com.example.emptyprinter.aidlutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import woyou.aidlservice.jiuiv5.ExtPrinterService;
import woyou.aidlservice.jiuiv5.ICallback;

public class AidlUtil {
    private static final String SERVICE＿PACKAGE = "woyou.aidlservice.jiuiv5";
    private static final String SERVICE＿ACTION = "woyou.aidlservice.jiuiv5.IWoyouService";

    private ExtPrinterService woyouService;
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
        if (woyouService != null) {
            context.getApplicationContext().unbindService(connService);
            woyouService = null;
        }
    }

    public boolean isConnect() {
        return woyouService != null;
    }

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("tryhard","aidl service is disconnected");
            woyouService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("tryhard","aidl service is connected");
            woyouService = ExtPrinterService.Stub.asInterface(service);
        }
    };

    public ICallback generateCB(final PrinterCallback printerCallback){
        return new ICallback.Stub(){
            @Override
            public void  onRunResult(boolean isSuccess, int code, String msg) throws RemoteException {

            }

        };
    }

    private int[] darkness = new int[]{0x0600, 0x0500, 0x0400, 0x0300, 0x0200, 0x0100, 0,
            0xffff, 0xfeff, 0xfdff, 0xfcff, 0xfbff, 0xfaff};

//
//    public void printerSelfChecking() {
//        try {
//            woyouService.printerSelfChecking(null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<String> getPrinterInfo() {
//        if (woyouService == null) {
//            Toast.makeText(context,"",Toast.LENGTH_LONG).show();
//            return null;
//        }
//
//        List<String> info = new ArrayList<>();
//        try {
//            info.add(woyouService.getPrinterSerialNo());
//            info.add(woyouService.getPrinterModal());
//            info.add(woyouService.getPrinterVersion());
//            info.add(woyouService.getPrintedLength()+"");
//            info.add("");
//            //info.add(woyouService.getServiceVersion());
//            PackageManager packageManager = context.getPackageManager();
//            try {
//                PackageInfo packageInfo = packageManager.getPackageInfo(SERVICE＿PACKAGE, 0);
//                if(packageInfo != null){
//                    info.add(packageInfo.versionName);
//                    info.add(packageInfo.versionCode+"");
//                }else{
//                    info.add("");info.add("");
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        return info;
//    }
//
//    public void initPrinter() {
//        if (woyouService == null) {
//            Toast.makeText(context,"Init",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.printerInit(null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printQr(String data, int modulesize, int errorlevel) {
//        if (woyouService == null) {
//            Toast.makeText(context,"QR",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//
//        try {
//			woyouService.setAlignment(1, null);
//            woyouService.printQRCode(data, modulesize, errorlevel, null);
//            woyouService.lineWrap(1, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
//        if (woyouService == null) {
//            Toast.makeText(context,"Bar",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//
//        try {
//            woyouService.printBarCode(data, symbology, height, width, textposition, null);
//            woyouService.lineWrap(1, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printText(String content, float size, int alignment, boolean isBold, boolean isUnderLine) {
//        if (woyouService == null) {
//            Toast.makeText(context,"Text",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            if (isBold) {
//                woyouService.sendRAWData(ESCUtil.boldOn(), null);
//            } else {
//                woyouService.sendRAWData(ESCUtil.boldOff(), null);
//            }
//
//            if (isUnderLine) {
//                woyouService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
//            } else {
//                woyouService.sendRAWData(ESCUtil.underlineOff(), null);
//            }
//
//            woyouService.setAlignment(alignment, null);
//            //woyouService.setFontName("gh", null);
//            woyouService.printTextWithFont(content, null, size, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setFontSize(int size) {
//        try {
//            woyouService.setFontSize(size, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setBold() {
//        try {
//            woyouService.sendRAWData(ESCUtil.boldOn(), null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void unsetBold() {
//        try {
//            woyouService.sendRAWData(ESCUtil.boldOff(), null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setUnderline() {
//        try {
//            woyouService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void unsetUnderline() {
//        try {
//            woyouService.sendRAWData(ESCUtil.underlineOff(), null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printBitmap(Bitmap bitmap) {
//        if (woyouService == null) {
//            Toast.makeText(context,"",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.setAlignment(1, null);
//            woyouService.printBitmap(bitmap, null);
//            woyouService.lineWrap(1, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printBitmap(Bitmap bitmap, int type) {
//        if (woyouService == null) {
//            Toast.makeText(context,"Service disconnected",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.setAlignment(1, null);
//            woyouService.printBitmapCustom(bitmap, type, null);
//            woyouService.lineWrap(1, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printTable(LinkedList<TableItem> list) {
//        if (woyouService == null) {
//            Toast.makeText(context,"",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            for (TableItem tableItem : list) {
//                woyouService.setFontSize(tableItem.getFontSize(), null);
//                if (tableItem.getIsBold()) {
//                    setBold();
//                }
//                else {
//                    unsetBold();
//                }
//                if (tableItem.getIsUnderline()) {
//                    setUnderline();
//                }
//                else {
//                    unsetUnderline();
//                }
//                woyouService.printColumnsString(tableItem.getText(), tableItem.getWidth(), tableItem.getAlign(), null);
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printLines(int number){
//        if (woyouService == null) {
//            Toast.makeText(context,"3Line",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.lineWrap(number, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendRawData(byte[] data) {
//        if (woyouService == null) {
//            Toast.makeText(context,"Raw",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.sendRAWData(data, null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void cutPaper() {
//        if (woyouService == null) {
//            Toast.makeText(context,"Cut",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            woyouService.cutPaper(null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int getPrintMode(){
//        if(woyouService == null){
//            Toast.makeText(context,"Service disconnected",Toast.LENGTH_LONG).show();
//            return -1;
//        }
//
//        int res;
//        try {
//            res =  woyouService.getPrinterMode();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            res = -1;
//        }
//        return res;
//    }
//
//    public void openCashDrawer() {
//        if(woyouService == null){
//            Toast.makeText(context,"Service disconnected",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        try {
//            //woyouService.openDrawer(null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

}

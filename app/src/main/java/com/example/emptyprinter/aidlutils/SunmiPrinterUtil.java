package com.example.emptyprinter.aidlutils;

import java.util.List;

public class SunmiPrinterUtil {
//
//    public static void printerSelfChecking() {
//        AidlUtil.getInstance().printerSelfChecking();
//    }
//
//    public static void print(String jsonArray) throws JSONException, IOException {
//        JSONArray data = new JSONArray(jsonArray);
//        for (int i=0; i < data.length(); i++) {
//            JSONObject obj = data.getJSONObject(i);
//            String printType = obj.getString("printType");
//            switch(printType) {
//                case "QRCODE": printQRCode(obj);
//                    break;
//                case "BARCODE": printBarCode(obj);
//                    break;
//                case "NEWLINE": printLines(obj);
//                    break;
//                case "IMAGE": printImage(obj);
//                    break;
//                case "TEXT": printText(obj);
//                    break;
//                case "TABLE": printTable(obj);
//                    break;
//                case "SEPARATOR": printSeparator(obj);
//                    break;
//            }
//        }
//        AidlUtil.getInstance().printLines(2);
//        AidlUtil.getInstance().cutPaper();
//    }
//
//    private static void printQRCode(JSONObject obj) throws JSONException {
//        String value = obj.getString("value");
//        AidlUtil.getInstance().printQr(value, 5, 3);
//    }
//
//    private static void printBarCode(JSONObject obj) throws JSONException {
//        String value = obj.getString("value");
//        //AidlUtil.getInstance().printBarCode(value, 5, 3);
//    }
//
//    private static void printLines(JSONObject obj) throws JSONException {
//        int value = obj.getInt("value");
//        AidlUtil.getInstance().printLines(value);
//    }
//
//    private static void printImage(JSONObject obj) throws JSONException, IOException {
//        String value = obj.getString("value");
//        URL url = new URL(value);
//        //Bitmap logo = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        //AidlUtil.getInstance().printBitmap(logo, 1);
//    }
//
//    private static void printSeparator(JSONObject obj) throws JSONException {
//        String value = obj.getString("value");
//        int count = Integer.parseInt(value);
//        String separator = "";
//        for (int i = 0; i < count; i++) {
//            separator += "─";
//        }
//        AidlUtil.getInstance().printText(separator + "\n", 30, 0, false, false);
//    }
//
//    private static void printText(JSONObject obj) throws JSONException {
//        String value = obj.getString("value");
//        Boolean isBold = false;
//        Boolean isUnderline = false;
//        int fontSize = 30;
//        int alignment = 0;
//        if (obj.has("isBold")) {
//            isBold = obj.getBoolean("isBold");
//        }
//        if (obj.has("isUnderline")) {
//            isUnderline = obj.getBoolean("isUnderline");
//        }
//        if (obj.has("fontSize")) {
//            fontSize = obj.getInt("fontSize");
//        }
//        if (obj.has("alignment")) {
//            alignment = obj.getInt("alignment");
//        }
//        AidlUtil.getInstance().printText(value + "\n", fontSize, alignment,isBold, isUnderline);
//    }
//
//    private static void printTable(JSONObject obj) throws JSONException {
//        JSONArray rows = obj.getJSONArray("rows");
//        LinkedList<TableItem> datalist = new LinkedList<>();
//
//        for (int i=0; i < rows.length(); i++) {
//            JSONObject rowObj = rows.getJSONObject(i);
//            JSONArray values = rowObj.getJSONArray("values");
//            JSONArray widths = rowObj.getJSONArray("widths");
//            JSONArray alignments = rowObj.getJSONArray("alignments");
//
//            TableItem ti = new TableItem();
//            Boolean isBold = false;
//            Boolean isUnderline = false;
//            int fontSize = 20;
//            List<String> texts = new ArrayList<>();
//            List<Integer> widthList = new ArrayList<>();
//            List<Integer> alignmentList = new ArrayList<>();
//
//            for (int j=0; j < values.length(); j++) {
//                JSONObject valueObj = values.getJSONObject(j);
//                if (valueObj.has("isBold")) {
//                    ti.setIsBold(valueObj.getBoolean("isBold"));
//                }
//                if (valueObj.has("isUnderline")) {
//                    ti.setIsUnderline(valueObj.getBoolean("isUnderline"));
//                }
//                if (valueObj.has("fontSize")) {
//                    ti.setFontSize(valueObj.getInt("fontSize"));
//                }
//                texts.add(valueObj.getString("value"));
//            }
//
//            for (int j=0; j < widths.length(); j++) {
//                widthList.add(widths.getInt(j));
//            }
//
//            for (int j=0; j < alignments.length(); j++) {
//                alignmentList.add(alignments.getInt(j));
//            }
//
//            ti.setText(texts.toArray(new String[texts.size()]));
//            ti.setWidth(toIntArray(widthList));
//            ti.setAlign(toIntArray(alignmentList));
//            datalist.add(ti);
//        }
//        AidlUtil.getInstance().printTable(datalist);
//    }

    private static int[] toIntArray(List<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e;
        return ret;
    }
}

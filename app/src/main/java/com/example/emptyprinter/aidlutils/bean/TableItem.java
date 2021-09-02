package com.example.emptyprinter.aidlutils.bean;

public class TableItem {
    private String[] text;
    private int[] width;
    private int[] align;
    private boolean isBold;
    private boolean isUnderline;
    private int fontSize;

    public TableItem() {
        text = new String[]{"test","test","test"};
        width = new int[]{1,1,1};
        align = new int[]{0,0,0};
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public int[] getWidth() {
        return width;
    }

    public void setWidth(int[] width) {
        this.width = width;
    }

    public int[] getAlign() {
        return align;
    }

    public void setAlign(int[] align) {
        this.align = align;
    }

    public boolean getIsBold() {
        return isBold;
    }

    public void setIsBold(boolean isBold) {
        this.isBold = isBold;
    }

    public boolean getIsUnderline() {
        return isUnderline;
    }

    public void setIsUnderline(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}

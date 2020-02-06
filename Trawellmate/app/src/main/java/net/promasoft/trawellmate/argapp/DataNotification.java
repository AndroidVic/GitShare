package net.promasoft.trawellmate.argapp;

public class DataNotification {
private String destinatn, packPrice,packTitle,packdates,notfctnTime;
    public DataNotification(String packageDest, String packageAmt, String packageName, String packdates, String notfctnTime) {
        this.destinatn = packageDest;
        this.packPrice = packageAmt;
        this.packTitle = packageName;
        this.packdates = packdates;
        this.notfctnTime = notfctnTime;
    }

    public String getDestinatn() {
        return destinatn;
    }

    public void setDestinatn(String destinatn) {
        this.destinatn = destinatn;
    }

    public String getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(String packPrice) {
        this.packPrice = packPrice;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

    public String getPackdates() {
        return packdates;
    }

    public void setPackdates(String packdates) {
        this.packdates = packdates;
    }

    public String getNotfctnTime() {
        return notfctnTime;
    }

    public void setNotfctnTime(String notfctnTime) {
        this.notfctnTime = notfctnTime;
    }
}

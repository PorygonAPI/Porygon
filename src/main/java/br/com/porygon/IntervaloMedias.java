package br.com.porygon;

public class IntervaloMedias {
    private String intervaloTexto;
    private double tempInsMedia;
    private double pressaoInsMedia;
    private double umiInsMedia;
    private double ptoOrvalhoInsMedia;

    public String getIntervaloTexto() {
        return intervaloTexto;
    }
    public void setIntervaloTexto(String intervaloTexto) {
        this.intervaloTexto = intervaloTexto;
    }
    
    public double getTempInsMedia() {
        return tempInsMedia;
    }
    public void setTempInsMedia(double tempInsMedia) {
        this.tempInsMedia = tempInsMedia;
    }

    public double getUmiInsMedia() {
        return umiInsMedia;
    }
    public void setUmiInsMedia(double umiInsMedia) {
        this.umiInsMedia = umiInsMedia;
    }

    public double getPtoOrvalhoInsMedia() {
        return ptoOrvalhoInsMedia;
    }
    public void setPtoOrvalhoInsMedia(double ptoOrvalhoInsMedia) {
        this.ptoOrvalhoInsMedia = ptoOrvalhoInsMedia;
    }

    public double getPressaoInsMedia() {
        return pressaoInsMedia;
    }
    public void setPressaoInsMedia(double pressaoInsMedia) {
        this.pressaoInsMedia = pressaoInsMedia;
    }

    public IntervaloMedias(String intervaloTexto, double tempInsMedia, double umiInsMedia, double ptoOrvalhoInsMedia, double pressaoInsMedia) {
        this.intervaloTexto = intervaloTexto;
        this.tempInsMedia = tempInsMedia;
        this.umiInsMedia = umiInsMedia;
        this.ptoOrvalhoInsMedia = ptoOrvalhoInsMedia;
        this.pressaoInsMedia = pressaoInsMedia;
    }
}


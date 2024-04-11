package br.com.porygon;

public class Registro {
    private String data;
    private String hora;
    private double velVento;
    private  double temperatura;

    public double getTemperatura(){
        return temperatura;
    }

    public Registro(String data, String hora, double velVento, double temperatura) {
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.temperatura = temperatura;
    }
}



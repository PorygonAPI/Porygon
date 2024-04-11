package br.com.porygon;

public class RegistroAutomatico extends Registro{
    public RegistroAutomatico(String data, String hora, double velVento, double temperatura) {
        super(data, hora, velVento, temperatura);
        temperatura = temperatura - 273.15;
    }
}
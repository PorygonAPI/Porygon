package br.com.porygon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroAutomatico extends Registro{
    public RegistroAutomatico(String data, String hora, double velVento, double temperatura) {
        super(data, hora, velVento, temperatura);
    }

//    public double converterEscala() {
//        // Obtém a temperatura da instância atual de RegistroAutomatico
//        double temperaturaCelsius = getTemperatura() - 273.15;
//        DecimalFormat df = new DecimalFormat("#.##");
//        System.out.println("Temperatura registrada em Kelvin: " + df.format(getTemperatura()));
//        System.out.println("Temperatura Convertida para Celsius: " + df.format(temperaturaCelsius));
//        return temperaturaCelsius;
//    }

//    public static void main(String[] args) {
//        // Criar uma instância da classe RegistroAutomatico
//        RegistroAutomatico registroAuto = new RegistroAutomatico("01/01/2024", "12:00", 10.5, 293.75);
//
//        // Chamar o método converterEscala
//        double temperaturaConvertida = registroAuto.converterEscala();
//
//    }

}
package br.com.porygon;

import java.time.LocalDate;

public class Registro {
    private LocalDate data;
    private String hora;

    private String cidade;

    private Double temperatura;

    private Double velVento;

    private Double dirVento;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getVelVento() {
        return velVento;
    }

    public void setVelVento(Double velVento) {
        this.velVento = velVento;
    }

    public Double getDirVento() {
        return dirVento;
    }

    public void setDirVento(Double dirVento) {
        this.dirVento = dirVento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Registro(String cidade, LocalDate data, String hora, Double velVento, Double dirVento) {
        this.cidade = cidade;
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
    }
}



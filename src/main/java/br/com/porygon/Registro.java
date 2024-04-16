package br.com.porygon;

public class Registro {
    private String data;
    private String hora;

    private Double temperatura;

    private Double velVento;

    private Double dirVento;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public Registro(String data, String hora, Double velVento, Double dirVento) {
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
    }
}



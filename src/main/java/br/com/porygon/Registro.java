package br.com.porygon;

public class Registro {
    private String data;
    private String hora;
    private Double temp;
    private Double velVento;
    private Double dirVento;
    private Double umidade;
    private Double pressao;
    private Double nebulosidade;
    private Double insolacao;
    private Double chuva;

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
        return temp;
    }

    public void setTemperatura(Double temp) {
        this.temp = temp;
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

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Double getPressao() {
        return pressao;
    }

    public void setPressao(Double pressao) {
        this.pressao = pressao;
    }

    public Double getNebulosidade() {
        return nebulosidade;
    }

    public void setNebulosidade(Double nebulosidade) {
        this.nebulosidade = nebulosidade;
    }

    public Double getInsolacao() {
        return insolacao;
    }

    public void setInsolacao(Double insolacao) {
        this.insolacao = insolacao;
    }

    public Double getChuva() {
        return chuva;
    }

    public void setChuva(Double chuva) {
        this.chuva = chuva;
    }


    public Registro(String data, String hora, Double velVento, Double dirVento) {
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
    }

    public Registro(String data, String hora, Double velVento, Double dirVento, Double temp, Double tempMax,
            Double tempMin, Double umiIns, Double umiMax, Double umiMin, Double pressao, Double pressaoMax,
            Double pressaoMin, Double rajVento, Double radiacao, Double chuva) {
        //TODO Auto-generated constructor stub
    }

    public Registro(String data, String hora, Double velVento, Double dirVento, Double temp, Double umi,
            Double pressao, Double nebulosidade, Double insolacao, Double tempMax, Double tempMin, Double chuva) {
        //TODO Auto-generated constructor stub
    }
}



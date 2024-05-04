package br.com.porygon;

public class Registro {
    private String data;
    private String hora;
    private String cidade;
    private Double temp;
    private Double velVento;
    private Double dirVento;
    private Double tempMax;
    private Double tempMin;
    private Double umi;
    private Double umiMax;
    private Double umiMin;
    private Double ptoOrvalhoIns;
    private Double ptoOrvalhoMax;
    private Double ptoOrvalhoMin;
    private Double pressao;
    private Double pressaoMax;
    private Double pressaoMin;
    private Double rajVento;
    private Double radiacao;
    private Double chuva;
    private Double nebulosidade;
    private Double insolacao;

    public Double getPressaoMax() {return pressaoMax;}
    public void setPressaoMax(Double pressaoMax) {this.pressaoMax = pressaoMax;}
    public Double getPressaoMin() {return pressaoMin;}
    public void setPressaoMin(Double pressaoMin) {this.pressaoMin = pressaoMin;}
    public Double getRajVento() {return rajVento;}
    public void setRajVento(Double rajVento) {this.rajVento = rajVento;}
    public Double getRadiacao() {return radiacao;}
    public void setRadiacao(Double radiacao) {this.radiacao = radiacao;}
    public Double getChuva() {return chuva;}
    public void setChuva(Double chuva) {this.chuva = chuva;}
    public Double getNebulosidade() {return nebulosidade;}
    public void setNebulosidade(Double nebulosidade) {this.nebulosidade = nebulosidade;}
    public Double getInsolacao() {return insolacao;}
    public void setInsolacao(Double insolacao) {this.insolacao = insolacao;}
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}
    public String getHora() {return hora;}
    public void setHora(String hora) {this.hora = hora;}
    public Double getTemperatura() {return temp;}
    public void setTemperatura(Double temp) {this.temp = temp;}
    public Double getVelVento() {return velVento;}
    public void setVelVento(Double velVento) {this.velVento = velVento;}
    public Double getDirVento() {return dirVento;}
    public void setDirVento(Double dirVento) {this.dirVento = dirVento;}
    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}
    public Double getTempMax() {return tempMax;}
    public void setTempMax(Double tempMax) {this.tempMax = tempMax;}
    public Double getTempMin() {return tempMin;}
    public void setTempMin(Double tempMin) {this.tempMin = tempMin;}
    public Double getUmiMax() {return umiMax;}
    public void setUmiMax(Double umiMax) {this.umiMax = umiMax;}
    public Double getUmiMin() {return umiMin;}
    public void setUmiMin(Double umiMin) {this.umiMin = umiMin;}
    public Double getPtoOrvalhoIns() {return ptoOrvalhoIns;}
    public void setPtoOrvalhoIns(Double ptoOrvalhoIns) {this.ptoOrvalhoIns = ptoOrvalhoIns;}
    public Double getPtoOrvalhoMax() {return ptoOrvalhoMax;}
    public void setPtoOrvalhoMax(Double ptoOrvalhoMax) {this.ptoOrvalhoMax = ptoOrvalhoMax;}
    public Double getPtoOrvalhoMin() {return ptoOrvalhoMin;}
    public void setPtoOrvalhoMin(Double ptoOrvalhoMin) {this.ptoOrvalhoMin = ptoOrvalhoMin;}
    public Double getPressao() {return pressao;}
    public void setPressao(Double pressao) {this.pressao = pressao;}
    public Double getUmi() {return umi;}
    public void setUmi(Double umi) {this.umi = umi;}

    public Double converterEscala(Double temp) {
        // Obtém a temperatura da instância atual de RegistroAutomatico
        if(temp != null){
            double temperaturaCelsius = temp - 273.15;
            return temperaturaCelsius;
        }
        return null;
    }

    public Registro(String cidade, String data, String hora, Double velVento, Double dirVento) {
        this.cidade = cidade;
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
    }

    public Registro(String cidade, String data, String hora, Double velVento, Double dirVento, Double temp,
            Double tempMax, Double tempMin, Double umi, Double pressao, Double chuva, Double umiMax, Double umiMin, Double ptoOrvalhoIns,
            Double ptoOrvalhoMax, Double ptoOrvalhoMin, Double pressaoMax, Double pressaoMin,
            Double rajVento, Double radiacao) {
        this.cidade = cidade;
        this.data = data; 
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umi = umi;
        this.pressao = pressao;
        this.chuva = chuva;
        this.umiMax = umiMax;
        this.umiMin = umiMin;
        this.ptoOrvalhoIns = ptoOrvalhoIns;
        this.ptoOrvalhoMax = ptoOrvalhoMax;
        this.ptoOrvalhoMin = ptoOrvalhoMin;
        this.pressaoMax = pressaoMax;
        this.pressaoMin = pressaoMin;
        this.rajVento = rajVento;
        this.radiacao = radiacao;
        setTemperatura(temp);
    }

    public Registro(String cidade, String data, String hora, Double velVento, Double dirVento, Double temp, Double tempMax, Double tempMin, Double umi,
            Double pressao, Double chuva, Double nebulosidade, Double insolacao) {
        this.cidade = cidade; 
        this.data = data;
        this.hora = hora;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umi = umi;
        this.pressao = pressao;
        this.chuva = chuva;
        this.nebulosidade = nebulosidade;
        this.insolacao = insolacao;
        setTemperatura(converterEscala(temp));
        }
}
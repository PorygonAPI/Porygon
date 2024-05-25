package br.com.porygon;

import br.com.porygon.dao.RegistroDAO;

import java.time.LocalDate;

public class RegistroAutomatico extends Registro {
    private Double tempIns;
    private Double tempMax;
    private Double tempMin;
    private Double umiIns;
    private Double umiMax;
    private Double umiMin;
    private Double ptoOrvalhoIns;
    private Double ptoOrvalhoMax;
    private Double ptoOrvalhoMin;
    private Double pressaoIns;
    private Double pressaoMax;
    private Double pressaoMin;
    private Double rajVento;
    private Double radiacao;
    private Double chuva;

    public Double getTempIns() {
        return tempIns;
    }

    public void setTempIns(Double tempIns) {
        this.tempIns = tempIns;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getUmiIns() {
        return umiIns;
    }

    public void setUmiIns(double umiIns) {
        this.umiIns = umiIns;
    }

    public Double getUmiMax() {
        return umiMax;
    }

    public void setUmiMax(Double umiMax) {
        this.umiMax = umiMax;
    }

    public Double getUmiMin() {
        return umiMin;
    }

    public void setUmiMin(Double umiMin) {
        this.umiMin = umiMin;
    }

    public Double getPtoOrvalhoIns() {
        return ptoOrvalhoIns;
    }

    public void setPtoOrvalhoIns(Double ptoOrvalhoIns) {
        this.ptoOrvalhoIns = ptoOrvalhoIns;
    }

    public Double getPtoOrvalhoMax() {
        return ptoOrvalhoMax;
    }

    public void setPtoOrvalhoMax(Double ptoOrvalhoMax) {
        this.ptoOrvalhoMax = ptoOrvalhoMax;
    }

    public Double getPtoOrvalhoMin() {
        return ptoOrvalhoMin;
    }

    public void setPtoOrvalhoMin(Double ptoOrvalhoMin) {
        this.ptoOrvalhoMin = ptoOrvalhoMin;
    }

    public Double getPressaoIns() {
        return pressaoIns;
    }

    public void setPressaoIns(Double pressaoIns) {
        this.pressaoIns = pressaoIns;
    }

    public Double getPressaoMax() {
        return pressaoMax;
    }

    public void setPressaoMax(Double pressaoMax) {
        this.pressaoMax = pressaoMax;
    }

    public Double getPressaoMin() {
        return pressaoMin;
    }

    public void setPressaoMin(Double pressaoMin) {
        this.pressaoMin = pressaoMin;
    }

    public Double getRajVento() {
        return rajVento;
    }

    public void setRajVento(Double rajVento) {
        this.rajVento = rajVento;
    }

    public Double getRadiacao() {
        return radiacao;
    }

    public void setRadiacao(Double radiacao) {
        this.radiacao = radiacao;
    }

    public Double getChuva() {
        return chuva;
    }

    public void setChuva(Double chuva) {
        this.chuva = chuva;
    }

    public RegistroAutomatico(String cidade, LocalDate data, String hora, Double velVento, Double dirVento, Double tempIns,
            Double tempMax, Double tempMin, Double umiIns, Double umiMax, Double umiMin, Double ptoOrvalhoIns,
            Double ptoOrvalhoMax, Double ptoOrvalhoMin, Double pressaoIns, Double pressaoMax, Double pressaoMin,
            Double rajVento, Double radiacao, Double chuva) {
        super(cidade, data, hora, velVento, dirVento);
        this.tempIns = tempIns;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umiIns = umiIns;
        this.umiMax = umiMax;
        this.umiMin = umiMin;
        this.ptoOrvalhoIns = ptoOrvalhoIns;
        this.ptoOrvalhoMax = ptoOrvalhoMax;
        this.ptoOrvalhoMin = ptoOrvalhoMin;
        this.pressaoIns = pressaoIns;
        this.pressaoMax = pressaoMax;
        this.pressaoMin = pressaoMin;
        this.rajVento = rajVento;
        this.radiacao = radiacao;
        this.chuva = chuva;
        setTemperatura(tempIns);
    }

    private void verifyIfIsNotNullAndSave(Double value, String name, int regId, RegistroDAO registroDAO) {
        if(value != null){
            registroDAO.salvarInformacao(regId, name, value);
        }
    }

    public void salvarRegistro(RegistroDAO registroDAO, int regId) {

        verifyIfIsNotNullAndSave(tempIns, "tempIns", regId, registroDAO);

        verifyIfIsNotNullAndSave(umiIns, "umiIns", regId, registroDAO);

        verifyIfIsNotNullAndSave(ptoOrvalhoIns, "ptoOrvalhoIns", regId, registroDAO);

        verifyIfIsNotNullAndSave(pressaoIns, "pressaoIns", regId, registroDAO);

        verifyIfIsNotNullAndSave(getVelVento(), "velVento", regId, registroDAO);

        verifyIfIsNotNullAndSave(getDirVento(), "dirVento", regId, registroDAO);

        verifyIfIsNotNullAndSave(rajVento, "rajVento", regId, registroDAO);

        verifyIfIsNotNullAndSave(radiacao, "radiacao", regId, registroDAO);

        verifyIfIsNotNullAndSave(chuva, "chuva", regId, registroDAO);
    }
}

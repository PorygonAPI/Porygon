package br.com.porygon;

import br.com.porygon.dao.RegistroDAO;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class Arquivo {
    private File conteudo;

    public File getConteudo() {
        return conteudo;
    }

    public void setConteudo(File conteudo) {
        this.conteudo = conteudo;
    }


    // Lê o nome do arquivo
    // public void tratar() {
    //    try {
    //     String nomeArquivo = conteudo.getName();
    //     // Extrair informações do nome do arquivo
    //     String[] nomePartes = nomeArquivo.split("_");
    //     String nome = nomePartes[0];
    //     String cidade = nomePartes[1];
    //     String estacao = nomePartes[2].substring(0, nomePartes[2].indexOf(".")); // Remove a extensão .csv

    //     // Criar um objeto Registro com os dados extraídos
    //     Registro registro = new Registro();
    //     registro.setNome(nome);
    //     registro.setCidade(cidade);
    //     registro.setEstacao(estacao);

    //     // Salvar no banco de dados
    //     ArquivoDAO arquivoDAO = new ArquivoDAO();
    //     arquivoDAO.salvarArquivo(registro);
    // } catch (IOException e) {
    //     e.printStackTrace();
    // }


    public static Timestamp convertToTimestamp(String dateStr, String timeStr) {
        // Definindo os formatos de data e hora
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        // Convertendo as strings para LocalDate e LocalTime
        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
        LocalTime time = LocalTime.parse(timeStr, timeFormatter);

        // Combinando LocalDate e LocalTime em LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // Convertendo LocalDateTime para Timestamp
        return Timestamp.valueOf(dateTime);
    }
    

    public void tratar(int arquivoId) {
        String[] fileNamePart = this.conteudo.getName().split(".csv")[0].split("_");
        String cidade = fileNamePart[1];
        RegistroDAO registroDAO = new RegistroDAO();
        if (fileNamePart[0].contains("A")) {
            try {
                try (BufferedReader br = new BufferedReader(new FileReader(this.conteudo.getPath()))) {
                    String line = br.readLine();
                    line = br.readLine();
                    while (line != null) {
                        String[] split = line.split(";");
                        int regId = registroDAO.salvarRegistro(convertToTimestamp(split[0].replace("\"", ""), split[1].replace("\"", "")), arquivoId, "Automático");

                        LocalDate data = null;
                        String hora = null;
                        Double tempIns = null;
                        Double tempMax = null;
                        Double tempMin = null;
                        Double umiIns = null;
                        Double umiMax = null;
                        Double umiMin = null;
                        Double ptoOrvalhoIns = null;
                        Double ptoOrvalhoMax = null;
                        Double ptoOrvalhoMin = null;
                        Double pressaoIns = null;
                        Double pressaoMax = null;
                        Double pressaoMin = null;
                        Double velVento = null;
                        Double dirVento = null;
                        Double rajVento = null;
                        Double radiacao = null;
                        Double chuva = null;

                        for (int i = 0; i < split.length; i++) {
                            // Remove as aspas duplas, se existirem, antes de fazer a conversão
                            split[i] = split[i].replace("\"", "");

                            if (!split[i].isEmpty()) {
                                switch (i) {
                                    case 0:
                                        String dateString = split[i];
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                        LocalDate date = LocalDate.parse(dateString, formatter);
                                        data = date;
                                        break;
                                    case 1:
                                        hora = split[i];
                                        break;
                                    case 2:
                                        tempIns = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 3:
                                        tempMax = Double.parseDouble(split[i].replace(",", "."));

                                        break;
                                    case 4:
                                        tempMin = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 5:
                                        umiIns = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 6:
                                        umiMax = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 7:
                                        umiMin = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 8:
                                        ptoOrvalhoIns = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 9:
                                        ptoOrvalhoMax = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 10:
                                        ptoOrvalhoMin = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 11:
                                        pressaoIns = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 12:
                                        pressaoMax = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 13:
                                        pressaoMin = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 14:
                                        velVento = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 15:
                                        dirVento = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 16:
                                        rajVento = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 17:
                                        radiacao = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 18:
                                        chuva = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                }
                            }

                        }

                        RegistroAutomatico regAutomatico = new RegistroAutomatico(cidade, data, hora,
                                velVento, dirVento, tempIns, tempMax, tempMin, umiIns, umiMax, umiMin,
                                ptoOrvalhoIns, ptoOrvalhoMax, ptoOrvalhoMin, pressaoIns, pressaoMax,
                                pressaoMin, rajVento, radiacao, chuva);
//                        registros.add(regAutomatico);
                        regAutomatico.salvarRegistro(registroDAO, regId);
                        line = br.readLine();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Aqui será salvo os registros AUTOMATICOS
        } else {
            System.out.println("Arquivo manual: - " + fileNamePart[0]);
            // Aqui será salvo os registros MANUAIS
            RegistroDAO registroMDAO = new RegistroDAO();


            try {
                try (BufferedReader br = new BufferedReader(new FileReader(this.conteudo.getPath()))) {
                    String line = br.readLine();
                    line = br.readLine();
                    while (line != null) {
                        String[] split = line.split(";");
                        int regMId = registroMDAO.salvarRegistro(convertToTimestamp(split[0].replace("\"", ""), split[1].replace("\"", "")), arquivoId, "Manual");

                        LocalDate data = null;
                        String hora = null;
                        Double temp = null;
                        Double umi = null;
                        Double pressao = null;
                        Double velVento = null;
                        Double dirVento = null;
                        Double nebulosidade = null;
                        Double insolacao = null;
                        Double tempMax = null;
                        Double tempMin = null;
                        Double chuva = null;

                        for (int i = 0; i < split.length; i++) {
                            if (!split[i].isEmpty()) {
                                switch (i) {
                                    case 0:
                                        String dateString = split[i];
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                        LocalDate date = LocalDate.parse(dateString, formatter);
                                        data = date;
                                        break;
                                    case 1:
                                        hora = split[i];
                                        break;
                                    case 2:
                                        temp = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 3:
                                        umi = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 4:
                                        pressao = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 5:
                                        velVento = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 6:
                                        dirVento = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 7:
                                        nebulosidade = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 8:
                                        insolacao = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 9:
                                        tempMax = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 10:
                                        tempMin = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                    case 11:
                                        chuva = Double.parseDouble(split[i].replace(",", "."));
                                        break;
                                }
                            }

                        }

                        RegistroManual regManual = new RegistroManual(cidade, data, hora, velVento, dirVento, temp, umi,
                                pressao,
                                nebulosidade, insolacao, tempMax, tempMin, chuva);

                                regManual.salvarRegistro(registroMDAO, regMId);
//                        registros.add(regManual);
                        line = br.readLine();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

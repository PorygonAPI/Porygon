package br.com.porygon;

import java.io.*;
import java.util.List;

public class Arquivo {
    private File conteudo;

    public File getConteudo() {
        return conteudo;
    }

    public void setConteudo(File conteudo) {
        this.conteudo = conteudo;
    }

    public void tratar(List<Registro> registros) {
        System.out.println("Estou tratando o arquivo - " + this.conteudo.getName());
        String[] fileNamePart = this.conteudo.getName().split(".csv")[0].split("_");
        if (fileNamePart[0].indexOf("A") >= 0) {
            System.out.println("Arquivo automtico: - " + fileNamePart[0]);
            try {
                try (BufferedReader br = new BufferedReader(new FileReader(this.conteudo.getPath()))) {
                    String line = br.readLine();
                    line = br.readLine();
                    while (line != null) {
                        String[] split = line.split(";");
                        String data = null;
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
                            if (!split[i].isEmpty()) {
                                switch (i) {
                                    case 0:
                                        data = split[i];
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

                        RegistroAutomatico regAutomatico = new RegistroAutomatico(data, hora,
                                velVento, dirVento, tempIns, tempMax, tempMin, umiIns, umiMax, umiMin,
                                ptoOrvalhoIns, ptoOrvalhoMax, ptoOrvalhoMin, pressaoIns, pressaoMax,
                                pressaoMin, rajVento, radiacao, chuva);
                        registros.add(regAutomatico);
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

            try {
                try (BufferedReader br = new BufferedReader(new FileReader(this.conteudo.getPath()))) {
                    String line = br.readLine();
                    line = br.readLine();
                    while (line != null) {
                        String[] split = line.split(";");
                        String data = null;
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
                                        data = split[i];
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

                        RegistroManual regManual = new RegistroManual(data, hora, velVento, dirVento, temp, umi,
                                pressao,
                                nebulosidade, insolacao, tempMax, tempMin, chuva);
                        registros.add(regManual);
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
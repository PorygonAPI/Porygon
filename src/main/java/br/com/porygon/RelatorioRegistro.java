package br.com.porygon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RelatorioRegistro {

    // Cria o caminho do arquivo
    private static String nomeArquivo = "C:\\Users\\Renato Mendes\\Documents\\clima\\RelatorioRegistro.csv";
    private static double temp;

    // Modulo para adiconar os registros ao arquivo CSV

    public static void BaixarRelatorioA(RegistroAutomatico registroa){
        try{
            //Verificar se o aquivo já existe
            boolean arquivoExiste = new File(nomeArquivo).exists();
            //Abre o escritor para adicionar dados ao arquivo
            FileWriter escritor = new FileWriter(nomeArquivo,StandardCharsets.ISO_8859_1, true);
                if(!arquivoExiste){
                    escritor.write("data;hora;velVento;dirVento;tempIns;tempMax;tempMin;umiIns;umiMax;umiMin;ptoOrvalhoIns;ptoOrvalhoMax;ptoOrvalhoMin;pressaoIns;pressaoMax;pressaoMin;rajVento;radiacao;chuva\n");
                }
                // Tira a media das variaveis

                //Escreve os dados no formato correto
                escritor.write(registroa.getData() + ";" + registroa.getHora() + ";" + registroa.getVelVento() + ";" + registroa.getDirVento() + ";" + registroa.getTempIns() + ";" + registroa.getTempMax() + ";" + registroa.getTempMin() + ";" + registroa.getUmiIns() + ";" + registroa.getUmiMax() + ";" + registroa.getUmiMin() + ";" + registroa.getPtoOrvalhoIns() + ";" + registroa.getPtoOrvalhoMax() + ";" + registroa.getPtoOrvalhoMin() + ";" + registroa.getPressaoIns() + ";" + registroa.getPressaoMax() + ";" + registroa.getPressaoMin() + ";" + registroa.getRajVento() + ";" + registroa.getRadiacao() + ";" + registroa.getChuva() + "\n");
                //Escreve todos os dados do Buffer no arquivo
                escritor.flush();
                //Fecha o recurso de escrita
                escritor.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void BaixarRelatorioM(RegistroManual registrom){
        try{
            //Verificar se o aquivo já existe
            boolean arquivoExiste = new File(nomeArquivo).exists();
            //Abre o escritor para adicionar dados ao arquivo
            FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, true);
            if(!arquivoExiste){
                escritor.write("data; hora; velVento; dirVento; temp; umi; pressao; nebulosidade; insolacao; tempMax; tempMin; chuva\n");
            }
            //Escreve os dados no formato correto
            escritor.write(registrom.getData() + ";" + registrom.getHora() + ";" + registrom.getVelVento() + ";" + registrom.getDirVento() + ";" + registrom.getTemp() + ";" + registrom.getUmi() + ";" + registrom.getPressao() + ";" + registrom.getNebulosidade() + ";" + registrom.getInsolacao() + ";" + registrom.getTempMax() + ";" + registrom.getTempMin() + ";" + registrom.getChuva() +"\n");
            //Escreve todos os dados do Buffer no arquivo
            escritor.flush();
            //Fecha o recurso de escrita
            escritor.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}

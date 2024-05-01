package br.com.porygon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RelatorioRegistro {

    // Cria o caminho do arquivo
    private static String nomeArquivo = "C:\\Users\\Renato Mendes\\Documents\\clima\\RelatorioRegistro.csv";
    private static double temp;

    // Modulo para adiconar os registros ao arquivo CSV

    public static void baixarRelatorioA(List<Registro> registros){
        try{
            //Verificar se o aquivo já existe
            boolean arquivoExiste = new File(nomeArquivo).exists();
            //Abre o escritor para adicionar dados ao arquivo
            FileWriter escritor = new FileWriter(nomeArquivo,StandardCharsets.ISO_8859_1, true);
                if(!arquivoExiste){
                    escritor.write("data;hora;velVento;dirVento;tempIns;umiIns;ptoOrvalhoIns;pressaoIns;rajVento;radiacao;chuva\n");
                }
                // Tira a media das variaveis
                double somaTemperaturas = 0.0;
                int quantidadeRegistrosTemperatura = 0;

                for (Registro registro : registros) {
                    if (registro instanceof RegistroAutomatico registroa) {
                        // Alterar visualização de lista
                        if(registroa.getTemperatura() != null){
                            somaTemperaturas += registroa.getTemperatura();
                            quantidadeRegistrosTemperatura++;
                        }

                    }
                }
                
//                escritor.write(registroa.getData() + ";" + registroa.getHora() + ";" + registroa.getVelVento() + ";" + registroa.getDirVento() + ";" + registroa.getTemperatura() + ";" + registroa.getUmiIns() + ";" + registroa.getPtoOrvalhoIns() + ";" + registroa.getPressaoIns() + ";" + registroa.getRajVento() + ";" + registroa.getRadiacao() + ";" + registroa.getChuva() + "\n");
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

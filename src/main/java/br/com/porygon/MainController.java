package br.com.porygon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;

public class MainController {

    // Atributos
    @FXML
    private Label selectedFilesLabel; // Mensagem do caminho do arquivo printado na tela

    @FXML
    private Label tempLabel; // Mensagem da temperatura máxima e mínima printada na tela após o input do
    // pesquisador

    @FXML
    private TextField tempMaxField; // Campo de input da temperatura máxima

    @FXML
    private TextField tempMinField; // Campo de input da temperatura mínima

    private double tempMaxima; // Atributo da temperatura máxima informada pelo pesquisador
    private double tempMinima; // Atributo da temperatura máxima informada pelo pesquisador

    @FXML
    private ListView<String> listViewApurado; // Atributo para visualizar a lista de registros apurados na tela

    @FXML
    private ListView<String> listViewSuspeito; // Atributo para visualizar a lista de registros apurados na tela

    @FXML
    private ListView<String> listViewRelatorio;

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do
    // arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do
    // método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método
    // verificarRegistros
    List<Registro> Relatorio = new ArrayList<Registro>();

    // Tira a media das variaveis A
    //Temperatura
    double somaTemp = 0.0; int quantidadeRegistrosTemp = 0; double mediaTemp = 0.0;
    //Umidade
    double somaUmi = 0.0; int quantidadeRegistrosUmi = 0; double mediaUmi = 0.0;
    //Pto Orvalho
    double somaPtoOrvalho = 0.0; int quantidadeRegistrosPtoOrvalho = 0; double mediaPtoOrvalho = 0.0;
    //Pressao
    double somaPressao = 0.0; int quantidadeRegistrosPressao = 0; double mediaPressao = 0.0;
    //Vel Vento
    double somaVelVento = 0.0; int quantidadeRegistrosVelVento = 0; double mediaVelVento = 0.0;
    //Dir Vento
    double somaDirVento = 0.0; int quantidadeRegistrosDirVento = 0; double mediaDirVento = 0.0;
    //Raj Vento
    double somaRajVento = 0.0; int quantidadeRegistrosRajVento = 0; double mediaRajVento = 0.0;
    //Radiacao
    double somaRadiacao = 0.0; int quantidadeRegistrosRadiacao = 0; double mediaRadiacao = 0.0;
    //Chuva
    double somaChuva = 0.0; int quantidadeRegistrosChuva = 0; double mediaChuva = 0.0;

    // Tira a media das variaveis A
    //Temperatura
    double somaTempM = 0.0; int quantidadeRegistrosTempM = 0; double mediaTempM = 0.0;
    //Umidade
    double somaUmiM = 0.0; int quantidadeRegistrosUmiM = 0; double mediaUmiM = 0.0;
    //Pressao
    double somaPressaoM = 0.0; int quantidadeRegistrosPressaoM = 0; double mediaPressaoM = 0.0;
    //Vel Vento
    double somaVelVentoM = 0.0; int quantidadeRegistrosVelVentoM = 0; double mediaVelVentoM = 0.0;
    //Dir Vento
    double somaDirVentoM = 0.0; int quantidadeRegistrosDirVentoM = 0; double mediaDirVentoM = 0.0;
    //Raj Vento
    double somaNebulosidadeM = 0.0; int quantidadeRegistrosNebulosidadeM = 0; double mediaNebulosidadeM = 0.0;
    //Radiacao
    double somaInsolacaoM = 0.0; int quantidadeRegistrosInsolacaoM = 0; double mediaInsolacaoM = 0.0;
    //Chuva
    double somaChuvaM = 0.0; int quantidadeRegistrosChuvaM = 0; double mediaChuvaM = 0.0;

    // Métodos de acesso
    public double getTempMaxima() {
        return tempMaxima;
    }

    public double getTempMinima() {
        return tempMinima;
    }

    public String stringify(Double dado){
        if(dado == null){
            return null;
        }
        return String.format("%.2f", dado);
    }

    // Método para colher informações das listas geradas no método
    // verificarRegistros e vincular com o fxml
    public void visualizarListas() {
        listViewApurado.getItems().clear();
        for (Registro registro : dadoApurado) {
            String listViewText;
            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                listViewText = "Automático: " + regAut.getData() + " | " + regAut.getHora() + " | " + stringify(regAut.getTemperatura()) + " | " + stringify(regAut.getUmiIns()) + " | " + stringify(regAut.getPtoOrvalhoIns()) + " | " + stringify(regAut.getPressaoIns()) + " | " + stringify(regAut.getVelVento()) + " | " + stringify(regAut.getDirVento()) + " | " + stringify(regAut.getRajVento()) + " | " + stringify(regAut.getRadiacao()) + " | " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;
                listViewText = "Manual: " + regManual.getData() + " | " + regManual.getHora() + " | " + stringify(regManual.getTemperatura()) + " | " + stringify(regManual.getUmi()) + " | " + stringify(regManual.getPressao()) + " | " + stringify(regManual.getVelVento()) + " | " + stringify(regManual.getDirVento()) + " | " + stringify(regManual.getNebulosidade()) + " | " + stringify(regManual.getInsolacao()) + " | " + stringify(regManual.getTempMax()) + " | " + stringify(regManual.getTempMin()) + " | " + stringify(regManual.getChuva());
            }
            listViewApurado.getItems().add(listViewText);
        }
        listViewSuspeito.getItems().clear();
        for (Registro registro : dadoSuspeito) {
            String listViewText;

            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                listViewText = "Automático: " + regAut.getData() + " | " + regAut.getHora() + " | " + stringify(regAut.getTemperatura()) + " | " + stringify(regAut.getUmiIns()) + " | " + stringify(regAut.getPtoOrvalhoIns()) + " | " + stringify(regAut.getPressaoIns()) + " | " + stringify(regAut.getVelVento()) + " | " + stringify(regAut.getDirVento()) + " | " + stringify(regAut.getRajVento()) + " | " + stringify(regAut.getRadiacao()) + " | " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;

                listViewText = "Manual: " + regManual.getData() + " | " + regManual.getHora() + " | " + stringify(regManual.getTemperatura()) + " | " + stringify(regManual.getUmi()) + " | " + stringify(regManual.getPressao()) + " | " + stringify(regManual.getVelVento()) + " | " + stringify(regManual.getDirVento()) + " | " + stringify(regManual.getNebulosidade()) + " | " + stringify(regManual.getInsolacao()) + " | " + stringify(regManual.getTempMax()) + " | " + stringify(regManual.getTempMin()) + " | " + stringify(regManual.getChuva());
            }
            listViewSuspeito.getItems().add(listViewText);
        }

        for (Registro registro : dadoApurado) {
            if (registro instanceof RegistroAutomatico registroa) {
                // Alterar visualização de lista
                if(registroa.getTemperatura() != null && registroa.getTemperatura() != 0.0){
                    somaTemp += registroa.getTemperatura();
                    quantidadeRegistrosTemp++;
                }
                if(registroa.getUmiIns() != null && registroa.getUmiIns() != 0.0){
                    somaUmi += registroa.getUmiIns();
                    quantidadeRegistrosUmi++;
                }
                if(registroa.getPtoOrvalhoIns() != null && registroa.getPtoOrvalhoIns() != 0.0){
                    somaPtoOrvalho += registroa.getPtoOrvalhoIns();
                    quantidadeRegistrosPtoOrvalho++;
                }
                if(registroa.getPressaoIns() != null && registroa.getPressaoIns() != 0.0){
                    somaPressao += registroa.getPressaoIns();
                    quantidadeRegistrosPressao++;
                }
                if(registroa.getVelVento() != null && registroa.getVelVento() != 0.0){
                    somaVelVento += registroa.getVelVento();
                    quantidadeRegistrosVelVento++;
                }
                if(registroa.getDirVento() != null && registroa.getDirVento() != 0.0){
                    somaDirVento += registroa.getDirVento();
                    quantidadeRegistrosDirVento++;
                }
                if(registroa.getRajVento() != null && registroa.getRajVento() != 0.0){
                    somaRajVento += registroa.getRajVento();
                    quantidadeRegistrosRajVento++;
                }
                if(registroa.getRadiacao() != null && registroa.getRadiacao() != 0.0){
                    somaRadiacao += registroa.getRadiacao();
                    quantidadeRegistrosRadiacao++;
                }
                if(registroa.getChuva() != null && registroa.getChuva() != 0.0){
                    somaChuva += registroa.getChuva();
                    quantidadeRegistrosChuva++;
                }
            }
            if (registro instanceof RegistroManual registrom) {
                // Alterar visualização de lista
                if(registrom.getTemperatura() != null && registrom.getTemperatura() != 0.0){
                    somaTempM += registrom.getTemperatura();
                    quantidadeRegistrosTempM++;
                }
                if(registrom.getUmi() != null && registrom.getUmi() != 0.0){
                    somaUmiM += registrom.getUmi();
                    quantidadeRegistrosUmiM++;
                }
                if(registrom.getPressao() != null && registrom.getPressao() != 0.0){
                    somaPressaoM += registrom.getPressao();
                    quantidadeRegistrosPressaoM++;
                }
                if(registrom.getVelVento() != null && registrom.getVelVento() != 0.0){
                    somaVelVentoM += registrom.getVelVento();
                    quantidadeRegistrosVelVentoM++;
                }
                if(registrom.getDirVento() != null && registrom.getDirVento() != 0.0){
                    somaDirVentoM += registrom.getDirVento();
                    quantidadeRegistrosDirVentoM++;
                }
                if(registrom.getNebulosidade() != null && registrom.getNebulosidade() != 0.0){
                    somaNebulosidadeM += registrom.getNebulosidade();
                    quantidadeRegistrosNebulosidadeM++;
                }
                if(registrom.getInsolacao() != null && registrom.getInsolacao() != 0.0){
                    somaInsolacaoM += registrom.getInsolacao();
                    quantidadeRegistrosInsolacaoM++;
                }
                if(registrom.getChuva() != null && registrom.getChuva() != 0.0){
                    somaChuvaM += registrom.getChuva();
                    quantidadeRegistrosChuvaM++;
                }
            }
        }

        mediaTemp        = (somaTemp / quantidadeRegistrosTemp);
        mediaUmi         = (somaUmi / quantidadeRegistrosUmi);
        mediaPtoOrvalho  = (somaPtoOrvalho / quantidadeRegistrosPtoOrvalho);
        mediaPressao     = (somaPressao / quantidadeRegistrosPressao);
        mediaVelVento    = (somaVelVento / quantidadeRegistrosVelVento);
        mediaDirVento    = (somaDirVento / quantidadeRegistrosDirVento);
        mediaRajVento    = (somaRajVento / quantidadeRegistrosRajVento);
        mediaRadiacao    = (somaRadiacao / quantidadeRegistrosRadiacao);
        mediaChuva       = (somaChuva / quantidadeRegistrosChuva);

        mediaTempM          = (somaTempM / quantidadeRegistrosTempM);
        mediaUmiM           = (somaUmiM / quantidadeRegistrosUmiM);
        mediaPressaoM       = (somaPressaoM / quantidadeRegistrosPressaoM);
        mediaVelVentoM      = (somaVelVentoM / quantidadeRegistrosVelVentoM);
        mediaDirVentoM      = (somaDirVentoM / quantidadeRegistrosDirVentoM);
        mediaNebulosidadeM  = (somaNebulosidadeM / quantidadeRegistrosNebulosidadeM);
        mediaInsolacaoM     = (somaInsolacaoM / quantidadeRegistrosInsolacaoM);
        mediaChuvaM         = (somaChuvaM / quantidadeRegistrosChuvaM);

        listViewRelatorio.getItems().clear();
        String listViewTextA;

            // Alterar visualização de lista
        listViewTextA = "Automático: " + "Temp.(C) " + String.format("%.2f", mediaTemp) + "|" +
                "Umi.(%) " + String.format("%.2f", mediaUmi) + "|" +
                "Pto Orvalho(C) " + String.format("%.2f", mediaPtoOrvalho) + "|" +
                "Pressao(hPa) " + String.format("%.2f", mediaPressao) + "|" +
                "Vel. Vento(m/s) " + String.format("%.2f", mediaVelVento) + "|" +
                "Dir. Vento(m/s) " + String.format("%.2f", mediaDirVento) + "|" +
                "Raj. Vento(m/s) " + String.format("%.2f", mediaRajVento) + "|" +
                "Radiacao(KJ/m²) " + String.format("%.2f", mediaRadiacao) + "|" +
                "Chuva(mm) " + String.format("%.2f", mediaChuva) + "\n";


        listViewRelatorio.getItems().add(listViewTextA);

        baixarRelatorio();
        String listViewTextM;

        // Alterar visualização de lista
        listViewTextM = "Manual: " + "Temp.(C) " + String.format("%.2f", mediaTempM) + "|" +
                "Umi.(%) " + String.format("%.2f", mediaUmiM) + "|" +
                "Pressao(hPa) " + String.format("%.2f", mediaPressaoM) + "|" +
                "Vel. Vento(m/s) " + String.format("%.2f", mediaVelVentoM) + "|" +
                "Dir. Vento(m/s) " + String.format("%.2f", mediaDirVentoM) + "|" +
                "Raj. Vento(m/s) " + String.format("%.2f", mediaNebulosidadeM) + "|" +
                "Radiacao(KJ/m²) " + String.format("%.2f", mediaInsolacaoM) + "|" +
                "Chuva(mm) " + String.format("%.2f", mediaChuvaM) + "\n";


        listViewRelatorio.getItems().add(listViewTextM);
    }

    @FXML
    protected void selectFilesClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.setTitle("Escolha um arquivo");
        fileChooser.getExtensionFilters().add(extensionFilter);

        Window stage = ((Node) event.getTarget()).getScene().getWindow();

        // Mostrar o diálogo de escolha de arquivo
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        String arquivosSelecionados = "";
        // Exemplo de como você pode manipular os arquivos selecionados
        if (selectedFiles != null) {
            System.out.println("Arquivos selecionados:");
            for (File file : selectedFiles) {
                System.out.println(file.getAbsolutePath());
                arquivosSelecionados = arquivosSelecionados + "\n" + file.getAbsolutePath();

                Arquivo arquivo = new Arquivo();
                arquivo.setConteudo(file);
                arquivo.tratar(registros);
            }
            selectedFilesLabel.setText(arquivosSelecionados);
        } else {
            selectedFilesLabel.setText(arquivosSelecionados);
        }
    }

    @FXML
    private void verificar(ActionEvent event) {// Método chamado quando o pesquisador clica no botão "Verificar"
        try {
            tempMaxima = Double.parseDouble(tempMaxField.getText());
            tempMinima = Double.parseDouble(tempMinField.getText());

            tempLabel.setText("Temperatura máxima: " + tempMaxima + "       Temperatura mínima: " + tempMinima);

            System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            System.out.println("Temperatura mínima: " + tempMinima);

            System.out.println("Dado Apurado: " + dadoApurado);
            System.out.println("Dado Registrado: " + registros);
            System.out.println("Dado Suspeito: " + dadoSuspeito);

            verificarRegistros();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
            tempLabel.setText("Por favor, insira valores válidos para as temperaturas.");
        }
    }

    private void verificarRegistros() {
        for (Registro registro : registros) {
            if (registro.getTemperatura() == null || registro.getTemperatura() >= tempMaxima || registro.getTemperatura() <= tempMinima) {
                dadoSuspeito.add(registro);
                System.out.println("Dado incorreto");
            } else {
                dadoApurado.add(registro);
                System.out.println("Dado correto");
            }
        }
        visualizarListas();
    }

    //Preciso fazer um novo botão para gerar o relatorio na tela
    //Preciso fazer a parte do relatorio Manual

    String desktopPath = System.getProperty("user.home") + "/Desktop/";
    String nomeArquivo = desktopPath + "RelatorioRegistro.csv";
    String nomeArquivoManual = desktopPath + "RelatorioRegistroManual.csv";

    // Modulo para adiconar os registros ao arquivo CSV
    public void baixarRelatorio(){
        try{
            //Verificar se o aquivo já existe
            boolean arquivoExiste = new File(nomeArquivo).exists();
            //Abre o escritor para adicionar dados ao arquivo
//            if(!arquivoExiste){
//                if(registros instanceof RegistroAutomatico){
//                    escritor.write("Temp.(C);Umi.(%);Pto Orvalho(C);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Raj. Vento(m/s);Radiacao(KJ/m²);Chuva(mm)\n");
//                }
//                else{
//                    escritor.write("Temp.(C);Umi.(%);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Nebulosidade(Decimos);Insolacao(h);Chuva(mm)\n");
//                }
//            }

           if(quantidadeRegistrosTemp > 0){
               FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, false);
               escritor.write("Temp.(C);Umi.(%);Pto Orvalho(C);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Raj. Vento(m/s);Radiacao(KJ/m²);Chuva(mm)\n");

               escritor.write(String.format("%.2f", mediaTemp) + ";" +
                       String.format("%.2f", mediaUmi) + ";" +
                       String.format("%.2f", mediaPtoOrvalho) + ";" +
                       String.format("%.2f", mediaPressao) + ";" +
                       String.format("%.2f", mediaVelVento) + ";" +
                       String.format("%.2f", mediaDirVento) + ";" +
                       String.format("%.2f", mediaRajVento) + ";" +
                       String.format("%.2f", mediaRadiacao) + ";" +
                       String.format("%.2f", mediaChuva) + ";" + "\n");
               escritor.write("\n");
               //Escreve todos os dados do Buffer no arquivo
               escritor.flush();

               //Fecha o recurso de escrita
               escritor.close();
           }



            if(quantidadeRegistrosTempM > 0){
                FileWriter escritor = new FileWriter(nomeArquivoManual, StandardCharsets.ISO_8859_1, false);
                escritor.write("Temp.(C);Umi.(%);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Nebulosidade(Decimos);Insolacao(h);Chuva(mm)\n");
                escritor.write(String.format("%.2f", mediaTempM) + ";" +
                        String.format("%.2f", mediaUmiM) + ";" +
                        String.format("%.2f", mediaPressaoM) + ";" +
                        String.format("%.2f", mediaVelVentoM) + ";" +
                        String.format("%.2f", mediaDirVentoM) + ";" +
                        String.format("%.2f", mediaNebulosidadeM) + ";" +
                        String.format("%.2f", mediaInsolacaoM) + ";" +
                        String.format("%.2f", mediaChuvaM) + ";" + "\n");
                escritor.write("\n");
                //Escreve todos os dados do Buffer no arquivo
                escritor.flush();

                //Fecha o recurso de escrita
                escritor.close();
            }


//            System.out.println("mediaTemperatura: " + somaTemp + ", " + quantidadeRegistrosTemp + ", " + mediaTemp);
//            System.out.println("mediaUmi: " + somaUmi + ", " + quantidadeRegistrosUmi + ", " + mediaUmi);
//            System.out.println("mediaPtoOrvalho: " + somaPtoOrvalho + ", " + quantidadeRegistrosPtoOrvalho + ", " + mediaPtoOrvalho);
//            System.out.println("mediaPressao: " + somaPressao + ", " + quantidadeRegistrosPressao + ", " + mediaPressao);
//            System.out.println("mediaVelVento: " + somaVelVento + ", " + quantidadeRegistrosVelVento + ", " + mediaVelVento);
//            System.out.println("mediaDirVento: " + somaDirVento + ", " + quantidadeRegistrosDirVento + ", " + mediaDirVento);
//            System.out.println("mediaRajVento: " + somaRajVento + ", " + quantidadeRegistrosRajVento + ", " + mediaRajVento);
//            System.out.println("mediaRadiacao: " + somaRadiacao + ", " + quantidadeRegistrosRadiacao + ", " + mediaRadiacao);
//            System.out.println("mediaChuva: " + somaChuva + ", " + quantidadeRegistrosChuva + ", " + mediaChuva);
//            System.out.println("mediaUmi: " + somaUmi + ", " + quantidadeRegistrosUmi + ", " + mediaUmi);

//            System.out.println("mediaTemperatura: " + somaTemp + ", " + quantidadeRegistrosTemp + ", " + mediaTemp);
//            System.out.println("mediaPressao: " + somaPressao + ", " + quantidadeRegistrosPressao + ", " + mediaPressao);
//            System.out.println("mediaVelVento: " + somaVelVento + ", " + quantidadeRegistrosVelVento + ", " + mediaVelVento);
//            System.out.println("mediaDirVento: " + somaDirVento + ", " + quantidadeRegistrosDirVento + ", " + mediaDirVento);
//            System.out.println("mediaNebulosidade: " + somaNebulosidade + ", " + quantidadeRegistrosNebulosidade + ", " + mediaNebulosidade);
//            System.out.println("mediaInsolacao: " + somaInsolacao + ", " + quantidadeRegistrosInsolacao + ", " + mediaInsolacao);
//            System.out.println("mediaChuva: " + somaChuva + ", " + quantidadeRegistrosChuva + ", " + mediaChuva);






        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}

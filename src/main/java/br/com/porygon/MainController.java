package br.com.porygon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.ListView;

import java.io.File;
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
    private DatePicker startDatePicker; //DatePicker para data inicial

    @FXML
    private DatePicker endDatePicker; //DatePicker para data final

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do
    // arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do
    // método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método
    
//  List<Registro> listrelatorio = new ArrayList<>(); //Lista dos dados do relatorio por data e período
    
    // verificarRegistros

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
                listViewText = "Automático - Data: " + regAut.getData() + " | Hora: " + regAut.getHora() + " | Temperatura (Ist): " + stringify(regAut.getTemperatura()) + " | Umidade (Ins): " + stringify(regAut.getUmiIns()) + " | Orvalho (Ins): " + stringify(regAut.getPtoOrvalhoIns()) + " | Pressão (Ins): " + stringify(regAut.getPressaoIns()) + " | Velocidade do Vento:  " + stringify(regAut.getVelVento()) + " | Direção do Vento: " + stringify(regAut.getDirVento()) + " | Rajada Vento: " + stringify(regAut.getRajVento()) + " | Radiação " + stringify(regAut.getRadiacao()) + " | Chuva: " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;
                listViewText = "Manual - Data: " + regManual.getData() + " | Hora: " + regManual.getHora() + " | Temperatura (Ist): " + stringify(regManual.getTemperatura()) + " | Umidade: " + stringify(regManual.getUmi()) + " | Pressão: " + stringify(regManual.getPressao()) + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: " + stringify(regManual.getDirVento()) + " | Nebulosidade: " + stringify(regManual.getNebulosidade()) + " | Insolação:  " + stringify(regManual.getInsolacao()) + " | Temperatura Máxima " + stringify(regManual.getTempMax()) + " | Temperatura Minima:  " + stringify(regManual.getTempMin()) + " | Chuva:  " + stringify(regManual.getChuva());
            }
            listViewApurado.getItems().add(listViewText);
        }
        listViewSuspeito.getItems().clear();
        for (Registro registro : dadoSuspeito) {
            String listViewText;

            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                listViewText = "Automático - Data: " + regAut.getData() + " | Hora: " + regAut.getHora() + " | Temperatura (Ist): " + stringify(regAut.getTemperatura()) + " | Umidade (Ins): " + stringify(regAut.getUmiIns()) + " | Orvalho (Ins): " + stringify(regAut.getPtoOrvalhoIns()) + " | Pressão (Ins): " + stringify(regAut.getPressaoIns()) + " | Velocidade do Vento: " + stringify(regAut.getVelVento()) + " | Direção do Vento: " + stringify(regAut.getDirVento()) + " | Rajada Vento: " + stringify(regAut.getRajVento()) + " | Radiação " + stringify(regAut.getRadiacao()) + " | Chuva: " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;

                listViewText = "Manual - Data: " + regManual.getData() + " | Hora: " + regManual.getHora() + " | Temperatura (Ist): " + stringify(regManual.getTemperatura()) + " | Umidade (Ins): " + stringify(regManual.getUmi()) + " | Pressão " + stringify(regManual.getPressao()) + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: " + stringify(regManual.getDirVento()) + " | Nebulosidade: " + stringify(regManual.getNebulosidade()) + " | Insolação: " + stringify(regManual.getInsolacao()) + " | Temperatura Máxima " + stringify(regManual.getTempMax()) + " | Temperatura Minima: " + stringify(regManual.getTempMin()) + " | Chuva: " + stringify(regManual.getChuva());
            }
            listViewSuspeito.getItems().add(listViewText);
        }
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

    public void exportar(ActionEvent actionEvent) {
//        ObservableList<String> dados = listrelatorio.getClass();
//        StringBuilder csvBuilder = new StringBuilder();
//
//        // Adicionar cabeçalho do CSV
//        csvBuilder.append("Coluna1,Coluna2,Coluna3\n");
//
//        // Iterar sobre os dados e adicionar ao CSV
//        for (String dado : dados) {
//            // Processar o dado e adicionar ao csvBuilder
//            // Se dado for um objeto, você precisará converter cada campo para String
//            // Por exemplo: csvBuilder.append(dado.getCampo1() + "," + dado.getCampo2() + "\n");
//            csvBuilder.append(dado).append("\n");
//        }
//
//        // Converter o StringBuilder para String
//        String csvContent = csvBuilder.toString();
//
//        // Salvar o conteúdo CSV em um arquivo
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Salvar Arquivo CSV");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
//        File file = fileChooser.showSaveDialog(((Node)actionEvent.getSource()).getScene().getWindow());
//
//        if (file != null) {
//            try (PrintWriter writer = new PrintWriter(file)) {
//                writer.write(csvContent);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void gerar(ActionEvent actionEvent) {
    }
}

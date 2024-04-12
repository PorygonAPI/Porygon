package br.com.porygon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.ListView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class MainController {

    //Atributos
    @FXML
    private Label selectedFilesLabel; // Mensagem do caminho do arquivo printado na tela

    @FXML
    private Label tempLabel; // Mensagem da temperatura máxima e mínima printada na tela após o input do pesquisador

    @FXML
    private TextField tempMaxField; // Campo de input da temperatura máxima

    @FXML
    private TextField tempMinField; // Campo de input da temperatura mínima

    private double tempMaxima; // Atributo da temperatura máxima informada pelo pesquisador
    private double tempMinima; // Atributo da temperatura máxima informada pelo pesquisador

    @FXML
    private ListView<Registro> listViewApurado; // Atributo para visualizar a lista de registros apurados na tela

    @FXML
    private ListView<Registro> listViewSuspeito; // Atributo para visualizar a lista de registros apurados na tela

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método verificarRegistros

    // Métodos de acesso
    public double getTempMaxima() {
        return tempMaxima;
    }
    public double getTempMinima() {
        return tempMinima;
    }

    // Método para colher informações das listas geradas no método verificarRegistros e vincular com o fxml
    public void visualizarListas() { 
        listViewApurado.getItems().clear();
        for (Registro registro : dadoApurado) {
            listViewApurado.getItems().add(registro);
        }
        listViewSuspeito.getItems().clear();
        for (Registro registro : dadoSuspeito) {
            listViewSuspeito.getItems().add(registro);
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
                arquivo.tratar();
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
        }
    }

        private void verificarRegistros(){
            for (Registro registro : registros ) {
                if (registro.getTemperatura() >= tempMaxima || registro.getTemperatura() <= tempMinima) {
                    dadoSuspeito.add(registro);
                    System.out.println("Dado incorreto");
                } else {
                    dadoApurado.add(registro);
                    System.out.println("Dado correto");
                }
            }
        }
    }


    

    
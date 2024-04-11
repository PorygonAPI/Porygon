package br.com.porygon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label selectedFilesLabel;

    @FXML
    private Label tempLabel;

    @FXML
    private TextField tempMaxField;

    @FXML
    private TextField tempMinField;

    private double tempMaxima;
    private double tempMinima;

    // Criando uma lista para armazenar os dados da temperatura
    List<Registro> dadoRegistrado = new ArrayList<Registro>();
    List<Registro> dadoSuspeito = new ArrayList<Registro>();
    List<Registro> dadoApurado = new ArrayList<Registro>();

    public static void converterTemperatura(String[] args) {
    }


    public double getTempMaxima() {
        return tempMaxima;
    }

    public double getTempMinima() {
        return tempMinima;
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
    private void verificar(ActionEvent event) {// Método chamado quando o usuário clica no botão "Verificar"
        try {
            tempMaxima = Double.parseDouble(tempMaxField.getText());
            tempMinima = Double.parseDouble(tempMinField.getText());

            tempLabel.setText("Temperatura máxima: " + tempMaxima + "       Temperatura mínima: " + tempMinima);

            System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            System.out.println("Temperatura mínima: " + tempMinima);

            System.out.println("Dado Apurado: " + dadoApurado);
            System.out.println("Dado Registrado: " + dadoRegistrado);
            System.out.println("Dado Suspeito: " + dadoSuspeito);

            verificarRegistros();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
        }
    }

        private void verificarRegistros(){
            for (Registro registro : dadoRegistrado ) {
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


    

    
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

    // Atributos
    @FXML
    private Label selectedFilesLabel; // Mensagem do caminho do arquivo printado na tela

    @FXML
    private Label tempLabel; // Mensagem da temperatura máxima e mínima printada na tela após o input do pesquisador

    // Campos de input da variáveis mínimas e máximas
    @FXML
    private TextField tempMaxField;
    @FXML
    private TextField tempMinField; 
    @FXML
    private TextField umiMaxField;
    @FXML
    private TextField umiMinField;
    @FXML
    private TextField presMaxField;
    @FXML
    private TextField presMinField;
    @FXML
    private TextField nebuMaxField;
    @FXML
    private TextField nebuMinField;
    @FXML
    private TextField ventoMaxField;
    @FXML
    private TextField ventoMinField;
    @FXML
    private TextField insoMaxField;
    @FXML
    private TextField insoMinField;
    @FXML
    private TextField chuMaxField;
    @FXML
    private TextField chuMinField;

    private Double tempMaxima; // Atributo da temperatura máxima informada pelo pesquisador
    private Double tempMinima; // Atributo da temperatura máxima informada pelo pesquisador
    private Double umiMaxima;
    private Double umiMinima;
    private Double presMaxima;
    private Double presMinima;
    private Double ventoMaxima;
    private Double ventoMinima;
    private Double nebuMaxima;
    private Double nebuMinima;
    private Double insoMaxima;
    private Double insoMinima;
    private Double chuvaMaxima;
    private Double chuvaMinima;

    @FXML
    private ListView<String> listViewApurado; // Atributo para visualizar a lista de registros apurados na tela

    @FXML
    private ListView<String> listViewSuspeito; // Atributo para visualizar a lista de registros apurados na tela

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do
    // arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do
    // método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método
    // verificarRegistros

    // Métodos de acesso
    public double getTempMaxima() {
        return tempMaxima;
    }
    public double getTempMinima() {
        return tempMinima;
    }
    public double getUmiMaxima() {
        return umiMaxima;
    }
    public void setUmiMaxima(double umiMaxima) {
        this.umiMaxima = umiMaxima;
    }
    public double getUmiMinima() {
        return umiMinima;
    }
    public void setUmiMinima(double umiMinima) {
        this.umiMinima = umiMinima;
    }
    public double getPresMaxima() {
        return presMaxima;
    }
    public void setPresMaxima(double presMaxima) {
        this.presMaxima = presMaxima;
    }
    public double getPresMinima() {
        return presMinima;
    }
    public void setPresMinima(double presMinima) {
        this.presMinima = presMinima;
    }
    public double getVentoMaxima() {
        return ventoMaxima;
    }
    public void setVentoMaxima(double ventoMaxima) {
        this.ventoMaxima = ventoMaxima;
    }
    public double getVentoMinima() {
        return ventoMinima;
    }
    public void setVentoMinima(double ventoMinima) {
        this.ventoMinima = ventoMinima;
    }
    public double getNebuMaxima() {
        return nebuMaxima;
    }
    public void setNebuMaxima(double nebuMaxima) {
        this.nebuMaxima = nebuMaxima;
    }
    public double getNebuMinima() {
        return nebuMinima;
    }
    public void setNebuMinima(double nebuMinima) {
        this.nebuMinima = nebuMinima;
    }
    public double getInsoMaxima() {
        return insoMaxima;
    }
    public void setInsoMaxima(double insoMaxima) {
        this.insoMaxima = insoMaxima;
    }
    public double getInsoMinima() {
        return insoMinima;
    }
    public void setInsoMinima(double insoMinima) {
        this.insoMinima = insoMinima;
    }
    public double getChuvaMaxima() {
        return chuvaMaxima;
    }
    public void setChuvaMaxima(double chuvaMaxima) {
        this.chuvaMaxima = chuvaMaxima;
    }
    public double getChuvaMinima() {
        return chuvaMinima;
    }
    public void setChuvaMinima(double chuvaMinima) {
        this.chuvaMinima = chuvaMinima;
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
                listViewText = "Automático: " + regAut.getData() + " | " + regAut.getHora() + " | " + stringify(regAut.getTemperatura()) + " | " + stringify(regAut.getUmiIns()) + " | " + stringify(regAut.getPtoOrvalhoIns()) + " | " + stringify(regAut.getPressao()) + " | " + stringify(regAut.getVelVento()) + " | " + stringify(regAut.getDirVento()) + " | " + stringify(regAut.getRajVento()) + " | " + stringify(regAut.getRadiacao()) + " | " + stringify(regAut.getChuva());
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
                listViewText = "Automático: " + regAut.getData() + " | " + regAut.getHora() + " | " + stringify(regAut.getTemperatura()) + " | " + stringify(regAut.getUmiIns()) + " | " + stringify(regAut.getPtoOrvalhoIns()) + " | " + stringify(regAut.getPressao()) + " | " + stringify(regAut.getVelVento()) + " | " + stringify(regAut.getDirVento()) + " | " + stringify(regAut.getRajVento()) + " | " + stringify(regAut.getRadiacao()) + " | " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;

                listViewText = "Manual: " + regManual.getData() + " | " + regManual.getHora() + " | " + stringify(regManual.getTemperatura()) + " | " + stringify(regManual.getUmi()) + " | " + stringify(regManual.getPressao()) + " | " + stringify(regManual.getVelVento()) + " | " + stringify(regManual.getDirVento()) + " | " + stringify(regManual.getNebulosidade()) + " | " + stringify(regManual.getInsolacao()) + " | " + stringify(regManual.getTempMax()) + " | " + stringify(regManual.getTempMin()) + " | " + stringify(regManual.getChuva());
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
            umiMaxima = Double.parseDouble(umiMinField.getText());
            umiMinima = Double.parseDouble(umiMaxField.getText());
            presMaxima = Double.parseDouble(presMaxField.getText());
            presMinima = Double.parseDouble(presMinField.getText());
            ventoMaxima = Double.parseDouble(ventoMaxField.getText());
            ventoMinima = Double.parseDouble(ventoMinField.getText());
            nebuMaxima = Double.parseDouble(nebuMaxField.getText());
            nebuMinima = Double.parseDouble(nebuMinField.getText());
            insoMaxima = Double.parseDouble(insoMaxField.getText());
            insoMinima = Double.parseDouble(insoMinField.getText());
            chuvaMaxima = Double.parseDouble(chuMaxField.getText());
            chuvaMinima = Double.parseDouble(chuMinField.getText());

            tempLabel.setText("Temperaturas salvas");

            // System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            // System.out.println("Temperatura mínima: " + tempMinima);

            // System.out.println("Dado Apurado: " + dadoApurado);
            // System.out.println("Dado Registrado: " + registros);
            // System.out.println("Dado Suspeito: " + dadoSuspeito);

            verificarRegistros();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            // System.err.println("Por favor, insira valores válidos para as temperaturas.");
            tempLabel.setText("Por favor, insira valores válidos.");
        }
    }

    private void verificarRegistros() {
        for (Registro registro : registros) {
            if (registro.getTemperatura() != null && (registro.getTemperatura() >= tempMaxima || registro.getTemperatura() <= tempMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getUmidade() != null && (registro.getUmidade() >= umiMaxima || registro.getUmidade() <= umiMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getPressao() != null && (registro.getPressao() >= presMaxima || registro.getPressao() <= presMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getVelVento() != null && (registro.getVelVento() >= ventoMaxima || registro.getVelVento() <= ventoMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getDirVento() != null && (registro.getDirVento() >= ventoMaxima || registro.getDirVento() <= ventoMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getNebulosidade() != null && (registro.getNebulosidade() >= nebuMaxima || registro.getNebulosidade() <= nebuMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getInsolacao() != null && (registro.getInsolacao() >= insoMaxima || registro.getInsolacao() <= insoMinima)) {
                dadoSuspeito.add(registro);
            } else if (registro.getChuva() != null && (registro.getChuva() >= chuvaMaxima || registro.getChuva() <= chuvaMinima)) {
                dadoSuspeito.add(registro);
            } else {
                dadoApurado.add(registro);
            }
        }
        visualizarListas();
    }
}

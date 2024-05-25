package br.com.porygon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;

public class PopUpController {
    private Map<String, String> cellData;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnManter;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField textField;

    public void setCellData(Map<String, String> newValue) {
        this.cellData = newValue;
        // Exibir ou usar os dados da célula conforme necessário
    }

    @FXML
    public void initialize() {
        // Inicializar os handlers dos botões
        btnExcluir.setOnAction(event -> handleExcluir());
        btnEditar.setOnAction(event -> handleEditar());
        btnManter.setOnAction(event -> handleManter());
        btnSalvar.setOnAction(event -> handleSalvar());
    }

    private void handleExcluir() {
        // Lógica para excluir o dado da célula
        System.out.println("Excluir: " + cellData.get("registro_id_sus"));
        // Fechar a janela
        ((Stage) btnExcluir.getScene().getWindow()).close();
    }

    private void handleEditar() {
        // Tornar o campo de texto e o botão de salvar visíveis
        textField.setVisible(true);
        btnSalvar.setVisible(true);
    }

    private void handleManter() {
        // Lógica para manter o dado da célula
        System.out.println("Manter: " + cellData.get("registro_id_sus"));
        // Fechar a janela
        ((Stage) btnManter.getScene().getWindow()).close();
    }

    private void handleSalvar() {
        // Lógica para salvar o novo valor
        String novoValor = textField.getText();
        System.out.println("Novo valor: " + novoValor);
        // Aqui você pode atualizar o dado da célula com o novo valor

        // Fechar a janela
        ((Stage) btnSalvar.getScene().getWindow()).close();
    }
}
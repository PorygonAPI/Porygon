package br.com.porygon;

import br.com.porygon.dao.RegistroDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Map;

public class PopUpController {
    private Map<String, String> cellData;
    private RegistroDAO registroDAO = new RegistroDAO();

    @FXML
    private Button btExcluir;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btRestaurar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField editarTextField;

    public void setCellData(Map<String, String> novoValor) {
        this.cellData = novoValor;
        // Exibir ou usar os dados da célula conforme necessário
    }

    @FXML
    public void initialize() {
        // Inicializar as ações dos botões
        btExcluir.setOnAction(event -> Excluir());
        btAlterar.setOnAction(event -> Alterar());
        btRestaurar.setOnAction(event -> Restaurar());
        btSalvar.setOnAction(event -> Salvar());
        editarTextField.setVisible(false);
        btSalvar.setVisible(false);
    }

    private void Excluir() {
        String registroIdStr = cellData.get("registro_id_sus");
        String nomeVariavel = "não_sei_declarar";// Substitua pelo nome da variável correta

        try {
            int registroId = Integer.parseInt(registroIdStr);
            registroDAO.excluirRegistroSuspeito(registroId, nomeVariavel);
            System.out.println("Registro excluído: " + registroId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicionar lógica para lidar com erros
        }

        // Fechar a janela
        ((Stage) btExcluir.getScene().getWindow()).close();
    }

    private void Alterar() {
        // Tornar o campo de texto e o botão de salvar visíveis
        editarTextField.setVisible(true);
        btSalvar.setVisible(true);
    }

    private void Restaurar() {
        String registroIdStr = cellData.get("registro_id_sus");
        String nomeVariavel = "não_sei_declarar"; // Substitua pelo nome da variável correta

        try {
            int registroId = Integer.parseInt(registroIdStr);
            registroDAO.restaurarRegistroSuspeito(registroId, nomeVariavel);
            System.out.println("Registro restaurado: " + registroId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicionar lógica para lidar com erros
        }

        // Fechar a janela
        ((Stage) btRestaurar.getScene().getWindow()).close();
    }

    private void Salvar() {
        String registroIdStr = cellData.get("registro_id_sus");
        String nomeVariavel = "não_sei_declarar"; // Substitua pelo nome da variável correta
        String novoValorStr = editarTextField.getText();

        try {
            int registroId = Integer.parseInt(registroIdStr);
            double novoValor = Double.parseDouble(novoValorStr);
            registroDAO.alterarRegistroSuspeito(registroId, nomeVariavel, novoValor, false);
            System.out.println("Novo valor salvo: " + novoValor);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicionar lógica para lidar com erros
        }

        // Fechar a janela
        ((Stage) btSalvar.getScene().getWindow()).close();
    }
}
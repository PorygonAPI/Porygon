package br.com.porygon;

import br.com.porygon.dao.RegistroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Map;

public class PopUpController {
    private Map<String, Double> cellData;

    private int registroId;
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

    @FXML
    private ComboBox comboDadoSuspeito;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setCellData(Map<String, Double> novoValor) {
        this.cellData = novoValor;
        ObservableList<String> items = FXCollections.observableArrayList();

        for (Map.Entry<String, Double> entry : novoValor.entrySet()) {
            String key = entry.getKey();
            double value = entry.getValue();
            items.add(key);

            System.out.println("Chave: " + key + ", Valor: " + value);
        }
        comboDadoSuspeito.setItems(items);
        // Exibir ou usar os dados da célula conforme necessário
    }

    public void setRegistroId(int registroId) {
        this.registroId = registroId;
        // Exibir ou usar os dados da célula conforme necessário
    }

    @FXML
    public void initialize() {
        // Inicializar as ações dos botões
        btExcluir.setOnAction(event -> excluir());
        btAlterar.setOnAction(event -> alterar());
        btRestaurar.setOnAction(event -> restaurar());
        btSalvar.setOnAction(event -> salvar());
        editarTextField.setVisible(false);
        btSalvar.setVisible(false);
    }

    private void excluir() {
        String registroIdStr = String.valueOf(registroId) ;
        String nomeVariavel = comboDadoSuspeito.getValue().toString(); // Substitua pelo nome da variável correta

        try {
            int registroId = Integer.parseInt(registroIdStr);
            registroDAO.excluirRegistroSuspeito(registroId, nomeVariavel);
            System.out.println("Registro excluído: " + registroId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicionar lógica para lidar com erros
        }
        mainController.visualizarListas();

        // Fechar a janela
        ((Stage) btExcluir.getScene().getWindow()).close();
    }

    private void alterar() {
        // Tornar o campo de texto e o botão de salvar visíveis
        editarTextField.setVisible(true);
        btSalvar.setVisible(true);
    }

    private void restaurar() {
        String registroIdStr = String.valueOf(registroId) ;
        String nomeVariavel = comboDadoSuspeito.getValue().toString(); // Substitua pelo nome da variável correta

        try {
            int registroId = Integer.parseInt(registroIdStr);
            registroDAO.restaurarRegistroSuspeito(registroId, nomeVariavel);
            System.out.println("Registro restaurado: " + registroId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicionar lógica para lidar com erros
        }
        mainController.visualizarListas();

        // Fechar a janela
        ((Stage) btRestaurar.getScene().getWindow()).close();
    }

    private void salvar() {
        String registroIdStr = String.valueOf(registroId) ;
        String nomeVariavel = comboDadoSuspeito.getValue().toString(); // Substitua pelo nome da variável correta
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
        mainController.visualizarListas();
        // Fechar a janela
        ((Stage) btSalvar.getScene().getWindow()).close();
    }
}
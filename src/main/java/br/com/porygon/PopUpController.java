package br.com.porygon;

import br.com.porygon.dao.RegistroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
        btSalvar.setVisible(false);
        editarTextField.setVisible(false);

        // Desabilitar botões até que um item seja selecionado na lista suspensa
        btExcluir.setDisable(true);
        btAlterar.setDisable(true);
        btRestaurar.setDisable(true);

        // Adicionar listener para a seleção da lista suspensa
        comboDadoSuspeito.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                btExcluir.setDisable(false);
                btAlterar.setDisable(false);
                btRestaurar.setDisable(false);
            } else {
                btExcluir.setDisable(true);
                btAlterar.setDisable(true);
                btRestaurar.setDisable(true);
            }
        });

        editarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Implementar validações ou lógica adicional, se necessário
        });

        btExcluir.setOnAction(event -> excluir());
        btAlterar.setOnAction(event -> alterar());
        btRestaurar.setOnAction(event -> restaurar());
        btSalvar.setOnAction(event -> salvar());
    }

    private void excluir() {
        String registroIdStr = String.valueOf(registroId);
        String nomeVariavel = comboDadoSuspeito.getValue().toString(); // Substitua pelo nome da variável correta

        // Criar um Alert para confirmar a exclusão
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão");
        alert.setHeaderText("Tem certeza que deseja excluir este registro?");
        alert.setContentText("Esta ação não pode ser desfeita.");

        // Opção de botões do Alert
        ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

        // Exibir o Alert e aguardar a resposta do usuário
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeConfirmar) {
                // Se o usuário confirmar, prosseguir com a exclusão
                try {
                    int registroId = Integer.parseInt(registroIdStr);
                    registroDAO.excluirRegistroSuspeito(registroId, nomeVariavel);
                    System.out.println("Registro excluído: " + registroId);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Adicionar lógica para lidar com erros
                }
                mainController.visualizarListas();
            } else {
                // Se o usuário cancelar, não fazer nada
                System.out.println("Exclusão cancelada pelo usuário.");
            }
        });

        // Fechar a janela (se necessário)
        ((Stage) btExcluir.getScene().getWindow()).close();
    }

    private void alterar() {
        // Tornar o campo de texto e o botão de salvar visíveis
        editarTextField.setVisible(true);
        btSalvar.setVisible(true);

        // Desabilitar inicialmente o botão salvar
        btSalvar.setDisable(true);

        // Adicionar um listener para monitorar as alterações no texto do campo editarTextField
        editarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Verificar se o novo valor (newValue) não está vazio
            if (!newValue.trim().isEmpty()) {
                // Se não estiver vazio, habilitar o botão salvar
                btSalvar.setDisable(false);
            } else {
                // Se estiver vazio, desabilitar o botão salvar
                btSalvar.setDisable(true);
            }
        });
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
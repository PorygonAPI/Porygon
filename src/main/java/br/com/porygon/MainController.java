package br.com.porygon;

import br.com.porygon.dao.ArquivoDAO;
// import javafx.collections.ObservableList;
import br.com.porygon.dao.CidadeDAO;
import br.com.porygon.dao.ConfiguracaoDAO;
import br.com.porygon.dao.RegistroDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
// import java.util.Objects;

public class MainController {

    // Atributos
    @FXML
    private Label selectedFilesLabel; // Mensagem do caminho do arquivo printado na tela

    @FXML
    private Label tempLabel; // Mensagem da temperatura máxima e mínima printada na tela após o input do
    // pesquisador

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
    private TextField velVentoMaxField;
    @FXML
    private TextField velVentoMinField;
    @FXML
    private TextField dirVentoMaxField;
    @FXML
    private TextField dirVentoMinField;
    @FXML
    private TextField insoMaxField;
    @FXML
    private TextField insoMinField;
    @FXML
    private TextField chuMaxField;
    @FXML
    private TextField chuMinField;
    @FXML
    private TextField ptoOrvMaxField;
    @FXML
    private TextField ptoOrvMinField;
    @FXML
    private TextField rajVenMaxField;
    @FXML
    private TextField rajVenMinField;

    private double tempMaxima; // Atributo da temperatura máxima informada pelo pesquisador
    private double tempMinima; // Atributo da temperatura máxima informada pelo pesquisador
    private double umiMaxima;
    private double umiMinima;
    private double presMaxima;
    private double presMinima;
    private double velVentoMaxima;
    private double velVentoMinima;
    private double dirVentoMaxima;
    private double dirVentoMinima;
    private double nebuMaxima;
    private double nebuMinima;
    private double insoMaxima;
    private double insoMinima;
    private double chuvaMaxima;
    private double chuvaMinima;
    private double ptoOrvalhoMaximo;
    private double ptoOrvalhoMinimo;
    private double rajVentoMaximo;
    private double rajVentoMinimo;

    private Stage modalStage;

    @FXML
    private ListView<String> listrelatorio; // Atributo para visualizar a lista do relatorio (Data/Periodo) na tela

    @FXML
    private TableView<Map<String, String>> tableViewBoxPlot;

    @FXML
    private TableColumn<Map<String, String>, String> dadoBoxPlot;

    @FXML
    private TableColumn<Map<String, String>, String> q1BoxPlot;
    @FXML
    private TableColumn<Map<String, String>, String> q2BoxPlot;
    @FXML
    private TableColumn<Map<String, String>, String> q3BoxPlot;
    @FXML
    private TableColumn<Map<String, String>, String> liBoxPlot;
    @FXML
    private TableColumn<Map<String, String>, String> lsBoxPlot;
    @FXML
    private TableColumn<Map<String, String>, String> outBoxPlot;

    @FXML
    private TableView<Map<String, String>> tableViewRelatorio;

    @FXML
    private TableColumn<Map<String, String>, String> dataHoraRelColumn;

    @FXML
    private TableColumn<Map<String, String>, String> tipoArquivoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> tempRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> pressaoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> velVentoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> chuvaRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> ptoOrvalhoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> umiRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> nebRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> radRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> dirVentoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> insolacaoRelColumn;
    @FXML
    private TableColumn<Map<String, String>, String> rajVentoRelColumn;

    @FXML
    private TableView<Map<String, String>> tableViewApurado;

    @FXML
    private TableColumn<Map<String, String>, String> dataHoraColumn;

    @FXML
    private TableColumn<Map<String, String>, String> tipoArquivoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> tempColumn;
    @FXML
    private TableColumn<Map<String, String>, String> pressaoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> velVentoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> chuvaColumn;
    @FXML
    private TableColumn<Map<String, String>, String> ptoOrvalhoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> umiColumn;
    @FXML
    private TableColumn<Map<String, String>, String> nebColumn;
    @FXML
    private TableColumn<Map<String, String>, String> radColumn;
    @FXML
    private TableColumn<Map<String, String>, String> dirVentoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> insolacaoColumn;
    @FXML
    private TableColumn<Map<String, String>, String> rajVentoColumn;

    @FXML
    private TableView<Map<String, String>> tableViewSuspeito;

    @FXML
    private TableColumn<Map<String, String>, String> dataHoraSusColumn;

    @FXML
    private TableColumn<Map<String, String>, String> tipoArquivoSusColumn;

    @FXML
    private TableColumn<Map<String, String>, String> registroIdSusColumn;

    @FXML
    private TableColumn<Map<String, String>, String> tempSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> pressaoSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> velVentoSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> chuvaSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> ptoOrvalhoSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> umiSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> nebSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> radSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> dirVentoSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> insolacaoSusColumn;
    @FXML
    private TableColumn<Map<String, String>, String> rajVentoSusColumn;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> cityComboBoxBoxPlot;

    @FXML
    private DatePicker startDatePickerBoxPlot;

    @FXML
    private DatePicker endDatePickerBoxPlot;

    @FXML
    private ListView<String> listViewRelatorio;

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do
    // arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do
    // método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método
    List<Registro> dadosNulos = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método

    String[] cidadesLista = {};

    // List<Registro> listrelatorio = new ArrayList<>(); //Lista dos dados do
    // relatorio por data e período
    // verificarRegistros
    List<Registro> Relatorio = new ArrayList<Registro>();

    // Tira a media das variaveis A
    // Temperatura
    double somaTemp = 0.0;
    int quantidadeRegistrosTemp = 0;
    double mediaTemp = 0.0;
    // Umidade
    double somaUmi = 0.0;
    int quantidadeRegistrosUmi = 0;
    double mediaUmi = 0.0;
    // Pto Orvalho
    double somaPtoOrvalho = 0.0;
    int quantidadeRegistrosPtoOrvalho = 0;
    double mediaPtoOrvalho = 0.0;
    // Pressao
    double somaPressao = 0.0;
    int quantidadeRegistrosPressao = 0;
    double mediaPressao = 0.0;
    // Vel Vento
    double somaVelVento = 0.0;
    int quantidadeRegistrosVelVento = 0;
    double mediaVelVento = 0.0;
    // Dir Vento
    double somaDirVento = 0.0;
    int quantidadeRegistrosDirVento = 0;
    double mediaDirVento = 0.0;
    // Raj Vento
    double somaRajVento = 0.0;
    int quantidadeRegistrosRajVento = 0;
    double mediaRajVento = 0.0;
    // Radiacao
    double somaRadiacao = 0.0;
    int quantidadeRegistrosRadiacao = 0;
    double mediaRadiacao = 0.0;
    // Chuva
    double somaChuva = 0.0;
    int quantidadeRegistrosChuva = 0;
    double mediaChuva = 0.0;

    // Tira a media das variaveis A
    // Temperatura
    double somaTempM = 0.0;
    int quantidadeRegistrosTempM = 0;
    double mediaTempM = 0.0;
    // Umidade
    double somaUmiM = 0.0;
    int quantidadeRegistrosUmiM = 0;
    double mediaUmiM = 0.0;
    // Pressao
    double somaPressaoM = 0.0;
    int quantidadeRegistrosPressaoM = 0;
    double mediaPressaoM = 0.0;
    // Vel Vento
    double somaVelVentoM = 0.0;
    int quantidadeRegistrosVelVentoM = 0;
    double mediaVelVentoM = 0.0;
    // Dir Vento
    double somaDirVentoM = 0.0;
    int quantidadeRegistrosDirVentoM = 0;
    double mediaDirVentoM = 0.0;
    // Raj Vento
    double somaNebulosidadeM = 0.0;
    int quantidadeRegistrosNebulosidadeM = 0;
    double mediaNebulosidadeM = 0.0;
    // Radiacao
    double somaInsolacaoM = 0.0;
    int quantidadeRegistrosInsolacaoM = 0;
    double mediaInsolacaoM = 0.0;
    // Chuva
    double somaChuvaM = 0.0;
    int quantidadeRegistrosChuvaM = 0;
    double mediaChuvaM = 0.0;
    CidadeDAO cidadeDAO = new CidadeDAO();
    RegistroDAO registroDAO = new RegistroDAO();

    private Callback<TableColumn.CellDataFeatures<Map<String, String>, String>, ObservableValue<String>> createCellFactory(
            String columnName) {
        return param -> {
            String value = param.getValue().get(columnName);
            return new SimpleStringProperty(value);
        };
    }

    public void initialize() throws SQLException {
        ObservableList<String> cidades = FXCollections.observableArrayList();

        cidadeDAO.getCidades(cidades);

        cityComboBox.setItems(cidades);
        cityComboBoxBoxPlot.setItems(cidades);

        dadoBoxPlot.setCellValueFactory(createCellFactory("dado_registro"));
        q1BoxPlot.setCellValueFactory(createCellFactory("q1"));
        q2BoxPlot.setCellValueFactory(createCellFactory("q2"));
        q3BoxPlot.setCellValueFactory(createCellFactory("q3"));
        liBoxPlot.setCellValueFactory(createCellFactory("li"));
        lsBoxPlot.setCellValueFactory(createCellFactory("ls"));
        outBoxPlot.setCellValueFactory(createCellFactory("out"));

        dataHoraRelColumn.setCellValueFactory(createCellFactory("data_hora_rel"));
        tipoArquivoRelColumn.setCellValueFactory(createCellFactory("tipo_arquivo_rel"));
        tempRelColumn.setCellValueFactory(createCellFactory("temperatura_rel"));
        pressaoRelColumn.setCellValueFactory(createCellFactory("pressao_rel"));
        velVentoRelColumn.setCellValueFactory(createCellFactory("velVento_rel"));
        chuvaRelColumn.setCellValueFactory(createCellFactory("chuva_rel"));
        ptoOrvalhoRelColumn.setCellValueFactory(createCellFactory("ptoOrvalho_rel"));
        umiRelColumn.setCellValueFactory(createCellFactory("umidade_rel"));
        nebRelColumn.setCellValueFactory(createCellFactory("nebulosidade_rel"));
        radRelColumn.setCellValueFactory(createCellFactory("radiacao_rel"));
        dirVentoRelColumn.setCellValueFactory(createCellFactory("dirVento_rel"));
        insolacaoRelColumn.setCellValueFactory(createCellFactory("insolacao_rel"));
        rajVentoRelColumn.setCellValueFactory(createCellFactory("rajVento_rel"));

        // Fazer esse cara num botão de análise
        dataHoraSusColumn.setCellValueFactory(createCellFactory("data_hora_sus"));
        tipoArquivoSusColumn.setCellValueFactory(createCellFactory("tipo_arquivo_sus"));
        registroIdSusColumn.setCellValueFactory(createCellFactory("registro_id_sus"));
        tempSusColumn.setCellValueFactory(createCellFactory("temperatura_sus"));
        pressaoSusColumn.setCellValueFactory(createCellFactory("pressao_sus"));
        velVentoSusColumn.setCellValueFactory(createCellFactory("velVento_sus"));
        chuvaSusColumn.setCellValueFactory(createCellFactory("chuva_sus"));
        ptoOrvalhoSusColumn.setCellValueFactory(createCellFactory("ptoOrvalho_sus"));
        umiSusColumn.setCellValueFactory(createCellFactory("umidade_sus"));
        nebSusColumn.setCellValueFactory(createCellFactory("nebulosidade_sus"));
        radSusColumn.setCellValueFactory(createCellFactory("radiacao_sus"));
        dirVentoSusColumn.setCellValueFactory(createCellFactory("dirVento_sus"));
        insolacaoSusColumn.setCellValueFactory(createCellFactory("insolacao_sus"));
        rajVentoSusColumn.setCellValueFactory(createCellFactory("rajVento_sus"));

        dataHoraColumn.setCellValueFactory(createCellFactory("data_hora"));
        tipoArquivoColumn.setCellValueFactory(createCellFactory("tipo_arquivo"));
        tempColumn.setCellValueFactory(createCellFactory("temperatura"));
        pressaoColumn.setCellValueFactory(createCellFactory("pressao"));
        velVentoColumn.setCellValueFactory(createCellFactory("velVento"));
        chuvaColumn.setCellValueFactory(createCellFactory("chuva"));
        ptoOrvalhoColumn.setCellValueFactory(createCellFactory("ptoOrvalho"));
        umiColumn.setCellValueFactory(createCellFactory("umidade"));
        nebColumn.setCellValueFactory(createCellFactory("nebulosidade"));
        radColumn.setCellValueFactory(createCellFactory("radiacao"));
        dirVentoColumn.setCellValueFactory(createCellFactory("dirVento"));
        insolacaoColumn.setCellValueFactory(createCellFactory("insolacao"));
        rajVentoColumn.setCellValueFactory(createCellFactory("rajVento"));

        // idColumn.setCellValueFactory(cellData -> {
        // Map<String, String> rowData = cellData.getValue();
        // // Aqui você pode retornar o valor específico do mapa que deseja exibir na
        // célula
        // // Neste exemplo, estamos retornando o valor associado à chave específica
        // (por exemplo, 1)
        // return (ObservableValue<String>) new PropertyValueFactory<String,
        // String>(rowData.get("id"));
        // });
        ConfiguracaoDAO configDao = new ConfiguracaoDAO();

        String tempMaxima = configDao.recuperarAtributos("tempMaxima");
        String tempMinima = configDao.recuperarAtributos("tempMinima");
        String umiMaxima = configDao.recuperarAtributos("umiMaxima");
        String umiMinima = configDao.recuperarAtributos("umiMinima");
        String presMaxima = configDao.recuperarAtributos("presMaxima");
        String presMinima = configDao.recuperarAtributos("presMinima");
        String velVentoMaxima = configDao.recuperarAtributos("velVentoMaxima");
        String velVentoMinima = configDao.recuperarAtributos("velVentoMinima");
        String nebuMaxima = configDao.recuperarAtributos("nebuMaxima");
        String nebuMinima = configDao.recuperarAtributos("nebuMinima");
        String dirVentoMaxima = configDao.recuperarAtributos("dirVentoMaxima");
        String dirVentoMinima = configDao.recuperarAtributos("dirVentoMinima");
        String ptoOrvalhoMaximo = configDao.recuperarAtributos("ptoOrvalhoMaximo");
        String ptoOrvalhoMinimo = configDao.recuperarAtributos("ptoOrvalhoMinimo");
        String rajVentoMaximo = configDao.recuperarAtributos("rajVentoMaximo");
        String rajVentoMinimo = configDao.recuperarAtributos("rajVentoMinimo");
        String insoMaxima = configDao.recuperarAtributos("insoMaxima");
        String insoMinima = configDao.recuperarAtributos("insoMinima");
        String chuvaMaxima = configDao.recuperarAtributos("chuvaMaxima");
        String chuvaMinima = configDao.recuperarAtributos("chuvaMinima");

        tempMaxField.setText(tempMaxima);
        tempMinField.setText(tempMinima);
        umiMaxField.setText(umiMaxima);
        umiMinField.setText(umiMinima);
        presMaxField.setText(presMaxima);
        presMinField.setText(presMinima);
        velVentoMaxField.setText(velVentoMaxima);
        velVentoMinField.setText(velVentoMinima);
        nebuMaxField.setText(nebuMaxima);
        nebuMinField.setText(nebuMinima);
        dirVentoMaxField.setText(dirVentoMaxima);
        dirVentoMinField.setText(dirVentoMinima);
        ptoOrvMaxField.setText(ptoOrvalhoMaximo);
        ptoOrvMinField.setText(ptoOrvalhoMinimo);
        rajVenMaxField.setText(rajVentoMaximo);
        rajVenMinField.setText(rajVentoMinimo);
        insoMaxField.setText(insoMaxima);
        insoMinField.setText(insoMinima);
        chuMaxField.setText(chuvaMaxima);
        chuMinField.setText(chuvaMinima);
        // tableViewApurado.getItems().addAll((HashMap<String, String>) data1);

        visualizarListas();

        tableViewSuspeito.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // CARLA E NAIRA O EVENTO TA AQUI
                // AQUI TEM QUE ABRIR O POPUP E TEM POSSIVEIS INFORMAÇÕES SOBRE O DADO SUSPEITO.
                // PARA PEGAR TODOS OS NOMES DAS ROWS, BASTA PESQUISAR "_sus" NO CODIGO QUE
                // ENCONTRARÃO
                // DEVEM VERIFICAR SE O CAMPO TA VAZIO PARA CONSEGUIR TRATAR E SEGUIR COM AS
                // INTEGRAÇÕES
                System.out.println("Clicou no registro:" + newValue.get("registro_id_sus"));

                Map<String, Double> map = registroDAO
                        .getDadoSuspeito(Integer.parseInt(newValue.get("registro_id_sus")));

                mostrarPopUp(map, Integer.parseInt(newValue.get("registro_id_sus")));
            }
        });

    }

    private void mostrarPopUp(Map<String, Double> dadosSupeitos, int registroId) {
        if (modalStage == null || !modalStage.isShowing()) {
            try {
                // Carregar o arquivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("popup-view.fxml"));
                Parent root = loader.load();

                // Obter o controlador da nova tela
                PopUpController controller = loader.getController();

                controller.setRegistroId(registroId);
                controller.setMainController(this);

                // Passar os dados da célula para o controlador
                controller.setCellData(dadosSupeitos);

                // Configurar a nova cena e exibir
                modalStage = new Stage();
                modalStage.setScene(new Scene(root));
                modalStage.setTitle("Registro Suspeito");
                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.setOnCloseRequest(e -> {
                    modalStage = null;
                    // Executa outras lógicas necessárias ao fechar o modal aqui
                });

                modalStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String stringify(Double dado) {
        if (dado == null) {
            return null;
        }
        return String.format("%.2f", dado);
    }

    // Método para colher informações das listas geradas no método
    // verificarRegistros e vincular com o fxml
    public void visualizarListas() {
        ObservableList<Map<String, String>> dadosSuspeitos = FXCollections.observableArrayList();
        ObservableList<Map<String, String>> dadosApurados = FXCollections.observableArrayList();

        dadosApurados = registroDAO.getDadosApurados();
        dadosSuspeitos = registroDAO.getDadosSuspeitos();

        tableViewApurado.setItems(dadosApurados);
        tableViewSuspeito.setItems(dadosSuspeitos);
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void updateLists(ActionEvent event) {
        visualizarListas();
        showAlert("Sucesso!", "Sucesso!", "Lista atualizada com sucesso!");
    }

    @FXML
    protected void selectFilesClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.setTitle("Escolha um arquivo");
        fileChooser.getExtensionFilters().add(extensionFilter);

        Window stage = ((Node) event.getTarget()).getScene().getWindow();

        // Mostrar o diálogo de escolha de arquivo
        File selectedFile = fileChooser.showOpenDialog(stage);
        ObservableList<String> opcoes = FXCollections.observableArrayList(cidadesLista);
        // Exemplo de como você pode manipular os arquivos selecionados
        if (selectedFile != null) {
            ArquivoDAO arquivoDao = new ArquivoDAO();
            String[] fileNamePart = selectedFile.getName().split(".csv")[0].split("_");
            String cidadeSigla = fileNamePart[1];
            String nome = fileNamePart[0].replace("A", "");
            int arquivoId = arquivoDao.salvarArquivo(cidadeSigla, nome);
            if (!opcoes.contains(cidadeSigla)) {
                // Adicionar novo valor ao array
                opcoes.add(cidadeSigla);
                System.out.println("");
            }

            // Definir a lista de opções para o ComboBox
            cityComboBox.setItems(opcoes);

            // Definir um valor padrão
            // cityComboBox.setValue(opcoes.getFirst());

            Arquivo arquivo = new Arquivo();
            arquivo.setConteudo(selectedFile);
            arquivo.tratar(arquivoId);
            //
            ConfiguracaoDAO configDao = new ConfiguracaoDAO();

            String tempMaxima = configDao.recuperarAtributos("tempMaxima");
            String tempMinima = configDao.recuperarAtributos("tempMinima");
            String umiMaxima = configDao.recuperarAtributos("umiMaxima");
            String umiMinima = configDao.recuperarAtributos("umiMinima");
            String presMaxima = configDao.recuperarAtributos("presMaxima");
            String presMinima = configDao.recuperarAtributos("presMinima");
            String velVentoMaxima = configDao.recuperarAtributos("velVentoMaxima");
            String velVentoMinima = configDao.recuperarAtributos("velVentoMinima");
            String nebuMaxima = configDao.recuperarAtributos("nebuMaxima");
            String nebuMinima = configDao.recuperarAtributos("nebuMinima");
            String dirVentoMaxima = configDao.recuperarAtributos("dirVentoMaxima");
            String dirVentoMinima = configDao.recuperarAtributos("dirVentoMinima");
            String ptoOrvalhoMaximo = configDao.recuperarAtributos("ptoOrvalhoMaximo");
            String ptoOrvalhoMinimo = configDao.recuperarAtributos("ptoOrvalhoMinimo");
            String rajVentoMaximo = configDao.recuperarAtributos("rajVentoMaximo");
            String rajVentoMinimo = configDao.recuperarAtributos("rajVentoMinimo");
            String insoMaxima = configDao.recuperarAtributos("insoMaxima");
            String insoMinima = configDao.recuperarAtributos("insoMinima");
            String chuvaMaxima = configDao.recuperarAtributos("chuvaMaxima");
            String chuvaMinima = configDao.recuperarAtributos("chuvaMinima");

            registroDAO.updateData(arquivoId, tempMaxima, tempMinima, umiMaxima, umiMinima, presMaxima, presMinima,
                    velVentoMaxima, velVentoMinima, nebuMaxima, nebuMinima, dirVentoMaxima, dirVentoMinima,
                    ptoOrvalhoMaximo, ptoOrvalhoMinimo, rajVentoMaximo, rajVentoMinimo, insoMaxima, insoMinima,
                    chuvaMaxima, chuvaMinima);
            showAlert("Arquivo selecionado!", "Arquivo adicionado com sucesso!",
                    "O arquivo " + selectedFile.getName() + " foi selecionado");
        }
    }

    @FXML
    private void verificar(ActionEvent event) { // Método chamado quando o pesquisador clica no botão "Verificar"
        // Transformar os valores de String para Double antes de fazer a comparação com
        // as minimas e máximas informadas pelo pesquisador
        try {
            ConfiguracaoDAO configDao = new ConfiguracaoDAO();
            tempMaxima = Double.parseDouble(tempMaxField.getText());
            configDao.adicionarAtributo("tempMaxima", tempMaxima);
            tempMinima = Double.parseDouble(tempMinField.getText());
            configDao.adicionarAtributo("tempMinima", tempMinima);

            umiMaxima = Double.parseDouble(umiMaxField.getText());
            configDao.adicionarAtributo("umiMaxima", umiMaxima);

            umiMinima = Double.parseDouble(umiMinField.getText());
            configDao.adicionarAtributo("umiMinima", umiMinima);

            presMaxima = Double.parseDouble(presMaxField.getText());
            configDao.adicionarAtributo("presMaxima", presMaxima);

            presMinima = Double.parseDouble(presMinField.getText());
            configDao.adicionarAtributo("presMinima", presMinima);

            velVentoMaxima = Double.parseDouble(velVentoMaxField.getText());
            configDao.adicionarAtributo("velVentoMaxima", velVentoMaxima);

            velVentoMinima = Double.parseDouble(velVentoMinField.getText());
            configDao.adicionarAtributo("velVentoMinima", velVentoMinima);

            dirVentoMaxima = Double.parseDouble(dirVentoMaxField.getText());
            configDao.adicionarAtributo("dirVentoMaxima", dirVentoMaxima);

            dirVentoMinima = Double.parseDouble(dirVentoMinField.getText());
            configDao.adicionarAtributo("dirVentoMinima", dirVentoMinima);

            nebuMaxima = Double.parseDouble(nebuMaxField.getText());
            configDao.adicionarAtributo("nebuMaxima", nebuMaxima);

            nebuMinima = Double.parseDouble(nebuMinField.getText());
            configDao.adicionarAtributo("nebuMinima", nebuMinima);

            insoMaxima = Double.parseDouble(insoMaxField.getText());
            configDao.adicionarAtributo("insoMaxima", insoMaxima);

            insoMinima = Double.parseDouble(insoMinField.getText());
            configDao.adicionarAtributo("insoMinima", insoMinima);

            chuvaMaxima = Double.parseDouble(chuMaxField.getText());
            configDao.adicionarAtributo("chuvaMaxima", chuvaMaxima);

            chuvaMinima = Double.parseDouble(chuMinField.getText());
            configDao.adicionarAtributo("chuvaMinima", chuvaMinima);

            ptoOrvalhoMaximo = Double.parseDouble(ptoOrvMaxField.getText());
            configDao.adicionarAtributo("ptoOrvalhoMaximo", ptoOrvalhoMaximo);

            ptoOrvalhoMinimo = Double.parseDouble(ptoOrvMinField.getText());
            configDao.adicionarAtributo("ptoOrvalhoMinimo", ptoOrvalhoMinimo);

            rajVentoMaximo = Double.parseDouble(rajVenMaxField.getText());
            configDao.adicionarAtributo("rajVentoMaximo", rajVentoMaximo);

            rajVentoMinimo = Double.parseDouble(rajVenMinField.getText());
            configDao.adicionarAtributo("rajVentoMinimo", rajVentoMinimo);

            // System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e
            // retorno
            // System.out.println("Temperatura mínima: " + tempMinima);

            // System.out.println("Dado Registrado: " + registros);

            // verificarRegistros();

            registroDAO.updateData(null, Double.toString(tempMaxima), Double.toString(tempMinima),
                    Double.toString(umiMaxima), Double.toString(umiMinima), Double.toString(presMaxima),
                    Double.toString(presMinima), Double.toString(velVentoMaxima), Double.toString(velVentoMinima),
                    Double.toString(nebuMaxima), Double.toString(nebuMinima), Double.toString(dirVentoMaxima),
                    Double.toString(dirVentoMinima), Double.toString(ptoOrvalhoMaximo),
                    Double.toString(ptoOrvalhoMinimo), Double.toString(rajVentoMaximo), Double.toString(rajVentoMinimo),
                    Double.toString(insoMaxima), Double.toString(insoMinima), Double.toString(chuvaMaxima),
                    Double.toString(chuvaMinima));
            visualizarListas();
            showAlert("Sucesso!", "Sucesso!", "Lista atualizada e configurações salvas com sucesso!");

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
            showAlert("Erro!", "Erro!", "Por favor, insira valores válidos para as temperaturas!");

        }
    }

    private String formatDouble(double value) {
        return String.format("%.2f", value);
    }

    public static String convertListToString(List<Double> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%.2f", list.get(i)));
            if (i < list.size() - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public Map<String, String> addBoxPlotData(LocalDate dataInicial, LocalDate dataFinal, String tipoDado,
            String cidadeEscolhida) {
        ArrayList<Double> valores = registroDAO.getSpecificData(dataInicial, dataFinal, tipoDado, cidadeEscolhida);
        DecimalFormat df = new DecimalFormat("#.00");

        if (!valores.isEmpty()) {
            double[] valoresArray = new double[valores.size()];
            for (int i = 0; i < valores.size(); i++) {
                valoresArray[i] = valores.get(i);
            }
            RelatorioBoxplot boxplot = RelatorioBoxplot.calculateSummaryStatistics(valoresArray);

            Map<String, String> row = new HashMap<>();
            row.put("dado_registro", tipoDado);
            row.put("q1", formatDouble(boxplot.getQ1()));
            row.put("q2", formatDouble(boxplot.getMedian()));
            row.put("q3", formatDouble(boxplot.getQ3()));
            row.put("li", formatDouble(boxplot.getLowerLimit()));
            row.put("ls", formatDouble(boxplot.getUpperLimit()));
            row.put("out", convertListToString(boxplot.getOutliers()));

            return row;
        } else {
            Map<String, String> row = new HashMap<>();
            row.put("dado_registro", tipoDado);
            row.put("q1", "Nao possui");
            row.put("q2", "Nao possui");
            row.put("q3", "Nao possui");
            row.put("li", "Nao possui");
            row.put("ls", "Nao possui");
            row.put("out", "Nao possui");
            return row;
        }

    }

    @FXML
    public void baixarRelatorioBoxPlot() throws IOException {
        String cidadeEscolhida = cityComboBoxBoxPlot.getValue();
        LocalDate dataInicial = startDatePickerBoxPlot.getValue();
        LocalDate dataFinal = endDatePickerBoxPlot.getValue();
        if (cidadeEscolhida != null && dataInicial != null && dataFinal != null) {
            FileWriter escritor = new FileWriter(nomeArquivoBoxPlot, StandardCharsets.ISO_8859_1, false);
            escritor.write(
                    "Primeiro Quartil (Q1);Mediana (Q2);Terceiro Quartil (Q3);Limite Inferior;Limite Superior;Outliers;\n");
            Map<String, String> rowTemp = addBoxPlotData(dataInicial, dataFinal, "temperatura", cidadeEscolhida);
            escritor.write(
                    rowTemp.get("dado_registro")+ ";" +
                    rowTemp.get("q1") + ";" +
                    rowTemp.get("q2") + ";" +
                    rowTemp.get("q3") + ";" +
                    rowTemp.get("li") + ";" +
                    rowTemp.get("ls") + ";" +
                    rowTemp.get("out") + ";" + "\n");

            Map<String, String> rowPressao = addBoxPlotData(dataInicial, dataFinal, "pressao", cidadeEscolhida);
            escritor.write(
                    rowPressao.get("dado_registro")+ ";" +        
                    rowPressao.get("q1") + ";" +
                    rowPressao.get("q2") + ";" +
                    rowPressao.get("q3") + ";" +
                    rowPressao.get("li") + ";" +
                    rowPressao.get("ls") + ";" +
                    rowPressao.get("out") + ";" + "\n");

            Map<String, String> rowVelVento = addBoxPlotData(dataInicial, dataFinal, "velVento", cidadeEscolhida);
            escritor.write(
                    rowVelVento.get("dado_registro")+ ";" +
                    rowVelVento.get("q1") + ";" +
                    rowVelVento.get("q2") + ";" +
                    rowVelVento.get("q3") + ";" +
                    rowVelVento.get("li") + ";" +
                    rowVelVento.get("ls") + ";" +
                    rowVelVento.get("out") + ";" + "\n");

            Map<String, String> rowChuva = addBoxPlotData(dataInicial, dataFinal, "chuva", cidadeEscolhida);
            escritor.write( 
                    rowChuva.get("dado_registro")+ ";" +
                    rowChuva.get("q1") + ";" +
                    rowChuva.get("q2") + ";" +
                    rowChuva.get("q3") + ";" +
                    rowChuva.get("li") + ";" +
                    rowChuva.get("ls") + ";" +
                    rowChuva.get("out") + ";" + "\n");

            Map<String, String> rowPtoOrvalho = addBoxPlotData(dataInicial, dataFinal, "ptoOrvalho", cidadeEscolhida);
            escritor.write( 
                    rowPtoOrvalho.get("dado_registro")+ ";" +
                    rowPtoOrvalho.get("q1") + ";" +
                    rowPtoOrvalho.get("q2") + ";" +
                    rowPtoOrvalho.get("q3") + ";" +
                    rowPtoOrvalho.get("li") + ";" +
                    rowPtoOrvalho.get("ls") + ";" +
                    rowPtoOrvalho.get("out") + ";" + "\n");

            Map<String, String> rowUmi = addBoxPlotData(dataInicial, dataFinal, "umiIns", cidadeEscolhida);
            escritor.write( 
                    rowUmi.get("dado_registro")+ ";" +
                    rowUmi.get("q1") + ";" +
                    rowUmi.get("q2") + ";" +
                    rowUmi.get("q3") + ";" +
                    rowUmi.get("li") + ";" +
                    rowUmi.get("ls") + ";" +
                    rowUmi.get("out") + ";" + "\n");

            Map<String, String> rowNebulosidade = addBoxPlotData(dataInicial, dataFinal, "nebulosidade",
                    cidadeEscolhida);
            escritor.write(
                    rowNebulosidade.get("dado_registro")+ ";" + 
                    rowNebulosidade.get("q1") + ";" +
                    rowNebulosidade.get("q2") + ";" +
                    rowNebulosidade.get("q3") + ";" +
                    rowNebulosidade.get("li") + ";" +
                    rowNebulosidade.get("ls") + ";" +
                    rowNebulosidade.get("out") + ";" + "\n");

            Map<String, String> rowRadiacao = addBoxPlotData(dataInicial, dataFinal, "radiacao", cidadeEscolhida);
            escritor.write(
                    rowRadiacao.get("dado_registro")+ ";" +
                    rowRadiacao.get("q1") + ";" +
                    rowRadiacao.get("q2") + ";" +
                    rowRadiacao.get("q3") + ";" +
                    rowRadiacao.get("li") + ";" +
                    rowRadiacao.get("ls") + ";" +
                    rowRadiacao.get("out") + ";" + "\n");

            Map<String, String> rowDirVento = addBoxPlotData(dataInicial, dataFinal, "dirVento", cidadeEscolhida);
            escritor.write( 
                    rowDirVento.get("dado_registro")+ ";" +
                    rowDirVento.get("q1") + ";" +
                    rowDirVento.get("q2") + ";" +
                    rowDirVento.get("q3") + ";" +
                    rowDirVento.get("li") + ";" +
                    rowDirVento.get("ls") + ";" +
                    rowDirVento.get("out") + ";" + "\n");

            Map<String, String> rowInsolacao = addBoxPlotData(dataInicial, dataFinal, "insolacao", cidadeEscolhida);
            escritor.write(
                    rowInsolacao.get("dado_registro")+ ";" + 
                    rowInsolacao.get("q1") + ";" +
                    rowInsolacao.get("q2") + ";" +
                    rowInsolacao.get("q3") + ";" +
                    rowInsolacao.get("li") + ";" +
                    rowInsolacao.get("ls") + ";" +
                    rowInsolacao.get("out") + ";" + "\n");

            Map<String, String> rowRajVento = addBoxPlotData(dataInicial, dataFinal, "rajVento", cidadeEscolhida);
            escritor.write(
                    rowRajVento.get("dado_registro")+ ";" + 
                    rowRajVento.get("q1") + ";" +
                    rowRajVento.get("q2") + ";" +
                    rowRajVento.get("q3") + ";" +
                    rowRajVento.get("li") + ";" +
                    rowRajVento.get("ls") + ";" +
                    rowRajVento.get("out") + ";" + "\n");

            // Escreve todos os dados do Buffer no arquivo
            escritor.flush();

            // Fecha o recurso de escrita
            escritor.close();
        }
    }

    @FXML
    public void gerarRelatorioBoxPlot() {
        String cidadeEscolhida = cityComboBoxBoxPlot.getValue();
        LocalDate dataInicial = startDatePickerBoxPlot.getValue();
        LocalDate dataFinal = endDatePickerBoxPlot.getValue();
        if (cidadeEscolhida != null && dataInicial != null && dataFinal != null) {

            // System.out.println(registroDAO.getSpecificData(dataInicial, dataFinal,
            // "temperatura", cidadeEscolhida));
            // System.out.println(registroDAO.getSpecificData(dataInicial, dataFinal,
            // "temperatura", cidadeEscolhida));

            ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();
            dados.add(addBoxPlotData(dataInicial, dataFinal, "temperatura", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "pressao", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "velVento", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "chuva", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "ptoOrvalho", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "umiIns", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "nebulosidade", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "radiacao", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "dirVento", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "insolacao", cidadeEscolhida));
            dados.add(addBoxPlotData(dataInicial, dataFinal, "rajVento", cidadeEscolhida));

            tableViewBoxPlot.setItems(dados);

            // tableViewBoxPlot.setItems(dadosRelatorio);
        } else {
            // Exibir mensagem de erro se os campos não estiverem preenchidos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, selecione a cidade e as datas inicial e final.");
            alert.showAndWait();
        }
    }

    @FXML
    public void gerarrelatorioperiocidade() {
        String cidadeEscolhida = cityComboBox.getValue();
        LocalDate dataInicial = startDatePicker.getValue();
        LocalDate dataFinal = endDatePicker.getValue();

        // Verifica se as datas foram selecionadas
        if (cidadeEscolhida != null && dataInicial != null && dataFinal != null) {
            LocalDate date = dataInicial;
            filtrarRegistrosPorDia(cidadeEscolhida, date, dataInicial, dataFinal);
        } else {
            // Exibir mensagem de erro se os campos não estiverem preenchidos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, selecione a cidade e as datas inicial e final.");
            alert.showAndWait();
        }
    }

    // Método para filtrar os registros para um dia específico e cidade
    private void filtrarRegistrosPorDia(String cidadeEscolhida, LocalDate data, LocalDate dataInicial,
            LocalDate dataFinal) {
        // listrelatorio.getItems().add("Cidade: " + cidadeEscolhida + " | Período: " +
        // dataInicial + " a " + dataFinal);
        ObservableList<Map<String, String>> dadosRelatorio = FXCollections.observableArrayList();

        dadosRelatorio = registroDAO.filterBetweenDates(cidadeEscolhida, dataInicial, dataFinal);

        tableViewRelatorio.setItems(dadosRelatorio);
    }

    // Exportar dados CSV
    public void exportarrelatorioperiocidade(@SuppressWarnings("exports") ActionEvent actionEvent) throws IOException {
        String desktopPath = System.getProperty("user.home") + "/Documents/";
        String nomeArquivo = desktopPath + "RelatorioRegistro.csv";

        String cidadeEscolhida = cityComboBox.getValue();
        LocalDate dataInicial = startDatePicker.getValue();
        LocalDate dataFinal = endDatePicker.getValue();

        registroDAO.saveRelatory(cidadeEscolhida, dataInicial, dataFinal, nomeArquivo);
    }
    // Preciso fazer um novo botão para gerar o relatorio na tela
    // Preciso fazer a parte do relatorio Manual

    String desktopPath = System.getProperty("user.home") + "/Documents/";
    String nomeArquivo = desktopPath + "RelatorioRegistro.csv";
    String nomeArquivoBoxPlot = desktopPath + "RelatorioRegistroBoxPlot.csv";
    String nomeArquivoManual = desktopPath + "RelatorioRegistroManual.csv";

    // Modulo para adiconar os registros ao arquivo CSV
    public void baixarRelatorio() {
        try {
            // Verificar se o aquivo já existe
            boolean arquivoExiste = new File(nomeArquivo).exists();
            // Abre o escritor para adicionar dados ao arquivo
            // if(!arquivoExiste){
            // if(registros instanceof RegistroAutomatico){
            // escritor.write("Temp.(C);Umi.(%);Pto Orvalho(C);Pressao(hPa);Vel.
            // Vento(m/s);Dir. Vento(m/s);Raj. Vento(m/s);Radiacao(KJ/m²);Chuva(mm)\n");
            // }
            // else{
            // escritor.write("Temp.(C);Umi.(%);Pressao(hPa);Vel. Vento(m/s);Dir.
            // Vento(m/s);Nebulosidade(Decimos);Insolacao(h);Chuva(mm)\n");
            // }
            // }

            if (quantidadeRegistrosTemp > 0) {
                FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, false);
                escritor.write(
                        "Temp.(C);Umi.(%);Pto Orvalho(C);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Raj. Vento(m/s);Radiacao(KJ/m²);Chuva(mm)\n");

                escritor.write(String.format("%.2f", mediaTemp) + ";" + String.format("%.2f", mediaUmi) + ";"
                        + String.format("%.2f", mediaPtoOrvalho) + ";" + String.format("%.2f", mediaPressao) + ";"
                        + String.format("%.2f", mediaVelVento) + ";" + String.format("%.2f", mediaDirVento) + ";"
                        + String.format("%.2f", mediaRajVento) + ";" + String.format("%.2f", mediaRadiacao) + ";"
                        + String.format("%.2f", mediaChuva) + ";" + "\n");
                escritor.write("\n");
                // Escreve todos os dados do Buffer no arquivo
                escritor.flush();

                // Fecha o recurso de escrita
                escritor.close();
            }

            if (quantidadeRegistrosTempM > 0) {
                FileWriter escritor = new FileWriter(nomeArquivoManual, StandardCharsets.ISO_8859_1, false);
                escritor.write(
                        "Temp.(C);Umi.(%);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Nebulosidade(Decimos);Insolacao(h);Chuva(mm)\n");
                escritor.write(String.format("%.2f", mediaTempM) + ";" + String.format("%.2f", mediaUmiM) + ";"
                        + String.format("%.2f", mediaPressaoM) + ";" + String.format("%.2f", mediaVelVentoM) + ";"
                        + String.format("%.2f", mediaDirVentoM) + ";" + String.format("%.2f", mediaNebulosidadeM) + ";"
                        + String.format("%.2f", mediaInsolacaoM) + ";" + String.format("%.2f", mediaChuvaM) + ";"
                        + "\n");
                escritor.write("\n");
                // Escreve todos os dados do Buffer no arquivo
                escritor.flush();

                // Fecha o recurso de escrita
                escritor.close();
            }

            // System.out.println("mediaTemperatura: " + somaTemp + ", " +
            // quantidadeRegistrosTemp + ", " + mediaTemp);
            // System.out.println("mediaUmi: " + somaUmi + ", " + quantidadeRegistrosUmi +
            // ", " + mediaUmi);
            // System.out.println("mediaPtoOrvalho: " + somaPtoOrvalho + ", " +
            // quantidadeRegistrosPtoOrvalho + ", " + mediaPtoOrvalho);
            // System.out.println("mediaPressao: " + somaPressao + ", " +
            // quantidadeRegistrosPressao + ", " + mediaPressao);
            // System.out.println("mediaVelVento: " + somaVelVento + ", " +
            // quantidadeRegistrosVelVento + ", " + mediaVelVento);
            // System.out.println("mediaDirVento: " + somaDirVento + ", " +
            // quantidadeRegistrosDirVento + ", " + mediaDirVento);
            // System.out.println("mediaRajVento: " + somaRajVento + ", " +
            // quantidadeRegistrosRajVento + ", " + mediaRajVento);
            // System.out.println("mediaRadiacao: " + somaRadiacao + ", " +
            // quantidadeRegistrosRadiacao + ", " + mediaRadiacao);
            // System.out.println("mediaChuva: " + somaChuva + ", " +
            // quantidadeRegistrosChuva + ", " + mediaChuva);
            // System.out.println("mediaUmi: " + somaUmi + ", " + quantidadeRegistrosUmi +
            // ", " + mediaUmi);

            // System.out.println("mediaTemperatura: " + somaTemp + ", " +
            // quantidadeRegistrosTemp + ", " + mediaTemp);
            // System.out.println("mediaPressao: " + somaPressao + ", " +
            // quantidadeRegistrosPressao + ", " + mediaPressao);
            // System.out.println("mediaVelVento: " + somaVelVento + ", " +
            // quantidadeRegistrosVelVento + ", " + mediaVelVento);
            // System.out.println("mediaDirVento: " + somaDirVento + ", " +
            // quantidadeRegistrosDirVento + ", " + mediaDirVento);
            // System.out.println("mediaNebulosidade: " + somaNebulosidade + ", " +
            // quantidadeRegistrosNebulosidade + ", " + mediaNebulosidade);
            // System.out.println("mediaInsolacao: " + somaInsolacao + ", " +
            // quantidadeRegistrosInsolacao + ", " + mediaInsolacao);
            // System.out.println("mediaChuva: " + somaChuva + ", " +
            // quantidadeRegistrosChuva + ", " + mediaChuva);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

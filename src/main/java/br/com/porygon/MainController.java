package br.com.porygon;

import br.com.porygon.dao.ArquivoDAO;
// import javafx.collections.ObservableList;
import br.com.porygon.dao.ConfiguracaoDAO;
import br.com.porygon.dao.RegistroDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;

import java.nio.charset.StandardCharsets;
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

    @FXML
    private ListView<String> listrelatorio; // Atributo para visualizar a lista do relatorio (Data/Periodo) na tela

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

    private Callback<TableColumn.CellDataFeatures<Map<String, String>, String>, ObservableValue<String>> createCellFactory(String columnName) {
        return param -> {
            String value = param.getValue().get(columnName);
            return new SimpleStringProperty(value);
        };
    }

    public void initialize() {

        // Fazer esse cara num botão de análise
        dataHoraSusColumn.setCellValueFactory(createCellFactory("data_hora_sus"));
        tipoArquivoSusColumn.setCellValueFactory(createCellFactory("tipo_arquivo_sus"));
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

        System.out.println();
    }

    public String stringify(Double dado) {
        if (dado == null) {
            return null;
        }
        return String.format("%.2f", dado);
    }

    // Método para colher informações das listas geradas no método
    // verificarRegistros e vincular com o fxml
    private void visualizarListas() {
        RegistroDAO registroDAO = new RegistroDAO();
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
//            cityComboBox.setValue(opcoes.getFirst());

            Arquivo arquivo = new Arquivo();
            arquivo.setConteudo(selectedFile);
            arquivo.tratar(arquivoId);
            RegistroDAO registroDAO = new RegistroDAO();
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

            registroDAO.updateData(arquivoId, tempMaxima, tempMinima, umiMaxima, umiMinima, presMaxima, presMinima, velVentoMaxima, velVentoMinima, nebuMaxima, nebuMinima, dirVentoMaxima, dirVentoMinima, ptoOrvalhoMaximo, ptoOrvalhoMinimo, rajVentoMaximo, rajVentoMinimo, insoMaxima, insoMinima, chuvaMaxima, chuvaMinima);
            showAlert("Arquivo selecionado!", "Arquivo adicionado com sucesso!", "O arquivo " + selectedFile.getName() + " foi selecionado");
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

            tempLabel.setText("Valores Cadastrados");

            // System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e
            // retorno
            // System.out.println("Temperatura mínima: " + tempMinima);

            // System.out.println("Dado Registrado: " + registros);

            // verificarRegistros();
            RegistroDAO registroDAO = new RegistroDAO();

            registroDAO.updateData(null, Double.toString(tempMaxima), Double.toString(tempMinima), Double.toString(umiMaxima), Double.toString(umiMinima), Double.toString(presMaxima), Double.toString(presMinima), Double.toString(velVentoMaxima), Double.toString(velVentoMinima), Double.toString(nebuMaxima), Double.toString(nebuMinima), Double.toString(dirVentoMaxima), Double.toString(dirVentoMinima), Double.toString(ptoOrvalhoMaximo), Double.toString(ptoOrvalhoMinimo), Double.toString(rajVentoMaximo), Double.toString(rajVentoMinimo), Double.toString(insoMaxima), Double.toString(insoMinima), Double.toString(chuvaMaxima), Double.toString(chuvaMinima));
            visualizarListas();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
            tempLabel.setText("Por favor, insira valores válidos para as temperaturas.");
        }
    }

    // private void verificarRegistros() {
    // for (Registro registro : registros) {
    // //verificar lista de regras
    // //se regra aplicada, entao verificar registro com aquela regra
    // if (registro instanceof RegistroAutomatico) {
    // RegistroAutomatico regAut = (RegistroAutomatico) registro;
    //
    // if (regAut.getTemperatura() != null && (regAut.getTemperatura() >= tempMaxima
    // || regAut.getTemperatura() <= tempMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getUmiIns() != null && (regAut.getUmiIns() >= umiMaxima ||
    // regAut.getUmiIns() <= umiMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getPressaoIns() != null && (regAut.getPressaoIns() >=
    // presMaxima || regAut.getPressaoIns() <= presMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getVelVento() != null && (regAut.getVelVento() >=
    // velVentoMaxima || regAut.getVelVento() <= velVentoMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getDirVento() != null && (regAut.getDirVento() >=
    // dirVentoMaxima || regAut.getDirVento() <= dirVentoMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getPtoOrvalhoIns() != null && (regAut.getPtoOrvalhoIns() >=
    // ptoOrvalhoMaximo || regAut.getPtoOrvalhoIns() <= ptoOrvalhoMinimo)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getRajVento() != null && (regAut.getRajVento() >=
    // rajVentoMaximo || regAut.getRajVento() <= rajVentoMinimo)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getChuva() != null && (regAut.getChuva() >= chuvaMaxima ||
    // regAut.getChuva() <= chuvaMinima)) {
    // dadoSuspeito.add(regAut);
    // } else if (regAut.getTemperatura() == null && regAut.getUmiIns() == null &&
    // regAut.getPressaoIns() == null && regAut.getVelVento() == null &&
    // regAut.getDirVento() == null && regAut.getPtoOrvalhoIns() == null &&
    // regAut.getRajVento() == null && regAut.getChuva() == null){
    // dadosNulos.add(regAut);
    // }
    // else {
    // dadoApurado.add(regAut);
    // }
    //
    // } else if (registro instanceof RegistroManual) {
    // RegistroManual regManual = (RegistroManual) registro;
    //
    // if (regManual.getTemperatura() != null && (regManual.getTemperatura() >=
    // tempMaxima || regManual.getTemperatura() <= tempMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getUmi() != null && (regManual.getUmi() >= umiMaxima ||
    // regManual.getUmi() <= umiMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getPressao() != null && (regManual.getPressao() >=
    // presMaxima || regManual.getPressao() <= presMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getVelVento() != null && (regManual.getVelVento() >=
    // velVentoMaxima || regManual.getVelVento() <= velVentoMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getDirVento() != null && (regManual.getDirVento() >=
    // dirVentoMaxima || regManual.getDirVento() <= dirVentoMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getNebulosidade() != null &&
    // (regManual.getNebulosidade() >= nebuMaxima || regManual.getNebulosidade() <=
    // nebuMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getInsolacao() != null && (regManual.getInsolacao() >=
    // insoMaxima || regManual.getInsolacao() <= insoMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getChuva() != null && (regManual.getChuva() >=
    // chuvaMaxima || regManual.getChuva() <= chuvaMinima)) {
    // dadoSuspeito.add(regManual);
    // } else if (regManual.getTemperatura() == null && regManual.getUmi() == null
    // && regManual.getPressao() == null && regManual.getVelVento() == null &&
    // regManual.getDirVento() == null && regManual.getChuva() == null &&
    // regManual.getNebulosidade() == null) {
    // dadosNulos.add(regManual);
    // } else {
    // dadoApurado.add(regManual);
    // }
    // }
    // }
    // visualizarListas();
    // }

    // RELATÓRIO PERIOCIDADE por CIDADE
    // Criando métodos para o relatório. Converte as horas, para criar intervalos de
    // tempo. Calcula a média dos variavéis Ins.
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
    private void filtrarRegistrosPorDia(String cidadeEscolhida, LocalDate data, LocalDate dataInicial, LocalDate dataFinal) {
        listrelatorio.getItems().add("Cidade: " + cidadeEscolhida + " | Período: " + dataInicial + " a " + dataFinal);

        for (Registro registro : dadoApurado) {

            if (registro.getCidade().equals(cidadeEscolhida) && !(data.isBefore(dataInicial) || data.isAfter(dataFinal))) {
                StringBuilder registroTexto = new StringBuilder();
                System.out.println(registro.getCidade());

                // Adicione aqui a lógica para obter os valores relevantes do registro
                if (registro instanceof RegistroAutomatico) {
                    RegistroAutomatico regAutomatico = (RegistroAutomatico) registro;
                    registroTexto.append("Registro Automático - ");

                    // formata a hora
                    String horaOriginal = regAutomatico.getHora();
                    String horaFormatada = horaOriginal.substring(0, 2) + ":" + horaOriginal.substring(2);

                    registroTexto.append("Hora: ").append(horaFormatada).append("  ");
                    registroTexto.append("Temperatura Ins: ").append(regAutomatico.getTemperatura()).append(" °C ");
                    registroTexto.append("Umidade Ins: ").append(regAutomatico.getUmiIns()).append(" % ");
                    registroTexto.append("Pto Orvalho Ins: ").append(regAutomatico.getPtoOrvalhoIns()).append(" C ");
                    registroTexto.append("Pressao Ins: ").append(regAutomatico.getPressaoIns()).append(" hPa ");

                    listrelatorio.getItems().add(registroTexto.toString());

                } else if (registro instanceof RegistroManual) {
                    RegistroManual regManual = (RegistroManual) registro;
                    registroTexto.append("Registro Manual - ");

                    // formata a hora
                    String horaOriginal = regManual.getHora();
                    String horaFormatada = horaOriginal.substring(0, 2) + ":" + horaOriginal.substring(2);
                    registroTexto.append("Hora: ").append(horaFormatada).append("  ");

                    // revisar os tipos de dados
                    registroTexto.append("Temperatura Ins: ").append(String.format("%.2f", regManual.getTemperatura())).append(" °C ");
                    registroTexto.append("Umidade: ").append(regManual.getUmi()).append(" % ");
                    registroTexto.append("Pressão: ").append(regManual.getPressao()).append(" hPa ");
                    registroTexto.append("Velocidade do Vento: ").append(regManual.getVelVento()).append(" m/s ");
                    registroTexto.append("Direção do Vento: ").append(regManual.getDirVento()).append(" m/s ");
                    registroTexto.append("Nebulosidade: ").append(regManual.getNebulosidade()).append("  ");
                    registroTexto.append("Insolação: ").append(regManual.getInsolacao()).append(" h ");
                    registroTexto.append("Chuva: ").append(regManual.getChuva()).append(" mm ");

                    listrelatorio.getItems().add(registroTexto.toString());
                }
            }
        }
    }

    // Exportar dados CSV
    public void exportarrelatorioperiocidade(@SuppressWarnings("exports") ActionEvent actionEvent) throws IOException {
        String desktopPath = System.getProperty("user.home") + "/Documents/";
        String nomeArquivoManual = desktopPath + "RelatorioRegistroManual.csv";
        FileWriter escritorManual = new FileWriter(nomeArquivoManual, StandardCharsets.ISO_8859_1, false);
        escritorManual.write("Data; Hora ;Temperatura Ins (°C) ;Umidade (%) ;Pressão (hPa) ;Velocidade do Vento (m/s) ; Direção do Vento (m/s) ; Nebulosidade ;Insolação (h) ; Chuva(mm)\n");

        String nomeArquivo = desktopPath + "RelatorioRegistroAuto.csv";
        FileWriter escritorAuto = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, false);
        escritorAuto.write("Data; Hora ;Temperatura Ins (°C) ;Umidade Ins (%) ;Pto Orvalho Ins (C) ;Pressao Ins (hPa) \n");

        for (Registro registro : dadoApurado) {

            if (registro instanceof RegistroAutomatico) {
                RegistroAutomatico regAutomatico = (RegistroAutomatico) registro;
                String horaOriginal = regAutomatico.getHora();
                String horaFormatada = horaOriginal.substring(0, 2) + ":" + horaOriginal.substring(2);

                escritorAuto.write(regAutomatico.getData().toString() + ";" + horaFormatada + ";" + String.format("%.2f", regAutomatico.getTemperatura()) + ";" + String.format("%.2f", regAutomatico.getUmiIns()) + ";" + String.format("%.2f", regAutomatico.getPtoOrvalhoIns()) + ";" + String.format("%.2f", regAutomatico.getPressaoIns()) + ";" + "\n");

            } else if (registro instanceof RegistroManual) {
                RegistroManual regManual = (RegistroManual) registro;
                String horaOriginal = regManual.getHora();
                String horaFormatada = horaOriginal.substring(0, 2) + ":" + horaOriginal.substring(2);

                escritorAuto.write(regManual.getData().toString() + ";" + horaFormatada + ";" + String.format("%.2f", regManual.getTemperatura()) + ";" + String.format("%.2f", regManual.getUmi()) + ";" + String.format("%.2f", regManual.getPressao()) + ";" + String.format("%.2f", regManual.getVelVento()) + ";" + String.format("%.2f", regManual.getDirVento()) + ";" + String.format("%.2f", regManual.getNebulosidade()) + ";" + String.format("%.2f", regManual.getInsolacao()) + ";" + String.format("%.2f", regManual.getChuva()) + ";" + "\n");
            }

        }
        // Escreve todos os dados do Buffer no arquivo
        escritorManual.flush();
        escritorManual.close();

        escritorAuto.flush();
        escritorAuto.close();
    }
    // Preciso fazer um novo botão para gerar o relatorio na tela
    // Preciso fazer a parte do relatorio Manual

    String desktopPath = System.getProperty("user.home") + "/Documents/";
    String nomeArquivo = desktopPath + "RelatorioRegistro.csv";
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
                escritor.write("Temp.(C);Umi.(%);Pto Orvalho(C);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Raj. Vento(m/s);Radiacao(KJ/m²);Chuva(mm)\n");

                escritor.write(String.format("%.2f", mediaTemp) + ";" + String.format("%.2f", mediaUmi) + ";" + String.format("%.2f", mediaPtoOrvalho) + ";" + String.format("%.2f", mediaPressao) + ";" + String.format("%.2f", mediaVelVento) + ";" + String.format("%.2f", mediaDirVento) + ";" + String.format("%.2f", mediaRajVento) + ";" + String.format("%.2f", mediaRadiacao) + ";" + String.format("%.2f", mediaChuva) + ";" + "\n");
                escritor.write("\n");
                // Escreve todos os dados do Buffer no arquivo
                escritor.flush();

                // Fecha o recurso de escrita
                escritor.close();
            }

            if (quantidadeRegistrosTempM > 0) {
                FileWriter escritor = new FileWriter(nomeArquivoManual, StandardCharsets.ISO_8859_1, false);
                escritor.write("Temp.(C);Umi.(%);Pressao(hPa);Vel. Vento(m/s);Dir. Vento(m/s);Nebulosidade(Decimos);Insolacao(h);Chuva(mm)\n");
                escritor.write(String.format("%.2f", mediaTempM) + ";" + String.format("%.2f", mediaUmiM) + ";" + String.format("%.2f", mediaPressaoM) + ";" + String.format("%.2f", mediaVelVentoM) + ";" + String.format("%.2f", mediaDirVentoM) + ";" + String.format("%.2f", mediaNebulosidadeM) + ";" + String.format("%.2f", mediaInsolacaoM) + ";" + String.format("%.2f", mediaChuvaM) + ";" + "\n");
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

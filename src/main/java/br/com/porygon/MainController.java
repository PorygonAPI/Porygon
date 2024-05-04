package br.com.porygon;

// import javafx.collections.ObservableList;
import br.com.porygon.dao.ConfiguracaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
    private ListView<String> listViewApurado; // Atributo para visualizar a lista de registros apurados na tela
    @FXML
    private ListView<String> listViewSuspeito; // Atributo para visualizar a lista de registros apurados na tela
    @FXML
    private ListView<String> listrelatorio; // Atributo para visualizar a lista do relatorio (Data/Periodo) na tela

    @FXML
    private ComboBox<String> cityComboBox;


    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método
    List<Registro> dadosNulos = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método

    String[] cidadesLista = {};

    // List<Registro> listrelatorio = new ArrayList<>(); //Lista dos dados do relatorio por data e período

    // Métodos de acesso

    public void initialize() {
        ConfiguracaoDAO configDao = new ConfiguracaoDAO();
        tempMaxField.setText(configDao.recuperarAtributos("tempMaxima"));
        tempMinField.setText(configDao.recuperarAtributos("tempMinima"));
        umiMaxField.setText(configDao.recuperarAtributos("umiMaxima"));
        umiMinField.setText(configDao.recuperarAtributos("umiMinima"));
        presMaxField.setText(configDao.recuperarAtributos("presMaxima"));
        presMinField.setText(configDao.recuperarAtributos("presMinima"));
        velVentoMaxField.setText(configDao.recuperarAtributos("velVentoMaxima"));
        velVentoMinField.setText(configDao.recuperarAtributos("velVentoMinima"));
        nebuMaxField.setText(configDao.recuperarAtributos("nebuMaxima"));
        nebuMinField.setText(configDao.recuperarAtributos("nebuMinima"));
        dirVentoMaxField.setText(configDao.recuperarAtributos("dirVentoMaxima"));
        dirVentoMinField.setText(configDao.recuperarAtributos("dirVentoMinima"));
        ptoOrvMaxField.setText(configDao.recuperarAtributos("ptoOrvalhoMaximo"));
        ptoOrvMinField.setText(configDao.recuperarAtributos("ptoOrvalhoMinimo"));
        rajVenMaxField.setText(configDao.recuperarAtributos("rajVentoMaximo"));
        rajVenMinField.setText(configDao.recuperarAtributos("rajVentoMinimo"));
        insoMaxField.setText(configDao.recuperarAtributos("insoMaxima"));
        insoMinField.setText(configDao.recuperarAtributos("insoMinima"));
        chuMaxField.setText(configDao.recuperarAtributos("chuvaMaxima"));
        chuMinField.setText(configDao.recuperarAtributos("chuvaMinima"));
    }
    public String stringify(Double dado) {
        if (dado == null) {
            return null;
        }
        return String.format("%.2f", dado);
    }

    // Método para colher informações das listas geradas no método verificarRegistros e vincular com o fxml
    public void visualizarListas() {
        listViewApurado.getItems().clear();
        for (Registro registro : dadoApurado) {
            String listViewTextApurado;
            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                listViewTextApurado = "Automático - Data: " + regAut.getData().format(formatter) + " | Hora: " + regAut.getHora()
                        + " | Temperatura (Ins) : " + stringify(regAut.getTemperatura()) + " | Temperatura Máxima :"
                        + stringify(regAut.getTempMax()) + " | Temperatura Mínima: " + stringify(regAut.getTempMin())
                        + " | Umidade (Ins): "
                        + stringify(regAut.getUmiIns()) + " | Orvalho (Ins): " + stringify(regAut.getPtoOrvalhoIns())
                        + " | Pressão (Ins): " + stringify(regAut.getPressaoIns()) + " | Velocidade do Vento:  "
                        + stringify(regAut.getVelVento()) + " | Direção do Vento: " + stringify(regAut.getDirVento())
                        + " | Rajada Vento: " + stringify(regAut.getRajVento()) + " | Radiação "
                        + stringify(regAut.getRadiacao()) + " | Chuva: " + stringify(regAut.getChuva());

            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                listViewTextApurado = "Manual - Data: " + regManual.getData().format(formatter) + " | Hora: " + regManual.getHora()
                        + " | Temperatura : " + stringify(regManual.getTemperatura()) + " | Umidade: "
                        + stringify(regManual.getUmi()) + " | Pressão: " + stringify(regManual.getPressao())
                        + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: "
                        + stringify(regManual.getDirVento()) + " | Nebulosidade: "
                        + stringify(regManual.getNebulosidade()) + " | Insolação:  "
                        + stringify(regManual.getInsolacao()) + " | Temperatura Máxima "
                        + stringify(regManual.getTempMax()) + " | Temperatura Minima:  "
                        + stringify(regManual.getTempMin()) + " | Chuva:  " + stringify(regManual.getChuva());
                System.out.println(listViewTextApurado);
            }
            listViewApurado.getItems().add(listViewTextApurado);
        }
        listViewSuspeito.getItems().clear();
        for (Registro registro : dadoSuspeito) {
            String listViewTextSuspeito;

            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                listViewTextSuspeito = "Automático - Data: " + regAut.getData().format(formatter) + " | Hora: " + regAut.getHora()
                        + " | Temperatura (Ins) : " + stringify(regAut.getTemperatura()) + " | Temperatura Máxima :"
                        + stringify(regAut.getTempMax()) + " | Temperatura Mínima: " + stringify(regAut.getTempMin())
                        + " | Umidade (Ins): "
                        + stringify(regAut.getUmiIns()) + " | Orvalho (Ins): " + stringify(regAut.getPtoOrvalhoIns())
                        + " | Pressão (Ins): " + stringify(regAut.getPressaoIns()) + " | Velocidade do Vento: "
                        + stringify(regAut.getVelVento()) + " | Direção do Vento: " + stringify(regAut.getDirVento())
                        + " | Rajada Vento: " + stringify(regAut.getRajVento()) + " | Radiação "
                        + stringify(regAut.getRadiacao()) + " | Chuva: " + stringify(regAut.getChuva());
            } else {
                // Alterar visualização de lista
                RegistroManual regManual = (RegistroManual) registro;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                listViewTextSuspeito = "Manual - Data: " + regManual.getData().format(formatter) + " | Hora: " + regManual.getHora()
                        + " | Temperatura : " + stringify(regManual.getTemperatura()) + " | Umidade (Ins): "
                        + stringify(regManual.getUmi()) + " | Pressão " + stringify(regManual.getPressao())
                        + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: "
                        + stringify(regManual.getDirVento()) + " | Nebulosidade: "
                        + stringify(regManual.getNebulosidade()) + " | Insolação: "
                        + stringify(regManual.getInsolacao()) + " | Temperatura Máxima "
                        + stringify(regManual.getTempMax()) + " | Temperatura Minima: "
                        + stringify(regManual.getTempMin()) + " | Chuva: " + stringify(regManual.getChuva());
            }
            listViewSuspeito.getItems().add(listViewTextSuspeito);
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
        ObservableList<String> opcoes = FXCollections.observableArrayList(cidadesLista);
        String arquivosSelecionados = "";
        // Exemplo de como você pode manipular os arquivos selecionados
        if (selectedFiles != null) {
            System.out.println("Arquivos selecionados:");
            for (File file : selectedFiles) {
                System.out.println(file.getAbsolutePath());
                arquivosSelecionados = arquivosSelecionados + "\n" + file.getAbsolutePath();
                String[] fileNamePart = file.getName().split(".csv")[0].split("_");
                String cidade = fileNamePart[1];

                if (!opcoes.contains(cidade)) {
                    // Adicionar novo valor ao array
                    opcoes.add(cidade);
                    System.out.println("");
                }

                // Definir a lista de opções para o ComboBox
                cityComboBox.setItems(opcoes);

                // Definir um valor padrão
                cityComboBox.setValue(opcoes.getFirst());

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
    private void verificar(ActionEvent event) { // Método chamado quando o pesquisador clica no botão "Verificar"
        // Transformar os valores de String para Double antes de fazer a comparação com as minimas e máximas informadas pelo pesquisador
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

            // System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            // System.out.println("Temperatura mínima: " + tempMinima);

            //System.out.println("Dado Registrado: " + registros);

            verificarRegistros();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
            tempLabel.setText("Por favor, insira valores válidos para as temperaturas.");
        }
    }

    private void verificarRegistros() {
        for (Registro registro : registros) {
            //verificar lista de regras
            //se regra aplicada, entao verificar registro com aquela regra
            if (registro instanceof RegistroAutomatico) {
                RegistroAutomatico regAut = (RegistroAutomatico) registro;

                if (regAut.getTemperatura() != null && (regAut.getTemperatura() >= tempMaxima || regAut.getTemperatura() <= tempMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getUmiIns() != null && (regAut.getUmiIns() >= umiMaxima || regAut.getUmiIns() <= umiMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getPressaoIns() != null && (regAut.getPressaoIns() >= presMaxima || regAut.getPressaoIns() <= presMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getVelVento() != null && (regAut.getVelVento() >= velVentoMaxima || regAut.getVelVento() <= velVentoMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getDirVento() != null && (regAut.getDirVento() >= dirVentoMaxima || regAut.getDirVento() <= dirVentoMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getPtoOrvalhoIns() != null && (regAut.getPtoOrvalhoIns() >= ptoOrvalhoMaximo || regAut.getPtoOrvalhoIns() <= ptoOrvalhoMinimo)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getRajVento() != null && (regAut.getRajVento() >= rajVentoMaximo || regAut.getRajVento() <= rajVentoMinimo)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getChuva() != null && (regAut.getChuva() >= chuvaMaxima || regAut.getChuva() <= chuvaMinima)) {
                    dadoSuspeito.add(regAut);
                } else if (regAut.getTemperatura() == null && regAut.getUmiIns() == null && regAut.getPressaoIns() == null && regAut.getVelVento() == null && regAut.getDirVento() == null && regAut.getPtoOrvalhoIns() == null && regAut.getRajVento() == null && regAut.getChuva() == null){
                    dadosNulos.add(regAut);
                }
                else {
                    dadoApurado.add(regAut);
                }

            } else if (registro instanceof RegistroManual) {
                RegistroManual regManual = (RegistroManual) registro;

                if (regManual.getTemperatura() != null && (regManual.getTemperatura() >= tempMaxima || regManual.getTemperatura() <= tempMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getUmi() != null && (regManual.getUmi() >= umiMaxima || regManual.getUmi() <= umiMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getPressao() != null && (regManual.getPressao() >= presMaxima || regManual.getPressao() <= presMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getVelVento() != null && (regManual.getVelVento() >= velVentoMaxima || regManual.getVelVento() <= velVentoMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getDirVento() != null && (regManual.getDirVento() >= dirVentoMaxima || regManual.getDirVento() <= dirVentoMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getNebulosidade() != null && (regManual.getNebulosidade() >= nebuMaxima || regManual.getNebulosidade() <= nebuMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getInsolacao() != null && (regManual.getInsolacao() >= insoMaxima || regManual.getInsolacao() <= insoMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getChuva() != null && (regManual.getChuva() >= chuvaMaxima || regManual.getChuva() <= chuvaMinima)) {
                    dadoSuspeito.add(regManual);
                } else if (regManual.getTemperatura() == null && regManual.getUmi() == null && regManual.getPressao() == null && regManual.getVelVento() == null && regManual.getDirVento() == null &&  regManual.getChuva() == null && regManual.getNebulosidade() == null) {
                    dadosNulos.add(regManual);
                } else {
                    dadoApurado.add(regManual);
                }
            }
        }
        visualizarListas();
    }

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
                    registroTexto.append("Temperatura Ins: ").append(regManual.getTemperatura()).append(" °C ");
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

                escritorAuto.write(
                        regAutomatico.getData().toString() + ";" + horaFormatada + ";" +
                        String.format("%.2f", regAutomatico.getTemperatura()) + ";" +
                        String.format("%.2f", regAutomatico.getUmiIns()) + ";" +
                        String.format("%.2f", regAutomatico.getPtoOrvalhoIns()) + ";" +
                        String.format("%.2f", regAutomatico.getPressaoIns()) + ";" + "\n");

            } else if (registro instanceof RegistroManual) {
                RegistroManual regManual = (RegistroManual) registro;
                String horaOriginal = regManual.getHora();
                String horaFormatada = horaOriginal.substring(0, 2) + ":" + horaOriginal.substring(2);

                escritorAuto.write(
                        regManual.getData().toString() + ";" + horaFormatada + ";" +
                                String.format("%.2f", regManual.getTemperatura()) + ";" +
                                String.format("%.2f", regManual.getUmi()) + ";" +
                                String.format("%.2f", regManual.getPressao()) + ";" +
                                String.format("%.2f", regManual.getVelVento()) + ";" +
                                String.format("%.2f", regManual.getDirVento()) + ";" +
                                String.format("%.2f", regManual.getNebulosidade()) + ";" +
                                String.format("%.2f", regManual.getInsolacao()) + ";" +
                                String.format("%.2f", regManual.getChuva()) + ";" +"\n");
            }


        }
            //Escreve todos os dados do Buffer no arquivo
            escritorManual.flush();
            escritorManual.close();

            escritorAuto.flush();
            escritorAuto.close();
        }
}


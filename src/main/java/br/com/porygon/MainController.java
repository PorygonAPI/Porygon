package br.com.porygon;

// import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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
    private ComboBox<String> hourIntervalComboBox;

    // Listas
    List<Registro> registros = new ArrayList<Registro>(); // Lista de registros geral, gerada a partir do upload do
    // arquivo .csv
    List<Registro> dadoSuspeito = new ArrayList<Registro>(); // Lista de registros suspeitos, gerada após a execução do
    // método verificarRegistros
    List<Registro> dadoApurado = new ArrayList<Registro>(); // Lista de registros apurados, gerada a execução do método

    String[] cidadesLista = {};

    // List<Registro> listrelatorio = new ArrayList<>(); //Lista dos dados do
    // relatorio por data e período

    // verificarRegistros

    // Métodos de acesso
    public double getTempMaxima() { return tempMaxima; }
    public void setTempMaxima(double tempMaxima) { this.tempMaxima = tempMaxima; }
    public double getTempMinima() { return tempMinima; }
    public void setTempMinima(double tempMinima) { this.tempMinima = tempMinima; }
    public double getChuvaMinima() { return chuvaMinima; }
    public void setChuvaMinima(double chuvaMinima) { this.chuvaMinima = chuvaMinima; }
    public double getUmiMaxima() { return umiMaxima; }
    public void setUmiMaxima(double umiMaxima) { this.umiMaxima = umiMaxima; }
    public double getUmiMinima() { return umiMinima; }
    public void setUmiMinima(double umiMinima) { this.umiMinima = umiMinima; }
    public double getPresMaxima() { return presMaxima; }
    public void setPresMaxima(double presMaxima) { this.presMaxima = presMaxima; }
    public double getPresMinima() { return presMinima; }
    public void setPresMinima(double presMinima) { this.presMinima = presMinima; }
    public double getVelVentoMaxima() { return velVentoMaxima; }
    public void setVelVentoMaxima(double velVentoMaxima) { this.velVentoMaxima = velVentoMaxima; }
    public double getVelVentoMinima() { return velVentoMinima; }
    public void setVelVentoMinima(double velVentoMinima) { this.velVentoMinima = velVentoMinima; }
    public double getDirVentoMaxima() { return dirVentoMaxima; }
    public void setDirVentoMaxima(double dirVentoMaxima) { this.dirVentoMaxima = dirVentoMaxima; }
    public double getDirVentoMinima() { return dirVentoMinima; }
    public void setDirVentoMinima(double dirVentoMinima) { this.dirVentoMinima = dirVentoMinima; }
    public double getNebuMaxima() { return nebuMaxima; }
    public void setNebuMaxima(double nebuMaxima) { this.nebuMaxima = nebuMaxima; }
    public double getChuvaMaxima() { return chuvaMaxima; }
    public void setChuvaMaxima(double chuvaMaxima) { this.chuvaMaxima = chuvaMaxima; }
    public double getInsoMinima() { return insoMinima; }
    public void setInsoMinima(double insoMinima) { this.insoMinima = insoMinima; }
    public double getInsoMaxima() { return insoMaxima; }
    public void setInsoMaxima(double insoMaxima) { this.insoMaxima = insoMaxima; }
    public double getNebuMinima() { return nebuMinima; }
    public void setNebuMinima(double nebuMinima) { this.nebuMinima = nebuMinima; }
    public double getPtoOrvalhoMaximo() { return ptoOrvalhoMaximo; }
    public void setPtoOrvalhoMaximo(double ptoOrvalhoMaximo) { this.ptoOrvalhoMaximo = ptoOrvalhoMaximo; }
    public double getPtoOrvalhoMinimo() { return ptoOrvalhoMinimo; }
    public void setPtoOrvalhoMinimo(double ptoOrvalhoMinimo) { this.ptoOrvalhoMinimo = ptoOrvalhoMinimo; }
    public double getRajVentoMaximo() { return rajVentoMaximo; }
    public void setRajVentoMaximo(double rajVentoMaximo) { this.rajVentoMaximo = rajVentoMaximo; }
    public double getRajVentoMinimo() { return rajVentoMinimo; }
    public void setRajVentoMinimo(double rajVentoMinimo) { this.rajVentoMinimo = rajVentoMinimo; }

    public String stringify(Double dado) {
        if (dado == null) {
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
                listViewText = "Automático - Data: " + regAut.getData() + " | Hora: " + regAut.getHora()
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
                listViewText = "Manual - Data: " + regManual.getData() + " | Hora: " + regManual.getHora()
                        + " | Temperatura : " + stringify(regManual.getTemperatura()) + " | Umidade: "
                        + stringify(regManual.getUmi()) + " | Pressão: " + stringify(regManual.getPressao())
                        + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: "
                        + stringify(regManual.getDirVento()) + " | Nebulosidade: "
                        + stringify(regManual.getNebulosidade()) + " | Insolação:  "
                        + stringify(regManual.getInsolacao()) + " | Temperatura Máxima "
                        + stringify(regManual.getTempMax()) + " | Temperatura Minima:  "
                        + stringify(regManual.getTempMin()) + " | Chuva:  " + stringify(regManual.getChuva());
            }
            listViewApurado.getItems().add(listViewText);
        }
        listViewSuspeito.getItems().clear();
        for (Registro registro : dadoSuspeito) {
            String listViewText;

            if (registro instanceof RegistroAutomatico regAut) {
                // Alterar visualização de lista
                listViewText = "Automático - Data: " + regAut.getData() + " | Hora: " + regAut.getHora()
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

                listViewText = "Manual - Data: " + regManual.getData() + " | Hora: " + regManual.getHora()
                        + " | Temperatura : " + stringify(regManual.getTemperatura()) + " | Umidade (Ins): "
                        + stringify(regManual.getUmi()) + " | Pressão " + stringify(regManual.getPressao())
                        + " | Velocidade do Vento: " + stringify(regManual.getVelVento()) + " | Direção do Vento: "
                        + stringify(regManual.getDirVento()) + " | Nebulosidade: "
                        + stringify(regManual.getNebulosidade()) + " | Insolação: "
                        + stringify(regManual.getInsolacao()) + " | Temperatura Máxima "
                        + stringify(regManual.getTempMax()) + " | Temperatura Minima: "
                        + stringify(regManual.getTempMin()) + " | Chuva: " + stringify(regManual.getChuva());
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
                hourIntervalComboBox.setValue("1 hora");

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
            velVentoMaxima = Double.parseDouble(velVentoMaxField.getText());
            velVentoMinima = Double.parseDouble(velVentoMinField.getText());
            dirVentoMaxima = Double.parseDouble(dirVentoMaxField.getText());
            dirVentoMinima = Double.parseDouble(dirVentoMinField.getText());
            nebuMaxima = Double.parseDouble(nebuMaxField.getText());
            nebuMinima = Double.parseDouble(nebuMinField.getText());
            insoMaxima = Double.parseDouble(insoMaxField.getText());
            insoMinima = Double.parseDouble(insoMinField.getText());
            chuvaMaxima = Double.parseDouble(chuMaxField.getText());
            chuvaMinima = Double.parseDouble(chuMinField.getText());
            ptoOrvalhoMaximo = Double.parseDouble(ptoOrvMaxField.getText());
            ptoOrvalhoMinimo = Double.parseDouble(ptoOrvMinField.getText());
            rajVentoMaximo = Double.parseDouble(rajVenMaxField.getText());
            rajVentoMinimo = Double.parseDouble(rajVenMinField.getText());

            tempLabel.setText("Valores Cadastrados");

            // System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            // System.out.println("Temperatura mínima: " + tempMinima);

            System.out.println("Dado Apurado: " + dadoApurado);
            System.out.println("Dado Registrado: " + registros);
            System.out.println("Dado Suspeito: " + dadoSuspeito);

            verificarRegistros();

        } catch (NumberFormatException e) {
            // Se a entrada do usuário não puder ser convertida para double
            System.err.println("Por favor, insira valores válidos para as temperaturas.");
            tempLabel.setText("Por favor, insira valores válidos para as temperaturas.");
        }
    }

    private void verificarRegistros() {
        System.out.println("teste temp" + tempMaxima);
        for (Registro registro : registros) {
            //verificar lista de regras
            //se regra aplicada, entao verificar registro com aquela regra
            System.out.println("Reg Manual" + registro.getTemperatura());
                if (registro instanceof RegistroAutomatico) {
                    RegistroAutomatico regAut = (RegistroAutomatico) registro;
                    if (regAut.getTemperatura() != null || regAut.getTemperatura() >= tempMaxima
                    || regAut.getTemperatura() <= tempMinima) {
                dadoSuspeito.add(regAut);
                System.out.println("Dado incorreto");
            } else {
                dadoApurado.add(regAut);
                System.out.println("Dado correto");
            }
        } else if (registro instanceof RegistroManual) {
            RegistroManual regManual = (RegistroManual) registro;
            if (regManual.getTemperatura() != null && (regManual.getTemperatura() >= tempMaxima || regManual.getTemperatura() <= tempMinima)) {
                dadoSuspeito.add(regManual);
                System.out.println("teste CSV");
            }
        }
        visualizarListas();
        }
    }

    // RELATÓRIO PERIOCIDADE por CIDADE
    // Criando métodos para o relatório. Converte as horas, para criar intervalos de
    // tempo. Calcula a média dos variavéis Ins.
    @FXML
    public void gerarrelatorioperiocidade() {
        String cidadeEscolhida = cityComboBox.getValue();
        int intervaloHoras = Integer.parseInt(hourIntervalComboBox.getValue().split(" ")[0]);
        List<List<Double>> mediasPorIntervalo = calcularMediasPorIntervalo(cidadeEscolhida, intervaloHoras);

        listrelatorio.getItems().clear();
        listrelatorio.getItems().add("Cidade: " + cidadeEscolhida);

        // Adiciona os intervalos de tempo e as médias de todas as variáveis
        for (int i = 0; i < mediasPorIntervalo.size(); i++) {
            int horaInicio = i * intervaloHoras;
            int horaFim = (horaInicio + intervaloHoras) % 24;
            String intervaloTexto = String.format("Das %02d:00 às %02d:00: ", horaInicio, horaFim);

            // Adiciona as médias de todas as variáveis para o intervalo atual
            List<Double> mediasVariaveis = mediasPorIntervalo.get(i);
            for (int j = 0; j < mediasVariaveis.size(); j++) {
                String variavel = getNomeVariavel(j);
                double media = mediasVariaveis.get(j);
                String unidadeMedida = getUnidadeMedida(j);
                intervaloTexto += variavel + ": " + String.format("%.2f", media) + " " + unidadeMedida + "    ";
            }
            // Adiciona o intervalo de tempo e as médias de todas as variáveis
            listrelatorio.getItems().add(intervaloTexto);
        }
    }

    // Constroi o texto na tela para cada variável
    private String getUnidadeMedida(int indiceVariavel) {
        List<String> unidadesMedida = new ArrayList<>();
        unidadesMedida.add("°C"); // Unidade de temperatura
        unidadesMedida.add("%"); // Unidade de umidade
        unidadesMedida.add("C"); // Unidade de ponto de orvalho
        unidadesMedida.add("hPa"); // Unidade de pressão

        if (indiceVariavel >= 0 && indiceVariavel < unidadesMedida.size()) {
            return unidadesMedida.get(indiceVariavel);
        } else {
            return "";
        }
    }

    private String getNomeVariavel(int indiceVariavel) {
        List<String> nomesVariaveis = new ArrayList<>();
        nomesVariaveis.add("Temperatura Ins. ");
        nomesVariaveis.add("Umidade Ins. ");
        nomesVariaveis.add("Pto Orvalho Ins. ");
        nomesVariaveis.add("Pressão Ins. ");

        if (indiceVariavel >= 0 && indiceVariavel < nomesVariaveis.size()) {
            return nomesVariaveis.get(indiceVariavel);
        } else {
            return "Variável Desconhecida";
        }
    }

    // Realia os agrupamentos de acordo com o período de tempo que o foi escolhido
    private List<List<Double>> calcularMediasPorIntervalo(String cidadeEscolhida, int intervaloHoras) {
        List<List<Double>> mediasPorIntervalo = new ArrayList<>();
        List<Registro> registrosFiltrados = filtrarRegistrosPorCidade(cidadeEscolhida);

        // Agrupar os registros em intervalos de tempo
        for (int i = 0; i < 24; i += intervaloHoras) {
            int horaInicio = i;
            int horaFim = (i + intervaloHoras) % 24;
            List<Registro> subLista = new ArrayList<>();

            // Se o intervalo for de 24 horas, adicione todos os registros do dia
            if (intervaloHoras == 24) {
                subLista.addAll(registrosFiltrados);
            } else {
                for (Registro registro : registrosFiltrados) {
                    int horaRegistro = Integer.parseInt(registro.getHora().substring(0, 2));
                    if ((horaRegistro >= horaInicio && horaRegistro < horaFim) ||
                            (horaInicio > horaFim && (horaRegistro >= horaInicio || horaRegistro < horaFim))) {
                        subLista.add(registro);
                    }
                }
            }

            // Integra as médias de acordo com os grupos dos períodos de tempo
            List<Double> mediasVariaveis = calcularMediasVariaveisPorIntervalo(subLista);
            mediasPorIntervalo.add(mediasVariaveis);
        }
        return mediasPorIntervalo;
    }

    // Calcular médias de todas as variáveis para o intervalo de tempo especificado
    private List<Double> calcularMediasVariaveisPorIntervalo(List<Registro> registros) {
        List<Double> medias = new ArrayList<>();
        double somaTempIns = 0.0;
        double somaUmiIns = 0.0;
        double somaPtoOrvalhoIns = 0.0;
        double somaPressaoIns = 0.0;

        int quantidadeRegistrosAuto = 0;

        for (Registro registro : registros) {
            if (registro instanceof RegistroAutomatico) {
                RegistroAutomatico registroAuto = (RegistroAutomatico) registro;
                if (registroAuto.getTempIns() != null) {
                    somaTempIns += registroAuto.getTempIns();
                    quantidadeRegistrosAuto++;
                }
                if (registroAuto.getUmiIns() != null) {
                    somaUmiIns += registroAuto.getUmiIns();
                }
                if (registroAuto.getPtoOrvalhoIns() != null) {
                    somaPtoOrvalhoIns += registroAuto.getPtoOrvalhoIns();
                }
                if (registroAuto.getPressaoIns() != null) {
                    somaPressaoIns += registroAuto.getPressaoIns();
                }
            }
        }

        if (quantidadeRegistrosAuto > 0) {
            medias.add(somaTempIns / quantidadeRegistrosAuto);
            medias.add(somaUmiIns / quantidadeRegistrosAuto);
            medias.add(somaPtoOrvalhoIns / quantidadeRegistrosAuto);
            medias.add(somaPressaoIns / quantidadeRegistrosAuto);
        } else {
            // Adicione valores padrão se não houver registros automáticos
            medias.add(0.0); // média tempIns
            medias.add(0.0); // média umiIns
            medias.add(0.0); // média ptoOrvalhoIns
            medias.add(0.0); // média pressaoIns
        }
        return medias;
    }

    // Filtra as variáveis de acordo com a cidade escolhida pelo usuário
    private List<Registro> filtrarRegistrosPorCidade(String cidadeEscolhida) {
        List<Registro> registrosFiltrados = new ArrayList<>();
        for (Registro registro : dadoApurado) {
            if (registro.getCidade().equals(cidadeEscolhida)) {
                registrosFiltrados.add(registro);
            }
        }
        return registrosFiltrados;
    }

    // Precisa Construir o arquivo como tabela sem passar os textos de string
    public void exportarrelatorioperiocidade(@SuppressWarnings("exports") ActionEvent actionEvent) {
        // Obtem os dados da lista
        ObservableList<String> dados = listrelatorio.getItems();

        // Constroe o conteúdo CSV
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(
                "Intervalo de Tempo,Temperatura Ins. (°C),Umidade Ins. (%),Ponto de Orvalho Ins. (C),Pressão Ins. (hPa)\n");
        for (String dado : dados) {
            csvBuilder.append(dado).append("\n");
        }

        // Converta o StringBuilder para String
        String csvContent = csvBuilder.toString();

        // Escolha um local para salvar o arquivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Relatorio Periocidade");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(((Node) actionEvent.getSource()).getScene().getWindow());

        // Salve o conteúdo CSV em um arquivo
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(csvContent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}

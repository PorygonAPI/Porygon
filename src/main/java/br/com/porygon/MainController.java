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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private TextField tempMaxField; // Campo de input da temperatura máxima

    @FXML
    private TextField tempMinField; // Campo de input da temperatura mínima

    private double tempMaxima; // Atributo da temperatura máxima informada pelo pesquisador
    private double tempMinima; // Atributo da temperatura máxima informada pelo pesquisador

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

    @FXML
    private DatePicker datePickerInicial;

    @FXML
    private DatePicker datePickerFinal;

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
    public double getTempMaxima() {
        return tempMaxima;
    }

    public double getTempMinima() {
        return tempMinima;
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

            tempLabel.setText("Temperatura máxima: " + tempMaxima + "       Temperatura mínima: " + tempMinima);

            System.out.println("Temperatura máxima: " + tempMaxima); // Teste de input e retorno
            System.out.println("Temperatura mínima: " + tempMinima);

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
        for (Registro registro : registros) {
            if (registro.getTemperatura() == null || registro.getTemperatura() >= tempMaxima
                    || registro.getTemperatura() <= tempMinima) {
                dadoSuspeito.add(registro);
                System.out.println("Dado incorreto");
            } else {
                dadoApurado.add(registro);
                System.out.println("Dado correto");
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
        LocalDate dataInicial = datePickerInicial.getValue();
        LocalDate dataFinal = datePickerFinal.getValue();

        // Verifica se as datas foram selecionadas
        if (cidadeEscolhida != null && dataInicial != null && dataFinal != null) {
            List<List<Double>> mediasPorIntervalo = calcularMediasPorIntervalo(cidadeEscolhida, dataInicial, dataFinal);

            listrelatorio.getItems().clear();
            listrelatorio.getItems()
                    .add("Cidade: " + cidadeEscolhida + " | Período: " + dataInicial + " a " + dataFinal);

            // Adiciona as médias por intervalo
            for (int i = 0; i < mediasPorIntervalo.size(); i++) {
                // Crie o texto do intervalo e as médias das variáveis
                String intervaloTexto = criarTextoIntervalo(dataInicial.plusDays(i), mediasPorIntervalo.get(i));
                listrelatorio.getItems().add(intervaloTexto);
            }
        } else {
            // Exibir mensagem de erro se os campos não estiverem preenchidos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, selecione a cidade e as datas inicial e final.");
            alert.showAndWait();
        }
    }

    // Método para criar texto do intervalo e médias das variáveis
    private String criarTextoIntervalo(LocalDate data, List<Double> mediasVariaveis) {
        StringBuilder texto = new StringBuilder();
        texto.append("Data: ").append(data).append(" | Médias: ");
        for (int i = 0; i < mediasVariaveis.size(); i++) {
            texto.append(getNomeVariavel(i)).append(": ").append(String.format("%.2f", mediasVariaveis.get(i)))
                    .append(" ").append(getUnidadeMedida(i)).append(" | ");
        }
        return texto.toString();
    }

    private List<List<Double>> calcularMediasPorIntervalo(String cidadeEscolhida, LocalDate dataInicial,
            LocalDate dataFinal) {
        // Inicializa a lista de médias por intervalo
        List<List<Double>> mediasPorIntervalo = new ArrayList<>();

        // Obtém o número total de dias no intervalo
        long totalDias = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        // Loop para cada dia no intervalo
        for (int i = 0; i <= totalDias; i++) {
            // Inicializa a lista de médias para o dia atual
            List<Double> mediasDia = new ArrayList<>();

            // Obtém a data atual no loop
            LocalDate dataAtual = dataInicial.plusDays(i);

            // Filtra os registros para o dia atual
            List<Registro> registrosDia = filtrarRegistrosPorDia(cidadeEscolhida, dataAtual);

            // Se houver registros para o dia atual, calcula as médias das variáveis
            if (!registrosDia.isEmpty()) {
                // Inicializa as somas das variáveis para o dia atual
                double somaTempIns = 0.0;
                double somaUmiIns = 0.0;
                double somaPtoOrvalhoIns = 0.0;
                double somaPressaoIns = 0.0;

                // Loop sobre os registros do dia atual para calcular as somas
                for (Registro registro : registrosDia) {
                    if (registro instanceof RegistroAutomatico) {
                        RegistroAutomatico registroAuto = (RegistroAutomatico) registro;
                        somaTempIns += registroAuto.getTempIns() != null ? registroAuto.getTempIns() : 0.0;
                        somaUmiIns += registroAuto.getUmiIns() != null ? registroAuto.getUmiIns() : 0.0;
                        somaPtoOrvalhoIns += registroAuto.getPtoOrvalhoIns() != null ? registroAuto.getPtoOrvalhoIns()
                                : 0.0;
                        somaPressaoIns += registroAuto.getPressaoIns() != null ? registroAuto.getPressaoIns() : 0.0;
                    }
                }

                // Calcula as médias das variáveis para o dia atual
                int totalRegistrosDia = registrosDia.size();
                mediasDia.add(somaTempIns / totalRegistrosDia);
                mediasDia.add(somaUmiIns / totalRegistrosDia);
                mediasDia.add(somaPtoOrvalhoIns / totalRegistrosDia);
                mediasDia.add(somaPressaoIns / totalRegistrosDia);
            } else {
                // Se não houver registros para o dia atual, adiciona médias nulas
                mediasDia.add(null);
                mediasDia.add(null);
                mediasDia.add(null);
                mediasDia.add(null);
            }

            // Adiciona as médias do dia atual à lista de médias por intervalo
            mediasPorIntervalo.add(mediasDia);
        }

        return mediasPorIntervalo;
    }

    // Método para filtrar os registros para um dia específico
    private List<Registro> filtrarRegistrosPorDia(String cidadeEscolhida, LocalDate data) {
        List<Registro> registrosFiltrados = new ArrayList<>();
        for (Registro registro : dadoApurado) {
            LocalDate dataRegistro = LocalDate.parse(registro.getData());
            if (registro.getCidade().equals(cidadeEscolhida) && dataRegistro.equals(data)) {
                registrosFiltrados.add(registro);
            }
        }
        return registrosFiltrados;
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

    // Precisa armazenar os dados das medias corretamente sem que ele separe por
    // ",".
    public void exportarrelatorioperiocidade(@SuppressWarnings("exports") ActionEvent actionEvent) {
        // Obtem os dados da lista
        ObservableList<String> dados = listrelatorio.getItems();

        // Constroe o conteúdo CSV
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(
                "Intervalo de Tempo,Temperatura Ins. (°C),Umidade Ins. (%),Ponto de Orvalho Ins. (C),Pressão Ins. (hPa)\n");

        // Processa cada linha de dados
        for (String dado : dados) {
            // Substitui os caracteres indesejados por vírgulas
            String linha = dado.replace("Temperatura Ins. :", "").replace("°C", "")
                    .replace("Umidade Ins. :", "").replace("%", "")
                    .replace("Pto Orvalho Ins. :", "").replace("C", "")
                    .replace("Pressão Ins. :", "").replace("hPa", "");

            // Adiciona a linha ao conteúdo CSV
            csvBuilder.append(linha).append("\n");
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

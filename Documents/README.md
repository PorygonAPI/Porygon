![API 2º SEMESTRE EM BANCO DE DADOS (2)](https://github.com/Porygonn/Porygon/assets/111442399/ad146d27-11e7-493d-bc00-03763d2e5f52)

| [Sprint 1](#sprint-1) |  [Sprint 2](#sprint-2) |


## Sprint 1

* Como pesquisador, quero poder carregar meus arquivos contendo variáveis climáticas para que possam ser manipulados e submetidos à análise para minha pesquisa.  

    - Essa user story visa fornecer ao pesquisador a capacidade de carregar seus próprios arquivos contendo variáveis climáticas para análise, essa funcionalidade permite uma maior flexibilidade e personalização em seus estudos, utilizando dados específicos e relevantes para suas pesquisas.
        - Carregamento de Arquivos: Os pesquisadores devem ter a capacidade de carregar arquivos contendo dados de variáveis climáticas a partir de seus próprios dispositivos de armazenamento.

* Como pesquisador, quero que cada arquivo carregado, referente a uma estação meteorológica específica, tenha seus registros armazenados separadamente por variável climática, para uma melhor organização e análise dos dados. 

    - Essa user story visa fornecer ao pesquisador a capacidade de armazenar os registros de cada arquivo carregado separadamente por variável climática e estação meteorológica, essa funcionalidade oferece uma organização mais eficiente dos dados e facilita análises detalhadas e precisas em suas pesquisas climáticas.
        - Armazenamento Separado por Variável: Cada arquivo carregado deve ser analisado e os registros separados por variável climática. Por exemplo, os registros de temperatura devem ser armazenados separadamente dos registros de umidade, pressão atmosférica, velocidade do vento, entre outros;
        - Identificação dos Registros: Cada registro de dados deve conter informações de identificação, como a data e hora da medição, para garantir a rastreabilidade e possibilitar análises temporais precisas.

* Como pesquisador, desejo que registros suspeitos nos arquivos sejam identificados durante o processo de carregamento e que sejam armazenados separadamente dos registros regulares.  

    - Essa user story visa fornecer ao pesquisador a capacidade de identificar e armazenar separadamente registros suspeitos durante o processo de carregamento de arquivos, essa funcionalidade ajuda a garantir a qualidade e a integridade dos dados utilizados em suas análises climáticas, promovendo resultados mais confiáveis e precisos em suas pesquisas.
        - Detecção de Registros Suspeitos: Durante o processo de carregamento de arquivos, o sistema deve ser capaz de identificar registros que parecem ser suspeitos ou inconsistentes com os padrões esperados;
        - Marcação e Separação dos Registros Suspeitos: Os registros identificados como suspeitos devem ser marcados e separados dos registros regulares, para que possam ser tratados de forma adequada durante a análise posterior. Isso pode ser feito atribuindo um marcador especial aos registros suspeitos ou armazenando-os em um local específico no sistema;
        - Registro da Razão da Suspeita: É importante que o sistema registre a razão pela qual um registro foi considerado suspeito. 

* Critérios de Aceitação:
    - Carregaramento de arquivos csv contendo dados de variáveis, utilizando uma interface intuitiva e amigável;
    - Os registros de dados devem ser separados por variável climática;
    - Cada registro de dados deve conter informações de identificação, incluindo data e hora da medição;
    - O sistema deve ser capaz de identificar e marcar registros suspeitos;
    - Os registros identificados como suspeitos devem ser separados dos registros regulares.

### Gráfico de BurnDown

![IMG-20240425-WA0024 (1)](https://github.com/Porygonn/Porygon/assets/142633184/dda168d4-afc8-440f-a6a7-32eff9e83118)

### WireFrames
![image](https://github.com/Porygonn/Porygon/assets/142633184/daa55d71-990a-4b02-b795-5cfa634921a2)

![image](https://github.com/Porygonn/Porygon/assets/142633184/72f8286b-a31e-4b4b-b19d-29fd7792e513)

![image](https://github.com/Porygonn/Porygon/assets/142633184/ea6abf81-7183-49ab-9c75-6590076e354d)

![image](https://github.com/Porygonn/Porygon/assets/142633184/44ed82fb-37d8-4df1-8fed-3c60ecd9dd8c)

## Sprint 2
* Como pesquisador, desejo um relatório que me permita calcular os elementos necessários para plotar um gráfico boxplot com base nos dados de uma estação em uma data específica. Isso me proporcionará uma visualização clara da distribuição e variabilidade dos dados climáticos, facilitando análises detalhadas em minha pesquisa.

    - Essa user story visa fornecer ao pesquisador uma ferramenta poderosa para explorar e compreender a distribuição dos dados climáticos de uma estação específica em uma data determinada.
        - Seleção Data: O pesquisador deve poder especificar a data para a qual deseja analisar os dados e gerar o gráfico boxplot.
        - Cálculo dos Elementos do Boxplot: Com base nos dados coletados, o sistema deve calcular os elementos necessários para construir o gráfico boxplot, como mínimo, primeiro quartil, mediana, terceiro quartil e máximo, proporcionando uma representação visual da distribuição dos dados.
        - Após o cálculo dos elementos do boxplot, o sistema deve gerar um relatório detalhado que apresente os resultados de forma clara e organizada, facilitando a interpretação e análise pelo pesquisador.

* Como pesquisador, desejo um relatório de situação que exiba as médias dos últimos valores das variáveis climáticas relevantes para cada cidade, possibilitando uma compreensão abrangente das condições climáticas. 

    - Essa user story visa fornecer ao pesquisador acesso a um relatório de médias climáticas por cidade, essa funcionalidade permite uma compreensão mais profunda das condições climáticas em diferentes regiões ao longo do tempo, facilitando análises e estudos mais aprofundados sobre o clima.
         - Cálculo das Médias: O relatório deve calcular as médias dos últimos valores das variáveis climáticas relevantes para cada cidade. Essas médias fornecerão uma visão geral das condições climáticas típicas em cada localidade.
         
* Como pesquisador, desejo poder escolher um período específico para a geração do relatório de valor médio das variáveis climáticas por cidade, possibilitando uma análise detalhada das condições climáticas ao longo de um período determinado. 

    - Essa user story visa fornecer ao pesquisador a capacidade de escolher um período específico para a geração do relatório de médias climáticas por cidade, essa funcionalidade permite uma análise mais detalhada e personalizada das condições climáticas.
        - Seleção de Período Específico: O pesquisador deve ter a capacidade de selecionar um período específico de tempo para o qual deseja gerar o relatório de médias climáticas;
        - Flexibilidade de Intervalos: A funcionalidade deve oferecer flexibilidade na escolha do intervalo de tempo, permitindo ao pesquisador selecionar períodos de tempo curtos (ex, uma semana) ou mais longos (ex, um mês ou um ano), conforme necessário para a pesquisa em questão;
        - Cálculo das Médias Personalizado: O relatório deve calcular as médias das variáveis climáticas relevantes dentro do período especificado pelo pesquisador para cada cidade incluída no relatório.

* Critérios de Aceitação:
    - Cálculo dos Elementos do Boxplot: O sistema deve calcular os elementos necessários para o gráfico boxplot, incluindo mínimo, primeiro quartil, mediana, terceiro quartil e máximo;
    - Cálculo das Médias: O relatório deve calcular as médias dos últimos valores das variáveis climáticas relevantes para cada cidade;
    - Seleção de Período Específico: O pesquisador deve poder escolher um período de tempo para a geração do relatório;
    - Seleção de Data: O sistema deve permitir que o pesquisador especifique a data para análise dos dados climáticos.

### WireFrames
![image](https://github.com/Porygonn/Porygon/assets/142633184/f770607b-7a2b-47e5-a36a-28c1c132db09)

![image](https://github.com/Porygonn/Porygon/assets/142633184/e064c3e7-82cb-4a66-9a1e-5835459c2b3f)

![image](https://github.com/Porygonn/Porygon/assets/142633184/afbcac8c-1342-4ff2-8229-49cb04c548de)

![image](https://github.com/Porygonn/Porygon/assets/142633184/c7111601-3d49-4efe-8643-e1eae7e2abd9)

![API 2º SEMESTRE EM BANCO DE DADOS (2)](https://github.com/Porygonn/Porygon/assets/111442399/ad146d27-11e7-493d-bc00-03763d2e5f52)

| [Sprint 1](#sprint-1) |  [Sprint 2](#sprint-2) | [Sprint 3](#sprint-3) | [Manual de Usuário](#manual-de-usuário) | [Modelagem](#modelagem) |


# Sprint 1

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

###  Critérios de Aceitação:

- Carregaramento de arquivos csv contendo dados de variáveis, utilizando uma interface intuitiva e amigável;
- Os registros de dados devem ser separados por variável climática;
- Cada registro de dados deve conter informações de identificação, incluindo data e hora da medição;
- O sistema deve ser capaz de identificar e marcar registros suspeitos;
- Os registros identificados como suspeitos devem ser separados dos registros regulares.

### Gráfico de BurnDown

![GB1](https://github.com/Porygonn/Porygon/assets/142633184/dda168d4-afc8-440f-a6a7-32eff9e83118)

### WireFrames
![S1](https://github.com/Porygonn/Porygon/assets/142633184/daa55d71-990a-4b02-b795-5cfa634921a2)
![S1](https://github.com/Porygonn/Porygon/assets/142633184/72f8286b-a31e-4b4b-b19d-29fd7792e513)
![S1](https://github.com/Porygonn/Porygon/assets/142633184/ea6abf81-7183-49ab-9c75-6590076e354d)
![S1](https://github.com/Porygonn/Porygon/assets/142633184/44ed82fb-37d8-4df1-8fed-3c60ecd9dd8c)

-------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------

# Sprint 2

* Como pesquisador, desejo um relatório de situação que exiba as médias dos últimos valores das variáveis climáticas relevantes para cada cidade, possibilitando uma compreensão abrangente das condições climáticas. 

    - Essa user story visa fornecer ao pesquisador acesso a um relatório de médias climáticas por cidade, essa funcionalidade permite uma compreensão mais profunda das condições climáticas em diferentes regiões ao longo do tempo, facilitando análises e estudos mais aprofundados sobre o clima.
         - Cálculo das Médias: O relatório deve calcular as médias dos últimos valores das variáveis climáticas relevantes para cada cidade. Essas médias fornecerão uma visão geral das condições climáticas típicas em cada localidade.
         
* Como pesquisador, desejo poder escolher um período específico para a geração do relatório de valor médio das variáveis climáticas por cidade, possibilitando uma análise detalhada das condições climáticas ao longo de um período determinado. 

    - Essa user story visa fornecer ao pesquisador a capacidade de escolher um período específico para a geração do relatório de médias climáticas por cidade, essa funcionalidade permite uma análise mais detalhada e personalizada das condições climáticas.
        - Seleção de Período Específico: O pesquisador deve ter a capacidade de selecionar um período específico de tempo para o qual deseja gerar o relatório de médias climáticas;
        - Flexibilidade de Intervalos: A funcionalidade deve oferecer flexibilidade na escolha do intervalo de tempo, permitindo ao pesquisador selecionar períodos de tempo curtos (ex, uma semana) ou mais longos (ex, um mês ou um ano), conforme necessário para a pesquisa em questão;
        - Cálculo das Médias Personalizado: O relatório deve calcular as médias das variáveis climáticas relevantes dentro do período especificado pelo pesquisador para cada cidade incluída no relatório.

### Critérios de Aceitação:
- Execução de DDL com Êxito: O sistema deve ser capaz de integrar o banco de dados com o código da aplicação, garantindo a criação ou atualização correta das estruturas necessárias, sem erros.
- Cálculo das Médias: O relatório deve calcular as médias dos últimos valores das variáveis climáticas relevantes para cada cidade;
- Seleção de Período Específico: O pesquisador deve poder escolher um período de tempo para a geração do relatório;
- Seleção de Data: O sistema deve permitir que o pesquisador especifique a data para análise dos dados climáticos.

### Gráfico de BurnDown

![GB2](https://github.com/Porygonn/Porygon/assets/142633184/82f2ecdb-7b07-4911-951f-8b6d30f572bc)

### WireFrames

![S2](https://github.com/Porygonn/Porygon/assets/142633184/225034ef-7b30-47cb-ac36-7409242d689f)
![S2](https://github.com/Porygonn/Porygon/assets/142633184/0998c8cb-50c7-48c0-a347-657d7cc4de96)
![S2](https://github.com/Porygonn/Porygon/assets/142633184/80fad055-9342-45ba-ae31-c47c0580ac93)
![S2](https://github.com/Porygonn/Porygon/assets/142633184/62cf561c-907c-4c89-aa6b-cc60f9e3e016)

-------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------
# Sprint 3

* Como pesquisador, desejo poder alterar, restaurar e excluir dados irregulares quando necessário, garantindo a precisão, integridade e confiabilidade dos dados para minha pesquisa e assegurando a rastreabilidade dos registros no sistema. 

    - Essa user story visa fornecer ao pesquisador as ferramentas necessárias para manter a precisão, integridade e confiabilidade dos dados utilizados em suas pesquisas, ao mesmo tempo em que garante a conformidade com os requisitos. 
        - Alterar Dados Irregulares: O pesquisador devem ter a capacidade de modificar dados que foram identificados como irregulares;
        - Restaurar Dados: Pode ser necessário restaurar dados alterados ou dados que se mostrarem 'coesos' para a planilha de dados "normais";
        - Excluir Dados Irregulares: Quando os dados forem considerados irreparáveis ou não confiáveis, o pesquisador deve ter a capacidade de excluí-los do sistema. Isso garante que apenas dados precisos e confiáveis sejam utilizados na pesquisa.

* Como pesquisador, desejo um relatório que me permita calcular os elementos necessários para plotar um gráfico boxplot com base nos dados de uma estação em uma data específica. Isso me proporcionará uma visualização clara da distribuição e variabilidade dos dados climáticos, facilitando análises detalhadas em minha pesquisa.

    - Essa user story visa fornecer ao pesquisador uma ferramenta poderosa para explorar e compreender a distribuição dos dados climáticos de uma estação específica em uma data determinada.
        - Seleção Data: O pesquisador deve poder especificar a data para a qual deseja analisar os dados e gerar o gráfico boxplot.
        - Cálculo dos Elementos do Boxplot: Com base nos dados coletados, o sistema deve calcular os elementos necessários para construir o gráfico boxplot, como mínimo, primeiro quartil, mediana, terceiro quartil e máximo, proporcionando uma representação visual da distribuição dos dados.
        - Após o cálculo dos elementos do boxplot, o sistema deve gerar um relatório detalhado que apresente os resultados de forma clara e organizada, facilitando a interpretação e análise pelo pesquisador.


#### Como fazer o gráfico: 

![BoxPlot](https://github.com/Porygonn/Porygon/assets/142633184/a959f99b-10d9-4a5d-86e3-0eefc106bdf2)

### Critérios de Aceitação:

- Alteração de Dados Irregulares: Deve ser possível para o pesquisador modificar registros identificados como irregulares.
- Restauração e Exclusão de Dados: O sistema deve permitir a restauração de dados coesos e a exclusão de dados irreparáveis ou não confiáveis, mantendo a rastreabilidade dos registros.
- Seleção de Data: O pesquisador deve ser capaz de especificar a data desejada para análise dos dados climáticos.
- Cálculo dos Elementos do Boxplot: O sistema deve calcular com precisão os elementos necessários (mínimo, primeiro quartil, mediana, terceiro quartil e máximo) para a construção do gráfico boxplot, fornecendo uma representação visual da distribuição dos dados.

### WireFrames

![S3](https://github.com/Porygonn/Porygon/assets/142633184/cb13af3c-e777-48df-bd66-311fc551aea3)
![S3](https://github.com/Porygonn/Porygon/assets/142633184/e6124ba3-8d93-4781-987f-571315a80c66)

-------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------

# Modelagem

![MDG](https://github.com/Porygonn/Porygon/assets/142633184/edfa0ca7-4406-4acd-a426-7c2de39b4e4b)

-------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------

# Manual de Usuário

![M1](https://github.com/Porygonn/Porygon/assets/142633184/a261642b-4261-4efa-ac3a-7de34b7e89f1)
![M2](https://github.com/Porygonn/Porygon/assets/142633184/6bee7706-68a0-4b88-9541-dee275d3b8a3)
![M3](https://github.com/Porygonn/Porygon/assets/142633184/3794367f-2ce2-4f7a-bb89-3fdd1f217cd0)
![M4](https://github.com/Porygonn/Porygon/assets/142633184/1ac8edf1-de40-4929-8bc4-6d116eaa6b69)
![M5](https://github.com/Porygonn/Porygon/assets/142633184/0824a1a5-3ab4-4f6b-8361-b4b2d0787a9a)
![M6](https://github.com/Porygonn/Porygon/assets/142633184/0dc737e2-ff0f-4f28-81cd-1792ed46c0eb)
![M7](https://github.com/Porygonn/Porygon/assets/142633184/0541d486-3aee-4ab4-ba49-8b34fd0b5441)
![M8](https://github.com/Porygonn/Porygon/assets/142633184/6dc78095-399b-46a4-ac4f-17cba40a844f)
![M9](https://github.com/Porygonn/Porygon/assets/142633184/18fb7746-801d-4b90-9ac1-dcefb1bbddd0)
![M10](https://github.com/Porygonn/Porygon/assets/142633184/fc53f65f-fae1-46c0-8383-2ab1b3efd17d)
![M11](https://github.com/Porygonn/Porygon/assets/142633184/16c484fd-fce8-4179-8de3-09c89e29b2bb)
![M12](https://github.com/Porygonn/Porygon/assets/142633184/07e606e7-26d7-43de-ad99-3bfbb72d0c2c)

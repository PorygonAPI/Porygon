![API 2º SEMESTRE EM BANCO DE DADOS (2)](https://github.com/Porygonn/Porygon/assets/111442399/ad146d27-11e7-493d-bc00-03763d2e5f52)


-------------------------------------------------------------------------------------------------------------------------------------------------------
<details>
<summary>Modelagem de Dados</summary>

### Modelagem de Dados

![MDG](https://github.com/Porygonn/Porygon/assets/142633184/edfa0ca7-4406-4acd-a426-7c2de39b4e4b)

</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
<summary>Manual de Usuário</summary>

### Manual de Usuário

Para uma compreensão detalhada das funcionalidades, por favor, consulte o manual do usuário disponível no link abaixo.

[Manual do Usuario](Manual_do_Usuario_V3.pdf)



</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
<summary> Sprint 1</summary>

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

###  Critérios de Aceitação:

- Carregaramento de arquivos csv contendo dados de variáveis, utilizando uma interface intuitiva e amigável;
- Os registros de dados devem ser separados por variável climática;
- Cada registro de dados deve conter informações de identificação, incluindo data e hora da medição;
- O sistema deve ser capaz de identificar e marcar registros suspeitos;
- Os registros identificados como suspeitos devem ser separados dos registros regulares.

### Gráfico de BurnDown

![GB1](https://github.com/Porygonn/Porygon/assets/142633184/dda168d4-afc8-440f-a6a7-32eff9e83118)

### WireFrames

![S1](https://github.com/Porygonn/Porygon/assets/142633184/6e6b6ce2-d081-4185-836e-756437bf27b8)
![S1](https://github.com/Porygonn/Porygon/assets/142633184/4fef5dc1-550d-4978-9244-7882a0ee7a0b)
![S1](https://github.com/Porygonn/Porygon/assets/142633184/ce9d2538-a724-4bc4-8cd4-f0afa5828f47)

</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
<summary>Sprint 2</summary>

## Sprint 2
* Como pesquisador, desejo um relatório de situação que exiba as médias dos últimos valores das variáveis climáticas relevantes para cada cidade, possibilitando uma compreensão abrangente das condições climáticas.

    - Essa user story visa fornecer ao pesquisador acesso a um relatório de médias climáticas por cidade, essa funcionalidade permite uma compreensão mais profunda das condições climáticas em diferentes regiões ao longo do tempo, facilitando análises e estudos mais aprofundados sobre o clima.
        - Cálculo das Médias: O relatório deve calcular as médias dos últimos valores das variáveis climáticas relevantes para cada cidade. Essas médias fornecerão uma visão geral das condições climáticas típicas em cada localidade.

* Como pesquisador, desejo poder escolher um período específico para a geração do relatório situacional das variáveis climáticas por cidade, possibilitando uma análise detalhada das condições climáticas ao longo de um período determinado.

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

![GB2](https://github.com/Porygonn/Porygon/assets/142633184/2f5b11d0-b215-4a4d-9edb-14e12927cda9)

### WireFrames

![S2](https://github.com/Porygonn/Porygon/assets/142633184/dc4383a6-2d6c-4b08-b17d-4d84a23e3349)
![S2](https://github.com/Porygonn/Porygon/assets/142633184/953b5dcd-1314-4cba-a67d-b8bfaf7f4adb)

</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
<summary>Sprint 3</summary>

## Sprint 3

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

### Gráfico de BurnDown
![GB3](https://github.com/Porygonn/Porygon/assets/142633184/86adbe65-f29c-444e-98ef-84f4482120e8)

### WireFrames

![S3](https://github.com/Porygonn/Porygon/assets/142633184/0be02075-68c9-4173-83c0-2fd5a7edda77)
![S3](https://github.com/Porygonn/Porygon/assets/142633184/ff2aed48-a3ae-413f-b1aa-14e18d3bf5e1)

</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
<summary>Sprint 4</summary>
    
## Sprint 4

* Como pesquisador, desejo poder visualizar e alterar informações sobre as estações, cidades e unidades de medida para manter meus dados climáticos atualizados e precisos.
    - Essa user story visa fornecer ao pesquisador as ferramentas necessárias para manter os dados climáticos atualizados e precisos, garantindo a integridade das informações sobre estações, cidades e unidades de medida.
        - Cadastro Automático de Estação: O sistema deve permitir o cadastro automático de novas estações, utilizando arquivos CSV não cadastrados no banco;
        - Edição da Estação Cadastrada: O pesquisador deve poder editar informações de estações já cadastradas, incluindo nome e unidades de medidas;
        - Exclusão de uma Estação/Cidade: Deve ser possível excluir estações/cidades que não estão mais em operação ou cujos dados não são mais necessários, deve ser definitiva, removendo todos os registros associados à estação do sistema, garantindo que não haja dados obsoletos ou irrelevantes.
        - Cadastro de Cidades: O sistema deve permitir o cadastro de cidades, usando siglas ou nome;
        - Adicionar Cidades à Estação: O pesquisador deve poder associar múltiplas cidades a uma estação específica, indicando a área de cobertura da estação;
        - Editar as Unidades de Medidas para Cada Estação: O sistema deve permitir a edição das unidades de medida utilizadas por cada estação para registrar dados climáticos;
        - Atualização Dinâmica: As alterações feitas nas informações das estações, cidades e unidades de medida devem ser refletidas imediatamente no sistema, garantindo que todos os dados e análises subsequentes utilizem as informações mais atualizadas.

### Critérios de Aceitação:

- Cadastro Automático de Estação: O sistema deve fazer o cadastro automático de novas estações a partir de arquivos CSV não cadastrados no banco de dados;
- Edição de informações: O pesquisador deve poder editar o nome das estações e unidades de medida associadas às estações;
- Exclusão de informações: Deve ser possível excluir estações e cidades, removendo definitivamente todos os registros associados do sistema;
- Atualização Dinâmica: As alterações feitas nas informações de estações, cidades e unidades de medida devem ser refletidas imediatamente no sistema.

### Gráfico de BurnDown
![GB4](https://github.com/Porygonn/Porygon/assets/142633184/309ec32e-f4e2-46bc-b3bd-73f623a2b1a3)

### WireFrames

![S4](https://github.com/Porygonn/Porygon/assets/142633184/c8d7d775-85be-42f0-ae2a-d340db31d11e)

</details>

-------------------------------------------------------------------------------------------------------------------------------------------------------






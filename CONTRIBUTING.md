# Contribuindo com o projeto

*Requisitos de instalação:* 
- GIT
- Java (definir versão)
- SGBD (definir modelo SQL)

**GIT**
-
Abordando sobre o git em especifico, é recomendado entender o básico de como funciona, então além de falar do GitFlow, iremos falar sobre comandos básicos para o funcionamento do projeto. 

0) `git clone https://github.com/Porygonn/Porygon.git`
Acima você está fazendo literalmente uma cópia do projeto do que está do ambiente da nuvem, na sua máquina. Só é necessário realizar o clone na primeira vez ao ter contato com o projeto. (Caso contrário terão várias cópias do projeto na sua máquina)

1) `git checkout <branch-desejada>`
Vamos usar por base a develop. Qualquer branch criada será de lá, portanto é importante que se esteja lá para criar uma branch

2) `git pull`
Este comando serve para atualizar a *branch* da sua máquina com o que está na nuvem.

3) `git checkout -b <nome-da-feature>`
A *flag* **-b** serve para criar uma versão exatamente igual da branch que você estava, seguindo com o nome que deseja da branch. 
Para uma nova implementação, é de grande importância que isso seja feito para que haja a rastreabilidade do que foi feito e nos permita resolver com mais agilidade possíveis **bugs**

4) `git status`
Ao finalizar sua implementação, provavelmente você terá mexido em mais de um arquivo. Com o git status você consegue saber exatamente em quais arquivos você mexeu. Muito útil para o próximo comando.

5) `git add <caminho-do-arquivo/nome-do-arquivo>`
Para ter controle de qual arquivo será ***commitado*** é necessário primeiro "adicioná-lo ao pacote". É recomendado que faça arquivo por arquivo, para que se tenha certeza do que se está subindo. Entretanto, caso garanta que o que fez está tudo certo, pode-se adicionar tudo de uma vez com o seguinte comando `git add .`

6) `git commit -m "closes #<numero-da-issue>, <resumo-da-task>"`
Subindo dessa maneira, temos um comando duplo. o *closes #1* (por exemplo) fecha a issue de número 1, atrelando ao conteúdo que você commitou. 

7) `git push`
Comando para subir sua branch à nuvem. 

**GitFlow** 

![Como funciona o GitFlow](https://docs.cronapp.io/download/attachments/145490620/gitflow_completo.png?version=1&modificationDate=1597265026000&api=v2)

Basicamente acima mostra um diagrama de como funciona o GitFlow. 
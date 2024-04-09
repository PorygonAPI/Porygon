package br.com.porygon;

import java.io.File;

public class Arquivo {
    private File conteudo;

    public File getConteudo() {
        return conteudo;
    }

    public void setConteudo(File conteudo) {
        this.conteudo = conteudo;
    }

    public void tratar(){
        System.out.println("Estou tratando o arquivo - " + this.conteudo.getName());
        String[] fileNamePart = this.conteudo.getName().split(".csv")[0].split("_");
        if(fileNamePart[0].indexOf("A") >= 0){
            System.out.println("Arquivo automtico: - " + fileNamePart[0]);
            //Aqui será salvo os registros AUTOMATICOS
        }else {
            System.out.println("Arquivo manual: - " + fileNamePart[0]);
            //Aqui será salvo os registros MANUAIS

        }
    }
}

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
        System.out.println("Estou tratando o arquivo - " + this.conteudo.getAbsolutePath());
    }
}

package com.example.firestoreatividade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarefa {
    private String DataCriacao;
    private String Titulo;
    private String Prioridade;
    private String Categoria;

    public Tarefa() {
    }

    public Tarefa(String dataCriacao, String titulo, String prioridade, String categoria) {
        DataCriacao = dataCriacao;
        Titulo = titulo;
        Prioridade = prioridade;
        Categoria = categoria;
    }

    public String getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getPrioridade() {
        return Prioridade;
    }

    public void setPrioridade(String prioridade) {
        Prioridade = prioridade;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}

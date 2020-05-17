package com.example.firestoreatividade;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Pessoa {
    private String nome;
    private int flihos;
    private Double salario;
    private boolean ativo;
    private List<String> pets;
    private Date dataAniversario;

    public Pessoa() {
    }

    public String returnString (){
        return "Nome: " + nome + "\nFilhos: " + Integer.toString(flihos) + "\nSalario: " + Double.toString(salario) + "\nPets: "
                + pets.toString() + "\nNascimento: " + dataAniversario + "\nAtivo: " + ativo + "\n";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFlihos() {
        return flihos;
    }

    public void setFlihos(int flihos) {
        this.flihos = flihos;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }

    public Date getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public Pessoa(String nome, int flihos, Double salario, boolean ativo, List<String> pets, Date dataAniversario) {
        this.nome = nome;
        this.flihos = flihos;
        this.salario = salario;
        this.ativo = ativo;
        this.pets = pets;
        this.dataAniversario = dataAniversario;
    }
}

package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;

@Entity //define que a classe é uma tabela do db
@Table(name = "usuario") //definir o nome da tabela do db

public class Usuario {

    @Id //define como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera o id automaticamente
    private Integer id;

    private String nome;

    @Column (unique = true) //define a coluna com valor único
    private String cpf;

    private String senha;

    @Enumerated(EnumType.STRING) //transforma o enum em texto
    private TipoUsuario tipo;

    //getters e setters

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;

@Entity //define que a classe é uma tabela do db
@Table(name = "veiculo") //definir o nome da tabela do db

public class Veiculo {

    @Id //define como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera o id automaticamente
    private Integer id;

    @Column (unique = true) //define a coluna com valor único
    private String placa;

    @Enumerated(EnumType.STRING) //transforma o enum em texto
    @Column(nullable = false)
    private TipoVeiculo tipo;

    private String modelo;

    private Integer ano;

    private String marca;

    //getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}

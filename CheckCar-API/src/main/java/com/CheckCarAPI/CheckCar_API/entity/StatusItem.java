package com.CheckCarAPI.CheckCar_API.entity;

public enum StatusItem {
    OK("Ok"),
    NOK("Não OK"),
    NA("Não Aplicável");

    private final String descricao;

    StatusItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

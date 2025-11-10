package com.CheckCarAPI.CheckCar_API.entity;

public enum TipoResposta {
    SELECAO("Seleção"),
    DESCRITIVA("Descritiva");

    private final String descricao;

    TipoResposta(String descricao) {
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

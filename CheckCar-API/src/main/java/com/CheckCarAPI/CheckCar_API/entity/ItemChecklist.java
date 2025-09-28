package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item_checklist")
public class ItemChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;
}


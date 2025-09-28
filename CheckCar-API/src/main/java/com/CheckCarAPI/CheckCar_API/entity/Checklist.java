package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checklist")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne //muitos checklists podem ser feitos por um mesmo usuario/veiculo
    @JoinColumn(name = "id_usuario") //define o nome da coluna que liga com a outra tabela
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    private LocalDateTime dataChecklist; // armazena datahora
}


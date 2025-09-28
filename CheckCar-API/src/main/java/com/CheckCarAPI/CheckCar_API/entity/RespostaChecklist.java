package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resposta_checklist")
public class RespostaChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_checklist")
    private Checklist checklist;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private ItemChecklist item;

    @Enumerated(EnumType.STRING)
    private StatusItem status;

    private String observacao;
}


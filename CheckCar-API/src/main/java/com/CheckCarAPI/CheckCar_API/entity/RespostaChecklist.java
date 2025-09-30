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

    //getters e setters

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    public ItemChecklist getItem() {
        return item;
    }

    public void setItem(ItemChecklist item) {
        this.item = item;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


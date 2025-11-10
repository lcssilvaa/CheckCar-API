package com.CheckCarAPI.CheckCar_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "resposta_checklist")
public class RespostaChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_checklist")
    private Checklist checklist;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_item")
    private ItemChecklist item;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusItem status;

    private String observacao;

    // Construtor vazio obrigatório pelo JPA
    public RespostaChecklist() {}

    // Construtor útil para criar respostas obrigatórias
    public RespostaChecklist(Checklist checklist, ItemChecklist item, StatusItem status) {
        this.checklist = checklist;
        this.item = item;
        this.status = status;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public ItemChecklist getItem() {
        return item;
    }

    public void setItem(ItemChecklist item) {
        this.item = item;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // Equals e hashCode baseados no ID (bom para coleções)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RespostaChecklist)) return false;
        RespostaChecklist that = (RespostaChecklist) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // ToString enxuto, evita ciclos com outras entidades
    @Override
    public String toString() {
        return "RespostaChecklist{" +
                "id=" + id +
                ", status=" + status +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}

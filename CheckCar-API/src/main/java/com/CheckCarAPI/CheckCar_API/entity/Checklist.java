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

    @PrePersist
    public void prePersist() {
        this.dataChecklist = LocalDateTime.now();
    }

    //getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataChecklist() {
        return dataChecklist;
    }

    public void setDataChecklist(LocalDateTime dataChecklist) {
        this.dataChecklist = dataChecklist;
    }

    public void setDataCheckList(LocalDateTime now) {
    }
}


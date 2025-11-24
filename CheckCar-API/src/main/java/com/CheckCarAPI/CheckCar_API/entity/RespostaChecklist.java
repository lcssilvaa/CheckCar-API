package com.CheckCarAPI.CheckCar_API.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;

@Entity
@Table(name = "resposta_checklist")
public class RespostaChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_lote", nullable = false)
    private Long idLote;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_veiculo", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_pergunta", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private PerguntaChecklist pergunta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;

    @Column(nullable = false)
    private String observacao;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
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

    public PerguntaChecklist getPergunta() {
        return pergunta;
    }

    public void setPergunta(PerguntaChecklist pergunta) {
        this.pergunta = pergunta;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
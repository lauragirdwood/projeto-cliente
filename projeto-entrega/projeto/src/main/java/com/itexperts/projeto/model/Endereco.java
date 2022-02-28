package com.itexperts.projeto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itexperts.projeto.enums.TipoStatusEndereco;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bairro;
    private String tipo_logradouro;
    private String nome_rua;
    private Integer numero;
    @Enumerated(EnumType.STRING)
    private TipoStatusEndereco status;
    private String cidade;
    private String estado;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(Long id, String bairro, String tipo_logradouro, String nome_rua, Integer numero, TipoStatusEndereco status, String cidade, String estado, Cliente cliente) {
        this.id = id;
        this.bairro = bairro;
        this.tipo_logradouro = tipo_logradouro;
        this.nome_rua = nome_rua;
        this.numero = numero;
        this.status = status;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTipo_logradouro() {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }

    public String getNome_rua() {
        return nome_rua;
    }

    public void setNome_rua(String nome_rua) {
        this.nome_rua = nome_rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TipoStatusEndereco getStatus() {
        return status;
    }

    public void setStatus(TipoStatusEndereco status) {
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

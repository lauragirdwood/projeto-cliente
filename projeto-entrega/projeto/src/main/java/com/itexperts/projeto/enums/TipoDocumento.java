package com.itexperts.projeto.enums;

public enum TipoDocumento {

    RG("RG"),
    CPF("CPF"),
    TITULO("Título");

    private final String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}

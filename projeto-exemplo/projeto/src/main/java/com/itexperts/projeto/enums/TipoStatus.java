package com.itexperts.projeto.enums;

public enum TipoStatus {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    TipoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

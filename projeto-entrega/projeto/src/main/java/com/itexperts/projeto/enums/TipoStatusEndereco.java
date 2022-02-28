package com.itexperts.projeto.enums;

public enum TipoStatusEndereco {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    TipoStatusEndereco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

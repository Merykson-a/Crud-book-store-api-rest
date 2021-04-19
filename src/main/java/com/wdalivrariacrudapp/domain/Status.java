package com.wdalivrariacrudapp.domain;

public enum Status {

    ENTREGUE_ATRASADO(2),
    ENTREGUE_PRAZO(1),
    EM_ANDAMENTO(0);

    private int valor;

    Status(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

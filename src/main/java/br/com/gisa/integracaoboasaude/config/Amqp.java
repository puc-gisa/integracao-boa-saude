package br.com.gisa.integracaoboasaude.config;

public class Amqp {

    private Amqp() {
    }

    public static final String ASSOCIADO_NOVO_EXCHANGE = "associado.novo";
    public static final String ASSOCIADO_ATUALIZADO_EXCHANGE = "associado.atualizado";

    public static final String ASSOCIADO_NOVO_QUEUE = "carteirinha.associado.novo";
}


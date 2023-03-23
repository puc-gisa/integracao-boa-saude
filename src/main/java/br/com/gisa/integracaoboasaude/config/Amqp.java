package br.com.gisa.integracaoboasaude.config;

public class Amqp {

    private Amqp() {
    }

    public static final String ASSOCIADO_NOVO_EXCHANGE = "associado.novo";
    public static final String ASSOCIADO_NOVO_QUEUE = "carteirinha.associado.novo";

    public static final String ASSOCIADO_ATUALIZADO_EXCHANGE = "associado.atualizado";

    public static final String SOLICITACAO_AUTORIZACAO_EXAME_EXCHANGE = "solicitacao.autorizacao.exame";
    public static final String SOLICITACAO_AUTORIZACAO_EXAME_QUEUE = "integracao.boa.saude.solicitacao.autorizacao.exame";

    public static final String RESPOSTA_AUTORIZACAO_EXAME_EXCHANGE = "resposta.autorizacao.exame";

}
package br.com.gisa.integracaoboasaude.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SituacaoAutoricacaoExame {
    FALHA(0),
    AUTORIZADO(2),
    NEGADO(3);

    private final int codigo;

    public static SituacaoAutoricacaoExame ofCodigo(Integer codigo) {
        return Arrays.stream(values())
            .filter(tipo -> codigo == tipo.codigo)
            .findFirst()
            .orElse(FALHA);
    }
}

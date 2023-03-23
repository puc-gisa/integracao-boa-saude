package br.com.gisa.integracaoboasaude.listener.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutorizacaoExameResponseMessage {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataValidade;

    private String justificativa;

    private Integer codigoSituacao;

}

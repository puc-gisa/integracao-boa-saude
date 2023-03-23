package br.com.gisa.integracaoboasaude.listener.message;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AutorizacaoExameRequestMessage {

    private Long id;
    private Long idAssociado;
    private String codigoExame;
    private LocalDate dataExame;
    private String crmMedicoSolicitante;
    private LocalDateTime dataSolicitacao;

}

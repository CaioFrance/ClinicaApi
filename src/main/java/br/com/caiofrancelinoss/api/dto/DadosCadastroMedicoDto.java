package br.com.caiofrancelinoss.api.dto;

import br.com.caiofrancelinoss.api.domain.models.Endereco;
import br.com.caiofrancelinoss.api.domain.models.Especialidade;

public record DadosCadastroMedicoDto(String nome, String email, String crm,
                                     Especialidade especialidade, Endereco endereco) {
}

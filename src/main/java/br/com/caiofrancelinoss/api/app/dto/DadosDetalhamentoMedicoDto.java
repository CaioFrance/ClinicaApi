package br.com.caiofrancelinoss.api.app.dto;

import br.com.caiofrancelinoss.api.domain.models.Endereco;
import br.com.caiofrancelinoss.api.domain.models.Especialidade;
import br.com.caiofrancelinoss.api.domain.models.Medico;

public record DadosDetalhamentoMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}

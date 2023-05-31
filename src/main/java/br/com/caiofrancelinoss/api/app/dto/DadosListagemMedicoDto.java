package br.com.caiofrancelinoss.api.app.dto;

import br.com.caiofrancelinoss.api.domain.models.Especialidade;
import br.com.caiofrancelinoss.api.domain.models.Medico;

public record DadosListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

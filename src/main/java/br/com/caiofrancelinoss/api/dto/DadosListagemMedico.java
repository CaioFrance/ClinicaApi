package br.com.caiofrancelinoss.api.dto;

import br.com.caiofrancelinoss.api.domain.models.Especialidade;
import br.com.caiofrancelinoss.api.domain.models.Medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

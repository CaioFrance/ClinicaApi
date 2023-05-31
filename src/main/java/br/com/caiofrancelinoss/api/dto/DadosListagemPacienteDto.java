package br.com.caiofrancelinoss.api.dto;

import br.com.caiofrancelinoss.api.domain.models.Paciente;

public record DadosListagemPacienteDto(Long id, String nome, String email, String cpf) {
    public DadosListagemPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}

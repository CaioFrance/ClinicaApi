package br.com.caiofrancelinoss.api.dto;

import br.com.caiofrancelinoss.api.domain.models.Endereco;
import br.com.caiofrancelinoss.api.domain.models.Paciente;

public record DadosDetalhamentoPacienteDto(Long id, String nome, String email, String telefone, String cpf, boolean ativo,
                                           Endereco endereco) {
    public DadosDetalhamentoPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(),
            paciente.isAtivo(), paciente.getEndereco());
    }
}

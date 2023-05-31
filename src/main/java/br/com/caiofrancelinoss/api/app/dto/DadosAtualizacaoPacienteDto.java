package br.com.caiofrancelinoss.api.app.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacienteDto(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEnderecoDto endereco
) {
}

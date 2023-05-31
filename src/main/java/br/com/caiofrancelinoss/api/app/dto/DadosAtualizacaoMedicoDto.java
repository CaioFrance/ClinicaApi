package br.com.caiofrancelinoss.api.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicoDto(
    @NotNull
    Long id,
    @NotBlank
    String nome,
    String telefone,
    DadosEnderecoDto endereco
) {}

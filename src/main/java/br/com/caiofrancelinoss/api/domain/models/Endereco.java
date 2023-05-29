package br.com.caiofrancelinoss.api.domain.models;

public record Endereco(String logradouro, String bairro, String cpf, String cidade,
                       String uf, String complemento, String numero) {
}

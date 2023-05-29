package br.com.caiofrancelinoss.api.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cpf;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

}

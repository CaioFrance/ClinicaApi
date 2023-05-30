package br.com.caiofrancelinoss.api.domain.models;

import br.com.caiofrancelinoss.api.dto.DadosCadastroPacienteDto;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "paciente")
@Table(name = "pacientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Enumerated
    private Endereco endereco;

    public Paciente(DadosCadastroPacienteDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
}
package br.com.caiofrancelinoss.api.domain.models;

import br.com.caiofrancelinoss.api.app.dto.DadosAtualizacaoPacienteDto;
import br.com.caiofrancelinoss.api.app.dto.DadosCadastroPacienteDto;
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
    private boolean ativo;

    @Enumerated
    private Endereco endereco;

    public Paciente(DadosCadastroPacienteDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPacienteDto dados) {
        if (dados.nome() != null && !dados.nome().equals("")) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null && !dados.telefone().equals("")) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

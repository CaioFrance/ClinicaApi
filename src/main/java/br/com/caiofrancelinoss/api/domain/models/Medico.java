package br.com.caiofrancelinoss.api.domain.models;

import br.com.caiofrancelinoss.api.app.dto.DadosAtualizacaoMedicoDto;
import br.com.caiofrancelinoss.api.app.dto.DadosCadastroMedicoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedicoDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.telefone = dados.telefone();
    }

    public void atualizarInformacoes(DadosAtualizacaoMedicoDto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
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

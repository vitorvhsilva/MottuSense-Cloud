package br.com.mottusense.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;
    @Column(name = "cpf_usuario", nullable = false, unique = true)
    private String cpfUsuario;
    @Column(name = "telefone_usuario", nullable = false, unique = true)
    private String telefoneUsuario;
    @Column(name = "email_usuario", nullable = false, unique = true)
    private String emailUsuario;
    @Column(name = "senha_usuario", nullable = false)
    private String senhaUsuario;
    @Column(name = "link_foto_usuario")
    private String linkFotoUsuario;
    @Column(name = "data_nascimento_usuario")
    private LocalDate dataNascimentoUsuario;
    @Column(name = "data_criacao_usuario")
    private LocalDateTime dataCriacaoUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_configuracao_usuario")
    private ConfiguracaoUsuario configuracaoUsuario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_USUARIO_FILIAL",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_filial")
    )
    private List<Filial> filiais;

}

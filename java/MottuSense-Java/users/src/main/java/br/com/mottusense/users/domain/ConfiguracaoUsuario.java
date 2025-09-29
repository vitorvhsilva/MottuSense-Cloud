package br.com.mottusense.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CONFIGURACAO_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConfiguracaoUsuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_configuracao_usuario")
    private String idConfiguracaoUsuario;

    @Column(name = "alerta_moto_entrar_patio")
    private Boolean alertaMotoEntrarPatio;
    @Column(name = "alerta_moto_sair_patio")
    private Boolean alertaMotoSairPatio;
    @Column(name = "alerta_moto_chegar_sem_placa")
    private Boolean alertaMotoChegarSemPlaca;
    @Column(name = "alerta_moto_precisa_manutencao")
    private Boolean alertaMotoPrecisaManutencao;
    @Column(name = "alerta_moto_preparada_alugada")
    private Boolean alertaMotoPreparadaAlugada;

    @OneToOne(mappedBy = "configuracaoUsuario", cascade = CascadeType.ALL)
    private Usuario usuario;
}

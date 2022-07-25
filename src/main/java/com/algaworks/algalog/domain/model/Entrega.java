package com.algaworks.algalog.domain.model;







import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;

}

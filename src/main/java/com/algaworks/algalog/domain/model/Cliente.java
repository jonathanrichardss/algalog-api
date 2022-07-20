package com.algaworks.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table
public class Cliente  {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;

    private String nome;

    private String email;

    @Column(name = "fone")
    private String telefone;

}

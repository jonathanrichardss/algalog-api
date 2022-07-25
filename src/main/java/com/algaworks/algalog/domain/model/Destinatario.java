package com.algaworks.algalog.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Destinatario {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}

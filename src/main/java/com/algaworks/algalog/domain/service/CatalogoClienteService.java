package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> clienteExistente.equals(cliente));

        if(emailEmUso) {
            throw new
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(UUID clienteId) {
        clienteRepository.deleteById(clienteId);
    }



}

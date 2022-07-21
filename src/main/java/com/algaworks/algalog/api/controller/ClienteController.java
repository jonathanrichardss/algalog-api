package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable UUID id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente>  atualizar(@PathVariable UUID clienteId, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(clienteId)) {
            ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }


    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable UUID clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
           return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }
}

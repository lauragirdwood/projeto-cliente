package com.itexperts.projeto.controller;

import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Endereco;
import com.itexperts.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente) {
        Cliente cli = clienteService.create(cliente);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cli.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> retornaTodosClientes(Pageable pageable) {
        Page<Cliente> list = clienteService.getAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<Cliente> retornarClientePorId(@PathVariable Long id) {
        Cliente cli = clienteService.getById(id);
        return ResponseEntity.ok().body(cli);
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Cliente> criaEndereco(@RequestBody Endereco endereco, @PathVariable Long id) {

        Cliente cli = clienteService.createEnderecoInCliente(endereco, id);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cli.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}




package com.itexperts.projeto.controller;

import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Endereco;
import com.itexperts.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente) {

        Cliente cli = clienteService.createCliente(cliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cli.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> retornaTodosClientes() {

        List<Cliente> clientes = clienteService.getAllClientes();

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> retornarClientePorId(@PathVariable Long id) {

        Cliente cli = clienteService.getClienteById(id);

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

//    @GetMapping("/{id}/enderecos")
//    public ResponseEntity<List<Endereco>> retornaTodosOsEnderecosDeUmCliente(@PathVariable Long id) {
//
//        List<Endereco> enderecos = clienteService.getAllEnderecosDeUmClienteById(id);
//
//        return ResponseEntity.ok().body(enderecos);
//    }
//
//    @GetMapping("/{id}/enderecos/{id}")
//    public ResponseEntity<Endereco> retornaTodosOsEnderecosDeUmCliente(@PathVariable Long idCliente, @PathVariable Long idEndereco) {
//
//        Endereco endereco = clienteService.getEnderecoByIdDeUmClienteById(idCliente, idEndereco);
//
//        return ResponseEntity.ok().body(endereco);
//    }

}




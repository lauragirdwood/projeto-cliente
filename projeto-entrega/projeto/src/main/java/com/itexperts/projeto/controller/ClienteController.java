package com.itexperts.projeto.controller;

import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Documento;
import com.itexperts.projeto.model.Endereco;
import com.itexperts.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/{idCliente}/enderecos")
    public ResponseEntity<Cliente> criaEndereco(@RequestBody Endereco endereco, @PathVariable Long idCliente) {

        Cliente cli = clienteService.createEnderecoInCliente(endereco, idCliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cli.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{idCliente}/enderecos")
    public ResponseEntity<List<Endereco>> retornaTodosOsEnderecosDeUmCliente(@PathVariable Long idCliente) {

        List<Endereco> enderecos = clienteService.getAllEnderecosDeUmClienteById(idCliente);

        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("/{idCliente}/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> retornaTodosOsEnderecosDeUmCliente(@PathVariable Long idCliente, @PathVariable Long idEndereco) {

        Endereco endereco = clienteService.getEnderecoByIdDeUmClienteById(idCliente, idEndereco);

        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping("/{idCliente}/documentos")
    public ResponseEntity<Cliente> criaDocumento(@RequestBody Documento documento, @PathVariable Long idCliente) {

        Cliente cli = clienteService.createDocumentoInCliente(documento, idCliente);
        //exemplo de metodo criar mais simples e retornando o objeto criado
        return ResponseEntity.status(HttpStatus.CREATED).body(cli);
    }

    @GetMapping("/{idCliente}/documentos")
    public ResponseEntity<List<Documento>> retornaTodosOsDocumentosDeUmCliente(@PathVariable Long idCliente) {

        List<Documento> documentos = clienteService.getAllDocumentosDeUmClienteById(idCliente);

        return ResponseEntity.ok().body(documentos);
    }

    @GetMapping("/{idCliente}/documentos/{idDocumento}")
    public ResponseEntity<Documento> retornaTodosOsDocumentosDeUmCliente(@PathVariable Long idCliente, @PathVariable Long idDocumento) {

        Documento documento = clienteService.getDocumentoByIdDeUmClienteById(idCliente, idDocumento);

        return ResponseEntity.ok().body(documento);
    }

}




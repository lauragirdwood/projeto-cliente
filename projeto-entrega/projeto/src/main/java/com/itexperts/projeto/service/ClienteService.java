package com.itexperts.projeto.service;

import com.itexperts.projeto.enums.TipoStatusEndereco;
import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Documento;
import com.itexperts.projeto.model.Endereco;
import com.itexperts.projeto.repository.ClienteRepository;
import com.itexperts.projeto.repository.DocumentoRepository;
import com.itexperts.projeto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    /*
    * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    * */

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional
    public Cliente createCliente(Cliente cliente) {

       return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAllClientes() {

        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty())
            throw new RuntimeException("Não há clientes cadastrads");

        return clientes;
    }

    @Transactional(readOnly = true)
    public Cliente getClienteById(Long id) {
        Optional<Cliente> cli = clienteRepository.findById(id);
        cli.orElseThrow(() -> new RuntimeException("cliente não encontrado"));

        return cli.get();
    }

    // getAllClientes com Pageable
//    public Page<Cliente> getAll(Pageable pageable) {
//
//        Page<Cliente> clientes = clienteRepository.findAll(pageable);
//
//        if (clientes.isEmpty())
//            throw new RuntimeException("Não há clientes cadastrados");
//
//
//        return clientes;
//    }

    @Transactional
    public Cliente createEnderecoInCliente(Endereco endereco, Long id) {

        List<Endereco> listEndereco = new ArrayList<>();
        Cliente cliPersist;
        Optional<Cliente> cli = clienteRepository.findById(id);
        cli.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //conto quantos endereços ativos o cliente tem para não deixar cadastrar mais de um endereço com status ativo
        Integer enderecoAtivo = enderecoRepository.countClienteIdAndStatus(id, TipoStatusEndereco.ATIVO);
        if(enderecoAtivo >= 1)
            throw new RuntimeException("Não pode existir mais de um endereço ativo");

        endereco.setCliente(cli.get());
        listEndereco.add(endereco);
        cli.get().setEnderecos(listEndereco);
        cliPersist = clienteRepository.save(cli.get());

        return cliPersist;
    }

    @Transactional(readOnly = true)
    public List<Endereco> getAllEnderecosDeUmClienteById(Long id) {

        Cliente cliente;

        try {
            cliente = getClienteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não há cliente cadastrado com este id");
        }

        List<Endereco> enderecos = cliente.getEnderecos();

        if (enderecos.isEmpty())
            throw new RuntimeException("Não há endereços cadastrados para este cliente");

        return enderecos;
    }

    @Transactional(readOnly = true)
    public Endereco getEnderecoByIdDeUmClienteById(Long idCliente, Long idEndereco) {

        Cliente cliente;

        try {
            cliente = getClienteById(idCliente);
        } catch (Exception e) {
            throw new RuntimeException("Não há cliente cadastrado com este id");
        }

        List<Endereco> enderecos = cliente.getEnderecos();
        if (enderecos.isEmpty())
            throw new RuntimeException("Não há endereços cadastrados para este cliente");

        Optional<Endereco> endereco = enderecoRepository.findByIdAndCliente(idEndereco, cliente);
        endereco.orElseThrow(() -> new RuntimeException("Não há endereço cadastrado com este id para este cliente"));

        return endereco.get();
    }

    @Transactional
    public Cliente createDocumentoInCliente(Documento documento, Long id) {

        //List<Documento> listDocumento = new ArrayList<>();
        Cliente cliPersist;
        Optional<Cliente> cli = clienteRepository.findById(id);
        cli.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        documento.setCliente(cli.get());
        cli.get().getDocumentos().add(documento);
        //cli.get().setDocumentos(listDocumento);
        cliPersist = clienteRepository.save(cli.get());

        return cliPersist;
    }

    @Transactional(readOnly = true)
    public List<Documento> getAllDocumentosDeUmClienteById(Long id) {

        Cliente cliente;

        try {
            cliente = getClienteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não há cliente cadastrado com este id");
        }

        List<Documento> documentos = cliente.getDocumentos();

        if (documentos.isEmpty())
            throw new RuntimeException("Não há endereços cadastrados para este cliente");

        return documentos;
    }

    @Transactional(readOnly = true)
    public Documento getDocumentoByIdDeUmClienteById(Long idCliente, Long idDocumento) {

        Cliente cliente;

        try {
            cliente = getClienteById(idCliente);
        } catch (Exception e) {
            throw new RuntimeException("Não há cliente cadastrado com este id");
        }

        List<Documento> documentos = cliente.getDocumentos();
        if (documentos.isEmpty())
            throw new RuntimeException("Não há endereços cadastrados para este cliente");

        Optional<Documento> documento = documentoRepository.findByIdAndCliente(idDocumento, cliente);
        documento.orElseThrow(() -> new RuntimeException("Não há endereço cadastrado com este id para este cliente"));

        return documento.get();
    }

}

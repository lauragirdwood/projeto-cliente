package com.itexperts.projeto.service;

import com.itexperts.projeto.enums.TipoStatus;
import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Endereco;
import com.itexperts.projeto.repository.ClienteRepository;
import com.itexperts.projeto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Cliente create(Cliente cliente) {
        Cliente cli = clienteRepository.save(cliente);
        return cli;
    }

    public Page<Cliente> getAll(Pageable pageable) {
        Page<Cliente> listCliente = clienteRepository.findAll(pageable);
        return listCliente;
    }

    @Transactional(readOnly = true)
    public Cliente getById(Long id) {
        Optional<Cliente> cli = clienteRepository.findById(id);
        cli.orElseThrow(() -> new RuntimeException("cliente não encontrado"));

        return cli.get();
    }

    @Transactional(readOnly = true)
    public Cliente findByNomeAndSobreNome(String nome, String sobreNome) {
        Optional<Cliente> cli = clienteRepository.findByNomeAndSobreNome(nome, sobreNome);
        cli.orElseThrow(() -> new RuntimeException("cliente não encontrado"));

        return cli.get();
    }

    @Transactional
    public Cliente createEnderecoInCliente(Endereco endereco, Long id) {

        List<Endereco> listEndereco = new ArrayList<>();
        Cliente cliPersist;
        Optional<Cliente> cli = clienteRepository.findById(id);
        cli.orElseThrow(() -> new RuntimeException("cliente não encontrado"));

        //conto quantos endereços ativos o cliente tem para não deixar cadastrar mais de um endereço com status ativo
        Integer enderecoAtivo = enderecoRepository.countClienteIdAndStatus(id, TipoStatus.ATIVO);
        if(enderecoAtivo >= 1)
            throw new RuntimeException("Não pode existir mais de um endereço ativo");

        //TODO exception caso usuario tente inserir status diferente de ativo ou inativo
//        try {
//            endereco.setCliente(cli.get());
//            listEndereco.add(endereco);
//            cli.get().setEnderecos(listEndereco);
//            cliPersist = clienteRepository.save(cli.get());
//
//            return cliPersist;
//
//        } catch (Exception e) {
//
//            throw new InvalidFormatException("Não pode colocar status diferente de ativo ou inativo", TipoStatus.valueOf(endereco.getStatus().getDescricao()), TipoStatus.class);
//        }

        endereco.setCliente(cli.get());
        listEndereco.add(endereco);
        cli.get().setEnderecos(listEndereco);
        cliPersist = clienteRepository.save(cli.get());

        return cliPersist;

        //TODO processo de update para mudar status caso insira um status ativo por cima de outro, dessa forma o endereço ativo fica inativo e o novo inserido fica ativo

    }




}

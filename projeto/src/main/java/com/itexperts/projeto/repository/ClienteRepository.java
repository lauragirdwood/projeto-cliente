package com.itexperts.projeto.repository;

import com.itexperts.projeto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNomeAndSobreNome(String nome, String sobreNome);

}

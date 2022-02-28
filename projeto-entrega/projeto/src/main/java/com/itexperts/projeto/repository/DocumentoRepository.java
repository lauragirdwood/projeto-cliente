package com.itexperts.projeto.repository;

import com.itexperts.projeto.model.Cliente;
import com.itexperts.projeto.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    //pega um documento dentro de um cliente
    Optional<Documento> findByIdAndCliente(Long idDocumento, Cliente cliente);
}

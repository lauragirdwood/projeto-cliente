package com.itexperts.projeto.repository;

import com.itexperts.projeto.enums.TipoStatus;
import com.itexperts.projeto.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // assim pego a quantidade de status
    @Query("select count(e) from Endereco e where e.cliente.id = ?1 and e.status = ?2")
    Integer countClienteIdAndStatus(Long id, TipoStatus status);

}

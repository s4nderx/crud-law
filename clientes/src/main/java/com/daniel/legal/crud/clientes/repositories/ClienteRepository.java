package com.daniel.legal.crud.clientes.repositories;

import com.daniel.legal.crud.clientes.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   @Query("SELECT obj FROM Cliente obj WHERE " +
           "(:cpf IS NULL OR obj.cpf = :cpf) AND " +
           "(LOWER(obj.nome) LIKE LOWER(CONCAT('%', :nome , '%') ) )")
   Page<Cliente> search(String cpf, String nome, Pageable pageable);

}

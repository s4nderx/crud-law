package com.daniel.legal.crud.clientes.services;

import com.daniel.legal.crud.clientes.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ClienteService {

    public Cliente cadastrar(Cliente cliente);

    public Cliente alterar(Long id, Cliente newCliente);

    public void deletar(Long id);

    public Page<Cliente> search(String cpf, String nome, PageRequest pageRequest);

    public void excluirEmLote(List<Cliente> clientes);

}

package com.daniel.legal.crud.clientes.services;

import com.daniel.legal.crud.clientes.entities.Cliente;
import com.daniel.legal.crud.clientes.repositories.ClienteRepository;
import com.daniel.legal.crud.clientes.services.excepitions.DataBaseException;
import com.daniel.legal.crud.clientes.services.excepitions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    public ClienteRepository repository;

    @Transactional
    public Cliente cadastrar(Cliente cliente){
        return this.repository.save(cliente);
    }

    @Transactional
    public Cliente alterar(Long id, Cliente newCliente){

        try {

            Cliente cliente = this.repository.getOne(id);
            newCliente.setId(id);
            return this.repository.save(newCliente);

        }catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }

    }

    @Transactional
    public void deletar(Long id){
        this.repository.deleteById(id);
    }

    @Transactional
    public Page<Cliente> search(String cpf, String nome, PageRequest pageRequest){
        return this.repository.search(cpf, nome, pageRequest);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirEmLote(List<Cliente> clientes){

        try {
            this.repository.deleteAll(clientes);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao deletar em lote");
        }

    }

}

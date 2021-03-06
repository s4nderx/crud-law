package com.daniel.legal.crud.clientes.controllers;

import com.daniel.legal.crud.clientes.entities.Cliente;
import com.daniel.legal.crud.clientes.services.ClienteService;
import com.daniel.legal.crud.clientes.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<Cliente>> search(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "nome", defaultValue = "", required = false) String nome
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Cliente> list = service.search(cpf, nome, pageRequest);
        return  ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente){
        cliente = service.cadastrar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return  ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente = service.alterar(id, cliente);
        return  ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id){
        service.deletar(id);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/excluir-lote")
    public ResponseEntity<Cliente> delete(@RequestBody List<Cliente> clientes){
        service.excluirEmLote(clientes);
        return  ResponseEntity.noContent().build();
    }
}

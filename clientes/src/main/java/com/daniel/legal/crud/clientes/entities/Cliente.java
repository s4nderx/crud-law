package com.daniel.legal.crud.clientes.entities;

import com.daniel.legal.crud.clientes.entities.enums.EstadoCivil;
import com.daniel.legal.crud.clientes.entities.enums.Sexo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    public Cliente() {
    }

    public Cliente(Long id, String cpf, String nome, String email, LocalDate nascimento, Sexo sexo, EstadoCivil estadoCivil) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate dateOfBirth) {
        this.nascimento = dateOfBirth;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}

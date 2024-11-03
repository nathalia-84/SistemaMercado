package br.ufrn.imd.sistemamercado.model;

import br.ufrn.imd.sistemamercado.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String cpf;
    String genero;
    Date dataNascimento;
    Boolean ativo;

    public void ativar() {
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public void carregarDTO(ClienteDTO cliente){
        if(cliente.nome() != null){
            this.nome = cliente.nome();
        } if(cliente.cpf() != null){
            this.cpf = cliente.cpf();
        } if (cliente.genero() != null){
            this.genero = cliente.genero();
        } if (cliente.dataNascimento() != null){
            this.dataNascimento = cliente.dataNascimento();
        }if (cliente.ativo() != null){
            this.ativo = true;
        }
    }
}
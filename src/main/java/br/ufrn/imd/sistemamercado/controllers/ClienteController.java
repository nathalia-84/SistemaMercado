package br.ufrn.imd.sistemamercado.controllers;

import br.ufrn.imd.sistemamercado.dto.ClienteDTO;
import br.ufrn.imd.sistemamercado.model.ClienteEntity;
import br.ufrn.imd.sistemamercado.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<ClienteEntity>> getAll() {
        List<ClienteEntity> clientes = clienteService.getAllAtivos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.getByIdAtivo(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/")
    public ResponseEntity<ClienteEntity> postCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteEntity cliente = clienteService.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteEntity clienteAtualizado = clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente deletado com sucesso.");
    }

    @DeleteMapping("/dl/{id}")
    public ResponseEntity<String> deleteLogic(@PathVariable Long id) {
        clienteService.deleteLogicCliente(id);
        return ResponseEntity.ok("Cliente desativado logicamente.");
    }
}


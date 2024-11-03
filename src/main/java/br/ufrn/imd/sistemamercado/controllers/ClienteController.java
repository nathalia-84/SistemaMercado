package br.ufrn.imd.sistemamercado.controllers;

import br.ufrn.imd.sistemamercado.dto.ClienteDTO;
import br.ufrn.imd.sistemamercado.model.ClienteEntity;
import br.ufrn.imd.sistemamercado.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ClienteEntity> clientes = clienteRepository.findByAtivoTrue();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Optional<ClienteEntity> cliente = clienteRepository.findByIdAndAtivoTrue(id);
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PostMapping("/")
    public ResponseEntity<ClienteEntity> postCliente(@RequestBody ClienteDTO clienteDTO){
        ClienteEntity cliente = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.ativar();
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        ClienteEntity cliente = clienteOptional.get();
        cliente.carregarDTO(clienteDTO);
        clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
    }

    @DeleteMapping("/dl/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        ClienteEntity cliente = clienteOptional.get();
        cliente.desativar();
        clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente desativado logicamente");
    }
}

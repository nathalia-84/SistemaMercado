package br.ufrn.imd.sistemamercado.services;

import br.ufrn.imd.sistemamercado.dto.ClienteDTO;
import br.ufrn.imd.sistemamercado.exceptions.ResourceNotFoundException;
import br.ufrn.imd.sistemamercado.model.ClienteEntity;
import br.ufrn.imd.sistemamercado.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> getAllAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    public ClienteEntity getByIdAtivo(Long id) {
        return clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    public ClienteEntity createCliente(ClienteDTO clienteDTO) {
        ClienteEntity cliente = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.ativar();
        return clienteRepository.save(cliente);
    }

    public ClienteEntity updateCliente(Long id, ClienteDTO clienteDTO) {
        ClienteEntity clienteExistente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));

        clienteExistente.atualizar(clienteDTO);
        return clienteRepository.save(clienteExistente);
    }

    public void deleteCliente(Long id) {
        ClienteEntity clienteExistente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));
        clienteRepository.delete(clienteExistente);
    }

    public void deleteLogicCliente(Long id) {
        ClienteEntity clienteExistente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));
        clienteExistente.desativar();
        clienteRepository.save(clienteExistente);
    }
}


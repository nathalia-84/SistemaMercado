package br.ufrn.imd.sistemamercado.repositories;
import br.ufrn.imd.sistemamercado.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByAtivoTrue();
    Optional<PedidoEntity> findByIdAndAtivoTrue(Long id);
}

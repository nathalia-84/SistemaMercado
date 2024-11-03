package br.ufrn.imd.sistemamercado.repositories;

import br.ufrn.imd.sistemamercado.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByAtivoTrue();
    Optional<ProdutoEntity> findByIdAndAtivoTrue(Long id);
}

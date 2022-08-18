package br.com.company.magazine.repositories;

import br.com.company.magazine.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
}

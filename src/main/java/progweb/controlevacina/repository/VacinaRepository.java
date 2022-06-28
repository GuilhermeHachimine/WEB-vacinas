package progweb.controlevacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.controlevacina.model.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

}

package progweb.controlevacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.controlevacina.model.Lote;
import progweb.controlevacina.repository.helper.lote.LoteQueries;

public interface LoteRepository extends JpaRepository<Lote, Long>, LoteQueries {

//	List<Lote> findByVacinaCodigo(Long codigo);
	
}

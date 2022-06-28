package progweb.controlevacina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.controlevacina.model.Profissao;
import progweb.controlevacina.repository.helper.profissao.ProfissaoQueries;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long>, ProfissaoQueries {

	List<Profissao> findByNomeContainingIgnoreCase(String nome);
	
}

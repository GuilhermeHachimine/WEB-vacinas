package progweb.controlevacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.controlevacina.model.Pessoa;
import progweb.controlevacina.repository.helper.pessoa.PessoaQueries;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaQueries {

	long countByProfissaoCodigo(Long codigo);
	
//	Pessoa findByCpf(String cpf);
	
}

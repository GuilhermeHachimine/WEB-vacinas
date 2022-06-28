package progweb.controlevacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.controlevacina.model.Aplicacao;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {

}

package progweb.controlevacina.repository.helper.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import progweb.controlevacina.model.Pessoa;

public class PessoaQueriesImpl implements PessoaQueries {

	private static final Logger logger = LoggerFactory.getLogger(PessoaQueriesImpl.class);

    @PersistenceContext
    private EntityManager manager;
	
	@Override
	public Pessoa buscarComProfissao(String cpf) {
		logger.trace("Entrou no método buscarComProfissao");
		logger.debug("Valor de CPF recebido: {}", cpf);
		String jpql = "select p from Pessoa p join fetch p.profissao where p.cpf = :cpf";
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);
		query.setParameter("cpf", cpf);
		Pessoa pessoa = query.getSingleResult();
		logger.debug("Pessoa buscada no BD: {}", pessoa);
		return pessoa;
	}

}

package progweb.controlevacina.repository.helper.profissao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import progweb.controlevacina.model.Profissao;
import progweb.controlevacina.model.filter.ProfissaoFilter;

public class ProfissaoQueriesImpl implements ProfissaoQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Profissao> pesquisar(ProfissaoFilter filtro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Profissao> criteriaQuery = builder.createQuery(Profissao.class);
		Root<Profissao> p = criteriaQuery.from(Profissao.class);
		TypedQuery<Profissao> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(p.<Long>get("codigo"), filtro.getCodigo()));
		}
		if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
			predicateList.add(builder.like(builder.lower(p.<String>get("nome")), "%" +
	                         filtro.getNome().toLowerCase() + "%"));
		}

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		Order order = builder.asc(p.<Profissao>get("nome"));
		criteriaQuery.select(p).where(predArray).distinct(true).orderBy(order);
		typedQuery = manager.createQuery(criteriaQuery);

		List<Profissao> profissoes = typedQuery.getResultList();

		return profissoes;
	}

}

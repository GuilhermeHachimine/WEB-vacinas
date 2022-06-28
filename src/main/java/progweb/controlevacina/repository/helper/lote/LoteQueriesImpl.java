package progweb.controlevacina.repository.helper.lote;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import progweb.controlevacina.model.Lote;
import progweb.controlevacina.model.Vacina;
import progweb.controlevacina.model.filter.LoteFilter;

public class LoteQueriesImpl implements LoteQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lote> pesquisar(LoteFilter filtro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lote> criteriaQuery = builder.createQuery(Lote.class);
		Root<Lote> l = criteriaQuery.from(Lote.class);
		l.fetch("vacina", JoinType.INNER);
		TypedQuery<Lote> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(l.<Long>get("codigo"), filtro.getCodigo()));
		}
		if (filtro.getValidadeInicial() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(l.<LocalDate>get("validade"), filtro.getValidadeInicial()));
		}
		if (filtro.getValidadeFinal() != null) {
			predicateList.add(builder.lessThanOrEqualTo(l.<LocalDate>get("validade"), filtro.getValidadeFinal()));
		}
		if (filtro.getNroDosesDoLoteInicial() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(l.<Integer>get("nroDosesDoLote"), filtro.getNroDosesDoLoteInicial()));
		}
		if (filtro.getNroDosesDoLoteFinal() != null) {
			predicateList.add(builder.lessThanOrEqualTo(l.<Integer>get("nroDosesDoLote"), filtro.getNroDosesDoLoteFinal()));
		}
		if (filtro.getNroDosesAtualInicial() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(l.<Integer>get("nroDosesAtual"), filtro.getNroDosesAtualInicial()));
		}
		if (filtro.getNroDosesAtualFinal() != null) {
			predicateList.add(builder.lessThanOrEqualTo(l.<Integer>get("nroDosesAtual"), filtro.getNroDosesAtualFinal()));
		}
		if (filtro.getVacina() != null) {
			predicateList.add(builder.equal(l.<Vacina>get("vacina"), filtro.getVacina()));
		}

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		Order order = builder.asc(l.<Long>get("codigo"));
		criteriaQuery.select(l).where(predArray).distinct(true).orderBy(order);
		typedQuery = manager.createQuery(criteriaQuery);

		List<Lote> lotes = typedQuery.getResultList();

		return lotes;
	}

	@Override
	public List<Lote> buscarDisponivelComVacina(Long codigoVacina) {
		String jpql = "select l from Lote l join fetch l.vacina where l.vacina.codigo = :codigoVacina and l.nroDosesAtual > 0";
		TypedQuery<Lote> query = manager.createQuery(jpql, Lote.class);
		query.setParameter("codigoVacina", codigoVacina);
		List<Lote> lotes = query.getResultList();
		return lotes;
	}

}

package progweb.controlevacina.repository.helper.lote;

import java.util.List;

import progweb.controlevacina.model.Lote;
import progweb.controlevacina.model.filter.LoteFilter;

public interface LoteQueries {

	List<Lote> pesquisar(LoteFilter filtro);
	
	List<Lote> buscarDisponivelComVacina(Long codigoVacina);
}

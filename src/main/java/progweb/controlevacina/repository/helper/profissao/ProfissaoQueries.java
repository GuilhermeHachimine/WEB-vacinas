package progweb.controlevacina.repository.helper.profissao;

import java.util.List;

import progweb.controlevacina.model.Profissao;
import progweb.controlevacina.model.filter.ProfissaoFilter;

public interface ProfissaoQueries {

	List<Profissao> pesquisar(ProfissaoFilter filtro);
	
}

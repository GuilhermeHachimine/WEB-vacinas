package progweb.controlevacina.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import progweb.controlevacina.model.Aplicacao;
import progweb.controlevacina.model.Lote;
import progweb.controlevacina.repository.AplicacaoRepository;

@Service
public class AplicacaoService {

	private static final Logger logger = LoggerFactory.getLogger(AplicacaoService.class);
	
	@Autowired
	private AplicacaoRepository aplicacaoRepository;
	
	@Autowired
	private LoteService loteService;
	
	@Transactional
	public void salvar(Aplicacao aplicacao) {
		logger.trace("Entrou no método salvar");
		aplicacaoRepository.save(aplicacao);
		Lote lote = aplicacao.getLote();
		lote.setNroDosesAtual(lote.getNroDosesAtual() - 1);
		loteService.alterar(lote);
		logger.info("Aplicacao salva com sucesso.");
	}
}

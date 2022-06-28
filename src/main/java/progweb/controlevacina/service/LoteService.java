package progweb.controlevacina.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import progweb.controlevacina.model.Lote;
import progweb.controlevacina.repository.LoteRepository;

@Service
public class LoteService {

	private static final Logger logger = LoggerFactory.getLogger(LoteService.class);
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Transactional
	public void salvar(Lote lote) {
		logger.trace("Entrou no método salvar");
		loteRepository.save(lote);
		logger.info("Lote salvo com sucesso.");
	}
	
	@Transactional
	public void alterar(Lote lote) {
		logger.trace("Entrou no método alterar");
		loteRepository.save(lote);
		logger.info("Lote alterado com sucesso.");
	}
	
	@Transactional
	public void remover(Long codigo) {
		logger.trace("Entrou no método remover");
		loteRepository.deleteById(codigo);
		logger.info("Lote removido com sucesso.");
	}
	
}

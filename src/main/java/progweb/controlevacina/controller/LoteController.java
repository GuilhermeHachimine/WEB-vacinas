package progweb.controlevacina.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import progweb.controlevacina.model.Lote;
import progweb.controlevacina.model.Vacina;
import progweb.controlevacina.model.filter.LoteFilter;
import progweb.controlevacina.repository.LoteRepository;
import progweb.controlevacina.repository.VacinaRepository;
import progweb.controlevacina.service.LoteService;

@Controller
@RequestMapping("/lotes")
public class LoteController {

	private static final Logger logger = LoggerFactory.getLogger(LoteController.class);
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Autowired
	private LoteService loteService;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@GetMapping("/inserir")
	public String abrirCadastro(Lote lote, Model model) {
		logger.trace("Entrou no método abrirCadastro");
		List<Vacina> vacinas = vacinaRepository.findAll();
		logger.debug("Vacinas buscadas: {}", vacinas);
		model.addAttribute("vacinas", vacinas);
		logger.trace("Encaminhando para a view lotes/novolote");
		return "lotes/novolote";
	}
	
	@PostMapping("/inserir")
	public String cadastrar(@Valid Lote lote,
			                BindingResult resultado,
			                Model model) {
		logger.trace("Entrou no método cadastrar");
		logger.debug("Lote recebido: {}", lote);
		logger.debug("Vacina do Lote recebido: {}", lote.getVacina());
		if (resultado.hasErrors()) {
			logger.debug("O lote recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			List<Vacina> vacinas = vacinaRepository.findAll();
			logger.debug("Vacinas buscadas: {}", vacinas);
			model.addAttribute("vacinas", vacinas);
			logger.trace("Encaminhando para a view lotes/novolote");
			return "lotes/novolote";
		} else {
			loteService.salvar(lote);
			logger.trace("Redirecionando para a URL /lotes/cadastrosucesso");
			return "redirect:/lotes/cadastrosucesso";
		}
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		logger.trace("Entrou no método mostrarMensagemCadastroSucesso");
		model.addAttribute("mensagem", 
				"Cadastro do lote efetuado com sucesso");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return "mostrarmensagem";
	}
	
	
	@GetMapping("/pesquisar")
	public String abrirPesquisa(Model model) {
		List<Vacina> vacinas = vacinaRepository.findAll();
		model.addAttribute("vacinas", vacinas);
		return "lotes/pesquisar";
	}
	
	@PostMapping("/pesquisar")
	public String pesquisar(LoteFilter filtro, Model model) {
		List<Lote> lotes = loteRepository.pesquisar(filtro);
		model.addAttribute("lotes", lotes);
		return "lotes/resultado";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Lote lote, Model model) {
		logger.info("Lote recebido: {}", lote);
		logger.info("Vacina do Lote recebido: {}", lote.getVacina());
		List<Vacina> vacinas = vacinaRepository.findAll();
		model.addAttribute("vacinas", vacinas);
		return "lotes/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(@Valid Lote lote,
                          BindingResult resultado,
                          Model model) {
		logger.trace("Entrou no método alterar");
		logger.debug("Lote recebido: {}", lote);
		logger.debug("Vacina do Lote recebido: {}", lote.getVacina());
		if (resultado.hasErrors()) {
			logger.debug("O lote recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			List<Vacina> vacinas = vacinaRepository.findAll();
			logger.debug("Vacinas buscadas: {}", vacinas);
			model.addAttribute("vacinas", vacinas);
			logger.trace("Encaminhando para a view lotes/novolote");
			return "lotes/alterar";
		} else {
			loteService.alterar(lote);
			logger.trace("Redirecionando para a URL /lotes/alteracaosucesso");
			return "redirect:/lotes/alteracaosucesso";
		}
	}
	
	@GetMapping("/alteracaosucesso")
	public String mostrarMensagemAlteracaoSucesso(Model model) {
		logger.trace("Entrou no método mostrarMensagemAlteracaoSucesso");
		model.addAttribute("mensagem", 
				"Lote alterado com sucesso");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return "mostrarmensagem";
	}
	
	@PostMapping("/remover")
	public String remover(Long codigo) {
		loteService.remover(codigo);
		logger.trace("Redirecionando para a URL /lotes/alteracaosucesso");
		return "redirect:/lotes/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarMensagemRemocaoSucesso(Model model) {
		logger.trace("Entrou no método mostrarMensagemRemocaoSucesso");
		model.addAttribute("mensagem", 
				"Lote removido com sucesso");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return "mostrarmensagem";
	}
	
}






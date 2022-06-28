package progweb.controlevacina.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import progweb.controlevacina.model.Aplicacao;
import progweb.controlevacina.model.Lote;
import progweb.controlevacina.model.Pessoa;
import progweb.controlevacina.model.Vacina;
import progweb.controlevacina.repository.LoteRepository;
import progweb.controlevacina.repository.PessoaRepository;
import progweb.controlevacina.repository.VacinaRepository;
import progweb.controlevacina.service.AplicacaoService;

@Controller
@RequestMapping("/aplicacoes")
public class AplicacaoController {

	private static final Logger logger = LoggerFactory.getLogger(AplicacaoController.class);

	@Autowired
	private VacinaRepository vacinaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private AplicacaoService aplicacaoService;

	@GetMapping("/escolherpessoa")
	public String abrirEscolherPessoa() {
		return "aplicacoes/escolherpessoa";
	}

	@PostMapping("/escolhervacina")
	public String abrirEscolhaVacina(String cpf, Model model) {
		Pessoa pessoa = pessoaRepository.buscarComProfissao(cpf);
		model.addAttribute("pessoa", pessoa);
		List<Vacina> vacinas = vacinaRepository.findAll();
		logger.debug("Vacinas buscadas: {}", vacinas);
		model.addAttribute("vacinas", vacinas);
		return "aplicacoes/escolhervacina";
	}

	@PostMapping("/escolherlote")
	public String escolherLote(Pessoa pessoa, Long codigoVacina, Model model) {
		logger.debug("Pessoa recebida: {}", pessoa);
		logger.debug("Profissao da Pessoa recebida: {}", pessoa.getProfissao());
		logger.debug("Codigo da Vacina recebido: {}", codigoVacina);
		Optional<Vacina> optVacina = vacinaRepository.findById(codigoVacina);

		if (optVacina.isPresent()) {
			Vacina vacina = optVacina.get();
			List<Lote> lotes = loteRepository.buscarDisponivelComVacina(codigoVacina);
			model.addAttribute("vacina", vacina);
			model.addAttribute("lotes", lotes);
			return "aplicacoes/escolherlote";
		} else {
			model.addAttribute("mensagem", "Vacina não encontrada");
			return "mostrarmensagem";
		}
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Aplicacao aplicacao) {
		aplicacaoService.salvar(aplicacao);
		logger.trace("Redirecionando para a URL /aplicacoes/cadastrosucesso");
		return "redirect:/aplicacoes/cadastrosucesso";
	}

	@GetMapping("/cadastrosucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		logger.trace("Entrou no método mostrarMensagemCadastroSucesso");
		model.addAttribute("mensagem", "Aplicacao salva com sucesso");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return "mostrarmensagem";
	}

}

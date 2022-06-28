package progweb.controlevacina.model.filter;

import java.time.LocalDate;

import progweb.controlevacina.model.Vacina;

public class LoteFilter {

	private Long codigo;
	private LocalDate validadeInicial;
	private LocalDate validadeFinal;
	private Integer nroDosesDoLoteInicial;
	private Integer nroDosesDoLoteFinal;
	private Integer nroDosesAtualInicial;
	private Integer nroDosesAtualFinal;
	private Vacina vacina;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getValidadeInicial() {
		return validadeInicial;
	}

	public void setValidadeInicial(LocalDate validadeInicial) {
		this.validadeInicial = validadeInicial;
	}

	public LocalDate getValidadeFinal() {
		return validadeFinal;
	}

	public void setValidadeFinal(LocalDate validadeFinal) {
		this.validadeFinal = validadeFinal;
	}

	public Integer getNroDosesDoLoteInicial() {
		return nroDosesDoLoteInicial;
	}

	public void setNroDosesDoLoteInicial(Integer nroDosesDoLoteInicial) {
		this.nroDosesDoLoteInicial = nroDosesDoLoteInicial;
	}

	public Integer getNroDosesDoLoteFinal() {
		return nroDosesDoLoteFinal;
	}

	public void setNroDosesDoLoteFinal(Integer nroDosesDoLoteFinal) {
		this.nroDosesDoLoteFinal = nroDosesDoLoteFinal;
	}

	public Integer getNroDosesAtualInicial() {
		return nroDosesAtualInicial;
	}

	public void setNroDosesAtualInicial(Integer nroDosesAtualInicial) {
		this.nroDosesAtualInicial = nroDosesAtualInicial;
	}

	public Integer getNroDosesAtualFinal() {
		return nroDosesAtualFinal;
	}

	public void setNroDosesAtualFinal(Integer nroDosesAtualFinal) {
		this.nroDosesAtualFinal = nroDosesAtualFinal;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "\nvalidadeInicial: " + validadeInicial + "\nvalidadeFinal: " + validadeFinal + "\nnroDosesDoLoteInicial: " + nroDosesDoLoteInicial + "\nnroDosesDoLoteFinal: " + nroDosesDoLoteFinal + "\nnroDosesAtualInicial: " + nroDosesAtualInicial + "\nnroDosesAtualFinal: "
				+ nroDosesAtualFinal + "\nvacina: " + vacina;
	}

}

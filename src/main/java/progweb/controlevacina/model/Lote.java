package progweb.controlevacina.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import progweb.controlevacina.validation.IntegerAttributesRelation;
import progweb.controlevacina.validation.util.AttributesRelation;

@Entity
@Table(name = "lote")
@DynamicUpdate
@IntegerAttributesRelation(attribute1 = "nroDosesDoLote",
                           attribute2 = "nroDosesAtual",
                           relation = AttributesRelation.GREATEROREQUAL)
public class Lote implements Serializable {

	private static final long serialVersionUID = -3935828642122652510L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="lote_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	@NotNull(message = "A validade é obrigatória.")
	private LocalDate validade;
	@Min(value = 1, message = "O número de doses do lote deve ser maior que 0 ")
	@NotNull(message = "O número de doses do lote é obrigatório.")
	@Column(name = "nro_doses_do_lote")
	private Integer nroDosesDoLote;
	@Min(value = 0, message = "O número de doses atual do lote deve ser maior igual a 0 ")
	@NotNull(message = "O número de doses do lote é obrigatório.")
	@Column(name = "nro_doses_atual")
	private Integer nroDosesAtual;
	@NotNull(message = "A vacina é obrigatória.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_vacina")
	private Vacina vacina;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Integer getNroDosesDoLote() {
		return nroDosesDoLote;
	}

	public void setNroDosesDoLote(Integer nroDosesDoLote) {
		this.nroDosesDoLote = nroDosesDoLote;
	}

	public Integer getNroDosesAtual() {
		return nroDosesAtual;
	}

	public void setNroDosesAtual(Integer nroDosesAtual) {
		this.nroDosesAtual = nroDosesAtual;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "\nvalidade: " + validade + "\nnroDosesDoLote: " + nroDosesDoLote
				+ "\nnroDosesAtual: " + nroDosesAtual;
	}

}

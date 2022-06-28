package progweb.controlevacina.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aplicacao")
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = -4121216198906081162L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="aplicacao_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	private LocalDate data;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_lote")
	private Lote lote;

	public Aplicacao() {
		data = LocalDate.now();
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
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
		Aplicacao other = (Aplicacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "\ndata: " + data;
	}

}

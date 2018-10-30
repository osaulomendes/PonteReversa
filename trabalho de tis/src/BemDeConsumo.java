import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONObject;

/**
 * Classe BemDeConsumo
 * 
 * @author Hugo de Paula
 * @version 2016.9.05.15
 *
 */
public class BemDeConsumo extends Produto {
	private LocalDate dataValidade;

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		// a data de fabricação deve ser anterior à data de validade.
		if (getDataFabricacao().isBefore(dataValidade.atStartOfDay()))
			this.dataValidade = dataValidade;
	}

	public BemDeConsumo() {
		super();
		// o default é uma validade de 6 meses.
		dataValidade = LocalDate.now().plusMonths(6);
	}

	public BemDeConsumo(String d, float p, int q, LocalDateTime f, LocalDate v) {
		super(d, p, q, f);
		setDataValidade(v);
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return super.toString() + "   Data de Validade: " + dataValidade;
	}

	@Override
	public boolean emValidade() {
		return LocalDateTime.now().isBefore(this.getDataValidade().atTime(23, 59));
	}

	@Override
	public JSONObject toJson() {
		return super.toJson().put("dataValidade", this.getDataValidade());
	}


}

package br.com.alura.dominio;

import java.math.BigDecimal;

public class CalculadoraImposto {

	public BigDecimal calculaImpostoParaLances(Leilao leilao) {
		if (leilao.getLances().get(0).getValor().compareTo(BigDecimal.valueOf(1000.00)) == 1) {
			if (leilao.getLances().get(0).getValor().compareTo(BigDecimal.valueOf(10000.00)) == 1)
				return BigDecimal.valueOf(2250.00);

			return BigDecimal.valueOf(450.00);
		}

		return BigDecimal.valueOf(15.00);
	}

}

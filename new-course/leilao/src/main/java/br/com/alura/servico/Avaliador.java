package br.com.alura.servico;

import java.math.BigDecimal;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;

public class Avaliador {
	
	private BigDecimal maiorDeTodos = BigDecimal.valueOf(Double.MAX_VALUE*(-1));
	private BigDecimal menorDeTodos = BigDecimal.valueOf(Double.MAX_VALUE);

	public void avalia(Leilao leilao) {

		for(Lance lance : leilao.getLances()) {
			if(lance.getValor().compareTo(maiorDeTodos) == 1) {
				maiorDeTodos = lance.getValor();
			}
			if(lance.getValor().compareTo(menorDeTodos) == -1) {
				menorDeTodos  = lance.getValor();
			}
		}
	}

	public BigDecimal getMaiorLance() {
		return maiorDeTodos;
	}

	public BigDecimal getMenorLance() {
		return menorDeTodos;
	}

}

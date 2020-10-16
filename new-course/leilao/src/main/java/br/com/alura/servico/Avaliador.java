package br.com.alura.servico;

import java.math.BigDecimal;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;

public class Avaliador {
	
	private BigDecimal maiorDeTodos = BigDecimal.valueOf(Double.MAX_VALUE*(-1));

	public void avalia(Leilao leilao) {
		System.out.println("maior de todos: " + maiorDeTodos);

		for(Lance lance : leilao.getLances()) {
			if(lance.getValor().compareTo(maiorDeTodos) == 1) {
				maiorDeTodos = lance.getValor();
			}
		}
	}

	public BigDecimal getMaiorLance() {
		return maiorDeTodos;
	}

}

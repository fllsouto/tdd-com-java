package br.com.alura.servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;

public class Avaliador {
	
	private BigDecimal maiorDeTodos = BigDecimal.valueOf(Double.MAX_VALUE*(-1));
	private BigDecimal menorDeTodos = BigDecimal.valueOf(Double.MAX_VALUE);
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {

		for(Lance lance : leilao.getLances()) {
			if(lance.getValor().compareTo(maiorDeTodos) == 1) {
				maiorDeTodos = lance.getValor();
			}
			if(lance.getValor().compareTo(menorDeTodos) == -1) {
				menorDeTodos  = lance.getValor();
			}
		}
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		
		maiores = maiores.subList(0, 3);
		
	}

	public BigDecimal getMaiorLance() {
		return maiorDeTodos;
	}

	public BigDecimal getMenorLance() {
		return menorDeTodos;
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}

}

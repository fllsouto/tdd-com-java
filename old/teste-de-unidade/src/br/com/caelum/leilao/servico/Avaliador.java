package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeTodos = 0;
	
	public void avalia(Leilao leilao) {

		double total = 0;
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
			
			total += lance.getValor();
		}
		
		if (total == 0) {
			mediaDeTodos = total;
			return;
		}

		mediaDeTodos = total / leilao.getLances().size();
	}
	
	public Double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public Double getMediaTodos() {
		return mediaDeTodos;
	}

	
	public Double getMenorLance() {
		return menorDeTodos;
	}

}

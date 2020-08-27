package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeTodos = 0;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		
		if (leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
		}

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
		} else {
			mediaDeTodos = total / leilao.getLances().size();			
		}

		
		maiores = new ArrayList<Lance>(leilao.getLances());
		
		maiores.sort(new Comparator<Lance>() {
			@Override
			public int compare(Lance l1, Lance l2) {
				if (l1.getValor() < l2.getValor()) return 1;
				if (l1.getValor() > l2.getValor()) return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 2 ? 3 : maiores.size());
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

	public List<Lance> getTresMaiores() {
		return this.maiores;
	}

}

package br.com.alura;

import java.math.BigDecimal;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;
import br.com.alura.dominio.Usuario;

public class TestaLeilao {

	public static void main(String[] args) {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(1200.00)));
		leilao.propoe(new Lance(maria, BigDecimal.valueOf(1400.00)));
		
		System.out.println("Total de lances: " + leilao.getLances().size());
	}
}
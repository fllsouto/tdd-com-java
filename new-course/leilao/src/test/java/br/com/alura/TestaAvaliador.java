package br.com.alura;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;
import br.com.alura.dominio.Usuario;
import br.com.alura.servico.Avaliador;

public class TestaAvaliador {

	@Test
	public void deveAvaliarLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(2000.00)));
		leilao.propoe(new Lance(maria, BigDecimal.valueOf(3000.00)));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		BigDecimal menorEsperado = BigDecimal.valueOf(1000.00);
		BigDecimal maiorEsperado = BigDecimal.valueOf(3000.00);

		Assertions.assertEquals(maiorEsperado, avaliador.getMaiorLance());
		Assertions.assertEquals(menorEsperado, avaliador.getMenorLance());
	}
}
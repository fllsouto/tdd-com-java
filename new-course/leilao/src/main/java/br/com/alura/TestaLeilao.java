package br.com.alura;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;
import br.com.alura.dominio.Usuario;

public class TestaLeilao {

	@Test
	public void deveReceberUmLance() {
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));

		int quantidadeDeLancesEsperado = 1;
		BigDecimal valorDoLanceEsperado = BigDecimal.valueOf(1000.00);

		assertEquals(quantidadeDeLancesEsperado, leilao.getLances().size());
		assertEquals(valorDoLanceEsperado, leilao.getLances().get(0).getValor());
	}

	@Test
	public void deveReceberVariosLances() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(2000.00)));

		int quantidadeDeLancesEsperado = 2;
		BigDecimal valorDoPrimeiroLanceEsperado = BigDecimal.valueOf(1000.00);
		BigDecimal valorDoSegundoLanceEsperado = BigDecimal.valueOf(2000.00);

		assertEquals(quantidadeDeLancesEsperado, leilao.getLances().size());
		assertEquals(valorDoPrimeiroLanceEsperado, leilao.getLances().get(0).getValor());
		assertEquals(valorDoSegundoLanceEsperado, leilao.getLances().get(1).getValor());
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("Macbook Pro 15");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(2000.00)));
		leilao.propoe(new Lance(joao, BigDecimal.valueOf(4000.00)));

		int quantidadeDeLancesEsperado = 1;
		BigDecimal valorDoLanceEsperado = BigDecimal.valueOf(2000.00);

		assertEquals(quantidadeDeLancesEsperado, leilao.getLances().size());
		assertEquals(valorDoLanceEsperado, leilao.getLances().get(0).getValor());

	}
}
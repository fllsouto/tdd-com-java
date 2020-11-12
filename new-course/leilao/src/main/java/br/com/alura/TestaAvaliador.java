package br.com.alura;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;
import br.com.alura.dominio.Usuario;
import br.com.alura.servico.Avaliador;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

		assertEquals(maiorEsperado, avaliador.getMaiorLance());

		assertEquals(menorEsperado, avaliador.getMenorLance());
	}
	
	@Test
	public void deveAvaliarLancesEmOrdemDecrescente() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, BigDecimal.valueOf(3000.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(2000.00)));
		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);


		BigDecimal menorEsperado = BigDecimal.valueOf(1000.00);
		BigDecimal maiorEsperado = BigDecimal.valueOf(3000.00);

		assertEquals(maiorEsperado, avaliador.getMaiorLance());

		assertEquals(menorEsperado, avaliador.getMenorLance());
	}
	
	@Test
	public void deveAvaliarLancesEmbaralhado() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(2000.00)));
		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));
		leilao.propoe(new Lance(maria, BigDecimal.valueOf(3000.00)));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);


		BigDecimal menorEsperado = BigDecimal.valueOf(1000.00);
		BigDecimal maiorEsperado = BigDecimal.valueOf(3000.00);

		assertEquals(maiorEsperado, avaliador.getMaiorLance());

		assertEquals(menorEsperado, avaliador.getMenorLance());
	}
	
	@Test
	public void deveAvaliarLeilaoComLanceUnico() {
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(1000.00)));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);


		BigDecimal menorEsperado = BigDecimal.valueOf(1000.00);
		BigDecimal maiorEsperado = BigDecimal.valueOf(1000.00);

		assertEquals(maiorEsperado, avaliador.getMaiorLance());

		assertEquals(menorEsperado, avaliador.getMenorLance());
	}
	
    @Test
    public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, BigDecimal.valueOf(200.00)));
		leilao.propoe(new Lance(maria, BigDecimal.valueOf(400.00)));
		
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(300.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(600.00)));
		
		leilao.propoe(new Lance(joao, BigDecimal.valueOf(250.00)));
		leilao.propoe(new Lance(joao, BigDecimal.valueOf(500.00)));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getTresMaiores();

        assertEquals(3, maiores.size());
    }

}
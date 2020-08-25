package br.com.caelum.leilao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdeCrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.00));
		leilao.propoe(new Lance(jose, 300.00));
		leilao.propoe(new Lance(maria, 400.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveCalcularAMediaDosLances() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 300.00));
		leilao.propoe(new Lance(jose, 500.00));
		leilao.propoe(new Lance(maria, 700.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double mediaValor = (300.0 + 500.0 + 700.0)/3;

		Assert.assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}
	
	@Test
	public void testaMediaDeZeroLances() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("IPhone 7");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double mediaValor = 0;

		Assert.assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}
	
	@Test
	public void testaMediaLanceNulo() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("IPhone 7");
		
		leilao.propoe(new Lance(joao, 0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double mediaValor = 0;

		Assert.assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}

}

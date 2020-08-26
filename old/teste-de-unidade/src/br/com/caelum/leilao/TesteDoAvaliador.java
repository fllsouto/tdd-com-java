package br.com.caelum.leilao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

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

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdeDecrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 500.00));
		leilao.propoe(new Lance(jose, 300.00));
		leilao.propoe(new Lance(maria, 100.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 500.0;
		double menorEsperado = 100.0;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
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

		assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}
	
	@Test
	public void testaMediaDeZeroLances() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("IPhone 7");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double mediaValor = 0;

		assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}
	
	@Test
	public void testaMediaLanceNulo() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("IPhone 7");
		
		leilao.propoe(new Lance(joao, 0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double mediaValor = 0;

		assertEquals(mediaValor, leiloeiro.getMediaTodos(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("IPhone 7");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.00, leiloeiro.getMenorLance(), 0.00001);
		assertEquals(1000.00, leiloeiro.getMaiorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 100.00));
		leilao.propoe(new Lance(maria, 200.00));
		leilao.propoe(new Lance(joao, 300.00));
		leilao.propoe(new Lance(maria, 400.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		
		assertEquals(3, maiores.size());
		assertEquals(400.00, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.00, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.00, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
	public void deveEncontrarOsDoisMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.00));
		leilao.propoe(new Lance(maria, 400.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		
		assertEquals(2, maiores.size());
		assertEquals(400.00, maiores.get(0).getValor(), 0.00001);
		assertEquals(250.00, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveEncontrarApenasUmLance() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
				
		assertEquals(1, maiores.size());
		assertEquals(250.00, maiores.get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveEncontrarLancesParaListaVazia() {
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(0, maiores.size());
	}
}

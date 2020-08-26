package br.com.caelum.leilao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoLeilao {
	
	@Test
	public void deveReceberUmLance() {
		Usuario jobs = new Usuario("Steve Jobs");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(jobs, 2000.00));
		List<Lance> lances = leilao.getLances();
		
		assertEquals(1, lances.size());
		assertEquals(2000.00, lances.get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Usuario jobs = new Usuario("Steve Jobs");
		Usuario wozniack = new Usuario("Steve Wozniack");
		Usuario gates = new Usuario("Bill Gates");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(jobs, 2000.00));
		leilao.propoe(new Lance(wozniack, 3000.00));
		leilao.propoe(new Lance(gates, 4000.00));
		List<Lance> lances = leilao.getLances();
		
		assertEquals(3, lances.size());
		assertEquals(2000.00, lances.get(0).getValor(), 0.00001);
		assertEquals(3000.00, lances.get(1).getValor(), 0.00001);
		assertEquals(4000.00, lances.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario jobs = new Usuario("Steve Jobs");
		Usuario wozniack = new Usuario("Steve Wozniack");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(jobs, 2000.00));
		leilao.propoe(new Lance(jobs, 2500.00));
		leilao.propoe(new Lance(wozniack, 3000.00));
		List<Lance> lances = leilao.getLances();
		
		assertEquals(2, lances.size());
		assertEquals(2000.00, lances.get(0).getValor(), 0.00001);
		assertEquals(3000.00, lances.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDeCincoLancesDoMesmoUsuario() {
		Usuario jobs = new Usuario("Steve Jobs");
		Usuario wozniack = new Usuario("Steve Wozniack");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(jobs, 1000.00));
		leilao.propoe(new Lance(wozniack, 2000.00));
		
		leilao.propoe(new Lance(jobs, 3000.00));
		leilao.propoe(new Lance(wozniack, 4000.00));
		
		leilao.propoe(new Lance(jobs, 5000.00));
		leilao.propoe(new Lance(wozniack, 6000.00));
		
		leilao.propoe(new Lance(jobs, 7000.00));
		leilao.propoe(new Lance(wozniack, 8000.00));
		
		leilao.propoe(new Lance(jobs, 9000.00));
		leilao.propoe(new Lance(wozniack, 10000.00));
		
		leilao.propoe(new Lance(jobs, 11000.00));

		List<Lance> lances = leilao.getLances();
		
		assertEquals(10, lances.size());
		assertEquals(10000.00, ultimoLance(lances), 0.00001);

	}
	
	@Test
	public void devePermitirDobrarOLanceBaseadoNoUltimoLance() {
		Usuario jobs = new Usuario("Steve Jobs");
		Usuario wozniack = new Usuario("Steve Wozniack");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(jobs, 1000.00));
		leilao.propoe(new Lance(wozniack, 2000.00));
		
		leilao.propoe(new Lance(jobs, 3000.00));
		leilao.propoe(new Lance(wozniack, 4000.00));
		
		leilao.dobraLance(jobs);
		
		List<Lance> lances = leilao.getLances();
		assertEquals(5, lances.size());
		assertEquals(6000.00, ultimoLance(lances), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarLanceDeListaVazia() {
		Usuario jobs = new Usuario("Steve Jobs");
		
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.dobraLance(jobs);
		
		List<Lance> lances = leilao.getLances();
		
		assertEquals(0, lances.size());
	}

	@Test
	public void naoDeveDobrarLanceDeUsuarioQueNaoDeuLances() {
		Usuario jobs = new Usuario("Steve Jobs");
		Usuario wozniack = new Usuario("Steve Wozniack");
		
		Leilao leilao = new Leilao("Macbook Pro");		
		leilao.propoe(new Lance(wozniack, 2000.00));
		
		leilao.dobraLance(jobs);
		
		List<Lance> lances = leilao.getLances();
		
		assertEquals(1, lances.size());
		assertEquals(2000.00, lances.get(0).getValor(), 0.00001);
	}

	private double ultimoLance(List<Lance> lances) {
		return lances.get(lances.size() - 1).getValor();
	}

}

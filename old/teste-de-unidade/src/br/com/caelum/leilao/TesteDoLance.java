package br.com.caelum.leilao;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoLance {
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirLanceNegativo() {
		new Lance(new Usuario("John Doe"), -1000.00);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirLanceNulo() {
		new Lance(new Usuario("John Doe"), 0.00);	
	}

}

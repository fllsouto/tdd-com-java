package br.com.alura;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.alura.dominio.CalculadoraImposto;
import br.com.alura.dominio.Lance;
import br.com.alura.dominio.Leilao;
import br.com.alura.dominio.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestaCalculadoraImposto {
	
	@Test
	public void deveCalcularImpostoDeEletronicosParaPrimeiraFaixa() {
		Usuario joao = new Usuario("João");
		Usuario paulo = new Usuario("Paulo");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Microondas");

		leilao.propoe(new Lance(joao, BigDecimal.valueOf(500.00)));
		leilao.propoe(new Lance(paulo, BigDecimal.valueOf(300.00)));
		leilao.propoe(new Lance(maria, BigDecimal.valueOf(700.00)));
		
		CalculadoraImposto calculadora = new CalculadoraImposto();
		
		BigDecimal impostoCalculadoObtido = calculadora.calculaImpostoParaLances(leilao);
		
		BigDecimal impostoCalculadoEsperado = BigDecimal.valueOf(15.00);
		
		assertEquals(impostoCalculadoEsperado, impostoCalculadoObtido);
	}

}
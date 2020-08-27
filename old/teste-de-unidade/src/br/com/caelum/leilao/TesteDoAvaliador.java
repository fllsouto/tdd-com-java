package br.com.caelum.leilao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador avaliador;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		this.avaliador = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
		System.out.println("Anotação @Before");
	}

	@After
	public void tearDown() {
		System.out.println("Anotação @After");
	}

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}
	
	@AfterClass
	public static void testandoAfterClassParte2() {
		System.out.println("after class parte 2");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo").constroi();
	
		avaliador.avalia(leilao);
	}

	@Test
	public void deveEntenderLancesEmOrdeCrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 250.00).lance(jose, 300.00)
				.lance(maria, 400.00).constroi();

		avaliador.avalia(leilao);

		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;
		
		assertThat(avaliador.getMaiorLance(), equalTo(maiorEsperado));
		assertThat(avaliador.getMenorLance(), equalTo(menorEsperado));
	}

	@Test
	public void deveEntenderLancesEmOrdeDecrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 500.00).lance(jose, 300.00)
				.lance(maria, 100.00).constroi();

		avaliador.avalia(leilao);

		double maiorEsperado = 500.0;
		double menorEsperado = 100.0;
		
		assertThat(avaliador.getMaiorLance(), equalTo(maiorEsperado));
		assertThat(avaliador.getMenorLance(), equalTo(menorEsperado));
	}

	@Test
	public void deveCalcularAMediaDosLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 300.00).lance(jose, 500.00)
				.lance(maria, 700.00).constroi();

		avaliador.avalia(leilao);

		double mediaValor = (300.0 + 500.0 + 700.0) / 3;

		assertThat(avaliador.getMediaTodos(), equalTo(mediaValor));
	}

//	@Test
//	public void testaMediaDeZeroLances() {
//		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
//
//		avaliador.avalia(leilao);
//
//		double mediaValor = 0;
//
//		assertEquals(mediaValor, avaliador.getMediaTodos(), 0.00001);
//	}

	@Test
	public void testaMediaLanceNulo() {
		Leilao leilao = new CriadorDeLeilao().para("IPhone 7").lance(joao, 0).constroi();

		avaliador.avalia(leilao);

		double mediaValor = 0;

		assertThat(avaliador.getMediaTodos(), equalTo(mediaValor));
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("IPhone 7").lance(joao, 1000.00).constroi();

		avaliador.avalia(leilao);

		assertThat(avaliador.getMenorLance(), equalTo(1000.00));
		assertThat(avaliador.getMaiorLance(), equalTo(1000.0));
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 100.00).lance(maria, 200.00)
				.lance(joao, 300.00).lance(maria, 400.00).constroi();

		avaliador.avalia(leilao);

		List<Lance> maiores = avaliador.getTresMaiores();

		assertThat(maiores.size(), equalTo(3));
		assertThat(maiores, hasItems(
				new Lance(maria, 200.00),
				new Lance(joao, 300.00),
				new Lance(maria, 400.00)
		));
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 200.00).lance(maria, 450.00)
				.lance(joao, 120.00).lance(maria, 700.00).lance(joao, 630.00).lance(maria, 230.00).constroi();

		avaliador.avalia(leilao);

		assertThat(avaliador.getMaiorLance(), equalTo(700.00));
		assertThat(avaliador.getMenorLance(), equalTo(120.00));
	}

	@Test
	public void deveEncontrarOsDoisMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 250.00).lance(maria, 400.00)
				.constroi();

		avaliador.avalia(leilao);

		List<Lance> maiores = avaliador.getTresMaiores();

		assertThat(maiores.size(), equalTo(2));
		assertThat(maiores, hasItems(
				new Lance(joao, 250.00),
				new Lance(maria, 400.00)
		));
	}

	@Test
	public void deveEncontrarApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 250.00).constroi();

		avaliador.avalia(leilao);

		List<Lance> maiores = avaliador.getTresMaiores();

		assertThat(maiores.size(), equalTo(1));
		assertThat(maiores, hasItem(
				new Lance(joao, 250.00)
		));
	}

//	@Test
//	public void naoDeveEncontrarLancesParaListaVazia() {
//		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
//
//		avaliador.avalia(leilao);
//
//		List<Lance> maiores = avaliador.getTresMaiores();
//
//		assertEquals(0, maiores.size());
//	}
}

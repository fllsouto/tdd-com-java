package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		if (lances.isEmpty() || podeDarLance(lance.getUsuario()))
			lances.add(lance);
	}

	private boolean podeDarLance(Usuario usuario) {
		return !doisLancesSeguidos(usuario) && (qntDeLancesDo(usuario) < 5);
	}

	private boolean doisLancesSeguidos(Usuario usuario) {
		return ultimoLanceDado().getUsuario().equals(usuario);
	}

	private int qntDeLancesDo(Usuario usuario) {
		int total = 0;
		for (Lance lance : this.lances) {
			if (lance.getUsuario().equals(usuario))
				total++;
		}
		return total;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		pegaUltimoLanceDo(usuario).ifPresent(lance -> {
			propoe(new Lance(usuario, lance.getValor() * 2));
		});
	}
	
	private Optional<Lance> pegaUltimoLanceDo(Usuario usuario) {
		Optional<Lance> ultimoLance = Optional.empty();
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario))
				ultimoLance = Optional.of(lance);
		}
		return ultimoLance;
	}

	
	
}

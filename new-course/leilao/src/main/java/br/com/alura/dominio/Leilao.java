package br.com.alura.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	private TipoDeItem tipoDeItem;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public Leilao(String descricao, TipoDeItem tipoDeItem) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
		this.tipoDeItem = tipoDeItem;
	}

	public void propoe(Lance lance) {
		int total = 0;
		for (Lance lanceDado : lances) {
			if (lanceDado.getUsuario().equals(lance.getUsuario()))
				total += 1;
		}

		if (lances.isEmpty() || (!ultimoLanceDado().getUsuario().equals(lance.getUsuario()) && total < 5)) {
			lances.add(lance);

		}
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

	public String getTipoDeItem() {
		return tipoDeItem.name();
	}
}

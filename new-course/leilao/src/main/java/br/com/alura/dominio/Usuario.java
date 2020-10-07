package br.com.alura.dominio;

import java.util.Random;

public class Usuario {

	private Integer id;
	private String nome;

	private static Random rand = new Random();

	public Usuario(String nome) {
		this.id = rand.nextInt(Integer.MAX_VALUE) + 1;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}

package controller;

import java.util.concurrent.Semaphore;

public class Cozinheiro {
	private String nome;
	private Semaphore fila_pratos;
	private Semaphore fila_entrega;
	public Cozinheiro(String _nome) {
		nome = _nome;
		fila_pratos = new Semaphore(5);
		fila_entrega = new Semaphore(1);
	}
	
	public void novoPrato() {
		new Prato(nome, fila_pratos, fila_entrega).start();
	}
}
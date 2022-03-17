package controller;

import java.util.concurrent.Semaphore;

public class Prato extends Thread {
	private String nome_cozinheiro;
	private String prato;
	private Semaphore fila_pratos;
	private Semaphore fila_entrega;

	public Prato(String _nome_cozinheiro, Semaphore _fila_pratos, Semaphore _fila_entrega) {
		nome_cozinheiro = _nome_cozinheiro;
		fila_pratos = _fila_pratos;
		fila_entrega = _fila_entrega;
	}

	public void run() {
		switch ( ( (int) this.getId() ) % 2) {
		case 0:
			prato = "Lasanha a Bolonhesa";
			try {
				fila_pratos.acquire();
				cozinharLasanha();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fila_pratos.release();
			}
			entregarPrato();
			break;
		case 1:
			prato = "Sopa de Cebola";
			try {
				fila_pratos.acquire();
				cozinharSopa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fila_pratos.release();
			}
			entregarPrato();
			break;
		}
	}

	private void cozinharSopa() {
		System.out.println(nome_cozinheiro + " cozinhando " + prato);
		// 500ms - 800ms = 0,5s a 0,8s
		int tempo_cozimento = (int) ((Math.random() * 301) + 500);
		int counter = 0;
		while (counter < tempo_cozimento) {
			try {
				if (counter + 100 <= tempo_cozimento)
					counter += 100;
				else
					counter += tempo_cozimento - counter;
				System.out.println(nome_cozinheiro + ": " + prato + " " + (counter * 100) / tempo_cozimento + "%");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cozinharLasanha() {
		System.out.println(nome_cozinheiro + " cozinhando " + prato);
		// 600ms - 1200ms = 0,6s a 1,2s
		int tempo_cozimento = (int) ((Math.random() * 601) + 600);
		int counter = 0;
		while (counter < tempo_cozimento) {
			try {
				if (counter + 100 <= tempo_cozimento)
					counter += 100;
				else
					counter += tempo_cozimento - counter;
				System.out.println(nome_cozinheiro + ": " + prato + " " + (counter * 100) / tempo_cozimento + "%");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void entregarPrato() {
		try {
			System.out.println(nome_cozinheiro + ": Entregando " + prato + " ...");
			fila_entrega.acquire();
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fila_entrega.release();
			System.out.println("DONE! " + nome_cozinheiro + ": " + prato + " entregue.");
		}
	}
}
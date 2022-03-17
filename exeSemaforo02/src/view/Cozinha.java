package view;

import java.util.Random;

import controller.Cozinheiro;

public class Cozinha {
	public static final String NOMES[] = { "Jorge", "Marcio", "Carlos", "Cezar", "Vinicius", "Leda", "Zoraide",
			"Agatha", "Helena", "Carla" };

	public static void main(String[] args) {

		// A quantidade de cozinheiros na simulação é entre 1 e 10
		int qtd_cozinheiros = (int) ((Math.random() * 10) + 1);
		// A quantidade de pratos para cada cozinheiro é entre 5 e 15
		int qtd_pratos = (int) ((Math.random() * 10) + 5);

		Cozinheiro[] cozinheiros = new Cozinheiro[qtd_cozinheiros];
		// String responsável por guardar o nome de cada cozinheiro na simulação
		String buffer = "";

		for (int i = 0; i < qtd_cozinheiros; i++) {
			String nome = nomeAleatorio();
			cozinheiros[i] = new Cozinheiro(nome);
			buffer += nome + " | ";
		}
		
		// Apresentamos todos os cozinheiros no começo da simulação
		System.out.println("Cozinheiros: " + buffer);

		// Distribuímos os pratos para todos os cozinheiros
		for (int i = 0; i < qtd_pratos; i++)
			for(Cozinheiro cozinheiro : cozinheiros)
				cozinheiro.novoPrato();

	}


	// Função retorna um nome aleatório da array de NOMES
	public static String nomeAleatorio() {
		int random = new Random().nextInt(NOMES.length);
		return NOMES[random];
	}
}

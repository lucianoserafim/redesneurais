package perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Rede {

	private static BufferedReader leitor;

	public Rede() {

	}

	public void funcaoGradiente(float saida, float taxa) {

	}

	public List<Iris> lerArquivo(String nome) {

		String linha;

		Iris iris = null;

		List<Iris> listaIris = new ArrayList<Iris>();

		try {

			FileReader reader = new FileReader("/home/luciano/MEGAsync/2015.2/Redes Neurais/base");
			leitor = new BufferedReader(reader);
			StringTokenizer st = null;

			while ((linha = leitor.readLine()) != null) {

				st = new StringTokenizer(linha, ",");

				while (st.hasMoreTokens()) {

					iris = new Iris();

					iris.setSepalLength(Float.parseFloat(st.nextToken()));
					iris.setSepalWidth(Float.parseFloat(st.nextToken()));
					iris.setPetalLenght(Float.parseFloat(st.nextToken()));
					iris.setPetalWidth(Float.parseFloat(st.nextToken()));
					iris.setClasse(st.nextToken());

					if (iris.getClasse().equals(nome)) {

						iris.setClasse("1");

					} else {

						iris.setClasse("0");

					}

					listaIris.add(iris);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return listaIris;

	}

	public void rede(String nome) {

		int i;
		int j;
		int epocas = 100;

		for (j = 0; j < epocas; j++) {

			System.out.println(j + 1 + "º Época");

			List<Iris> listaOrd = lerArquivo(nome);

			for (i = 0; i < listaOrd.size(); i++) {

				Collections.shuffle(listaOrd);

				Perceptron perceptron1 = new Perceptron(listaOrd.get(i));
				System.out.print(nome + " Valor esperado: " + listaOrd.get(i).getClasse() + " Valor obtido: "
						+ perceptron1.perceptron());

				if (perceptron1.perceptron() == Integer.parseInt(listaOrd.get(i).getClasse())) {

					System.out.println(" Acertou!!!");

				} else {

					System.out.println(" Errou!!!");

				}

				i++;

			}

		}

	}

}

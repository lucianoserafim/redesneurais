package mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static List<Caractere> lerArquivo(int classe) {

		BufferedReader leitor;

		List<Float> listaPixel = null;

		Caractere caractere = null;

		List<Caractere> listaCaractere = new ArrayList<Caractere>();

		String linha;

		try {

			FileReader reader = new FileReader("/home/luciano/git/redesneurais/train");
			leitor = new BufferedReader(reader);
			StringTokenizer st = null;

			while ((linha = leitor.readLine()) != null) {

				caractere = new Caractere();

				listaPixel = new ArrayList<Float>();

				st = new StringTokenizer(linha, ",");

				String s = st.nextToken();

				if (Integer.parseInt(s) == classe) {

					caractere.setLabel(1);

					System.out.println("Classe " + 1);

				} else {

					caractere.setLabel(0);

					System.out.println("Classe " + 0);

				}

				while (st.hasMoreTokens()) {

					listaPixel.add(Float.parseFloat(st.nextToken()));

				}

				caractere.setListaPixel(listaPixel);

				listaCaractere.add(caractere);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return listaCaractere;

	}

	public static float[][] matrizPesos(int linha, int coluna) {

		NumberFormat formatarFloat = new DecimalFormat("0.00");

		float[][] wji = new float[linha][coluna];

		for (int i = 0; i < linha; i++) {

			for (int j = 0; j < coluna; j++) {

				float n = (float) Math.random();
				String f = formatarFloat.format(n).replace(",", ".");

				System.out.print(" ");
				System.out.print(wji[i][j] = Float.parseFloat(f));

			}

			System.out.println();

		}

		return wji;

	}

	public static double sigmoide(double u) {
		double e = Math.E;
		return 1 / (1 + Math.pow(e, -u * (0.5)));
	}

	public static float atualizaSaida(float uk, float y) {

		float deltak = (float) ((sigmoide(uk)) * (1 - sigmoide(uk)) * (y - sigmoide(uk)));

		return deltak;

	}

	public static double atualizaIntermediarias(float uk, float wm, double deltam) {

		double deltak = (sigmoide(uk)) * (1 - sigmoide(uk)) * (wm * deltam);

		return deltak;

	}

	public static void pausa() {

		try {

			Thread.currentThread();
			Thread.sleep(1000);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		int acertos = 0;
		int erros = 0;

		// Classe que queremos observar
		float classe = (float) 4;

		int classes = 0;

		// Vetores Wm
		float wm1 = 0;
		float wm2 = 0;

		// Quantidade de épocas
		int epocas = 10;

		// Número de exemplos
		int exemplos = 42000;

		// Taxa de aprendizado
		float n = (float) 20;

		// Número de perceptrons por camada
		int quantPerceptronsEntr = 3;
		int quantPerceptronsEsc = 2;
		int quantPerceptronsSaida = 1;

		// Método que lê o arquivo e rotarna uma lista de objetos
		List<Caractere> c = lerArquivo((int) classe);

		for (int i = 0; i < c.size(); i++) {

			if (c.get(i).getLabel() == 1) {

				classes++;

			}

		}

		CamadaEntrada camada1 = new CamadaEntrada();
		CamadaEscondida camada2 = new CamadaEscondida();
		CamadaSaida camada3 = new CamadaSaida();

		float[][] mp1 = matrizPesos(quantPerceptronsEntr, c.get(0).getListaPixel().size() + 1);
		float[][] mp2 = matrizPesos(quantPerceptronsEsc, quantPerceptronsEntr + 1);
		float[][] mp3 = matrizPesos(quantPerceptronsSaida, quantPerceptronsEsc + 1);

		for (int i = 0; i < epocas; i++) {

			if (acertos != classes) {

				acertos = 0;
				erros = 0;

				System.out.println("Época: " + (i + 1) + " ");

				for (int j = 0; j < exemplos; j++) {

					float[] cout1 = camada1.perceptron(c.get(j), mp1, quantPerceptronsEntr);

					float[] cout2 = camada2.perceptron(cout1, mp2, quantPerceptronsEsc);

					float[] cout3 = camada3.perceptron(cout2, mp3, quantPerceptronsSaida);

					if (cout3[0] == c.get(j).getLabel()) {

						if (c.get(j).getLabel() == 1) {

							// System.out.println("Saída " + cout3[0] + " classe
							// " +
							// c.get(j).getLabel() + " Acertou!!!");

							// pausa();

							acertos++;

						}

					} else {

						erros++;

						// pausa();

						// System.err.println("Saída " + cout3[0] + " classe " +
						// c.get(j).getLabel() + " errou!!!");

						float delta3 = atualizaSaida(cout3[0], 1);

						for (int k = 0; k < 3; k++) {

							mp3[0][k] = (float) (mp3[0][k] + (n * (cout3[0]) * delta3));

							wm1 = wm1 + mp3[0][k];

						}

						float delta2 = 0;

						for (int k = 0; k < 2; k++) {

							float r = (float) atualizaIntermediarias(cout2[k], wm1, delta3);

							delta2 = delta2 + r;

							for (int k2 = 0; k2 < 4; k2++) {

								mp2[k][k2] = (float) (mp2[k][k2] + (n * (cout2[k]) * r));

								wm2 = wm2 + mp2[0][k];

							}

						}

						for (int k = 0; k < 3; k++) {

							for (int k2 = 0; k2 < 785; k2++) {

								mp1[k][k2] = (float) (mp1[k][k2]
										+ (n * (cout1[k]) * atualizaIntermediarias(cout1[k], wm2, delta2)));

								wm2 = wm2 + mp2[0][k];

							}

						}

						delta2 = 0;
						wm1 = 0;
						wm2 = 0;

					}

				}

				System.out.println("Número de acertos " + acertos);
				System.out.println("Número de classes " + classes);

			} else {
				
				epocas = 0;
				
			}

		}

	}

}

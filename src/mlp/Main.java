package mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static List<Caractere> lerArquivo() {

		BufferedReader leitor;

		List<Float> listaPixel = null;

		Caractere caractere = null;

		List<Caractere> listaCaractere = new ArrayList<Caractere>();

		String linha;

		try {

			FileReader reader = new FileReader("/home/luciano/MEGAsync/2015.2/Redes Neurais/train");
			leitor = new BufferedReader(reader);
			StringTokenizer st = null;

			while ((linha = leitor.readLine()) != null) {

				caractere = new Caractere();

				listaPixel = new ArrayList<Float>();

				st = new StringTokenizer(linha, ",");

				caractere.setLabel(Integer.parseInt(st.nextToken()));

				while (st.hasMoreTokens()) {

					listaPixel.add(Float.parseFloat(st.nextToken()));

				}

				caractere.setListaPixel(listaPixel);

				listaCaractere.add(caractere);

			}

			System.out.println(caractere.getListaPixel().size());

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("Arquivo lido com sucesso!!!");

		return listaCaractere;

	}

	public static float[][] matrizPesos(int linha, int coluna) {

		NumberFormat formatarFloat = new DecimalFormat("0.00");

		float[][] wji = new float[linha][coluna];

		for (int i = 0; i < linha; i++) {

			for (int j = 0; j < coluna; j++) {

				float n = (float) Math.random();
				String f = formatarFloat.format(n).replace(",", ".");

				wji[i][j] = Float.parseFloat(f);

			}

		}

		System.out.println("Matriz de pesos wji criado om succeso!!!");

		return wji;

	}

	public static void main(String[] args) {

		int quantPerceptronsEntr = 3;
		int quantPerceptronsEsc = 2;
		int quantPerceptronsSaida = 1;

		List<Caractere> c = lerArquivo();

		CamadaEntrada camada1 = new CamadaEntrada();
		CamadaEscondida camada2 = new CamadaEscondida();
		CamadaSaida camada3 = new CamadaSaida();

		float[][] mp1 = matrizPesos(quantPerceptronsEntr, c.get(0).getListaPixel().size() + 1);
		float[][] mp2 = matrizPesos(quantPerceptronsEsc, quantPerceptronsEntr + 1);
		float[][] mp3 = matrizPesos(quantPerceptronsSaida, quantPerceptronsEsc + 1);

		for (int i = 0; i < 5; i++) {

			float[] cout1 = camada1.perceptron(c.get(i), mp1, quantPerceptronsEntr);

			float[] cout2 = camada2.perceptron(cout1, mp2, quantPerceptronsEsc);

			float[] cout3 = camada3.perceptron(cout2, mp3, quantPerceptronsSaida);

		}

	}

}

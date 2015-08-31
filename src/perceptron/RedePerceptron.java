package perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class RedePerceptron {

	private static BufferedReader leitor;

	private float[] wi = { (float) 0.35, (float) 0.02, (float) 0.003, (float) 0.004, (float) 0.005 };

	private float[] deltaWi = new float[5];

	private float n = (float) 0.001;

	private int NumAcertos, numErros;

	public RedePerceptron() {

	}

	public List<Iris> lerArquivo(String nome, String base) {

		String linha;

		Iris iris = null;

		List<Iris> listaIris = new ArrayList<Iris>();

		try {

			FileReader reader = new FileReader("/home/luciano/MEGAsync/2015.2/Redes Neurais/" + base);
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

	public void rede(String nome, String base, int numEpocas) {

		int i;
		int j;
		int epocas = numEpocas;

		List<Iris> listaOrd = lerArquivo(nome, base);

		Collections.shuffle(listaOrd);

		for (j = 0; j < epocas; j++) {

			NumAcertos = 0;

			numErros = 0;

			for (i = 0; i < listaOrd.size(); i++) {

				try {

					Thread.currentThread();
					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

				Perceptron perceptron = new Perceptron(listaOrd.get(i), wi);

				int t = Integer.parseInt(listaOrd.get(i).getClasse());
				int o = perceptron.perceptron();

				if (o == t) {

					if (o == 1) {

						System.out.println("t [" + t + "] o [" + o + "] Acertou");

						NumAcertos++;

					}

				} else {

					System.err.println("t [" + t + "] o [" + o + "] Errou");

					numErros++;

					deltaWi[0] = (t - o) * (-1);
					deltaWi[1] = (t - o) * listaOrd.get(i).getPetalLenght();
					deltaWi[2] = (t - o) * listaOrd.get(i).getPetalWidth();
					deltaWi[3] = (t - o) * listaOrd.get(i).getSepalLength();
					deltaWi[4] = (t - o) * listaOrd.get(i).getSepalWidth();

					wi[0] = wi[0] + (n * deltaWi[0]);
					wi[1] = wi[1] + (n * deltaWi[1]);
					wi[2] = wi[2] + (n * deltaWi[2]);
					wi[3] = wi[3] + (n * deltaWi[3]);
					wi[4] = wi[4] + (n * deltaWi[4]);

				}

			}

		}

		System.out.println();

		System.out.println("Classe: " + nome + " Acertos: " + NumAcertos + " Erros: " + numErros);
		System.out.print("w0 ");
		System.out.println(wi[0]);
		System.out.print("w1 ");
		System.out.println(wi[1]);
		System.out.print("w2 ");
		System.out.println(wi[2]);
		System.out.print("w3 ");
		System.out.println(wi[3]);
		System.out.print("w4 ");
		System.out.println(wi[4]);

	}

}

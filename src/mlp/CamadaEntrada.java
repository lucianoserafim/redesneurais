package mlp;

public class CamadaEntrada {

	private float x0 = -1;

	private float[] somatorio1;

	private float[][] pesos;

	public CamadaEntrada() {

	}

	public float[] perceptron(Caractere caractere, float[][] vetorPesos, int quantPerceptrons) {

		somatorio1 = new float[quantPerceptrons];

		pesos = vetorPesos;

		return funcaoComposicao(caractere, pesos, quantPerceptrons);

	}

	public float[] funcaoComposicao(Caractere caractere, float[][] vetorPesos, int quantPerceptrons) {

		for (int i = 0; i < quantPerceptrons; i++) {

			somatorio1[i] = (x0 * vetorPesos[i][0]);

			for (int j = 1; j < caractere.getListaPixel().size() + 1; j++) {

				somatorio1[i] = somatorio1[i] + ((vetorPesos[i][j] * caractere.getListaPixel().get(j - 1)));

			}

		}

		return funcaoAtivacao(somatorio1);

	}

	public float[] funcaoAtivacao(float[] u) {

		double e = Math.E;

		float[] y = new float[u.length];

		for (int i = 0; i < u.length; i++) {

			y[i] = (float) (1 / (1 + Math.pow(e, -u[i] * (0.5))));

		}

		return y;

	}

}

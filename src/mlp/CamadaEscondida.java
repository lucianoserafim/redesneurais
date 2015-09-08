package mlp;

public class CamadaEscondida {

	private float x0 = -1;

	private float[] somatorio2;

	private float[][] pesos;

	public CamadaEscondida() {

	}

	public float[] perceptron(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		somatorio2 = new float[quantPerceptrons];

		pesos = vetorPesos;

		return funcaoComposicao(cin, pesos, quantPerceptrons);

	}

	public float[] funcaoComposicao(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		for (int i = 0; i < quantPerceptrons; i++) {

			somatorio2[i] = (x0 * vetorPesos[i][0]);

			for (int j = 1; j < cin.length + 1; j++) {

				somatorio2[i] = somatorio2[i] + ((vetorPesos[i][j] * cin[j - 1]));

			}

		}

		return funcaoAtivacao(somatorio2);

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

package mlp;

public class CamadaSaida {

	private float[] somatorio3;

	private float[][] pesos;

	public CamadaSaida() {

	}

	public float[] perceptron(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		somatorio3 = new float[quantPerceptrons];

		pesos = vetorPesos;

		return funcaoComposicao(cin, pesos, quantPerceptrons);

	}

	public float[] funcaoComposicao(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		for (int i = 0; i < quantPerceptrons; i++) {

			for (int j = 0; j < cin.length; j++) {

				somatorio3[i] = somatorio3[i] + ((vetorPesos[i][j] * cin[j]));

			}

		}

		return funcaoAtivacao(somatorio3);

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

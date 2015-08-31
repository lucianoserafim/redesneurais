package mlp;

public class CamadaSaida {

	private float[] somatorio;

	private float[][] pesos;

	public CamadaSaida() {

	}

	public float[] perceptron(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		somatorio = new float[quantPerceptrons];

		pesos = vetorPesos;

		return funcaoComposicao(cin, pesos, quantPerceptrons);

	}

	public float[] funcaoComposicao(float[] cin, float[][] vetorPesos, int quantPerceptrons) {

		for (int i = 0; i < quantPerceptrons; i++) {

			for (int j = 0; j < cin.length; j++) {

				somatorio[i] = somatorio[i] + ((vetorPesos[i][j] * cin[j]));

			}

			System.out.println("Camada Saida: " + somatorio[i]);

		}

		return funcaoAtivacao(somatorio);

	}

	public float[] funcaoAtivacao(float[] u) {

		float[] y = new float[u.length];

		for (int i = 0; i < u.length; i++) {

			y[i] = (float) Math.tanh(u[i]);

		}

		return y;

	}

	public void atualizaPesos() {

	}

}

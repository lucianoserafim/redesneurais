package mlp;
public class CamadaEntrada {

	private float x0 = -1;

	private float[] somatorio;

	private float[][] pesos;

	public CamadaEntrada() {

	}

	public float[] perceptron(Caractere caractere, float[][] vetorPesos, int quantPerceptrons) {

		somatorio = new float[quantPerceptrons];

		pesos = vetorPesos;

		return funcaoComposicao(caractere, pesos, quantPerceptrons);

	}

	public float[] funcaoComposicao(Caractere caractere, float[][] vetorPesos, int quantPerceptrons) {

		for (int i = 0; i < quantPerceptrons; i++) {

			somatorio[i] = (x0 * vetorPesos[i][0]);

			for (int j = 1; j < caractere.getListaPixel().size() + 1; j++) {

				somatorio[i] = somatorio[i] + ((vetorPesos[i][j] * caractere.getListaPixel().get(j - 1)));

			}

			System.out.println("Camada Entrada: " + somatorio[i]);

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

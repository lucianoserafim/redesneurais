package perceptron;

public class Perceptron {

	private Iris iris;

	private float[] listaPesos;

	private float x0 = -1;

	public Perceptron(Iris iris, float[] listaPesos) {

		this.iris = iris;
		this.listaPesos = listaPesos;

	}

	public int perceptron() {

		return funcaoAtivacao(funcaoAgregacao());

	}

	public float funcaoAgregacao() {

		return (x0 * listaPesos[0]) + (iris.getPetalLenght() * listaPesos[1]) + (iris.getPetalWidth() * listaPesos[2])
				+ (iris.getSepalLength() * listaPesos[3]) + (iris.getSepalWidth() * listaPesos[4]);

	}

	public int funcaoAtivacao(float u) {

		int y;

		if (u > 0) {

			y = 1;

		} else {

			y = 0;

		}

		return y;

	}

}

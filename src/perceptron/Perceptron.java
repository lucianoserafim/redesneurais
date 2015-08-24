package perceptron;

public class Perceptron {

	private Iris iris;

	private float[] listaPesos = { 1, 2, 3, 4, 5 };

	private float bias = -1;

	public Perceptron(Iris iris) {

		this.iris = iris;

	}

	public int perceptron() {

		return funcaoAtivacao(funcaoAgregacao());

	}

	public float funcaoAgregacao() {

		return (bias * listaPesos[0]) + (iris.getPetalLenght() * listaPesos[1]) + (iris.getPetalWidth() * listaPesos[2])
				+ (iris.getSepalLength() * listaPesos[3]) + (iris.getSepalWidth() * listaPesos[4]);

	}

	public int funcaoAtivacao(float y) {

		int y1;

		if (y > listaPesos[0]) {

			y1 = 1;

		} else {

			y1 = 0;

		}

		return y1;

	}

}

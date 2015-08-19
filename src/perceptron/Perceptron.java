package perceptron;

import java.util.ArrayList;

public class Perceptron {

	public Perceptron() {
		
	}

	public int funcaoAtivacao(ArrayList<Iris> iris, ArrayList<Float> pesos) {

		int i = 0;

		for (i = 0; i < iris.size(); i++) {

			float y = iris.get(i).getPetalLenght() * pesos.get(0);

		}

		return 0;

	}

	public int atualizaPesos() {

		return 0;

	}

}

package perceptron;

public class Main {

	public static void main(String[] args) {

		Rede rede1 = new Rede();
		Rede rede2 = new Rede();
		Rede rede3 = new Rede();

		rede1.rede("Iris-setosa", "base", 5);
		rede2.rede("Iris-versicolor", "base",100);
		rede3.rede("Iris-virginica", "base", 100);

	}

}

package perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		String linha;

		Iris iris = null;

		try {

			FileReader reader = new FileReader("/home/luciano/MEGA/MEGAsync Uploads/2015.2/Redes Neurais/base");
			BufferedReader leitor = new BufferedReader(reader);
			StringTokenizer st = null;

			while ((linha = leitor.readLine()) != null) {

				st = new StringTokenizer(linha, ",");
				String dados = null;

				while (st.hasMoreTokens()) {

					iris = new Iris();

					iris.setSepalLength(Float.parseFloat(st.nextToken()));
					iris.setSepalWidth(Float.parseFloat(st.nextToken()));
					iris.setPetalLenght(Float.parseFloat(st.nextToken()));
					iris.setPetalWidth(Float.parseFloat(st.nextToken()));
					iris.setClasse(st.nextToken());

					System.out.println(iris.getSepalLength() + " " + iris.getSepalWidth() + " " + iris.getPetalLenght()
							+ " " + iris.getSepalWidth() + " " + iris.getClasse());

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

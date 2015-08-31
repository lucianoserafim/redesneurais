package mlp;

import java.util.List;

public class Caractere {

	private int label;

	private List<Float> listaPixel;

	public Caractere() {

	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public List<Float> getListaPixel() {
		return listaPixel;
	}

	public void setListaPixel(List<Float> listaPixel) {
		this.listaPixel = listaPixel;
	}

}

package control;

import java.awt.Color;

import modelo.Casilla;

public class Control {

	public boolean win(Casilla[][] casilla) {
		for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla[i].length; j++) {
				if (casilla[i][j].isOculta() && casilla[i][j].getNumero() > 0) {
					return false;
				}
				if (casilla[i][j].isMina() && !casilla[i][j].isBandera()) {
					return false;
				}
			}
		}
		return true;

	};

	public Color obtenerColor(int numero) {
		switch (numero) {
		case 1:
			return Color.BLUE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.RED;
		case 4:
			return new Color(18, 16, 123);
		case 5:
			return new Color(118, 0, 4);
		default:
			return Color.BLACK;
		}
	}

}

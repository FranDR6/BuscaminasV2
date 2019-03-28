package modelo;

import java.util.Random;

public class Tablero {

	private Casilla[][] casilla;

	private int filas;
	private int columnas;

	public Tablero(int filas, int columnas, int minas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
		this.casilla = new Casilla[filas][columnas];
		rellenarCasilla();
		colocarMinas(minas);
		colocarNumeros();
	}

	private void rellenarCasilla() {
		for (int i = 0; i < this.casilla.length; i++) {
			for (int j = 0; j < this.casilla[i].length; j++) {
				this.casilla[i][j] = new Casilla();
			}
		}
	}

	private void colocarMinas(int minas) {
		int contador = 0;

		do {
			int i = obtenerNumeroAleatorio(0, this.filas - 1);
			int j = obtenerNumeroAleatorio(0, this.columnas - 1);
			if (!this.casilla[i][j].isMina()) {
				this.casilla[i][j].setMina(true);
				contador++;
			}
		} while (contador != minas);
	}

	private static int obtenerNumeroAleatorio(int min, int max) {
		Random aleatorio = new Random();
		return aleatorio.nextInt((max + 1) - min) + min;
	}

	private void colocarNumeros() {
		for (int i = 0; i < this.casilla.length; i++) {
			for (int j = 0; j < this.casilla[0].length; j++) {
				if (this.casilla[i][j].isMina()) {
					sumarAlrededor(i, j);
				}
			}
		}
	}

	private void sumarAlrededor(int i, int j) {
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x < 0 || y < 0 || x > this.filas - 1 || y > this.columnas - 1) {
					continue;
				}
				if (!this.casilla[x][y].isMina()) {
					this.casilla[x][y].setNumero(this.casilla[x][y].getNumero() + 1);
				}
			}
		}
	}

	public Casilla[][] getCasilla() {
		return casilla;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int getNumero(int i, int j) {
		return this.casilla[i][j].getNumero();
	}

	public boolean isOculta(int i, int j) {
		return this.casilla[i][j].isOculta();
	}

	public void setOculta(int i, int j, boolean valor) {
		this.casilla[i][j].setOculta(valor);
	}

	public boolean isMina(int i, int j) {
		return this.casilla[i][j].isMina();
	}

	public boolean isBandera(int i, int j) {
		return this.casilla[i][j].isBandera();
	}

	public void setBandera(int i, int j, boolean valor) {
		this.casilla[i][j].setBandera(valor);
	}
}

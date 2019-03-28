package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import modelo.Tablero;

public class Logica {

	public void crearBotonera(JButton[][] botonera, JPanel panelBotonera, Tablero tablero) {

		int i = tablero.getCasilla().length;
		int j = tablero.getCasilla()[0].length;

		panelBotonera.setLayout(new GridLayout(i, j, 1, 1));
		botonera = new JButton[i][j];

		for (int x = 0; x < botonera.length; x++) {
			for (int y = 0; y < botonera[x].length; y++) {
				botonera[x][y] = new JButton();
				botonera[x][y].setName(x + " " + y);
				botonera[x][y].setBackground(Color.WHITE);
				botonera[x][y].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				botonera[x][y].setSize(new Dimension(20, 20));
				// listener
				botonera[x][y].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JButton boton = (JButton) e.getSource();
						String[] coordenadas = boton.getName().split(" ");
						int i = Integer.parseInt(coordenadas[0]);
						int j = Integer.parseInt(coordenadas[1]);
						
						if (tablero.getCasilla()[i][j].getNumero() > 0) {
//							mostrarNumero(i, j, botonera[i][j], tablero);
						}
					}
				});
				
				panelBotonera.add(botonera[x][y]);
			}
		}
		panelBotonera.revalidate();
	}

	private void mostrarNumero(int i, int j, JButton boton, Tablero tablero) {
		boton.setForeground(obtenerColor(tablero.getCasilla()[i][j].getNumero()));
		boton.setText(String.valueOf(tablero.getCasilla()[i][j].getNumero()));
		tablero.getCasilla()[i][j].setOculta(false);
	}

	private Color obtenerColor(int numero) {
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

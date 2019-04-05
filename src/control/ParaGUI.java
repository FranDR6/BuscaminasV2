package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Tablero;
import vista.Ventana;

public class ParaGUI extends Ventana {

	private Control control;
	private Tablero tablero;

	public ParaGUI() {
		super();
		this.control = new Control();
		listenerBotones();
	}

	private void listenerBotones() {
		getBtnFacil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(8, 8, 5);
				crearBotonera();
			}
		});
		getBtnMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(16, 16, 40);
				crearBotonera();
			}
		});
		getBtnDificil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(16, 30, 99);
				crearBotonera();
			}
		});
	}

	private void crearBotonera() {
		int ladoBotones = 40;
		getPanelBotonera().removeAll();
		cambiarLadoVentana(getContentPane().getX(), getContentPane().getY(), ladoBotones * tablero.getColumnas() + 50,
				ladoBotones * tablero.getFilas() + 90);
		getPanelAbsolute().setBounds(
				new Rectangle(20, 50, tablero.getColumnas() * ladoBotones, tablero.getFilas() * ladoBotones));
		getPanelBotonera()
				.setBounds(new Rectangle(0, 0, tablero.getColumnas() * ladoBotones, tablero.getFilas() * ladoBotones));
		getPanelBotonera().setLayout(new GridLayout(tablero.getFilas(), tablero.getColumnas(), 1, 1));
		setBotonera(new JButton[tablero.getFilas()][tablero.getColumnas()]);

		centrarVentana();

		for (int i = 0; i < getBotonera().length; i++) {
			for (int j = 0; j < getBotonera()[i].length; j++) {
				personalizarBotones(i, j);
				getBotonera()[i][j].addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						JButton boton = (JButton) e.getSource();
						String[] coordenadas = boton.getName().split(" ");
						int i = Integer.parseInt(coordenadas[0]);
						int j = Integer.parseInt(coordenadas[1]);

						if (e.getButton() == 3 && tablero.isOculta(i, j)) {
							colocarBandera(i, j);
						} else {
							mostrarAdyacentes(i, j);
							mostrarNumero(i, j);
							recursividad(i, j);
							gameOver(i, j);
						}
						if (control.win(tablero.getCasilla())) {
							JOptionPane.showMessageDialog(null, "Has ganado!!");
							getPanelBotonera().removeAll();
						}
						actualizarPantalla();

					}

					public void mousePressed(MouseEvent e) {
					}

					public void mouseExited(MouseEvent e) {
					}

					public void mouseEntered(MouseEvent e) {
					}

					public void mouseClicked(MouseEvent e) {
					}
				});

				getPanelBotonera().add(getBotonera()[i][j]);
			}
		}
		getContentPane().revalidate();
	}

	private void mostrarAdyacentes(int i, int j) {
		int contador = 0;
		if (this.tablero.getNumero(i, j) == getBanderasAdyacentes(i, j) && !this.tablero.isOculta(i, j)
				&& !this.tablero.isMina(i, j)) {
			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					if (x < 0 || y < 0 || x > getBotonera().length - 1 || y > getBotonera()[0].length - 1)
						continue;
					mostrarNumero(x, y);
					recursividad(x, y);
					gameOver(x, y);
				}
			}
		}
	}

	private int getBanderasAdyacentes(int i, int j) {
		int retorno = 0;
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x < 0 || y < 0 || x > getBotonera().length - 1 || y > getBotonera()[0].length - 1)
					continue;
				if (this.tablero.isBandera(x, y))
					retorno++;
			}
		}
		return retorno;
	}

	private void colocarBandera(int i, int j) {
		if (!tablero.isBandera(i, j)) {
			tablero.setBandera(i, j, true);
			ponerImgBandera(i, j, false);
		} else {
			tablero.setBandera(i, j, false);
			ponerImgBandera(i, j, true);
		}
	}

	private void gameOver(int i, int j) {
		if (tablero.isMina(i, j) && !tablero.isBandera(i, j)) {
			for (int x = 0; x < getBotonera().length; x++) {
				for (int y = 0; y < getBotonera()[i].length; y++) {
					if (tablero.isMina(x, y)) {
						if (!tablero.isBandera(x, y)) {
							ponerImgBomba(x, y, false);
						} else {
							ponerImgBomba(x, y, true);
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Has perdido!!");
			getPanelBotonera().removeAll();
		}
	}

	private void recursividad(int i, int j) {
		if (this.tablero.isOculta(i, j) && this.tablero.getNumero(i, j) == 0 && !this.tablero.isMina(i, j)) {
			this.tablero.setOculta(i, j, false);
			getBotonera()[i][j].setVisible(false);
			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					if (x < 0 || y < 0 || x > getBotonera().length - 1 || y > getBotonera()[0].length - 1) {
						continue;
					}
					recursividad(x, y);
					mostrarNumero(x, y);
				}
			}
		}
	}

	private void mostrarNumero(int i, int j) {
		if (this.tablero.getNumero(i, j) > 0 && this.tablero.isOculta(i, j) && !this.tablero.isMina(i, j)
				&& !tablero.isBandera(i, j)) {
			getBotonera()[i][j].setText(String.valueOf(tablero.getNumero(i, j)));
			getBotonera()[i][j].setBackground(Color.WHITE);
			getBotonera()[i][j].setForeground(control.obtenerColor(tablero.getNumero(i, j)));
			this.tablero.setOculta(i, j, false);
		}
	}

}

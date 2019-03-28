package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Ventana extends JFrame {

	private JButton[][] botonera;
	private JPanel contentPane;
	private JButton btnFacil;
	private JButton btnMedio;
	private JButton btnDificil;
	private JPanel panelBotonera;
	private JPanel panelDificultad;
	private JPanel panelAbsolute;

	public Ventana() {
		crearVentana();
	}

	protected void cambiarLadoVentana(int ancho, int alto) {
		setBounds(100, 100, ancho, alto);
	}

	private void crearVentana() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setTitle("BUSCAMINAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 500, 381, 429);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		panelDificultad = new JPanel();
		panelDificultad.setOpaque(false);
		panelDificultad.setMinimumSize(new Dimension(50, 50));
		panelDificultad.setBounds(new Rectangle(20, 15, 320, 30));
		contentPane.add(panelDificultad);

		btnFacil = new JButton("FACIL");
		btnFacil.setBounds(new Rectangle(0, 0, 100, 100));
		btnFacil.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		btnFacil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFacil.setForeground(Color.BLACK);
		btnFacil.setBackground(Color.WHITE);

		btnMedio = new JButton("MEDIO");
		btnMedio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		btnMedio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMedio.setForeground(Color.BLACK);
		btnMedio.setBackground(Color.WHITE);

		btnDificil = new JButton("DIFICIL");
		btnDificil.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		btnDificil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDificil.setForeground(Color.BLACK);
		btnDificil.setBackground(Color.WHITE);
		panelDificultad.setLayout(new GridLayout(0, 3, 10, 0));
		panelDificultad.add(btnFacil);
		panelDificultad.add(btnMedio);
		panelDificultad.add(btnDificil);

		panelAbsolute = new JPanel();
		panelAbsolute.setBounds(22, 55, 509, 416);
		contentPane.add(panelAbsolute);
		panelAbsolute.setLayout(null);

		panelBotonera = new JPanel();
		panelAbsolute.add(panelBotonera);
		panelBotonera.setBackground(Color.DARK_GRAY);
		panelBotonera.setLayout(new GridLayout(1, 0, 0, 0));
		panelAbsolute.setBackground(Color.DARK_GRAY);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnFacil() {
		return btnFacil;
	}

	public JButton getBtnMedio() {
		return btnMedio;
	}

	public JButton getBtnDificil() {
		return btnDificil;
	}

	public JPanel getPanelBotonera() {
		return panelBotonera;
	}

	public JPanel getPanelDificultad() {
		return panelDificultad;
	}

	public JPanel getPanelAbsolute() {
		return panelAbsolute;
	}

	public JButton[][] getBotonera() {
		return botonera;
	}

	public JPanel getPanel() {
		return panelDificultad;
	}

	public void setBotonera(JButton[][] botonera) {
		this.botonera = botonera;
	}

}

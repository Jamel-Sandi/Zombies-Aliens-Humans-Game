package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GUIInicio extends JFrame {
	// botones
	private JButton jBPlay;
	private JButton jInfoProgram;
	private JButton jInfoGame;
	// label del fondo
	private JLabel jlFondo;

	public GUIInicio() {
		// boton de jugar
		setJBPlay("Jugar");
		add(jBPlay);
		// boton para info del juego
		setJInfoGame("Instrucciones");
		add(jInfoGame);
		// boton de informacion del programa
		setJInfoProgram("Reportes");
		add(jInfoProgram);
		// fondo
		setJLFondo();
		add(jlFondo);

		initializer();
	}

	public void initializer() {
		getContentPane().setBackground(Color.orange);// color
		jlFondo.setIcon(new ImageIcon(getClass().getResource("/images/snk.jpg")));
		setTitle("Batalla en Guacimo");
		setSize(800, 600);// x y
		setResizable(false);
		setVisible(true);
		setLayout(null);
		setLocationRelativeTo(null);// centrar la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// cerrar la ventana de memoria
	}

	// metodo para el fondo del inicio
	public void setJLFondo() {
		jlFondo = new JLabel();
		jlFondo.setBounds(0, 0, 800, 500);

	}

	public JLabel getJlFondo() {
		return jlFondo;
	}

	// boton de iniciar juego
	public void setJBPlay(String message) {
		jBPlay = new JButton();
		jBPlay.setText(message);
		jBPlay.setBounds(0, 0, 100, 30);
	}

	public JButton getJBPlay() {
		return jBPlay;
	}

	// boton de la info del programa
	public void setJInfoProgram(String message) {
		jInfoProgram = new JButton();
		jInfoProgram.setText(message);
		jInfoProgram.setBounds(690, 0, 100, 30);
	}

	public JButton getJInfoProgram() {
		return jInfoProgram;
	}

	// boton para info del juego
	public void setJInfoGame(String message) {
		jInfoGame = new JButton();
		jInfoGame.setText(message);
		jInfoGame.setBounds(0, 532, 130, 30);
	}

	public JButton getInfoGame() {
		return jInfoGame;
	}
}
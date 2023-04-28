package logics;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import data.FilestTxt;
import domain.City;
import presentation.GUICity;

public class Logic {
	private FilestTxt ft;
	private GUICity gc;
	private JLabel matCity[][];
	private String matrix[][];// variable de la matriz de string donde ocurre la magia
	private int click = 0;
	private int pocims = 0;

	public Logic() {
		ft = new FilestTxt();

	}

	// set matriz string
	public void setMatCity(JLabel matCity[][]) {
		int i = matCity.length;
		int j = matCity[0].length;
		this.matCity = matCity;
		matrix = new String[i][j];
	}

	// vector de los tamaños
	public int[] getSizeMat() {

		String posiTop[] = ft.readFile("Ciudad.txt").split(";");
		int vect[] = new int[posiTop.length];

		for (int i = 0; i < posiTop.length; i++) {
			posiTop[i] = posiTop[i].trim();
			if (!posiTop[i].equals("")) {
				String var[] = posiTop[i].split("=");

				vect[i] = Integer.parseInt(var[1]);// numeros del txt
			}
		}
		return vect;
	}

	// matriz de string
	public void stringMat(City ci) {
		int a, b;
		int i = 0;
		// colocar los edificios
		while (i < ci.getBuilds()) {// edificios
			a = (int) (Math.random() * ci.getSize());
			b = (int) (Math.random() * ci.getSize());
			if (matrix[a][b] == null) {
				matrix[a][b] = "e";
				i++;
			}
		}
		i = 0;
		// colocar los arboles
		while (i < ci.getTrees()) {
			a = (int) (Math.random() * ci.getSize());
			b = (int) (Math.random() * ci.getSize());
			if (matrix[a][b] == null) {
				matrix[a][b] = "a";
				i++;
			}
		}
		i = 0;
		// colocar los aliens
		while (i < ci.getAliens()) {// edificios
			a = (int) (Math.random() * ci.getSize());
			b = (int) (Math.random() * ci.getSize());
			if (matrix[a][b] == null) {
				matrix[a][b] = "al";
				i++;
			}
		}
		i = 0;
		// colocar los zombies
		while (i < ci.getZombs()) {// edificios
			a = (int) (Math.random() * ci.getSize());
			b = (int) (Math.random() * ci.getSize());
			if (matrix[a][b] == null) {
				matrix[a][b] = "z";
				i++;
			}
		}
		i = 0;
		// colocar los humanos
		while (i < ci.getHumans()) {// edificios
			a = (int) (Math.random() * ci.getSize());
			b = (int) (Math.random() * ci.getSize());
			if (matrix[a][b] == null) {
				matrix[a][b] = "h";
				i++;
			}
		}
		i = 0;// reinicio de la i
		updateMatrix();
	}

	// metodo del updatematrix para poner imagenes en las letras
	public void updateMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {

					if (matrix[i][j].equals("e")) {
						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/edificio.png")));// icono de
																										// edificio
					} else if (matrix[i][j].equals("a")) {
						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/iconoarbol2.png")));// icono de
																										// arbol
					} else if (matrix[i][j].equals("al")) {
						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/alien.png")));// icono de alien
					} else if (matrix[i][j].equals("z")) {
						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/iconozombi2.png")));// icono de
																										// zombie
					} else if (matrix[i][j].equals("h")) {
						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/person.png")));// icono de humano
					} else if (matrix[i][j].equals("p")) {

						matCity[i][j].setIcon(new ImageIcon(getClass().getResource("/images/pocima.png")));// icono de pocima;
					}
				} else {
					matCity[i][j].setIcon(null);
				}
			}
		}
	}

	// metodo para erradicar zombies
	public void killZombs() {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("z")) {
						matrix[i][j] = null;
					}
				}
			}
		}
		updateMatrix();
	}

	// metodo para erradicar aliens
	public void killAliens() {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("al")) {
						matrix[i][j] = null;
					}
				}
			}
		}
		updateMatrix();
	}

	// metodo para los movimientos y los acontecimientos
	public void makeMove(City ci) {// uso la matriz de string
		boolean t = true;
		int b;
		int vect[] = new int[4];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {

				t = true;// para no mover las pocimas los arboles ni edificios
				while (t && matrix[i][j] != null && !matrix[i][j].equals("p") && !matrix[i][j].equals("e")
						&& !matrix[i][j].equals("a")) {
					b = (int) (Math.random() * 4) + 1;// numero random
					// para que suba un espacio
					// zombies suben
					if (b == 1 && i > 0) {
						if (matrix[i][j].equals("z")) {
							if ((matrix[i - 1][j] != null && !matrix[i - 1][j].equals("e")
									&& !matrix[i - 1][j].equals("a") && !matrix[i - 1][j].equals("p"))
									|| matrix[i - 1][j] == null) {
								matrix[i - 1][j] = matrix[i][j];
								matrix[i][j] = null;// vaciar

							}
							if (matrix[i - 1][j].equals("h")) {// muere el humano
								matrix[i - 1][j] = "z";
								ft.writeFile("Acontecimientos.txt",
										"(" + (i - 1) + "," + j + ")" + "Ha muerto un humano");
								matrix[i][j] = null;

							} else if (matrix[i - 1][j].equals("p")) {// convertir zombie en humano
								matrix[i - 1][j] = "h";
								ft.writeFile("Acontecimientos.txt",
										"(" + (i - 1) + "," + j + ")" + "Zombie se convirtio en humano");
								matrix[i][j] = null;

							} /*
								 * else if(matrix[i-1][j].equals("p")){//continuan los zombies
								 * 
								 * }
								 */
						}
						// humanos suben
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("h")) {
								if ((matrix[i - 1][j] != null && !matrix[i - 1][j].equals("e")
										&& !matrix[i - 1][j].equals("a") && !matrix[i - 1][j].equals("p"))
										|| matrix[i - 1][j] == null) {
									matrix[i - 1][j] = matrix[i][j];
									matrix[i][j] = null;// vaciar

								}
								if (matrix[i - 1][j].equals("al")) {// muere el humano
									matrix[i - 1][j] = "al";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i - 1) + "," + j + ")" + "Muere el humano");
									matrix[i][j] = null;

								} else if (matrix[i - 1][j].equals("z")) {// muere humano
									matrix[i - 1][j] = "z";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i - 1) + "," + j + ")" + "Muere el humano");
									matrix[i][j] = null;

								}
							}
							// aliens suben
							if (matrix[i][j] != null) {
								if (matrix[i][j].equals("al")) {
									if ((matrix[i - 1][j] != null && !matrix[i - 1][j].equals("e")
											&& !matrix[i - 1][j].equals("a") && !matrix[i - 1][j].equals("p"))
											|| matrix[i - 1][j] == null) {
										matrix[i - 1][j] = matrix[i][j];
										matrix[i][j] = null;// vaciar

									}
									if (matrix[i - 1][j].equals("h")) {// muere el humano
										matrix[i - 1][j] = "al";
										ft.writeFile("Acontecimientos.txt",
												"(" + (i - 1) + "," + j + ")" + "Muere el humano");
										matrix[i][j] = null;

									} else if (matrix[i - 1][j].equals("p")) {// se convierte en humano
										matrix[i - 1][j] = "h";
										ft.writeFile("Acontecimientos.txt",
												"(" + (i - 1) + "," + j + ")" + "Alien se convirtio en humano");
										matrix[i][j] = null;

									} /*
										 * else if(matrix[i-1][j].equals("al")){//continuan los zombies
										 * 
										 * }
										 */
								}

							}
						}
					}

					t = false;
					/// para que vaya a la derecha
					// zombies a la derecha
					if (b == 2 && j < matrix[0].length - 2) {
						if (matrix[i][j].equals("z")) {
							if ((matrix[i][j + 1] != null && !matrix[i][j + 1].equals("e")
									&& !matrix[i][j + 1].equals("a") && !matrix[i][j + 1].equals("p"))
									|| matrix[i][j + 1] == null) {
								matrix[i][j + 1] = matrix[i][j];
								matrix[i][j] = null;// vaciar

							}
							if (matrix[i][j + 1].equals("h")) {// muere el humano
								matrix[i][j + 1] = "z";
								ft.writeFile("Acontecimientos.txt",
										"(" + i + "," + (j + 1) + ")" + "Ha muerto un humano");
								matrix[i][j] = null;

							} else if (matrix[i][j + 1].equals("p")) {// convertir zombie en humano
								matrix[i][j + 1] = "h";
								ft.writeFile("Acontecimientos.txt",
										"(" + i + "," + (j + 1) + ")" + "Zombie se convirtio en humano");
								matrix[i][j] = null;

							}
						}
						// humanos a la derecha
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("h")) {
								if ((matrix[i][j + 1] != null && !matrix[i][j + 1].equals("e")
										&& !matrix[i][j + 1].equals("a") && !matrix[i][j + 1].equals("p"))
										|| matrix[i][j + 1] == null) {
									matrix[i][j + 1] = matrix[i][j];
									matrix[i][j] = null;// vaciar

								}
								if (matrix[i][j + 1].equals("z")) {// muere el humano
									matrix[i][j + 1] = "z";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j + 1) + ")" + "Muere el humano");
									matrix[i][j] = null;

								} else if (matrix[i][j + 1].equals("al")) {// muere el humano
									matrix[i][j + 1] = "al";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j + 1) + ")" + "Muere el humano");
									matrix[i][j] = null;

								}
							}
						}
						// aliens a la derecha
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("al")) {
								if ((matrix[i][j + 1] != null && !matrix[i][j + 1].equals("e")
										&& !matrix[i][j + 1].equals("a") && !matrix[i][j + 1].equals("p"))
										|| matrix[i][j + 1] == null) {
									matrix[i][j + 1] = matrix[i][j];
									matrix[i][j] = null;// vaciar

								}
								if (matrix[i][j + 1].equals("h")) {// muere el humano
									matrix[i][j + 1] = "al";
									ft.writeFile("Acontecimientos.txt", "(" + i + "," + (j + 1) + ")" + "Muere humano");
									matrix[i][j] = null;

								} else if (matrix[i][j + 1].equals("p")) {// convertir alien en humano
									matrix[i][j + 1] = "h";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j + 1) + ")" + "Alien se convirtio en humano");
									matrix[i][j] = null;

								}
							}
						}
					}

					t = false;
					// para que bajen
					// zombies
					if (b == 3 && i < matrix.length - 2) {
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("z")) {
								if ((matrix[i + 1][j] != null && !matrix[i + 1][j].equals("e")
										&& !matrix[i + 1][j].equals("a") && !matrix[i + 1][j].equals("p"))
										|| matrix[i + 1][j] == null) {
									matrix[i + 1][j] = matrix[i][j];
									matrix[i][j] = null;

								}
								if (matrix[i + 1][j].equals("h")) {// muere el humano
									matrix[i + 1][j] = "z";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Ha muerto un humano");
									matrix[i][j] = null;

								} else if (matrix[i + 1][j].equals("p")) {// convertir zombie en humano
									matrix[i + 1][j] = "h";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Zombie se convirtio en humano");
									matrix[i][j] = null;

								} /*
									 * else if(matrix[i-1][j].equals("p")){//continuan los zombies
									 * 
									 * }
									 */
							}
						}
						// humanos bajan
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("h")) {
								if ((matrix[i + 1][j] != null && !matrix[i + 1][j].equals("e")
										&& !matrix[i + 1][j].equals("a") && !matrix[i + 1][j].equals("p"))
										|| matrix[i + 1][j] == null) {
									matrix[i + 1][j] = matrix[i][j];
									matrix[i][j] = null;

								}
								if (matrix[i + 1][j].equals("h")) {// muere humano
									matrix[i + 1][j] = "z";
									matrix[i][j] = null;
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Muere el humano");
								} else if (matrix[i + 1][j].equals("al")) {// muere humano
									matrix[i + 1][j] = "al";
									matrix[i][j] = null;
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Muere el humano");
								} // cuando se topen 2 humanos
							}
						}
						// aliens bajan
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("al")) {
								if ((matrix[i + 1][j] != null && !matrix[i + 1][j].equals("e")
										&& !matrix[i + 1][j].equals("a") && !matrix[i + 1][j].equals("p"))
										|| matrix[i + 1][j] == null) {
									matrix[i + 1][j] = matrix[i][j];
									matrix[i][j] = null;

								}
								if (matrix[i + 1][j].equals("h")) {// muere el humano
									matrix[i + 1][j] = "al";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Muere el humano");
									matrix[i][j] = null;

								} else if (matrix[i + 1][j].equals("p")) {// convertir alien en humano
									matrix[i + 1][j] = "h";
									ft.writeFile("Acontecimientos.txt",
											"(" + (i + 1) + "," + j + ")" + "Alien se convirtio en humano");
									matrix[i][j] = null;

								} /*
									 * else if(matrix[i-1][j].equals("al")){//continuan los zombies
									 * 
									 * }
									 */
							}
						}
					}
					t = false;
					// para que se mueva a la izquierda
					// zombie
					if (b == 4 && j > 0) {
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("z")) {
								if ((matrix[i][j - 1] != null && !matrix[i][j - 1].equals("e")
										&& !matrix[i][j - 1].equals("a") && !matrix[i][j - 1].equals("p"))
										|| matrix[i][j - 1] == null) {
									matrix[i][j - 1] = matrix[i][j];// para que se mueva a la izquierda
									matrix[i][j] = null;

								}
								if (matrix[i][j - 1].equals("h")) {// muere el humano
									matrix[i][j - 1] = "z";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Ha muerto un humano");
									matrix[i][j] = null;

								} else if (matrix[i][j - 1].equals("p")) {// convertir zombie en humano
									matrix[i][j - 1] = "h";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Zombie se convirtio en humano");
									matrix[i][j] = null;
								}
							}
						}
						// aliens
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("al")) {
								if ((matrix[i][j - 1] != null && !matrix[i][j - 1].equals("e")
										&& !matrix[i][j - 1].equals("a") && !matrix[i][j - 1].equals("p"))
										|| matrix[i][j - 1] == null) {
									matrix[i][j - 1] = matrix[i][j];// para que se mueva a la izquierda
									matrix[i][j] = null;

								}
								if (matrix[i][j - 1].equals("h")) {// muere el humano
									matrix[i][j - 1] = "al";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Muere el humano");
									matrix[i][j] = null;

								} else if (matrix[i][j - 1].equals("p")) {// se convierte en humano
									matrix[i][j - 1] = "h";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Alien se convirtio en humano");
									matrix[i][j] = null;
								}
							}
						}
						// humanos a la izquierda
						if (matrix[i][j] != null) {
							if (matrix[i][j].equals("h")) {
								if ((matrix[i][j - 1] != null && !matrix[i][j - 1].equals("e")
										&& !matrix[i][j - 1].equals("a") && !matrix[i][j - 1].equals("p"))
										|| matrix[i][j - 1] == null) {
									matrix[i][j - 1] = matrix[i][j];// para que se mueva a la izquierda
									matrix[i][j] = null;

								}
								if (matrix[i][j - 1].equals("z")) {// muere el humano
									matrix[i][j - 1] = "z";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Muere el humano");
									matrix[i][j] = null;
								} else if (matrix[i][j - 1].equals("al")) {// muere humano
									matrix[i][j - 1] = "al";
									ft.writeFile("Acontecimientos.txt",
											"(" + i + "," + (j - 1) + ")" + "Muere el humano");
									matrix[i][j] = null;
								}
							}
						}
					}

					t = false;
				}
			}
		}
		click++;
		getPocims(ci);
		updateMatrix();

	}

//metodo para pocimas
	public void getPocims(City ci) {

		// pocimas
		if (this.click == 5 && pocims < ci.getPocims()) {
			boolean t = true;
			while (t) {
				int a = (int) (Math.random() * ci.getSize());
				int b = (int) (Math.random() * ci.getSize());
				if (matrix[a][b] == null) {
					matrix[a][b] = "p";
					// reinicio
					this.click = 0;
					pocims++;
					t = false;
				}
			}

		}

	}

//metodo para terminar el juego respecto a zombies
	public boolean endGameZombs() {
		// contador para los zombies
		int zombs = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("z")) {
						zombs++;
					}
				}
			}
		}
		if (zombs == 0) {// si se queda en 0 es porque no encontro ningun zombie
			return true;
		}
		return false;
		// updateMatrix();
	}

	// metodo para terminar el juego respecto a aliens
	public boolean endGameAliens() {
		// contador para los aliens
		int aliens = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("al")) {
						aliens++;
					}
				}
			}
		}
		if (aliens == 0) {// si se queda en 0 es porque no encontro ningun aliens
			return true;
		}
		return false;
		// updateMatrix();
	}

	// metodo para verificar letras juntas
	public void verifyMix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("pz") || matrix[i][j].equals("pal")) {
						matrix[i][j] = "h";
					}
				}
			}
		}
	}

	// metodo para terminar el juego respecto a humanos
	public boolean endGameHumans() {
		// contador para los humanos
		int humans = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].equals("h")) {
						humans++;
					}
				}
			}
		}
		if (humans == 0) {// si se queda en 0 es porque no encontro ningun humano
			return true;
		}
		return false;

	}
}
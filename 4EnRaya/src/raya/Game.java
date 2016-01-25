package raya;

import java.util.Scanner;

public class Game {

	public static Scanner dato = new Scanner(System.in);

	public static void main(String[] args) {

		char tipoTablero;
		int tableroMedidas[] = new int[2];
		tableroMedidas[1] = 8;
		tableroMedidas[0] = 10;
		char tablero[][];
		char j1, j2;

		System.out.println("4 EN RAYA");
		System.out.println("*********");
		System.out.println("Quieres un tablero personalizado? (s/n)");

		tipoTablero = dato.next().charAt(0);
		while (tipoTablero != 's' && tipoTablero != 'n') {
			error();
			tipoTablero = dato.next().charAt(0);
		}
		if (tipoTablero == 's') {
			tableroMedidas[1] = crearTableroFilas();
			tableroMedidas[0] = crearTableroColumnas();
		}
		tablero = new char[tableroMedidas[0]][tableroMedidas[1]];
		tablero = llenarTablero(tablero);
		System.out.println("Jugador 1, escoge tu ficha: (x/o)");
		j1 = dato.next().charAt(0);
		while (j1 != 'x' && j1 != 'o') {
			error();
			j1 = dato.next().charAt(0);
		}
		if (j1 == 'x') {
			j1 = 'X';
			j2 = 'O';
			System.out.println("Jugador 2, tu ficha es: O");
		} else {
			j1 = 'O';
			j2 = 'X';
			System.out.println("Jugador 2, tu ficha es: X");
		}
		jugar(tablero, j1, j2);

	}

	public static void error() {
		System.out.println("Valor incorrecto, vuelve a introducir");
	}

	public static int crearTableroColumnas() {
		int a;
		System.out.println("Introduce numero de columnas [4,30]");
		a = dato.nextInt();
		while (a < 4 || a > 30) {
			error();
			a = dato.nextInt();
		}
		return (a);
	}

	public static int crearTableroFilas() {
		int a;
		System.out.println("Introduce numero de filas [4,20]");
		a = dato.nextInt();
		while (a < 4 || a > 20) {
			error();
			a = dato.nextInt();
		}
		return (a);
	}

	public static char[][] llenarTablero(char tablero[][]) {
		for (int y = 0; y < tablero[0].length; y++) {
			for (int x = 0; x < tablero.length; x++) {
				tablero[x][y] = ' ';
			}
		}
		return tablero;
	}

	public static void jugar(char tablero[][], char j1, char j2) {
		int jugador = 1;
		int x;
		int y;
		int tiradas[];
		tiradas = new int[tablero.length];
		for (int a = 0; a < tiradas.length; a++) {
			tiradas[a] = 0;
		}
		boolean ganado = false;
		while (!ganado) {
			System.out.println("Turno del jugador " + jugador + ".");
			mostrarTablero(tablero);
			System.out.println("Escoge la columna en la que dejar la ficha:");
			x = dato.nextInt();
			while (x <= 0 || x > tablero.length || tiradas[x - 1] == tablero[0].length) {
				error();
				x = dato.nextInt();
			}
			x--;
			tiradas[x]++;
			y = tirada(tablero, x, jugador, j1, j2);
			ganado = comprovarGanado(tablero, x, jugador, j1, j2, y);
			jugador = cambioJugador(jugador);
		}
		mostrarTablero(tablero);
		if (jugador == 1) {
			System.out.println("Ha ganado el jugador 2");
		} else {
			System.out.println("Ha ganado el jugador 1");
		}
		x = dato.nextInt();
	}

	public static void mostrarTablero(char tablero[][]) {
		for (int espacio = 1; espacio <= 3; espacio++) {
			System.out.print(" ");
		}
		for (int tapa = 1; tapa <= tablero.length; tapa++) {
			System.out.print(tapa);
			if (tapa < 10) {
				System.out.print(" ");
			}
		}
		System.out.println("");

		for (int y = 0; y < tablero[0].length; y++) {
			System.out.print(y + 1);
			System.out.print(" ");
			if (y < 10) {
				System.out.print(" ");
			}
			for (int x = 0; x < tablero.length; x++) {
				System.out.print(tablero[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}

		for (int fin = 1; fin <= tablero.length * 2 + 3; fin++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	public static int tirada(char tablero[][], int x, int jugador, char j1, char j2) {
		int cont = 0;
		while (cont < tablero[0].length && tablero[x][cont] == ' ') {
			cont++;
		}
		if (jugador == 1) {
			tablero[x][cont - 1] = j1;
		} else {
			tablero[x][cont - 1] = j2;
		}
		return cont - 1;
	}

	public static boolean comprovarGanado(char tablero[][], int x, int jugador, char j1, char j2, int y) {
		boolean ganado = false;
		char car;
		int contCar = 1;

		if (jugador == 1) {
			car = j1;
		} else {
			car = j2;
		}

		if (x + 1 < tablero.length && tablero[x + 1][y] == car) {
			contCar++;
			if (x + 2 < tablero.length && tablero[x + 2][y] == car) {
				contCar++;
				if (x + 3 < tablero.length && tablero[x + 3][y] == car) {
					contCar++;
				}
			}
		}

		if (x - 1 >= 0 && tablero[x - 1][y] == car) {
			contCar++;
			if (x - 2 >= 0 && tablero[x - 2][y] == car) {
				contCar++;
				if (x - 3 >= 0 && tablero[x - 3][y] == car) {
					contCar++;
				}
			}
		}
		if (contCar >= 4)

		{
			ganado = true;
			return ganado;
		}

		contCar = 1;

		if (y - 1 >= 0) {
			if (tablero[x][y - 1] == car) {
				contCar++;
				if (y - 2 >= 0 && tablero[x][y - 2] == car) {
					contCar++;
					if (y - 3 >= 0 && tablero[x][y - 3] == car) {
						contCar++;
					}
				}
			}
		}

		if (y + 1 < tablero[0].length) {
			if (tablero[x][y + 1] == car) {
				contCar++;
				if (y + 2 < tablero[0].length && tablero[x][y + 2] == car) {
					contCar++;
					if (y + 3 < tablero[0].length && tablero[x][y + 3] == car) {
						contCar++;
					}
				}
			}
		}

		if (contCar >= 4) {
			ganado = true;
			return ganado;
		}

		contCar = 1;

		if (y - 1 >= 0 && x - 1 >= 0) {
			if (tablero[x - 1][y - 1] == car) {
				contCar++;
				if (y - 2 >= 0 && x - 2 >= 0) {
					if (tablero[x - 2][y - 2] == car) {
						contCar++;
						if (y - 3 >= 0 && x - 3 >= 0) {
							if (tablero[x - 3][y - 3] == car) {
								contCar++;
							}
						}
					}
				}
			}
		}

		if (x + 1 < tablero.length && y + 1 < tablero[0].length) {
			if (tablero[x + 1][y + 1] == car) {
				contCar++;
				if (x + 2 < tablero.length && y + 2 < tablero[0].length && tablero[x + 2][y + 2] == car) {
					contCar++;
					if (x + 3 < tablero.length && y + 3 < tablero[0].length && tablero[x + 3][y + 3] == car) {
						contCar++;
					}
				}
			}
		}

		if (contCar >= 4) {
			ganado = true;
			return ganado;
		}

		contCar = 1;

		if (y - 1 >= 0 && x + 1 < tablero.length && tablero[x + 1][y - 1] == car) {
			contCar++;
			if (y - 2 >= 0 && x + 2 < tablero.length && tablero[x + 2][y - 2] == car) {
				contCar++;
				if (y - 3 >= 0 && x + 3 < tablero.length && tablero[x + 3][y - 3] == car) {
					contCar++;
				}
			}
		}

		if (x - 1 >= 0 && y + 1 < tablero[0].length && tablero[x - 1][y + 1] == car) {
			contCar++;
			if (x - 2 >= 0 && y + 2 < tablero[0].length && tablero[x - 2][y + 2] == car) {
				contCar++;
				if (x - 3 >= 0 && y + 3 < tablero[0].length && tablero[x - 3][y + 3] == car) {
					contCar++;
				}
			}
		}

		if (contCar >= 4)

		{
			ganado = true;
			return ganado;
		}

		contCar = 1;

		return (ganado);

	}

	public static int cambioJugador(int jugador) {
		if (jugador == 1) {
			jugador = 2;
		} else {
			jugador = 1;
		}
		return jugador;
	}
}

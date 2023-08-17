package fabio.gui.karte;

public class Position {

	private int x; // x-Koordinate der Position
	private int y; // y-Koordinate der Position

	/**
	 * Konstruktor, um eine neue Position mit gegebenen x- und y-Werten zu
	 * erstellen.
	 *
	 * @param x Die x-Koordinate der neuen Position.
	 * @param y Die y-Koordinate der neuen Position.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter-Methode für die x-Koordinate der Position.
	 *
	 * @return Die aktuelle x-Koordinate der Position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter-Methode für die y-Koordinate der Position.
	 *
	 * @return Die aktuelle y-Koordinate der Position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Aktualisiert die Position auf Basis gegebener x- und y-Werte.
	 *
	 * @param x Die neue x-Koordinate.
	 * @param y Die neue y-Koordinate.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Setter-Methode um die x-Koordinate der Position zu ändern.
	 *
	 * @param x Die neue x-Koordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	// Ein paar nützliche Methoden:

	/**
	 * Setter-Methode um die y-Koordinate der Position zu ändern.
	 *
	 * @param y Die neue y-Koordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gibt eine String-Darstellung der Position zurück.
	 *
	 * @return Ein String in der Form "x,y".
	 */
	@Override
	public String toString() {
		return x + "," + y;
	}

	// Weitere Methoden (wie Abstandsberechnungen, Überprüfung auf Gleichheit etc.)
	// könnten hier hinzugefügt werden.
}
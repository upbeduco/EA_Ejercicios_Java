package upb.ea.ea01_ADT;

/**
 * Definicion del API para el ADT Punto2D
 * 
 * @author jmlon
 *
 */
public interface Punto2D {
	
	/**
	 * 
	 * @return La coordenada X del punto
	 */
	double getX();

	/**
	 * 
	 * @return La coordenada Y del punto
	 */
	double getY();

	/**
	 * 
	 * @return La distancia euclideana entre 'this' y 'punto'
	 */
	double distancia(Punto2D punto);
	
}


// Ejercicios:
// ==========
// 1. Dar dos implementaciones de este ADT: Una version en coordenadas cartesianas y otra en polares
// 1b. Hacer que el método distancia opere con cualquier representacion del Punto2D
// 2. Implementar los métodos heredados de clase Object: equals, toString
// 3. Implementar pruebas unitarias de los métodos distancia, equals.




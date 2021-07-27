package upb.ea.ea01_ADT;

/**
 * Definicion del API para el ADT Contador
 *  
 * @author jmlon
 *
 */
public interface Contador {

	/**
	 * Incrementa el valor del contador
	 */
	void incrementar();
	
	/**
	 * Obtiene el valor del contador
	 */
	int getConteo();
	
	/**
	 * Obtiene el id del contador
	 */
	String getId();
	
}

// Ejercicios:
// ==========
// 1. Implementar el ADT a partir de esta especificación
// 2. Implementar pruebas unitarias del ADT utilizando `assert`
// 3. Implementar un cliente para registrar un conteo de votos 

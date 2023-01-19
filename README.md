# Ejemplos y Ejercicios para el Curso: Estructuras de Datos y Algoritmos

Ejercicios para el curso de estructuras de datos y algoritmos basados en el Texto Sedgewick.



## Referencias
Sedgewick, Wayne  
Algorithms, 4th ed.  
[https://algs4.cs.princeton.edu/home/](https://algs4.cs.princeton.edu/home/)  


## Compilación en consola
```
# A single file
javac -g -cp ./lib/algs4.jar -d ./build ./src/upb/ea/ea01_ADT/Contador.java

# All the files
javac -g -cp ./lib/algs4.jar -d ./build $(find ./src -name '*.java')
```

## Para configurarlo en Codespaces

1. Usar el botón `Open in a Codespace`
2. Abrir el programa `src/HelloWorld.java` e instalar las extensiones para Java.
3. Volver a `src/HelloWorld.java` y dar `Run Java`. Cuando pregunte si correr el motor del lenguaje en modo estandar, responder "Yes".
4. Cuando diga que "HelloWorld.java" no está en el classpath, aceptar la opción "Add to source path".
5. Si se demora mucho, dar "Cancel" y volver a intentar `Run Java`.
6. Para incluir las bibliotecas del texto guia, subir el archivo `algs4.jar` a la carpeta `lib` del proyecto.
7. Si no reconoce la biblioteca, ir a `Java Projects` y ejecutar `Configure Classpath` y en "Referenced Libraries" agregar `lib/alg4.jar`.





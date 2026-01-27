# Examples and exercises about Data Structures and Algorithms

This is a project about examples and exercises about data structures and algorithms in Java.

## General guidelines

- When implementing ADTs, always ensure proper encapsulation.
- ADTs should provide basic test of their methods in their own `main` method using `asserts`. Do not use other testing frameworks. Each ADT's `main` method should include at least 3-5 assert statements covering normal cases, edge cases (e.g., empty structures), and error conditions.
- To test expected exceptions, use the following pattern:

  ```java
  try {
      stack.pop(); // empty stack
      assert false : "Should have thrown exception";
  } catch (NoSuchElementException e) {
      // expected
  }
  ```

- Run all classes with the `-ea` JVM flag to enable assertions (already configured in `.vscode/launch.json`).
- Classes and methods should include javadoc documentation.
- ADTs should not accept `null` as a valid element; throw `IllegalArgumentException` if `null` is passed.

## Code Style and Conventions

- Follow standard Java naming conventions: classes in PascalCase (e.g., `ListaSimple`), methods and variables in camelCase (e.g., `pushElement`), constants in UPPER_SNAKE_CASE.
- Use consistent indentation (4 spaces, no tabs) and adhere to a 120-character line limit for readability.
- Organize imports alphabetically and avoid wildcard imports (`import java.util.*;`) to maintain clarity.
- Implement ADTs using generics where appropriate (e.g., `class ListaSimple<T>` for type safety), but keep implementations simple for educational purposes unless specified in exercises.
- **Language policy**: package and class names may use Spanish (e.g., `ListaSimple`, `Fecha`). Method names should use English (e.g., `addHead`, `removeHead`). Javadoc and comments may use Spanish.

## Project Structure and Dependencies

- Maintain the package structure under `upb.ea.*` (e.g., `upb.ea.ea01_ADT`) for all new classes, ensuring they align with the existing folder hierarchy in `src/`.
- Avoid external libraries unless explicitly required by an exercise; rely on standard Java APIs to keep the project self-contained and focused on core concepts.
- The Princeton `edu.princeton.cs.algs4.*` library (`lib/algs4.jar`) is available for I/O utilities (`StdOut`, `StdIn`, `StdDraw`), timing (`Stopwatch`), and reference algorithm implementations. Use it for I/O and utilities; prefer custom implementations over algs4 for ADTs and algorithms that are the subject of an exercise.
- To compile from the command line: `javac -g -cp ./lib/algs4.jar -d ./build ./src/upb/ea/<package>/<File>.java`

## Implementation Best Practices

- Prioritize efficiency and correctness in algorithms: document time/space complexity in Javadoc using the format `Time: O(n), Space: O(1)`, especially in modules like ea03_AnalisisDeAlgoritmos or ea04_MétodosDeOrdenación.
- Handle edge cases and exceptions gracefully (e.g., use `IllegalArgumentException` for invalid inputs in ADTs like stacks/queues), but keep error handling minimal for exercise simplicity.
- Value-type ADTs (e.g., `Fecha`, `Punto2D`) should be immutable: declare fields `final`, provide no setters, and return defensive copies from accessors when needed. Container ADTs (e.g., `Pila`, `Cola`, `ListaSimple`) are inherently mutable but should protect internal state from external modification.
- Always override `toString()` in ADTs for debugging and educational output.
- Override `equals()` and `hashCode()` in value-type ADTs (e.g., `Fecha`, `Punto2D`). Follow the standard `equals()` contract: reflexive, symmetric, transitive, consistent, and null-safe (return `false` for `null`).
- Implement `Comparable<T>` for ADTs with a natural ordering (e.g., `Fecha`). Provide separate `Comparator<T>` implementations for alternate orderings (e.g., sorting by different fields).
- All collection ADTs should implement `Iterable<T>` with a private inner iterator class.
- Use private inner classes for internal nodes (`Nodo`) in linked data structures.

## Documentation and Collaboration

- Enhance Javadoc: include `@param`, `@return`, and `@throws` tags for all public methods, plus brief examples in `@code` blocks. For classes, add a high-level description of the ADT's purpose and invariants.
- When proposing code changes, reference relevant exercises (e.g., from [ea02_EstructurasBasicas/EXERCISES.md](ea02_EstructurasBasicas/EXERCISES.md)) and explain algorithmic choices to aid learning.
- If generating new files or features, ensure they fit the project's educational scope—focus on fundamental data structures without over-engineering.

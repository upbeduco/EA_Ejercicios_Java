# Examples and exercises about Data Structures and Algorithms

This is a project about examples and exercises about data structures and algorithms in Java.

## General guidelines

- When implementing ADTs, always ensure proper encapsulation.
- ADTs should provide basic test of their methods in their own `main` method using `asserts`. Do not use other testing frameworks. Each ADT's `main` method should include at least 3-5 assert statements covering normal cases, edge cases (e.g., empty structures), and error conditions. 
- Classes and methods should include javadoc documentation.

## Code Style and Conventions

- Follow standard Java naming conventions: classes in PascalCase (e.g., `ListaSimple`), methods and variables in camelCase (e.g., `pushElement`), constants in UPPER_SNAKE_CASE.
- Use consistent indentation (4 spaces, no tabs) and adhere to a 120-character line limit for readability.
- Organize imports alphabetically and avoid wildcard imports (`import java.util.*;`) to maintain clarity.
- Implement ADTs using generics where appropriate (e.g., `class ListaSimple<T>` for type safety), but keep implementations simple for educational purposes unless specified in exercises.

## Project Structure and Dependencies

- Maintain the package structure under `upb.ea.*` (e.g., `upb.ea.ea01_ADT`) for all new classes, ensuring they align with the existing folder hierarchy in `src/`.
- Avoid external libraries unless explicitly required by an exercise; rely on standard Java APIs to keep the project self-contained and focused on core concepts.

## Implementation Best Practices

- Prioritize efficiency and correctness in algorithms: analyze time/space complexity informally in comments or Javadoc where relevant, especially in modules like ea03_AnalisisDeAlgoritmos or ea04_MétodosDeOrdenación.
- Handle edge cases and exceptions gracefully (e.g., use `IllegalArgumentException` for invalid inputs in ADTs like stacks/queues), but keep error handling minimal for exercise simplicity.
- Ensure ADTs are immutable where possible (e.g., for value objects like `Punto2D`), and provide defensive copies in constructors/mutators to prevent external modification.

## Documentation and Collaboration

- Enhance Javadoc: include `@param`, `@return`, and `@throws` tags for all public methods, plus brief examples in `@code` blocks. For classes, add a high-level description of the ADT's purpose and invariants.
- When proposing code changes, reference relevant exercises (e.g., from [ea02_EstructurasBasicas/EXERCISES.md](ea02_EstructurasBasicas/EXERCISES.md)) and explain algorithmic choices to aid learning.
- If generating new files or features, ensure they fit the project's educational scope—focus on fundamental data structures without over-engineering.

# Data Structures Algorithms and Examples

## General guidelines

- When implementing ADTs, always ensure encapsulation.
- ADTs should provide basic test of their methods in their own `main` method using `asserts`. Do not use other testing frameworks.
- Classes and methods should include javadoc documentation.

## Additional Guidelines for AI Assistants

#### Code Style and Conventions
- Follow standard Java naming conventions: classes in PascalCase (e.g., `ListaSimple`), methods and variables in camelCase (e.g., `pushElement`), constants in UPPER_SNAKE_CASE.
- Use consistent indentation (4 spaces, no tabs) and adhere to a 120-character line limit for readability.
- Organize imports alphabetically and avoid wildcard imports (`import java.util.*;`) to maintain clarity.
- Implement ADTs using generics where appropriate (e.g., `class ListaSimple<T>` for type safety), but keep implementations simple for educational purposes unless specified in exercises.

#### Project Structure and Dependencies
- Maintain the package structure under `upb.ea.*` (e.g., `upb.ea.ea01_ADT`) for all new classes, ensuring they align with the existing folder hierarchy in `src/`.
- Place compiled classes in the `bin/` directory after building. Use `javac` for compilation and ensure the classpath includes any dependencies from `lib/` if needed (refer to [lib/README.md](lib/README.md) for details).
- Avoid external libraries unless explicitly required by an exercise; rely on standard Java APIs to keep the project self-contained and focused on core concepts.

#### Implementation Best Practices
- Prioritize efficiency and correctness in algorithms: analyze time/space complexity informally in comments or Javadoc where relevant, especially in modules like ea03_AnalisisDeAlgoritmos or ea04_MétodosDeOrdenación.
- Handle edge cases and exceptions gracefully (e.g., use `IllegalArgumentException` for invalid inputs in ADTs like stacks/queues), but keep error handling minimal for exercise simplicity.
- Ensure ADTs are immutable where possible (e.g., for value objects like `Punto2D`), and provide defensive copies in constructors/mutators to prevent external modification.
- For graph-related ADTs (e.g., in ea07_Grafos), use adjacency lists or matrices as specified, and include basic traversal methods (DFS/BFS) with clear documentation.

#### Testing and Validation
- Expand on the existing testing guideline: each ADT's `main` method should include at least 3-5 assert statements covering normal cases, edge cases (e.g., empty structures), and error conditions. Run tests manually via `java` after compilation to verify.
- If an ADT involves randomness or performance (e.g., in ea03_AnalisisDeAlgoritmos), include simple benchmarks in `main` using `System.nanoTime()` for timing comparisons.
- After implementing or modifying code, compile and run tests to ensure no regressions; report any failures with details for debugging.

#### Documentation and Collaboration
- Enhance Javadoc: include `@param`, `@return`, and `@throws` tags for all public methods, plus brief examples in `@code` blocks. For classes, add a high-level description of the ADT's purpose and invariants.
- When proposing code changes, reference relevant exercises (e.g., from [ea02_EstructurasBasicas/EXERCISES.md](ea02_EstructurasBasicas/EXERCISES.md)) and explain algorithmic choices to aid learning.
- If generating new files or features, ensure they fit the project's educational scope—focus on fundamental data structures without over-engineering.

#### Workflow and Tools
- Use the terminal for compilation (`javac -d bin src/upb/ea/*/*.java`) and execution (`java -cp bin upb.ea.ea01_ADT.Contador`) to validate changes.
- If debugging is needed, leverage Java's built-in tools like `jdb` or IDE features, but keep solutions simple and console-based.
- For version control, commit changes with descriptive messages tied to specific exercises (e.g., "Implement bubble sort in ea04_MétodosDeOrdenación").

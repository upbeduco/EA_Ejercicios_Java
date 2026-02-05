# Abstract Data Types in Java: Implementation Patterns for Collections and Data Exchange

## What is an Abstract Data Type?

An **Abstract Data Type (ADT)** is defined by *what it does*, not *how it does it*. Think of it as a contract that specifies operations without revealing implementation details.

**Key principle:** Separate the interface (behavior) from the implementation (data structure).

## Core Implementation Strategies

### 1. Interface-First Design

**Best Practice:** Define behavior through interfaces, then provide concrete implementations.

```java
// The ADT contract
public interface Stack<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}

// A concrete implementation
public class ArrayStack<T> implements Stack<T> {
    private T[] elements;
    private int top;
    // implementation details...
}
```

**Why interfaces over abstract classes?**
- Allow multiple inheritance of behavior
- Easier to mock in tests
- More flexible for future extensions

### 2. Generics for Type Safety

Always use generics (`<T>`) when your ADT works with different data types:

```java
List<String> names = new ArrayList<>();  // ✓ Type-safe
List names = new ArrayList();             // ✗ Avoid raw types
```

### 3. Immutability When Possible

Immutable ADTs prevent side effects and are inherently thread-safe:

```java
// Java 16+ Record (immutable by default)
public record Point(int x, int y) {
    // Automatically provides: constructor, getters, equals, hashCode
}
```

## Implementing Collection ADTs

### Common Collection Interfaces

| ADT | Key Operations | Use Case |
|-----|---------------|----------|
| **List** | `add(E)`, `get(int)`, `remove(int)` | Ordered, allows duplicates |
| **Set** | `add(E)`, `contains(E)`, `remove(E)` | No duplicates, unordered |
| **Queue** | `offer(E)`, `poll()`, `peek()` | FIFO processing |
| **Stack** | `push(E)`, `pop()`, `peek()` | LIFO processing |
| **Map** | `put(K,V)`, `get(K)`, `containsKey(K)` | Key-value pairs |

### Programming to the Interface

```java
// ✓ Good: Flexible, can change implementation
List<String> items = new ArrayList<>();
items = new LinkedList<>();  // Easy to switch

// ✗ Avoid: Tied to specific implementation
ArrayList<String> items = new ArrayList<>();
```

### Custom ADT Example: Priority Queue

```java
public interface PriorityQueue<T extends Comparable<T>> {
    void enqueue(T item);
    T dequeue();  // Returns highest priority item
    T peek();
    boolean isEmpty();
}

public class HeapPriorityQueue<T extends Comparable<T>> 
    implements PriorityQueue<T> {
    private List<T> heap = new ArrayList<>();
    
    @Override
    public void enqueue(T item) {
        heap.add(item);
        bubbleUp(heap.size() - 1);
    }
    
    // ... other methods
}
```

## Iteration Patterns for Collections

Making your custom collections **iterable** is essential for integration with Java's enhanced for-loop, streams, and other iteration-based APIs.

### The Iteration Protocol

Java's iteration is built on two key interfaces:

| Interface | Purpose | Key Methods |
|-----------|---------|-------------|
| **Iterable\<T\>** | Data source that can produce an iterator | `iterator()` |
| **Iterator\<T\>** | Stateful cursor for traversing elements | `hasNext()`, `next()`, `remove()` |

**Critical Design:** The collection (Iterable) and the traversal state (Iterator) must be **separate objects** to allow multiple concurrent iterations.

### Basic Implementation Pattern

```java
public class SimpleList<E> implements Iterable<E> {
    private E[] elements;
    private int size;
    
    @Override
    public Iterator<E> iterator() {
        return new SimpleListIterator();
    }
    
    // Inner class maintains its own traversal state
    private class SimpleListIterator implements Iterator<E> {
        private int cursor = 0;  // Current position
        
        @Override
        public boolean hasNext() {
            return cursor < size;
        }
        
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[cursor++];
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
```

**Usage:**
```java
SimpleList<String> list = new SimpleList<>();
// ... add elements

// Enhanced for-loop (syntactic sugar for iterator)
for (String item : list) {
    System.out.println(item);
}

// Explicit iterator usage
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String item = iter.next();
    System.out.println(item);
}
```

### Iterator Contract Requirements

#### 1. hasNext() Must Be Idempotent

Calling `hasNext()` multiple times without calling `next()` must always return the same result:

```java
@Override
public boolean hasNext() {
    return cursor < size;  // ✓ No side effects
}

// ✗ WRONG: Don't modify state in hasNext()
@Override
public boolean hasNext() {
    cursor++;  // Violates idempotence!
    return cursor < size;
}
```

#### 2. next() Must Throw NoSuchElementException

Always perform defensive checking:

```java
@Override
public E next() {
    if (!hasNext()) {  // ✓ Defensive check
        throw new NoSuchElementException("No more elements");
    }
    return elements[cursor++];
}
```

#### 3. remove() Implementation (Optional)

If your collection supports removal during iteration:

```java
private class RemovableIterator implements Iterator<E> {
    private int cursor = 0;
    private int lastReturned = -1;  // Track last returned element
    private int expectedModCount = modCount;
    
    @Override
    public E next() {
        checkForComodification();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturned = cursor;
        return elements[cursor++];
    }
    
    @Override
    public void remove() {
        if (lastReturned < 0) {
            throw new IllegalStateException(
                "next() must be called before remove()"
            );
        }
        checkForComodification();
        
        SimpleList.this.remove(lastReturned);
        cursor = lastReturned;
        lastReturned = -1;
        expectedModCount = modCount;
    }
    
    private void checkForComodification() {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}
```

### Fail-Fast vs. Fail-Safe Iterators

#### Fail-Fast (Standard Collections)

Most Java collections use a **modification counter** to detect concurrent modifications:

```java
public class FailFastList<E> implements Iterable<E> {
    private E[] elements;
    private int size;
    private int modCount = 0;  // Tracks structural changes
    
    public void add(E element) {
        // ... add logic
        modCount++;  // Increment on structural modification
    }
    
    private class FailFastIterator implements Iterator<E> {
        private int expectedModCount = modCount;
        
        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            // ... return element
        }
    }
}
```

**Usage:**
```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

for (String item : list) {
    if (item.equals("B")) {
        list.remove(item);  // ✗ Throws ConcurrentModificationException
    }
}

// ✓ Safe: Use iterator's remove method
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String item = iter.next();
    if (item.equals("B")) {
        iter.remove();  // Safe removal
    }
}
```

#### Fail-Safe (Concurrent Collections)

Concurrent collections provide iterators that operate on a **snapshot** of the data:

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");
list.add("B");

for (String item : list) {
    list.add("C");  // ✓ No exception; iterator sees snapshot
}
```

### Advanced: Implementing ListIterator

For bidirectional traversal in List implementations:

```java
public class BidirectionalList<E> implements List<E> {
    
    @Override
    public ListIterator<E> listIterator(int index) {
        return new BidirectionalIterator(index);
    }
    
    private class BidirectionalIterator implements ListIterator<E> {
        private int cursor;  // Points between elements
        private int lastReturned = -1;
        
        BidirectionalIterator(int index) {
            this.cursor = index;
        }
        
        @Override
        public boolean hasNext() {
            return cursor < size;
        }
        
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            return elements[cursor++];
        }
        
        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }
        
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursor--;
            lastReturned = cursor;
            return elements[cursor];
        }
        
        @Override
        public int nextIndex() {
            return cursor;
        }
        
        @Override
        public int previousIndex() {
            return cursor - 1;
        }
        
        @Override
        public void set(E e) {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            elements[lastReturned] = e;
        }
        
        @Override
        public void add(E e) {
            BidirectionalList.this.add(cursor, e);
            cursor++;
            lastReturned = -1;
        }
    }
}
```

**Key Insight:** The cursor in a ListIterator sits **between elements**, not on them. This allows `next()` and `previous()` to return the same element when called alternately.

### Performance Considerations

#### Iterator vs. Index-Based Loops

```java
// ✓ Efficient for ArrayList (O(n))
for (int i = 0; i < list.size(); i++) {
    String item = list.get(i);
}

// ✗ TERRIBLE for LinkedList (O(n²))
for (int i = 0; i < linkedList.size(); i++) {
    String item = linkedList.get(i);  // Each get() is O(n)
}

// ✓ Efficient for ALL List implementations (O(n))
for (String item : linkedList) {
    // Uses iterator internally
}
```

#### Bulk Operations with forEachRemaining()

Java 8+ provides a bulk traversal method that can be optimized:

```java
private class OptimizedIterator implements Iterator<E> {
    private int cursor = 0;
    
    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        
        // Single modification check instead of per-element
        int expectedModCount = modCount;
        
        // Direct array access without iterator overhead
        while (cursor < size) {
            action.accept(elements[cursor++]);
        }
        
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}
```

### Stream Integration

Once your collection implements `Iterable<T>`, it automatically works with streams:

```java
SimpleList<Integer> numbers = new SimpleList<>();
// ... add numbers

// Stream API works seamlessly
int sum = StreamSupport.stream(numbers.spliterator(), false)
    .filter(n -> n % 2 == 0)
    .mapToInt(Integer::intValue)
    .sum();
```

**For optimal stream performance**, implement a custom Spliterator:

```java
@Override
public Spliterator<E> spliterator() {
    return new ArrayListSpliterator();
}

private class ArrayListSpliterator implements Spliterator<E> {
    private int index = 0;
    private int fence = size;
    
    @Override
    public boolean tryAdvance(Consumer<? super E> action) {
        if (index < fence) {
            action.accept(elements[index++]);
            return true;
        }
        return false;
    }
    
    @Override
    public Spliterator<E> trySplit() {
        int mid = (index + fence) >>> 1;
        if (mid <= index) {
            return null;  // Can't split further
        }
        int oldIndex = index;
        index = mid;
        return new PartialSpliterator(oldIndex, mid);
    }
    
    @Override
    public long estimateSize() {
        return fence - index;
    }
    
    @Override
    public int characteristics() {
        return Spliterator.ORDERED | Spliterator.SIZED | 
               Spliterator.SUBSIZED;
    }
}
```

### Iteration Best Practices Summary

1. ✓ **Always separate Iterable from Iterator** - Use inner classes for iterator implementations
2. ✓ **Make hasNext() idempotent** - No side effects or state changes
3. ✓ **Throw NoSuchElementException in next()** when exhausted
4. ✓ **Implement fail-fast detection** with modCount for non-concurrent collections
5. ✓ **Support remove() if the collection is mutable** - Track lastReturned index
6. ✓ **Override forEachRemaining()** for better performance
7. ✓ **Provide a custom Spliterator** for optimal stream and parallel processing
8. ✓ **Document thread-safety guarantees** - Specify if iterators are fail-fast or fail-safe

### Common Iterator Pitfalls

❌ **Modifying collection during iteration:**
```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String item : list) {
    list.remove(item);  // ConcurrentModificationException
}
```

❌ **Calling remove() before next():**
```java
Iterator<String> iter = list.iterator();
iter.remove();  // IllegalStateException
```

❌ **Calling remove() twice in a row:**
```java
Iterator<String> iter = list.iterator();
iter.next();
iter.remove();
iter.remove();  // IllegalStateException
```

❌ **Collection and Iterator sharing state:**
```java
// ✗ WRONG: Don't do this
public class BadList<E> implements Iterable<E>, Iterator<E> {
    private int cursor = 0;  // Shared state prevents multiple iterations
    
    @Override
    public Iterator<E> iterator() {
        return this;  // Returns itself!
    }
}
```

## Data Transfer Objects (DTOs)

DTOs are ADTs that carry data between application layers or systems.

### Modern Approach: Java Records

```java
// Traditional DTO (verbose)
public class UserDTO {
    private final String id;
    private final String username;
    private final LocalDateTime createdAt;
    
    public UserDTO(String id, String username, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
    }
    
    // + getters, equals, hashCode, toString
}

// Java 16+ Record (concise)
public record UserDTO(
    String id,
    String username,
    LocalDateTime createdAt
) { }
```

### Validation in DTOs

Use **Bean Validation (JSR 380)** annotations:

```java
import javax.validation.constraints.*;

public record CreateUserRequest(
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20)
    String username,
    
    @Email
    @NotNull
    String email,
    
    @Min(18)
    int age
) {
    // Compact constructor for custom validation
    public CreateUserRequest {
        if (username != null && username.contains(" ")) {
            throw new IllegalArgumentException(
                "Username cannot contain spaces"
            );
        }
    }
}
```

## ADTs in Distributed Systems

### REST APIs

DTOs serve as the **contract** between client and server:

```java
// Response DTO
public record UserResponse(
    String id,
    String username,
    String email,
    LocalDateTime createdAt
) { }

// Spring Controller
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        User user = userService.findById(id);
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCreatedAt()
        );
    }
}
```

### Backend for Frontend (BFF) Pattern

BFF aggregates data from multiple services into UI-specific DTOs:

```java
// Mobile-optimized DTO (minimal data)
public record MobileUserProfile(
    String username,
    String avatarUrl,
    int notificationCount
) { }

// Web-optimized DTO (richer data)
public record WebUserProfile(
    String username,
    String email,
    String fullName,
    String avatarUrl,
    List<String> recentActivity,
    Map<String, Object> preferences
) { }
```

### Message Queues

DTOs as **event messages** should be immutable:

```java
public record OrderPlacedEvent(
    String orderId,
    String customerId,
    BigDecimal totalAmount,
    Instant timestamp,
    String correlationId  // For idempotency
) implements Event {
    
    // Ensure immutability with defensive copying
    public OrderPlacedEvent {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException(
                "Order ID cannot be null"
            );
        }
    }
}
```

## Best Practices Summary

### For Collection ADTs:
1. ✓ Define behavior through interfaces
2. ✓ Use generics for type safety
3. ✓ Program to the interface, not the implementation
4. ✓ Implement Iterable\<T\> for traversal support
5. ✓ Separate collection state from iterator state
6. ✓ Consider thread-safety requirements
7. ✓ Document time complexity of operations

### For Iterators:
1. ✓ Make hasNext() idempotent
2. ✓ Throw NoSuchElementException from next() when exhausted
3. ✓ Implement fail-fast detection for non-concurrent collections
4. ✓ Support remove() only if collection is mutable
5. ✓ Override forEachRemaining() for performance
6. ✓ Provide custom Spliterator for stream optimization

### For DTOs:
1. ✓ Use Records (Java 16+) for immutability
2. ✓ Add validation at boundaries (Bean Validation)
3. ✓ Keep DTOs anemic (no business logic)
4. ✓ Version your DTOs for API evolution
5. ✓ Use builder pattern for complex DTOs with many optional fields

### For Distributed Systems:
1. ✓ DTOs are contracts—document them thoroughly
2. ✓ Make message DTOs immutable and self-validating
3. ✓ Include correlation IDs for tracing
4. ✓ Use schema evolution strategies (Avro, Protobuf)
5. ✓ Separate internal domain models from external DTOs

## Common Pitfalls to Avoid

❌ **Exposing internal implementation:**
```java
// Don't do this
public ArrayList<String> getItems() { 
    return items; 
}

// Do this instead
public List<String> getItems() {
    return Collections.unmodifiableList(items);
}
```

❌ **Mutable DTOs in distributed systems:**
```java
// Risky
public class UserDTO {
    public String username;  // Mutable field
}

// Better
public record UserDTO(String username) { }
```

❌ **Logic in DTOs:**
```java
// DTOs should be dumb data carriers
public record OrderDTO(String id, BigDecimal amount) {
    public BigDecimal calculateTax() {  // ✗ Belongs in domain layer
        return amount.multiply(new BigDecimal("0.08"));
    }
}
```

## Practical Exercises

### Exercise 1: Iterable Stack
Implement a `Stack<T>` ADT that:
1. Supports `push()`, `pop()`, `peek()` operations
2. Implements `Iterable<T>` for top-to-bottom traversal
3. Has fail-fast iterator semantics
4. Includes comprehensive JavaDoc

### Exercise 2: Bidirectional Queue
Implement a `Deque<T>` ADT (double-ended queue) that:
1. Supports adding/removing from both ends
2. Has O(1) operations for all basic operations
3. Provides a `ListIterator<T>` for bidirectional traversal
4. Uses generics properly

### Exercise 3: Task Queue System
Create a DTO for a task queue system that:
1. Can serialize/deserialize tasks between microservices
2. Includes validation for required fields
3. Supports priority levels and deadlines
4. Is immutable and thread-safe

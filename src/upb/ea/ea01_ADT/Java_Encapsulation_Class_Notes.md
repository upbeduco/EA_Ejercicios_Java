# Encapsulation in Java: Best Practices for Data Structures and Algorithms

---

## Table of Contents
1. [What is Encapsulation?](#what-is-encapsulation)
2. [Core Principles](#core-principles)
3. [Access Modifiers in Java](#access-modifiers-in-java)
4. [Practical Patterns](#practical-patterns)
5. [Common Pitfalls](#common-pitfalls)
6. [Examples in Collections](#examples-in-collections)

---

## What is Encapsulation?

Encapsulation is a fundamental principle of object-oriented programming that bundles data (state) and methods (behavior) into a single unit and hides internal details from the outside world.

### The Three Goals of Encapsulation

1. **Information Hiding**: Keep implementation details private
2. **Invariant Protection**: Maintain object validity at all times
3. **Flexibility**: Allow internal changes without affecting external code

### Why This Matters in DSA

When you implement a data structure like a Stack, Queue, or LinkedList, encapsulation ensures:
- Users can't accidentally corrupt the structure by directly accessing internal fields
- You can optimize internal representation without breaking client code
- The contract (what the data structure does) is clear and stable

---

## Core Principles

### 1. Minimize Visibility (Information Hiding)

**Rule**: Use the narrowest access level that works.

```
private → package-private → protected → public
```

#### ❌ DON'T: Expose everything as public

```java
public class Stack {
    public ArrayList<Integer> elements = new ArrayList<>();  // BAD!
    public int top = -1;  // BAD!
}
```

**Problem**: Clients can do this:
```java
Stack s = new Stack();
s.elements.clear();  // Destroys the stack without calling pop()!
s.top = 100;         // Stack invariant broken!
```

#### ✅ DO: Keep fields private

```java
public class Stack<T> {
    private ArrayList<T> elements;  // GOOD
    private int top;                 // GOOD
    
    // Provide controlled public methods
    public void push(T value) { /* ... */ }
    public T pop() { /* ... */ }
    public boolean isEmpty() { /* ... */ }
}
```

---

### 2. Maintain Class Invariants

An **invariant** is a condition that must always be true for your object.

#### Example: Stack Invariant

For a Stack, the invariant might be: `0 ≤ top < elements.size()`

Every public method must preserve this invariant.

```java
public class Stack<T> {
    private ArrayList<T> elements;
    private int top = -1;
    
    // Constructor establishes the invariant
    public Stack() {
        elements = new ArrayList<>();
        // Invariant: top = -1, elements is empty
    }
    
    // push() maintains the invariant
    public void push(T value) {
        elements.add(value);
        top++;
        // Invariant still holds: 0 ≤ top < elements.size()
    }
    
    // pop() maintains the invariant
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();  // Fail fast
        }
        T value = elements.get(top);
        elements.remove(top);
        top--;
        return value;
        // Invariant still holds
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
}
```

**Key insight**: If you catch an error *inside* the class, the invariant is never violated. Errors are thrown *before* state changes occur.

---

### 3. Prefer Immutability When Feasible

Immutable objects are inherently encapsulated because their state cannot change.

#### Example: Immutable Coordinate Class

```java
public final class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    // No setters! The object cannot be modified.
}
```

**Benefits**:
- Thread-safe by design
- Easier to reason about
- Can be safely shared
- Invariants are enforced once at construction

---

## Access Modifiers in Java

Java provides four levels of visibility. Understand each clearly.

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| **public** | ✓ | ✓ | ✓ | ✓ |
| **protected** | ✓ | ✓ | ✓ | ✗ |
| *package-private* (no modifier) | ✓ | ✓ | ✗ | ✗ |
| **private** | ✓ | ✗ | ✗ | ✗ |

### When to Use Each

#### `private` — Default Choice for Fields

```java
public class LinkedListNode<T> {
    private T data;           // Only this class accesses
    private LinkedListNode<T> next;  // Only this class accesses
    
    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
    }
}
```

#### `public` — Only for API Contracts

```java
public class Stack<T> {
    // Public API that clients depend on
    public void push(T value) { /* ... */ }
    public T pop() { /* ... */ }
    public boolean isEmpty() { /* ... */ }
}
```

#### `protected` — For Inheritance (Use Cautiously)

```java
public abstract class BinarySearchTree<T extends Comparable<T>> {
    protected Node root;  // Subclasses can access, but not external code
    
    protected class Node {
        protected T value;
        protected Node left, right;
    }
    
    protected abstract void insertHelper(Node current, T value);
}
```

**Warning**: Protected fields weaken encapsulation. Document what subclasses can expect.

#### Package-Private — For Internal Packages

```java
// In package com.example.datastructures
class BalancingHelper {  // Package-private: only other classes in the package
    static void rebalance(Node root) { /* ... */ }
}

public class BalancedBST {  // Public API
    public void insert(int value) {
        // Can call package-private helper
        BalancingHelper.rebalance(root);
    }
}
```

---

## Practical Patterns

### Pattern 1: Avoid "Anemic" Getters and Setters

An **anemic object** is one where all you can do is get and set raw data. The logic lives elsewhere.

#### ❌ DON'T: Expose raw state

```java
public class Account {
    private double balance;
    
    public void setBalance(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }
}

// Client code needs to contain the logic:
Account acc = new Account();
acc.setBalance(100);
double newBalance = acc.getBalance() - 50;  // Logic outside the object!
acc.setBalance(newBalance);
```

#### ✅ DO: Hide implementation, expose behavior

```java
public class Account {
    private double balance;
    
    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = initialBalance;
    }
    
    // Behavior-driven methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;  // or throw exception
        }
        balance -= amount;
        return true;
    }
    
    public double getBalance() {  // Query, not state exposure
        return balance;
    }
}

// Client code is simpler and safer:
Account acc = new Account(100);
acc.deposit(50);
if (acc.withdraw(30)) {
    System.out.println("Success: " + acc.getBalance());
}
```

---

### Pattern 2: Validate Inputs at the Boundary

Fail fast: catch errors as early as possible, before they corrupt state.

```java
public class BinaryHeap<T extends Comparable<T>> {
    private List<T> data;
    
    public BinaryHeap() {
        this.data = new ArrayList<>();
    }
    
    // Validate at the boundary
    public void insert(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot insert null");
        }
        data.add(value);
        bubbleUp(data.size() - 1);
    }
    
    public T extractMin() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        bubbleDown(0);
        return min;
    }
    
    // Internal helper: assume preconditions are met
    private void bubbleUp(int index) {
        // No validation needed here; caller already validated
    }
}
```

---

### Pattern 3: Control Mutability of Exposed Collections

Never return a direct reference to a mutable internal collection.

#### ❌ DON'T: Return the internal list directly

```java
public class Graph {
    private List<Integer> adjacencyList;  // Internal mutable list
    
    public List<Integer> getNeighbors(int node) {
        return adjacencyList;  // BAD! Caller can modify!
    }
}

// Caller corrupts the graph:
List<Integer> neighbors = graph.getNeighbors(0);
neighbors.clear();  // Oops, we just deleted all neighbors!
```

#### ✅ DO: Return immutable view or copy

```java
public class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    
    // Option 1: Return immutable view
    public List<Integer> getNeighbors(int node) {
        return Collections.unmodifiableList(
            adjacencyList.getOrDefault(node, new ArrayList<>())
        );
    }
    
    // Option 2: Return a copy
    public List<Integer> getNeighborsCopy(int node) {
        return new ArrayList<>(
            adjacencyList.getOrDefault(node, new ArrayList<>())
        );
    }
}

// Now the caller can't corrupt the internal state:
List<Integer> neighbors = graph.getNeighbors(0);
neighbors.clear();  // Throws UnsupportedOperationException
```

---

### Pattern 4: Use Constructors to Enforce Invariants

Object construction is a critical moment: establish the invariant once.

```java
public class Rectangle {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        // Validate inputs BEFORE modifying state
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Width and height must be positive"
            );
        }
        
        // Now we can safely assign
        this.width = width;
        this.height = height;
        // Invariant: width > 0 && height > 0
    }
    
    public double area() {
        return width * height;
    }
    
    // Don't provide setWidth/setHeight unless the domain requires it.
    // If you do, apply the same validation:
    public void setWidth(double newWidth) {
        if (newWidth <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = newWidth;
    }
}
```

---

## Common Pitfalls

### Pitfall 1: Getters That Expose Internal Structure

```java
// ❌ BAD
public Node getRoot() {
    return root;  // Caller can modify Node's fields directly
}

// ✅ GOOD
public T getRootValue() {
    return root != null ? root.value : null;
}

public boolean hasLeft() {
    return root != null && root.left != null;
}
```

---

### Pitfall 2: Mutable Default Arguments

```java
// ❌ BAD
private static final List<Integer> DEFAULT_LIST = new ArrayList<>();

public MyClass(List<Integer> items) {
    this.items = items;  // Caller's list is stored directly
}

// Caller modifies their list, and the object sees the change:
List<Integer> myList = new ArrayList<>(Arrays.asList(1, 2, 3));
MyClass obj = new MyClass(myList);
myList.add(4);  // obj.items now has 4 elements!

// ✅ GOOD
public MyClass(List<Integer> items) {
    this.items = new ArrayList<>(items);  // Defensive copy
}
```

---

### Pitfall 3: Breaking Invariants in Subclasses

When using inheritance, subclasses can accidentally break encapsulation.

```java
// ❌ RISKY
public class Counter {
    protected int count = 0;  // Subclasses can modify directly
    
    public void increment() {
        count++;
    }
}

public class BadCounter extends Counter {
    public void badIncrement() {
        count += 2;  // Oops, now count increments by 2 unexpectedly
    }
}

// ✅ BETTER: Use private fields and protected methods
public class Counter {
    private int count = 0;
    
    public void increment() {
        count++;
    }
    
    protected int getCount() {
        return count;
    }
}
```

---

## Examples in Collections

### Example 1: Stack Implementation

```java
/**
 * A generic stack implementation with proper encapsulation.
 * Invariant: top >= -1 and top < elements.size()
 */
public class Stack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int top;
    
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = new T[DEFAULT_CAPACITY];
        top = -1;
    }
    
    // Public API
    public void push(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot push null");
        }
        if (top == elements.length - 1) {
            resize();
        }
        elements[++top] = value;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = elements[top];
        elements[top] = null;  // Help garbage collection
        top--;
        return value;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    public int size() {
        return top + 1;
    }
    
    // Private helper
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newElements = new T[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }
}
```

**Encapsulation benefits**:
- Users can't access the internal array or top counter
- Invalid operations (like calling pop on empty stack) fail safely
- Internal resizing logic is hidden; implementation can change

---

### Example 2: LinkedList Node Encapsulation

```java
public class LinkedList<T> {
    // Private inner class: not part of public API
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    public LinkedList() {
        head = null;
        size = 0;
    }
    
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null");
        }
        
        if (head == null) {
            head = new Node(value);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(value);
        }
        size++;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public int size() {
        return size;
    }
    
    // Don't expose head directly!
    // Node is private, so clients can't access nodes
}
```

---

## Summary: The Encapsulation Checklist

Before submitting your DSA assignment, verify:

- [ ] **All fields are private** (or protected only for inheritance)
- [ ] **Public API is minimal** (only what clients need)
- [ ] **Constructor validates inputs** and establishes invariants
- [ ] **Every public method preserves invariants**
- [ ] **No direct mutable collection returns** (use defensive copies or immutable views)
- [ ] **Internal helper methods are private**
- [ ] **Errors are caught early** (fail fast)
- [ ] **No "getter/setter" pairs without purpose**
- [ ] **Documentation explains invariants and contracts**

---

## Key Takeaways

1. **Encapsulation is about trust**: Other programmers (and future you) should not need to understand your internal implementation.

2. **The goal is invariants**: Every public method must leave the object in a valid state.

3. **Use access modifiers intentionally**: `private` for implementation, `public` for contracts.

4. **Immutability when possible**: Immutable objects are the simplest to encapsulate.

5. **Fail fast**: Validate inputs at boundaries and throw exceptions early.

6. **Think about invariants**: What must always be true about your object? Protect it.

---

## Practice Problems

1. Design an `IntQueue` class with proper encapsulation. What is the invariant?
2. Implement a `DynamicArray<T>` that doubles its capacity when full. How does encapsulation help hide the resizing logic?
3. Create a `BinarySearchTree<T>` that prevents null values. How would a getter method on the root node break encapsulation?

---

**Remember**: Great encapsulation means great code. Invest the time to get it right from the start.

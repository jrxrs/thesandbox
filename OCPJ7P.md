# Java Class Design #

## Foundations of Object Orientates Programming ##

  * _Abstraction_: Hiding lower-level details and exposing only the essential and relevant details to the users.
  * _Encapsulation_: Combining data and the functions operating on it as a single unit. Encapsulation also allows you to control access to infomration, validate data, or modify data format without the risk of breaking another class.
  * _Inheritance_: Creating hierarchical relationships between related classes.
  * _Polymorphism_: Interpreting the same message (i.e. method call) with different meanings depending on the context.

## Class Foundations ##

  * A "_class_" is a template (or blueprint) and an "_object_" is an instance of a class.
  * A _constructor_ does not have a return type.
  * You cannot access the _private_ methods of the base class in a derived class.
  * You can access the _protected_ methods either from a class in the same package (just like package private or default) as well as from a derived class.
  * You can also access a method with a _default access modifier_ if it is in the same package.
  * You can access _public_ methods of a class from any other class.

| / | any class | classes in the same package and subclasses | classes in the same package only | same class |
| - |:---------:|:------------------------------------------:|:--------------------------------:|:----------:|
| public    | y | y | y | y |
| protected | x | y | y | y |
| default   | x | x | y | y |
| private   | x | x | x | y |

Note: When implementing a method from an interface, the concrete class may only widen the access modifier, i.e. a `public` method in the interface must also be a `public` method in the concrete class.

## Overloading ##

  * _Method overloading_: Creating methods with the same name but different types and/or numbers of parameters.
  * You can have _overloaded constructors_. You can call a constructor of the same class in another constructor using the `this` keyword.
  * _overload resolution_ is the process by which the compiler looks to resolve a call when overloaded definitions of a method are available.

## Inheritance ##

  * _Inheritance_ is also called an "_is-a_" relationship.
  * Resolving a method call based on the dynamic type of the object is referred to as _runtime polymorphism_.
  * In _overriding_, the name of the method, number of arguments, types of arguments, and return type should match exactly.
  * In _covariant return types_, you can provide the derived class of the return type in the overriding method.
  * You use the `super` keyword to calls base class methods.
  * Overloading is an example of _static polymorphism_ (_early binding_) while overriding is an example of _dynamic polymorphism_ (_late binding_).
  * You don't need to do an explicit cast for doing an _upcast_. An upcast will always succeed.
  * You **do** need to do an explicit cast for doing a _downcast_. A downcast may fail. So you cn use the `instanceof` operator to see if a downcast is valid.
  * When overriding a method the child class may widen the access modifier, i.e. a `protected` method is the super class may become a `public` method in the child class.

## Java Packages ##

  * A _package_ is a scoping construct to categorize your classes and to provide namespace management.

# Advanced Class Design #

## Abstract Classes ##

  * An abstraction specifying functionality supported without disclosing finer level details.
  * You cannot create instances of an abstract class.
  * Abstract classes enable runtime polymorphism, and runtime polymorphism in turn enable loose coupling.

## Using the `final` Keyword ##

  * A final class in a non-inheritable class (i.e. you cannot inherit from a final class).
  * A final method is a non-overridable method (i.e. subclasses cannot override a final method).
  * All methods of a final class are implicitly final (i.e. non-overridable).
  * A final variable can be assigned only once.

## Using the `static` Keyword ##

  * There are two types of member variables: class variables and instance variables. All variables that require an instance (object) of the class to access them are known as _instance variables_. All variables that are shared among all instances and are associated with a class rather than an object are referred to as _class variables_ (declared using the `static` keyword.
  * All static members do not required an instance to call/access them. you can directly call/access them using the class name.
  * A static member can call/access only a static member.

## Flavours of Nested Classes ##

  * Java supports fours types of nested classes: static nested classes, inner classes, local inner classes and anonymous inner classes.
  * Static nested classes may have static members, whereas the other flavours of nested classes can't.
  * Static nested classes can inner classes can access members of an outer class (even private members). However, static nested classes can access only static members of the outer class.
  * Local classes (both local inner classes and anonymous inner classes) can access all variables declared in the outer scope (whether a method, constructor or a statement block).

## Enums ##

  * Enums are a typesafe way to achieve restricted input from users.
  * You cannot use `new` with enums, even inside the enum definition.
  * Enum classes are by default final classes.
  * Enum values are implicitly `public`, `static` and `final`.
  * All enum classes are implicitly derived from `java.lang.Enum`.

## Immutability ##

Immutable objects present read-only data. (You cannot modify it after object construction.)
  * Instance variables must be encapsulated (`private`) to prevent direct access.
  * Instance variables are initialized immediately or via constructors.
  * No setter methods are provided.
  * Immutable objects are thread-safe, without an overhead cost of coordinating synchronized access.
  * Many JDK classes are designed this way: primitive wrapper, local date and time, string etc.

## Initializers ##

You might already be familiar with a static initalizer that is executed only once when the class if first loaded, e.g.

```
public class Product {
    private static int maxId;
    
    static {
        maxId = 100;
    }
}
```

But did you know that you can use instance initalizers that are invoked everytime an object of that class is constructed:

```
public class Product {

    private static int maxId;
    private final int id;

    static {
        maxId = 0;
    }

    {
        id = ++maxId;
    }
    
}
```

There are three rules for the instance initializer block. They are as follows:
1. The instance initializer block is created when instance of the class is created.
2. The instance initializer block is invoked after the parent class constructor is invoked (**i.e. after super() constructor call**).
3. The instance initializer block comes in the order in which they appear.

**Example**
Consider the following code snippets:
```
public class Object {
    static { }
    public Object() { }
}
```
```
public class Product {
    static  { }
    { }
    public Product() { }
}
```
```
public class Food extends Product {
    static { }
    { }
    public Food() { }
}
```
```
public class Shop {
    static { }
    public static void main (String[] args) {
        Product p1 = new Food();
        Product p2 = new Food();
    }
}
```

Class loading and initalization execute order (the following is executed only once):
1. Object class static initializer
2. Show class static initializer
3. Product class static initializer
4. Foob class static initializer

Object instantiation execution order (the following code is executed for each object that is initalised):
1. Object class constructor
2. Product instance initializer
3. Product constructor
4. Food instance initializer
5. Food constructor

## Java Memory Allocation ##

Java has the following memory contexts: stack and heap.
  * Stack is a memory context of a thread, storing local method variables.
  * Heap is a shared memory area, accessible from different method variables.
  * Stack can hold only primitives and object references.
  * Classes and Objects are stored in the heap.

The stack and the heap are key to how parameter passing works in Java, basically passing parameters means copying stack values:
  * A copy of an object reference value
  * A copy of a primitive value

# Object-Orientated Design Principles #

## Interfaces ##

  * An interface is a set of abstract methods that defines a protocol.
  * An interface cannot be instantiated; however, an interface can extend another interface.
  * All methods declared in an interface are implicitly considered to be abstract.
  * Abstract classes and interfaces are quite similar concepts. However, you should be careful to use the appropriate construct based on the context.
  * When implementing multiple interfaces or extending a parent class:
    * Private interface method do not cause conflicts, because they are not visivle outside of that interface.
    * Static interface methods do not cause conflicts, because they are invoked cia specific parent types and do not rely on the super reference.
    * If there is a conflict between `default` methods, it must be resolved by overriding this default method within the implementation class.
    * Otherwise, the default method implementation can be inherited.
    * If multiple implemented interfaces declare the same method name and return type then the implementing class must override it as normal.
  * An interface may also define `static` methods, these don't cause any conflicts when implementing multiple interfaces even if static methods have the same name because they can simply be reference via the class name.

### `default` methods ###

Default methods enable you to add new functionality to existing interfaces and ensure binary compatibility with code written for older versions of those interfaces. In particular, default methods enable you to add methods that accept lambda expressions as parameters to existing interfaces.

One of their key advantages is that you can add methods without having to provide an implementation in every implementing class.

#### Extending Interfaces That Contain Default Methods ####

When you extend an interface that contains a default method, you can do the following:
  * Not mention the default method at all, which lets your extended interface inherit the default method.
  * Redeclare the default method, which makes it abstract.
  * Redefine the default method, which overrides it.

## Object Composition ##

  * Inheritance implies is-a, interface implies is-like-a, and composition implies has-a relationships.
  * Favour composition over inheritance whenever feasible.
  * Program to an interface, not to an implementation.

## Functional Interfaces ##

A Functional Interface is an interface that defines a single abstract operation (function/method). Functional interfaces are a reccommended approach now-a-days because they prents the implementing class from having to implement methods that they may not otherwise be interested in, thus they provide us more flexibility. 

## Cloneable ##

`Cloneable` is an example of a "type-marker" or "tag-interface" as it doesn't have any abstract methods that must be implemented, instead it simply marks a class as something which can be cloned using the `.clone()` method overridden from `Object`, note that if you don't implement `Cloneable` then the `Object.clone()` method (which you'll reach by calling `super.clone()`) will throw a `CloneNotSupportedException`.

## Design Patterns ##

  * Design patterns are reusable solutions of frequently recurring design problems.
  * The observer design pattern improves loose coupling between subject and observers.
  * The singleton design pattern ensures that only one instance of the class is created.
  * Making sure that an intended singleton implementation is indeed single is a non-trivial task, especially in a multi-threaded environment.
  * The factory design pattern "manufactures" the required type of product on demand.
  * You should consider using the abstract factory design pattern when you have a family of objects to be created.
  * A DAO design pattern essentially separates your core business logic from your persistence logic.
  * The Visitor pattern works on the basis of double displatch, each class accepts a Vistor and then invokes a method on that Vistor passing itself as a parameter to the method it calls.
  * The composition pattern can be used to solve the multiple inheritance problem in Java:
    * Interfaces describe capabilities.
    * Classes implement these capabilities.
    * Capabilities are aggregated.
    * A (wrapper) class that needs to provide a wide spectrum of features, e.g. a Bank that must support `Depositing`, `Withdrawing` and `Athentication` can simply compose objects that implement those features and then delegate behaviour to them, e.g. an `Account` might implement `Depositing` & `Withdrawing`, where as a Secutiry class might provide the `Athentication`.

## Generics ##
### Generic and Raw Type Compatibility ###
When applying generics to override methods:
  * Compiler verifies type-saffetly of your code before erasing generics
  * Add a synthetic (compiler generated) bridge method
  * Bridge method compiles with non-generics signature of the method that your code is overriding
  * Bridge method invokes your non-systhetic method, applying type-casting to the generic type
  * No type-casting need to be applied to the code that invokes such an operation

**Original Code**
```
public class Some implements UnaryOperator<String> {
    public String apply(String s) {
        return s;
    }
}
```
**Compiles To**
```
public class Some implements UnaryOperator<String> {
    public String apply(final String s) {
        return s;
    }
}

/* bridge synthetic method generated by the compiler */
public Object apply(final Object o) {
    return this.apply((String)o);
}
```

### Generics and the Type Hierarchy ###
Generics are invariant to enforce compile-time verifications of types.
  * Java arrys are covariant (which means that can be replaced by a "narrower" type, so an array od sub-type is considered to be assignable to an array declared as a super-tpye, see [here](https://dzone.com/articles/covariance-and-contravariance) for more detail), which can result in runtime exceptions:
```
Product[] products = new Food[10];
products[0] = new Drink("Tea"); // throws a java.lang.ArrayStoreException
```
  * Collection API uses generics that are invariant, so code is validated at compile time:
```
List<Product> products = new ArrayList<Food>(); // Compiler error indicating incompatible type: ArrayList<Food> cannot be converted to List<Product>
```
  * Generics compiler checks are not performed for raw types, which can result in runtime exceptions:
```
List<Food> foods = new ArrayList<Food>();
List values = foods;
List<Product> products = values; // Compiler warns about unchecked or unsafe operations
products.add(new Drink("Tea"));  // No compiler warning
Drink x1 = (Drink)values.get(0);
Food x2 = foods.get(0);          // java.lang.ClasCastException: class demos.Drink cannot be cast to class demos.Food
```

### Wildcard Generics ###
Generics are used to enforce compile-time verification of a type.
Generics are often used with Collection API. COnsider the following collection examples:
  * When **generics are not applied**, code defaults to use type Object:
    * Only Object class operation can be safely used.
    * Type-check and type-casting must be applied to access any sub-type specific operations.
  * When **specific type is applied**:
    * Any operation declared for this type or its parents can be safely used
    * Type-check and type-casting must be applied to access any sub-type specific operations
  * When **whildcard <?> is applied** (representing unknown type):
    * Elements are accessed just like in a collection of Objects
    * Effectively it's a read-only collection
    * No object in Java is of unknown type, so no values (except null) can be added to such a collection
```
// Add, remove and manipulate instances of Object class or its descendents:
List listOfAnyObjects1 = new ArrayList();
List<Object> listOfAnyObjects2 = new ArrayList<>();
// Add, remove and manipulate instances of Product class or its descendents:
List<Product> listOfProducts = new ArrayList<>();
// Nothing could be added to this list, except null:
List<?> listOfUnknownType = listOfProducts;
```

### Upper Bound Wildcard ###
Upper bounded wildcard `<? extends ParentType>` allows use of subtype collections.
  * A list of specific type `List<Product>`
    * Is writable - you can add instances of `Product`, `Food` and `Drink` to such a list
    * Is invariant - you **cannot** assign a `List<Drink>` or `List<Food>` to such a list
  * A list of super type and descendents `List<? extends Product>`
    * Is read only - no values (except null) can be added to such a list
    * Is covariant - you **can** assign a `List<Drink>` or `List<Food>` to such a list
```
public void setProducts(List<Product>) {}
public void setProductAndSubtypes(List<? extends Product> products) {}
```

### Lower Bound Wildcard ###
Lower bounded wildcard `<? super Type>` allows to use this type and its parents.
  * A list of specific tpye `List<Food>`
    * Is invariant - you can only assign a `List<Food>` to such a list
  * A list of type and its parents `List<? super Food>`
    * Is writable - you can add instances of `Object`, `Product` and `Food` to such a list
    * Is contra-variant - you can assign a `List<Food>` or `List<Product>` or `List<Object>` to such a list
```
public void addFoodToFoods(List<Food> order, Food food) {
    order.add(food);
}
public void addFoodToFoodParents(List<? super Food> order, Food food) {
    order.add(food);
}
```
The interesting this here is while a list of products may contain food and drink, the methods above will prevent drink being added.

### Collections and Generics Best Practices ###
Collections and generics wildcard best practices:
  * When class hierarchy (super/sub types) is irrelevant
    * Use specific type `<SpecificType>` invariant, read-write generics
    * This allows type-safe read-write access to the collection
  * When collection is a consumer (from the perspective of the collection, writable) of values and your code needs to be type-hierarchy aware
    * Use `<? super LowerBoundType>` contravarient, writable generics
    * This allows type-safe addition of new values to the collection
  * When collection is a producer (read-only) of values and your code needs to be type-hierarchy aware
    * User `<? extends UpperBoundType>` covarient, read-only generics
    * This allows type-safe retrieval of values from the collection
  * Avoid raw types
```
public void addFood(List<? super Food>) { o.add(i); } // contravariant, writable
public void addDrink(List<? super Drink>) { o.add(i); } // contravariant, writable
public void processOrder(List<? extends Product> o) { o.stream().forEach(p -> p.prepare()); }
public void addProductAndProcessOrder(List<Product> o, Product i) {
    o.add(i);
    o.stream().forEach(p -> p.prepare());
}
```

-----

# OJCP11 #

## Date & Time ##
Classes covered in the course were `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`, `Duration` and `Period`.

## Strings ##
`StringBuilder` is a `StringBuffer` for which you don't have to pay the price of synchronization, `StringBuffer` has always been a part of the Java API but `StringBuilder` was only introduced in Java 1.5 (5).

## Streams ##
A `Stream` can only be operated on once, for example the following code will through an `IllegalStateException` because the stream has already been operated upon or closed:

```java
Stream<Integer> numStream = Stream.of(10, 20, 30);
numStream.map(n -> n + 10).peek(s -> System.out.print(s));
numStream.forEach(s -> System.out.println(s));
```

Stream processing can be sequential (default) or parallel. The Stream pipeline is traversed using method chaining so intermediate operations also return streams. **The chain of activities could be fused into a single pass on data**, meaning that operations don't happen piecemeal.

To avoid autoboxing and unboxing of primativies the Streams API provides specifc implementations: `DoubleStream`, `IntStream` and `LongStream`, using these prevents you from having to copy values back and forth between the stack (where primitives are stored) and the heap (where objects are stored) which can be slow and degrade performance.

There are 3 main types of pipeline processing operations:
  * Intermediate - perform some action and produce another stream, e.g. `filter`, `map`, `flatMap`, `peek`, `distinct`, `sorted`, `dropWhile`, `skip`, `limit*`, `takeWhile*`
  * Terminal - terverse a stream pipeline and end the stream processing, e.g. `forEach`, `forEachOrdered`, `count`, `min`, `max`, `sum`, `average`, `collect`, `reduce`, `allMatch*`, `anyMatch*`, `noneMatch*`, `findAny*`, `findFirst*`
  * Short-circuit - produce a finite result, even if presented with an infinite input (short-circuit operators are marked with a `*` above)

Note that `peek` and `forEach` are actually very similar, the only difference is that `peek` is an intermediate and `forEach` is terminal.

There are five basic functions that can be used with Streams, they are all come from the `java.util.function` package and can be implemented using lambda expressions:
  * `Predicate` performs tests
  * `Function` converts types
  * `UnaryOperator` (a variant of Function) converts values
  * `Consumer` processes elements
  * `Supplier` produces elements

You might stitch these together as follows:
```
Stream.generate(<Supplier>)
      .filter(<Predicate>)
      .peek(<Consumer>)
      .map(<Function>/<UnaryOperator>)
      .forEach(<Consumer>);
```

Note that all of the types functions listed above also have pimitive specific versions as well.

### Collectors ###
The `Collectors` class provides two methods of subdividng a stream of elements, into partitions or groups:
  * Partitioning divides content into a map with two key values (boolean true/false) using a `Predicate`.
  * Grouping fivides content into a map of multiple key values using a `Function`.

```
Map<Boolean, List<Product>> productTypes = list.stream()
                                               .collect(Collectors.partitioningBy(p -> p instanceof Drink));
Map<LocalDate, List<Product>> productGroups = list.stream()
                                                  .collect(Collections.groupingBy(p -> p.getBestBefore()));
```

Groups and Partitions can also be mapped and filtered in a multilevel reduction, using the downstream groupsing and partitioning:
  * `flatMapping` collector is applied is applied to each input element in the stream before accumulation.
  * `filtering` collector eliminates content from the stream without removing an entire group, even if the group turns out empty.

### Parallel Stream Procesing ###
Parallel stream processing should observe the following guidelines:
  * Stateless - the state of one element must not affect another element
  * Non-interfering - the data source must not be affected
  * Associative - the result must not be affected by the order of the operands

Other rules of thumb to use which help avoid memory corruption and slow processing:
  * Do not perform operations that require sequential access to a shared resource, e.g. printing something to the console
  * Do not perform operations that modify shared resources, e.g. accessing any synchronised object
  * Use appropriate collectors, such as:
    * `toMap` in sequential mode
    * `toConcurrentMap` in parallel mode
Generally speaking parallel streams should only be used in the following cases:
  * Stream contains large number of elements
  * Multiple CPU cores are available to physically parallelise conputations
  * Processing of stream elements requires significant CPU resources

**Exmaples**
Consider we have an `Order` object consisting of a `Customer`, `LocalDate` and `List<Product>`, the following orders are in the stream:
  * Joe, 2018-11-21, [Tea, Cake]
  * Bob, 2018-11-21, [Coffee]
  * Joe, 2018-11-22, [Coffee, Cake]

```
/* Find all the unique products ordered by a customer */
Map<Customer, Set<Product>> customerProducts = orders.collect(Collectors.groupingBy(o -> o.getCustomer(),
                                                              Collectors.flatMapping(o -> o.getItems().streams(), Collections.toSet())));
/* Result: {Joe=[Tea, Coffee, Cake], Bob=[Coffee]} */

/* Find all the orders on a specific date organised by customer */
Map<Customer, Set<Order>> customerOrdersOnDate = orders.collect(Collectors.groupingBy(o -> o.getCustomer(),
                                                                Collectors.filtering(o -> o.getDate().equals(LocalDate.of(2018, 11, 22)), Collectors.toSet())));
/* Result: {Joe=[Order[date=2018-11-22, customer Joe, products=[Coffee, Cake]]], Bob=[]} */
```

## Collections ##
In Java 9 a number of convenience method were introduced to allow small collections to be initialised via static methods on the collection class, e.g. `List.of(...)`, the caveat with these methods is that they return unmodifiable/immutable collections.

### Collections and Concurrency ###
A collection can be corrupted if accessed concurrently from multiple threads.
  * If two or more threads within your program try to access a collection at the same time, they can corrupt it, if this collection is not immutable.
  * Any object in a heap is not thread-safe if it is not immutable. Any thread can be inturrupted, even when it is modifying an object, making other threads observe incomplete modifiation state.
  * Making a collection thread-safe does not guarantee the thread safety of the objects it contains. Only immutable objects are automatically thread-safe.

There are three main strategies to prevent your collection being corrupted, make it:
  * Unmodifiable (fast, but read only): `Collections.unmodifiableSet(set)`
  * Synchronized (slow and unscalable): `Collections.synchronizedSet(set)`
  * Copy-on-write (fast, but consumes memory): `new CopyOnWriteArryList(list)`
    * The [`CopyOnWriteArrayList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html) Java Docs are very clear and concise about it's intended use, i.e. you should read from the collection more than your write to it, but it's not very clear on the writing behaviour, what actually happens behind the scenes if two threads attempt to write to a `CopyOnWriteArrayList` at the same time is that both threads create a copy of the original list to perform their update operations on, once they complete, the contents of the list will be merged in the background, after the merge all reading threads will see the combined contents with all modifications made by both threads.

## .equals() and .hashCode() ##
The `hashCode` method must consistenly return the same `int` value for the same instance (i.e. don't include any mutable variables in your hashCode logic), it must also return the same `int` value for any pair of objects that are considered to be the same when compared with the `equals` method.

## Loops ##

A `for` loop is slightly odd in Java because it uses the semicolon as a position separator rather than a statement separator, so instead of using `;` to separate statements we use `,` instead, e.g. in the example below name the comma between the `i++` and `j--`.

```
int [][] values = {{1,2,3},{4,5,6},{7,8,9}};
for (int i = 0, j = 2; ! (i == 3 || j == -1; i++, j--)) {
    int value = matrix[i][j];
}
```

## Local Variable Type Inference (`var`) ##

As the name states `var` can only be used for loval variables inside methods, `var` is simply a means of matking the code more readable by inferring the type of the local variable from the initialisation, `var` essentially makes the variable name and constructor call stand out more to someone reading the code.

https://openjdk.java.net/projects/amber/LVTIFAQ.html#:~:text=In%20Java%2C%20var%20can%20be,the%20type%20were%20declared%20explicitly.

## Threading and Concurrency ##

### Implementing Executor Service Tasks ###
`ExecutorService` supports two types of task inplementations:
  * Runnable objects
    * Implementing `public void run();` method of `Runnable` interface
    * Launched using the `execute` or `submit` methods of the `ExecutorService`
  * Callable objects
    * Implemtning the `public <T> call()` throws Exception; method of `Callable` interface
    * Launched using the `submit` method of the `ExecutorService`
    * Returned value is wrapped into a `Future` ojbect, which is returned immediately
    * Method `get` blocks invoking thread until time out or when the value within the `Future` object becomes availble

### Locking Problems ###
#### Starvation ####
The thread is waiting for a resource blocked by another busy thread.
```
synchronized (a) {
    /* actions taking a very long time */
}
```

#### Livelock ####
Threads form an indefitite loop, expecting confirmation of completion from each other.
```
while (b.isOver()) {
    // do A actions
}
aOver = true;

while (a.over()) {
    // do B actions
}
bOver = true;
```

#### Deadlock ####
Two or more threads are blocked forever, waiting for each other.
```
synchronized (a) {
     synchronized (b) { }
}

synchronized (b) {
     synchronized (a) { }
}
```

### Writing Thread-Safe Code ###
Stack values such as local variables and method argumetns are thread-safe.
  * Each thread operates ith its own stack.
  * No other thread can see this portions of memory.
Immutable objects in a shared heap memory are thread-safe because they cannot be changed at all.
Mutable objects in a shraed heap memory are thread-undafe.
  * Heap memory is shared between all threads.
  * Heap values undergoing modifications may be:
    * Inconsistent - observered by other threads before modification is complete
    * Corrupted - partially changed by another thread writing to memory at the same time
  * The comiler may choose to **cache heap value locally** within a thread, causing a thread to not notice that data has been changed by another thread.

### Ensure Consistent Access to Shared Data ###
Disable compiler otimization that is caching the shared value locally within a thread.
The `volatile` keywork instructs the Java compiler:
  * Not to cache the variable values locally
  * Always read it from the main memory (heap)
  * Applies all changes to the main memoty that occurred in a thread before the update of the volatile variable
For example:
```
public class Shared {
    public int x;
    public volatile int y;
}

Shared s = new Shared();
new Thread(() -> {
        while (s.y < 1) {
            int x = s.x;
        }
    }).start();
new Thread(() -> {
        s.x = 2;
        s.y = 2;
    }).start();
```
The `while` loop in the example could become indefinite without the `volatile` instruction if the compiler chooses to cache variable `y` locally.
Even with the `volatile` keyword, it is not really possible to predict how many iteractions this while loop is going to perform, because there is no way to tell the order in which these threads would get CPU time to execute their instrucitons.
**Note**: Using volatile will inevitable have some kind of performance impact on your application because it can no longer cache anything on the threads stack and will need to read and write to the heap every time that variable is accessed.

### Non-Blocking Atomic Actions ###
Action is atomic if it is guaranteed to be perform by a thread without an interruption.
  * Atomic actions cannot be interleaved.
  * Only action performed by a CPU in a single cycle are by default atomic.
  * Variable assignments are atomic actions, expect `long` and `double` as these are 64-bit values, and it takes more than a single step to assign these **on a 32-bit platform**.
  * Other operations such as `+ - / * % ++ --` etc are not atmic.
  * Package `java.util.concurrent.atomic` provides classes that implement lock-free thread-safe programming of atomic behaviours on single variables e.g.
    * `AtomicBoolean`
    * `AtomicInteger`
    * `AtomicLong`
    * `AtomicReference<V>`
  * Atomic variables also behave as if they are volatile.

### Ensure Exclusive Object Access Using Intrinsic Locks ###
Use intrinsic lock to enforce an exclusive access to a shared object.
  * Order of execution and object consistency are ensured.
  * Synchronized logic creates a bottleneck in a multithreaded application.
  * Performance and scalability can be significantly degraded.
```
List<String> list = new ArrayList<>();
Runnable r = () -> {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 10; i++) {
        synchronized(list) {
            list.add(name + ' ' + i);
        }
    }
};
for (int i = 0; i < 10; i++) {
    new Thread(r).start();
}
```

### Intrinsic Lock Automation ###
Some Java APIs provide synchronized versions of objects, for example:
  * Class `Collections` provides synchronized wrapper for `Collection`, `List`, `Set` and `Map` objects.
  * Operations such as add and remove are already synchronized to ensure consistent access to the collection content.
  * However, note that iterating through a synchronized collection would still require a explicity synchronized block.
```
List<String> list = new ArrayList<>();
List<String> sList = Collections.synchronizedList(list);
Runnable r = () -> {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 10; i++) {
        sList.add(name + ' ' + i);
    }
};
/* start threads and wait for their completions (using .join()) */
synchronized (sList) {
    Iterator i = sList.iterator();
    while (i.hasNext()) {
        System.out.println(i.next());
    }
}
```
 This implies that the synchronisation occurs on the `sList` iself.
 
 ### Non-Blocking Concurrency Automation ###
   * Package `java.util.concurrent` provides classes to manage concurrency.
   * For example, classes such as `CopyOnWriteArraylist` or `CopyOnWriteArraySet` provide thread-safe variants of `List` and `Set`.
     * All mutative operations (add, remove, and so on) make fresh copies of the underlying collection.
     * The read-only snapshot of merge content is used for traversal.
     * These types of construct are best suited to **small collections**, where **read-only operations vastly outnumber mutative operations** and prevent interference among threads during traversal.
     * Each thread that mutates the collection acquires its own local copy which is then merged back to the master copy automatically.
     * There is no need to synchronise during iteration.
```
List<String> list = new ArrayList<>();
List<String> copyOnWriteList = new CopyOnWriteArrayList(list);
Runnable r = () -> {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 10; i++) {
        copyOnWriteList.add(name + ' ' + i);
    }
};
/* start threads and wait for their completions (using .join()) */
Iterator i = copyOnWriteList.iterator();
while (i.hasNext()) {
    System.out.println(i.next());
}
```

### Alternative Locking Mechanisims ###
Locking API provides flexible programtic concurrency control mechanisms.
  * Allows actions to be performed on an object, without interference from other threads
  * Available from the `java.util.concurrent.locks` package
  * Write lock prevents other threads from concurrently modifiying the object
  * Read lock can be acquired if Write lock is not held by another thread, allowing concurrent read actions
```
public class PriceList {
    private List<Product> menu = new ArrayList<>();
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock rl = rwl.readLock();
    private Lock wl = rwl.writeList();
    
    public Product get(int id) {
        rl.lock();
        try {
            return menu.stream.findFirst(p -> p.getId() == id);
        } finally {
            rl.unlock();
        }
    }
    
    public void add (Product product) {
        wl.lock();
        try {
            return menu.add(product);
        } finally {
            wl.unlock();
        }
    }
}
```

## Open Modules ##
### Non-Modular Java Characteristics ###
There were no modules in Java before version 9.
  * Packages provided logical grouping of classes.
  * Packages did not impose physical restrictions on how they are used.
  * Classes are packaged into jar files and accessed via classpath.
  * Common deployment of related classes is not enforced.
  * Visibility of classes is controlled with public and default access modifiers.
  * Encapsulation can always be bypassed using reflections.
  * Impossible to restrict in which exact other packages your code can be used.

### What is a Module? ###
Module is a high-level code aggregation.
  * It comprises one or more closely related packages and other resources such as images or xml files.
  * Module descriptor `module-info.class` stored in the module's root folder contains:
    * Unique module name (reccommended reverse-dns convention e.g. `com.youcompany.whatever`)
    * Required module dependencies (other modules that this module depends on)
    * Packages that this module exports, making them available to other modules (all other packages contained within the module are unavailble to other modules)
    * Permissions to open content of this module to other modules using relection
    * Services this module offers to other modules
    * Services this module consumes
    * Modules do not allow splitting java packages even when they are not exported (private)
```
module <this module name> {
    requires <other module names>;
    exports <packages of this module to other modules that require them>;
    opens <packages of this module to other modules via reflection>;
    uses <services provides by other modules>;
    provides <services to other modules> with <service implementations>;
    version <value>;
}
```

# Links #

  * https://blogs.oracle.com/certification/test-your-java-knowledge-with-free-sample-questions
  * https://javarevisited.blogspot.com/2019/07/top-4-java-11-certification-free-mock-exams-practice-tests-ocajp11-ocpjp11-1z0-815-16-questions.html
  * http://java.boot.by/ocpjd11-upgrade-quiz/
  * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/package-summary.html#MemoryVisibility - this text details happens-before
  * http://tutorials.jenkov.com/java-concurrency/index.html
  * https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
  * https://www.marcobehler.com/guides/a-guide-to-java-versions-and-features

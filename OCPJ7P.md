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

## Generics & Collections ##

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

## Collections ##
In Java 9 a number of convenience method were introduced to allow small collections to be initialised via static methods on the collection class, e.g. `List.of(...)`, the caveat with these methods is that they return unmodifiable/immutable collections.

## .equals() and .hashCode() ##
The `hashCode` method must consistenly return the same `int` value for the same instance (i.e. don't include any mutable variables in your hashCode logic), it must also return the same `int` value for any pair of objects that are considered to be the same when compared with the `equals` method.

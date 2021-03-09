

# Java Class Design #

## Foundations of Object Orientates Programming ##

  * _Abstraction_: Hiding lower-level details and exposing only the essential and relevant details to the users.
  * _Encapsulation_: Combining data and the functions operating on it as a single unit.
  * _Inheritance_: Creating hierarchical relationships between related classes.
  * _Polymorphism_: Interpreting the same message (i.e. method call) with different meanings depending on the context.

## Class Foundations ##

  * A "_class_" is a template (or blueprint) and an "_object_" is an instance of a class.
  * A _constructor_ does not have a return type.
  * You cannot access the _private_ methods of the base class in a derived class.
  * You can access the _protected_ methods either from a class in the same package (just like package private or default) as well as from a derived class.
  * You can also access a method with a _default access modifier_ if it is in the same package.
  * You can access _public_ methods of a class from any other class.

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
  * All enum classes are implicitly derived from `java.lang.Enum`.

# Object-Orientated Design Principles #

## Interfaces ##

  * An interface is a set of abstract methods that defines a protocol.
  * An interface cannot be instantiated; however, an interface can extend another interface.
  * All methods declared in an interface are implicitly considered to be abstract.
  * Abstract classes and interfaces are quite similar concepts. However, you should be careful to use the appropriate construct based on the context.

## Object Composition ##

  * Inheritance implies is-a, interface implies is-like-a, and composition implies has-a relationships.
  * Favour composition over inheritance whenever feasible.
  * Program to an interface, not to an implementation.

## Design Patterns ##

  * Design patterns are reusable solutions of frequently recurring design problems.
  * The observer design pattern improves loose coupling between subject and observers.
  * The singleton design pattern ensures that only one instance of the class is created.
  * Making sure that an intended singleton implementation is indeed single is a non-trivial task, especially in a multi-threaded environment.
  * The factory design pattern "manufactures" the required type of product on demand.
  * You should consider using the abstract factory design pattern when you have a family of objects to be created.
  * A DAO design pattern essentially separates your core business logic from your persistence logic.

## Generics & Collections ##

-----

# OJCP11 #

## Date & Time ##
Classes covered in the course were `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`, `Duration` and `Period`.

## Strings ##
`StringBuilder` is a `StringBuffer` for which you don't have to pay the price of synchronization, `StringBuffer` has always been a part of the Java API but `StringBuilder` was only introduced in Java 1.5 (5).

## Streams ##
A ``Stream` can only be operated on once, for example the following code will through an `IllegalStateException` because the stream has already been operated upon or closed:

```java
Stream<Integer> numStream = Stream.of(10, 20, 30);
numStream.map(n -> n + 10).peek(s -> System.out.print(s));
numStream.forEach(s -> System.out.println(s));
```

## Collections ##
In Java 9 a number of convenience method were introduced to allow small collections to be initialised via static methods on the collection class, e.g. `List.of(...)`, the caveat with these methods is that they return unmodifiable/immutable collections.

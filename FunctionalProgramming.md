

# Basic Principles #

## Avoiding Mutable State ##

Make your objects immutable. Declare fields final. Only provide getters
for fields and then only when necessary. Be careful that mutable final
objects can still be modified. Use mutable collections carefully. See Item 15 of Effective Java (2<sup>nd</sup> Edition) for more practical tips.

## Functions as First-Class Values ##

In Java objects and primitives are considered first-class values, functions are not, to really get to the bottom of this we have to understand to difference between a method and a function:

> A method is a block of code attached to a particular class. It can only be called in the context of the class, if it's defined to be static, or in the context of an instance of the class. A function is more general. It is not attached to any particular class or object. Therefore, all instance methods are functions where one of the arguments is the object.

Using the definition above and the fact that Java only has methods, we can see that Java methods are not first-class because you can't pass a method as an argument to another method, return a method from a method, or assign a method as a value to a variable.

## Lambdas & Closures ##

## Higher-Order Functions ##

## Side-Effect-Free Functions ##

## Recursion ##

### Tail Recursion ###

If a function calls itself as its last action, the function's stack frame can be reused. This is called _tail recursion_. Tail recursive functions are therefore iterative processes.

## Lazy vs. Eager Evaluation ##

## Declarative vs. Imperative Programming ##

## Working with Collections ##

Functional languages provide many standard methods of working with collections, a few are outlines below:

  * Filter - Used to create a new collection, which filters out any elements that don't satisfy some condition.
  * Map - Apply a transformation to each element of a collection, and work with that transformed collection.
  * Reduce - Build up an overall value for a property of the whole collection, for example adding up or averaging the values.

# Resources #

  1. Functional Programming for Java Developers by Dean Wampler, published by O'Reilly
  1. http://www.oraclejavamagazine-digital.com/javamagazine_open/20121112#pg37
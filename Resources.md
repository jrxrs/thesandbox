

# To Do #

  1. A section about Databases

# Java #

  * [Java API Specifications](http://www.oracle.com/technetwork/java/api-141528.html)
  * [Oracle's FAQ for all Java topics](http://www.oracle.com/technetwork/java/index-141834.html).

## The Absolute Basics ##

These take the form of quick fire questions with short descriptive answers.

  1. **What is a class?**<br>A class is a blueprint, or prototype, that defines the variables and the methods common to all objects of a certain kind.<br>
<ol><li><b>What is a object?</b><br>An object is a programming unit, which defines behaviour and state.<br>
</li><li><b>What is a method?</b><br>Encapsulation of a functionality which can be called to perform specific tasks.<br>
</li><li><b>What are the different types of modifiers?</b><br>There are access modifiers and there are other identifiers. Access modifiers are <code>public</code>, <code>protected</code> and <code>private</code> (if no access modifier is specified then package level (or friendly) access is granted). Other are <code>final</code>, <code>static</code>, <code>volatile</code>, <code>transient</code>.<br>
</li><li><b>What is a wrapper class?</b><br>They are classes that wrap a primitive data type so it can be used as a first class object.<br>
</li><li><b>What is a static variable and static method? What’s the difference between two?</b><br>The modifier static can be used with a variable and method. When declared as static variable, there is only one variable no matter how many instances are created, this variable is initialized when the class is loaded and stored in the <code>Class</code> object. Static methods do not need a class to be instantiated to be called, also a non static method cannot be called from static method.<br>
</li><li><b>What is garbage collection?</b><br>Garbage Collection is a thread that runs to reclaim the memory by destroying the objects that cannot be referenced anymore. Also see the <a href='#Garbage_Collection.md'>Garbage Collection</a> section below.<br>
</li><li><b>What is an abstract class?</b><br>An abstract class is a class that cannot be instantiated directly and therefore needs to be extended and its methods implemented, a class has to be declared abstract if it has one or more abstract methods, but on the flip-side a class declared abstract need not necessarily have any abstract methods.<br>
</li><li><b>What is meant by a final class, methods and variables?</b><br>When declared <code>final</code> a class cannot be extended (by a sub-class). Similarly when a method is declared <code>final</code> it cannot be overridden by a method belonging to a sub-class. When a primitive variable is declared <code>final</code> its value cannot be changed...If the variable is a reference to an object then it will always refer to the same object, internal attributes of the object can be changed. Using the final keyword on a class can bring with it a modest performance boost because it allows methods to be in-lined, this is only possible if the compiler can guarantee that the methods cannot be overridden.<br>
</li><li><b>What is an interface?</b><br>An interface is a contract that can be implemented by a class, it contains method signatures that must be implemented.<br>
</li><li><b>What is method overloading?</b><br>Overloading is declaring multiple methods with the same name, but with a different argument list. This is an example of static polymorphism because we can resolve things at compile time (early binding). <b>Note:</b> constructors can be overridden in exactly the same way.<br>
</li><li><b>What is method overriding?</b><br>When a method is overridden it means that a method exists in a sub-class which has the same (method signature) name and identical arguments used by the original method in the parent class. The overriding method can still call the parent method using the <code>super</code> keyword but will most likely provide some alternative behaviour. Methods may be overridden to be more <code>public</code>, not more <code>private</code>.<br>
</li><li><b>What is casting?</b><br>Conversion of one object or primitive type to another.<br>
</li><li><b>What is the difference between <code>final</code>, <code>finally</code> and <code>finalize</code>?</b><br>The modifier <code>final</code> is used on variables, methods and classes to specify certain behaviour explained above. The <code>finally</code> keyword is used as one of the blocks of code in try-catch scenarios, it is used to hold code that needs to be executed whether or not the exception occurs in the try catch block (for example to release locks or close resources, although Java SE 7 allows for this with the try with resources block). Java provides a method called <code>finalize()</code> that can be defined in the class. When the garbage collector is ready to release the storage for your object, it will first call <code>finalize()</code>, and only on the next garbage-collection pass will it reclaim the objects memory. So <code>finalize()</code>, gives you the ability to perform some important clean up at the time of garbage collection. <b>This use of <code>finalize()</code>, however, is not recommended, since there is no guarantee as to when, or even if, this method will be run.</b>
</li><li><b>What are packages?</b><br>A package is a collection of related classes and interfaces providing access protection and namespace management. Packages help programmers achieve encapsulation.<br>
</li><li><b>What is a super class and how can you call a super class?</b><br>When a class is extended that is derived from another class a relationship is created, the parent class is referred to as the super class by the derived class that is the child or sub class. The derived class can make a call to the super class using the keyword <code>super</code>. If used in the constructor of the derived class it has to be the first statement.<br>
</li><li><b>What is meant by a Thread?</b><br>A thread is defined as an instantiated parallel process of a given program.<br>
</li><li><b>What is multi-threading?</b><br>Multi-threading as the name suggest is the scenario where more than one threads are running.<br>
</li><li><b>What are two ways of creating a thread? Which is the best way and why?</b><br>Two ways of creating threads are, either to extend the <code>java.lang.Thread</code> class and implement/override the <code>run()</code> method or a different class can be created which implements the <code>Runnable</code> interface, and the then implement the <code>run()</code> method. The latter is most commonly used due to the Java restriction of single implementation inheritance. Implementation of the Runnable interface solves this problem, if additional implementation inheritance (i.e. extending another class) is required.<br>
</li><li><b>What is deadlock?</b><br>Deadlock is a situation when two threads are waiting on each other to release a resource. Each thread waiting for a resource which is held by the other waiting thread. In Java, this resource is usually the object lock obtained by the <code>synchronized</code> keyword.<br>
</li><li><b>What are the three types of priority?</b><br><code>MAX_PRIORITY</code> which is <code>10</code>, <code>MIN_PRIORITY</code> which is <code>1</code> & <code>NORM_PRIORITY</code> which is <code>5</code>. Some concurrency texts do not recommend altering thread priorities as it can lead to starvation (where a thread never progresses).<br>
</li><li><b>What is the use of the <code>synchronized</code> keyword?</b><br>Every object has a lock, when a <code>synchronized</code> keyword is used on a piece of code the lock must be obtained by the thread first to execute that code, other threads will not be allowed to execute that piece of code until this lock is released. See <a href='http://code.google.com/p/thesandbox/wiki/EgMessageQueue'>here</a> for code examples.<br>
</li><li><b>What are the methods in the <code>Object</code> class?</b><br>The <a href='http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html'>Object</a> class has 11 methods, they are:<br>
</li></ol><ul><li><code>clone()</code>
</li><li><code>equals(Object o)</code>
</li><li><code>hashCode()</code>
</li><li><code>finalize()</code>
</li><li><code>notify()</code>
</li><li><code>notifyAll()</code>
</li><li><code>wait()</code>
</li><li><code>wait(long timeout)</code>
</li><li><code>wait(long timeout, int nano)</code>
</li><li><code>toString()</code>
</li><li><code>getClass()</code>
</li></ul><ol><li><b>If you override <code>equals()</code> what else should you do?</b><br>You should also override the <code>hashCode()</code> method in order to ensure conformation with the contract in the <code>Object</code> class, specifially that if <code>equals(Object)</code> is true for two objects then calling <code>hashCode()</code> on the same two objects must result in the same integer result. See <a href='http://www.ibm.com/developerworks/java/library/j-jtp05273/index.html'>here</a> for some basic examples of how to write simple <code>equals()</code> and <code>hashCode()</code> methods.<br>
</li><li><b>Explain how <code>wait()</code> & <code>notify()</code> are used?</b><br><code>wait()</code> and <code>notify()</code> belong to the <code>Object</code> class and are used in conjunction with the monitor (lock) of the object in question. A thread can obtain the monitor of an object in one of three ways:<br>
</li></ol><ul><li>By executing a synchronized instance method of that object.<br>
</li><li>By executing the body of a <code>synchronized</code> statement that synchronizes on the object.<br>
</li><li>For objects of type <code>Class</code>, by executing a synchronized static method of that class.<br>
A thread may only call <code>wait()</code> or <code>notify()</code> on an object for which it owns the monitor, if such a thread were to call <code>wait()</code> it must release the monitor while it waits to be notified, once it is notified it must re-obtain the same monitor before continuing. Conditions are usually used to decide when to wait and actions are usually performed before notifying. See EgMessageQueue for an example.</li></ul>

<b>ADJPQ</b>

<b>Q1.</b> What is the difference between these two equality checks?<br>
<pre><code>a == b;<br>
a.equals(b);<br>
</code></pre>

<b>A1.</b> The double equals <code>==</code> operator compares the values of primitives and the references of objects whereas the <code>equals()</code> method should compare the contents of one object with another.<br>
<br>
<b>Q2a.</b> Can you describe the difference between primitive values and reference variables?<br>
<br>
<b>A2a.</b> A primitive value is not a proper object, i.e. it does not extend <code>Object</code> and only holds a value, no methods can be called on a primitive value. A reference variable on the other hand is a reference to a proper object which does extend <code>Object</code> and on which you can invoke methods etc.<br>
<br>
<b>Q2b.</b> Why does this code work in Java:<br>
<pre><code>int a = 10;<br>
List&lt;Integer&gt; as = ArrayList&lt;Integer&gt;();<br>
as.add(a);<br>
</code></pre>

<b>A2b.</b> This works because since Java 5 the language has included auto-boxing and un-boxing which allow the primitive <code>int</code> type to be assigned to a new <code>Integer</code> object which is eligible to added to the list.<br>
<br>
<b>Q3a.</b> What is the value of c after evaluating the following expressions?<br>
<pre><code>int a = 10;<br>
int b = 3;<br>
double c = a / b;<br>
</code></pre>

<b>A3a.</b> The variable <code>c</code> will hold the value <code>3.0</code>, this loss in precision (the answer should be <code>3.333...</code>) is due to the <code>int</code> division taking place and yielding 3 before the widening conversion which casts the <code>int</code> to a <code>double</code>. More information can be found <a href='http://stackoverflow.com/questions/3144610/java-integer-division-how-do-you-produce-a-double'>here</a>.<br>
<br>
<b>Q3b.</b> How should one change this to attain the expected value?<br>
<br>
<b>A3b.</b> You could either declare <code>a</code> or <code>b</code> as a <code>double</code> or you could cast either (or both) to <code>double</code> in the declaration of <code>c</code> (the calculation itself).<br>
<br>
<b>Q4a.</b> What could be said about this method?<br>
<pre><code>public void updateString(final String a) {<br>
    a.concat("some extra code");<br>
}<br>
</code></pre>

<b>A4a.</b> The code itself is safe, however semantically it doesn't make any sense because the return value of the <code>concat</code> method is ignored as this method does not mutate the variable <code>a</code>, which is immutable anyway (as are all Strings).<br>
<br>
<b>Q4b.</b> What is the difference between pass-by-reference and pass-by-value? Which is Java?<br>
<br>
<b>A4b.</b> Java is pass-by-value, this means that when a variable is passed to a method a copy of the value telling the JVM where to find the object in question on the heap is actually passed and assigned to the parameter of the method, regardless of what you do inside the other method it is impossible to change what the original variable references. Pass-by-reference on the other hand allows you to do just that, i.e. in C++ if you pass a variable by reference then you can manipulate that reference inside your function, perhaps creating a new Object and pointing to that and when you return from the function the variable you originally passed in will no point to the object created inside the function you called.<br>
<a href='http://stackoverflow.com/questions/40480/is-java-pass-by-reference'>This subject is commonly misunderstood</a>.<br>
<br>
<b>Q4c.</b> Why are Strings immutable in Java?<br>
<br>
<b>A4c.</b> The best reasons are probably for performance and security reasons. See <a href='http://javarevisited.blogspot.co.uk/2010/10/why-string-is-immutable-in-java.html'>here</a>.<br>
<br>
<b>Q5a.</b> How does Java behave as a result of this call?<br>
<pre><code>String a = "Some test text";<br>
a.intern();<br>
</code></pre>

<b>A5a.</b> A call to <code>intern()</code> will cause Java to check the String class's string pool for an occurrence of the text held in the variable on which you call it, <code>"Some test text"</code> in this case, if found it will return a reference to the instance help in the pool, otherwise it will add the String to the pool and return a reference to it. In the case above <code>String a</code> is declared as a String literal (see <a href='http://stackoverflow.com/questions/3297867/difference-between-string-object-and-string-literal'>here</a>) which means the JVM will have already interned the String before <code>a.intern()</code> is called, as a result <code>a.intern()</code> would just return the same reference that <code>a</code> already holds.<br>
<br>
The whole point of String interning is to prevent the same String being held multiple times in memory.<br>
<br>
<b>Q5b.</b> What differences are there between how this call is handled in 1.6 and 1.7?<br>
<br>
<b>A5b.</b> In Java 1.6 any String literals are interned when the class is loaded by the JVM, the string pool is stored in permgen space. In 1.7 String interning doesn't take place until the statement is executed and the pool is located in main memory, rather than permgen. A very detailed article on this topic (also discussing Java 8) can be found <a href='http://java-performance.info/string-intern-in-java-6-7-8/'>here</a>.<br>
<br>
<b>Q5c.</b> When is the following expression true?<br>
<pre><code>a.intern() == b.intern();<br>
</code></pre>

<b>A5c.</b> This is only true when the contents of <code>a</code> and <code>b</code> are equal i.e. a.equals(b) is also true.<br>
<br>
<b>Q5a.</b> Which of these lines compile? For ones that do not, please explain why not?<br>
<pre><code>    Map&lt;String, Double&gt; a = new HashMap&lt;String, Double&gt;();<br>
//  Map&lt;String, Double&gt; b = new HashMap&lt;Object, Double&gt;();<br>
//  Map&lt;Object, Double&gt; c = new HashMap&lt;String, Double&gt;();<br>
    Map&lt;?, Double&gt; d = new HashMap&lt;String, Double&gt;();<br>
    Map&lt;String, ?&gt; e = new HashMap&lt;String, Double&gt;();<br>
//  Map&lt;?, ?&gt; f = new HashMap&lt;&gt;();<br>
</code></pre>

<b>A5a.</b> The lines commented out will not compile.<br>
<br>
For the questions below we depends on the definition of subtypes in generic context, more <a href='http://docs.oracle.com/javase/tutorial/extra/generics/subtype.html'>here</a>, particularly the following snippet:<br>
<br>
<blockquote>In general, if <code>Foo</code> is a subtype (subclass or subinterface) of <code>Bar</code>, and <code>G</code> is some generic type declaration, it is not the case that <code>G&lt;Foo&gt;</code> is a subtype of <code>G&lt;Bar&gt;</code>. This is probably the hardest thing you need to learn about generics, because it goes against our deeply held intuitions.</blockquote>

<a href='http://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html'>Generic Wildcards</a>

<b>Q5b.</b> Given the following method declaration which of the line will compile?<br>
<br>
<pre><code>    public static void test(List&lt;String&gt; a) {<br>
        a.add(null);<br>
        a.add("test");<br>
//      a.add(new Object());<br>
        String b = a.get(0);<br>
        Object c = a.get(0);<br>
    }<br>
</code></pre>

<b>A5b.</b> The lines commented out will not compile.<br>
<br>
<b>Q5c.</b> Given the following method declaration which of the line will compile?<br>
<br>
<pre><code>    public static void test(List&lt;Object&gt; a) {<br>
        a.add(null);<br>
        a.add("test");<br>
        a.add(new Object());<br>
//      String b = a.get(0);<br>
        Object c = a.get(0);<br>
    }<br>
</code></pre>

<b>A5c.</b> The lines commented out will not compile.<br>
<br>
<b>Q5d.</b> Given the following method declaration which of the line will compile?<br>
<br>
<pre><code>    public static void test(List&lt;?&gt; a) {<br>
        a.add(null);<br>
//      a.add("test");<br>
//      a.add(new Object());<br>
//      String b = a.get(0);<br>
        Object c = a.get(0);<br>
    }<br>
</code></pre>

<b>A5d.</b> The lines commented out will not compile.<br>
<br>
<b>Q6a.</b> What are <code>synchronized</code> methods and <code>synchronized</code> blocks? What are the differences between the two? What do they both provide?<br>
<br>
<b>A6a.</b>

<b>Q6b.</b> What does the keyword <code>volatile</code> mean?<br>
<br>
<b>A6b.</b> Post Java 1.5 the semantics of the <code>volatile</code> keyword are that a thread wishing to read or write a volatile variable must only do so from main memory i.e. not from a local cached copy of the Object it may have stored. The <code>volatile</code> keyword also helps to establish a happens-before relationship between threads such that any changes made to non-volatile variables by a thread t1 before it writes to a volatile variable are also synchronized back to main memory and therefore become visible to thread t2. See <a href='http://jeremymanson.blogspot.co.uk/2008/11/what-volatile-means-in-java.html'>here</a> for an explanation from one of the authors of JSR-133.<br>
<br>
<b>Q6c.</b> Why would you use an "Atomic" object (from the concurrency package, post Java 1.5) over a <code>volatile</code> variable?<br>
<br>
<b>A6c.</b> You'd use an atomic object because they provide methods that guarantee atomicity of reads and writes of a variable, this avoids the case where one threads is halfway through a command such as <code>x++</code> and another threads begins the same operation reading the old value of <code>x</code>.<br>
<br>
<b>Q7a.</b> What is meant by the JVM? What does the JVM provide that is not true for languages such as C++?<br>
<br>
<b>A7a.</b>

<b>Q7b.</b> What is the difference between Java <a href='source.md'>source</a> code, Bytecode and Machine code?<br>
<br>
<b>A7b.</b>

<b>Q7c.</b> Where and when does conversion from one form to another take place?<br>
<br>
<b>A7c.</b>

<b>Q8.</b> Explain the differences between a HashMap and a TreeMap?<br>
<br>
<b>A8.</b>

<b>Q9.</b> Explain the differences between an ArrayList and a LinkedList?<br>
<br>
<b>A9.</b> Include points from <a href='http://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist'>here</a>.<br>
<br>
<h2>P.I.E. - The Pillars of Object Orientation</h2>

Another method of remembering this is as AEIoP:<br>
<ul><li>Abstraction<br>
</li><li>Encapsulation<br>
</li><li>Inheritance<br>
</li><li>Polymorphism</li></ul>

Think of the "o" as standing for Object Orientation.<br>
<br>
<h3>Polymorphism</h3>

<a href='http://en.wikipedia.org/wiki/Polymorphism_in_object-oriented_programming'>Wikipedia: Polymorphism</a>

<ul><li>Is the ability of methods to behave differently, based on the object calling it</li></ul>

In object-oriented programming, polymorphism refers to a programming language’s ability to process objects differently depending on their data type or class. More specifically, it is the ability to redefine methods for derived [sub-] classes. For example, given a base class shape, polymorphism enables the programmer to define different area methods for any number of derived classes, such as circles, rectangles and triangles. No matter what shape an object is, applying the area method to it will return the correct results. Polymorphism is considered to be a requirement of any true object-oriented programming language.<br>
<br>
<h3>Inheritance</h3>

<a href='http://en.wikipedia.org/wiki/Inheritance_(computer_science)'>Wikipedia: Inheritance</a>

<ul><li>In simple words, Inheritance is way to define new a class, using classes which have already been defined</li></ul>

Inheritance (is-a) vs. Composition (has-a)<br>
<br>
<b>Inheritance</b> is uni-directional. For example <i>House</i> <b>is-a</b> <i>Building</i>, but <i>Building</i> is not a <i>House</i>. <b>Inheritance</b> uses the <b>extends</b> keyword. <b>Composition</b> on the other hand is used when <i>House</i> <b>has-a</b> <i>Bathroom</i>. Inheritance should not be used just to facilitate code reuse, if there is no <b>is-a</b> relationship then use composition for code reuse.<br>
<br>
Inheritance in object oriented programming means that a class of objects can inherit properties and methods from another (parent) class of objects.<br>
<br>
Interface Inheritance (is-like-a)<br>
<br>
The is-a relationship described above is relevant to class inheritance, but we also have interface inheritance in Java, for instance a <i>Circle</i> <b>is-a</b> <i>Share</i>, but it might also be possible to rotate a Circle, clearly Circle is not directly a Rotatable, but it <b>is-like-a</b> Rotatable which implies Rotatable should be declared as an interface which Circle implements.<br>
<br>
<b>NOTE</b> In Java multiple inheritance of classes is not allowed, however a sub-interface can extend multiple interfaces, <a href='http://en.wikipedia.org/wiki/Interface_%28Java%29#Subinterfaces'>example</a>.<br>
<br>
<h3>Encapsulation</h3>

<a href='http://en.wikipedia.org/wiki/Encapsulation_(object-oriented_programming)'>Wikipedia: Encapsulation</a>

<ul><li>With Encapsulation you can hide (restrict access) to critical data members in your code, which improves security<br>
</li><li>Encapsulation combines data and actions together (just like a capsule)</li></ul>

Encapsulation is the term given to the process of hiding the implementation details of the object. Once an object is encapsulated, its implementation details are not immediately accessible any more. Instead they are packaged and are only indirectly accessible via the interface of the object. When used properly encapsulation allows us to very easily change the implementation of a class without burdening those who use it (provided we maintain a consistent interface for interaction with our class).<br>
<br>
<h4>Data Abstraction</h4>

<a href='http://wiki.answers.com/Q/What_is_data_abstraction_in_java'>Data Abstraction in Java</a>

<ul><li>Abstraction in the process of selecting important data sets for an Object in your software , and leaving out the insignificant ones.<br>
</li><li>Once you have modelled your object using Abstraction , the same set of data could be used in different applications.</li></ul>

<h3>SOLID Principles</h3>

Bob Martin describe five SOLID principles for good object-oriented design in his article <a href='http://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod'>The Principles of OOD</a>. These simple principles aim to jog our memories each time we begin designing, writing or refactoring code:<br>
<br>
<table><thead><th> <b>Principle</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> <b><i>S</i></b>ingle responsibility principle (SRP) </td><td> Each object should do one thing, and one thing only. <i>So a class should have one, and only one, reason to change.</i> </td></tr>
<tr><td> <b><i>O</i></b>pen/closed principle (OCP) </td><td> Objects should be extensible but not modifiable. <i>So you should be able to extend a classes behaviour, without modifying it.</i> </td></tr>
<tr><td> <b><i>L</i></b>iskov substitution principle (LSP) </td><td> Objects should be replaceable by their subtypes. </td></tr>
<tr><td> <b><i>I</i></b>nterface segregation principle (ISP) </td><td> Small specific interfaces are better. <i>So make fine grained interfaces that are client specific.</i> </td></tr>
<tr><td> <b><i>D</i></b>ependency inversion principle (DIP) </td><td> Don’t depend on concrete implementations. <i>So depend on abstractions, not on concretions.</i> </td></tr></tbody></table>

<h2>Modifiers</h2>

According to the suggestions of the Java Language Specification, 8.1.1, 8.3.1 and 8.4.3, the correct order of modifiers is:<br>
<br>
<ol><li><code>public</code>
</li><li><code>protected</code>
</li><li><code>private</code>
</li><li><code>abstract</code>
</li><li><code>static</code>
</li><li><code>final</code>
</li><li><code>transient</code>
</li><li><code>volatile</code>
</li><li><code>synchronized</code>
</li><li><code>native</code>
</li><li><code>strictfp</code></li></ol>

<h3>Access Modifiers</h3>

The table below list the different access modifiers and their corresponding visibility:<br>
<br>
<table><thead><th> <b>Access modifier/accessibility</b> </th><th> <b>Within the same class</b> </th><th> <b>Subclass inside the package</b> </th><th> <b>Subclass outside the package</b> </th><th> <b>Other class inside the package</b> </th><th> <b>Other class outside the package</b> </th></thead><tbody>
<tr><td> <code>public</code>                  </td><td> y                            </td><td> y                                  </td><td> y                                   </td><td> y                                     </td><td> y                                      </td></tr>
<tr><td> <code>private</code>                 </td><td> y                            </td><td> n                                  </td><td> n                                   </td><td> n                                     </td><td> n                                      </td></tr>
<tr><td> <code>protected</code>               </td><td> y                            </td><td> y                                  </td><td> y                                   </td><td> <b>y</b>                              </td><td> n                                      </td></tr>
<tr><td> Default                              </td><td> y                            </td><td> y                                  </td><td> <b>n</b>                            </td><td> y                                     </td><td> n                                      </td></tr></tbody></table>

The "<b>y</b>" in the <b>Other class inside the package</b> column for <code>protected</code> is interesting as it has passed me by before, for years I had assumed that it's only real use was for access in sub-classes but in fact <code>protected</code> fields and methods can also be accessed by classes in the same package as well.<br>
<br>
<b>Note:</b> When dealing with access modifiers for classes there is a simple gottcha to be aware of:<br>
<br>
<blockquote>A public method in a class is accessible to the outside world only if the class is declared as public. If the class does not specify any access modifier, then the public method is accessible only within the containing package. It's also important to note that a class (or interface) cannot be declared private or protected. Furthermore, member methods or fields of an interface cannot be declared as private of protected.</blockquote>

<h2>Overloading</h2>

Overloading is an important part of polymorphism and categorised as static polymorphism because it takes place at compile time. Both methods and constructors can be overloaded, if the compiler cannot find a match for a method call after exhausting all possible upcasting possibilities (i.e. byte > short > int > long etc. or Integer > Object) or if it finds too many ambiguous matches then a compiler error will result. Things to remember:<br>
<br>
<ul><li>Overload resolution takes places entirely at compile time (the opposite is runtime polymorphism).<br>
</li><li>You cannot overload methods where the methods differ only in return type.<br>
</li><li>You cannot overload methods where the methods differ only in exception specifications alone.<br>
</li><li>For overload resolution to succeed, you need to define methods such that the compiler finds one exact match. If the compiler finds no matches or of the matching is ambiguous, the overload resolution fails and the compiler issues an error.</li></ul>

<h2>Inheritance</h2>

Inheritance in Object Orientated programming is the hierarchical classification of knowledge. An inheritance relationship is referred to an an <i>is a</i> relationship.<br>
<br>
<h3>Runtime Polymorphism</h3>

In runtime polymorphism the type of the base class <b>reference</b> is known as the <i>static type</i> of the object and the actual object pointed to by the reference at runtime is known as the <i>dynamic type</i> of the object. When the compiler sees the invocation of a methods from a base class reference and if the method is an over-ridable method (<b>a non-static and non-final method</b>), the compiler defers determining the exact method to be called to runtime (late binding). At runtime, based on the actual dynamic type of the object, an appropriate method is invoked. This mechanism is known as <i>dynamic method resolution</i> or <i>dynamic method invocation</i>.<br>
<br>
<h3>Overriding</h3>

Things to keep in mind when overriding, the overriding method:<br>
<br>
<ul><li>Should have the same argument list types (or compatible types) as the base version.<br>
</li><li>Should have the same return type - although since Java 5, the return type can be a subclass-covariant return type.<br>
</li><li>Should not have a more restrictive access modifier than the base version - but it may have a less restrictive access modifier.<br>
</li><li>Should not throw new or broader checked exceptions - but it may throw fewer or narrower checked exceptions, or any unchecked exception.<br>
</li><li>And, oh yes, the names should match...exactly!</li></ul>

<h3>Covariant Return Types</h3>

Since Java 5 it has been possible to make the return type of your over-ridable method a derived class of the return type declared in the base method (class). But why does this matter? Well, this might be convenient if you're attempting to trying to define an <code>abstract copy()</code> method is a <code>Shape</code> base class, ideally you'd like to be able to call copy on a Circle and get a fully fledge circle returned, however unless you can override the original copy method from Shape e.g. <code>public abstract Shape copy();</code> with a more specific derived return type in Circle i.e. <code>public Circle copy() {...}</code> then this wouldn't be possible.<br>
<br>
<h2>Covariance and Contravariance (Object Typing)</h2>

<a href='http://en.wikipedia.org/wiki/Covariance_and_contravariance_%28computer_science%29'>Covariance &amp; Contravariance (computer science) from Wikipedia</a>.<br>
<br>
<ul><li>covariant - converting from a specialized type (Cats) to a more general type (Animals): Every cat is an animal.<br>
</li><li>contravariant: - converting from a general type (Shapes) to a more specialized type (Rectangles): Is this shape a rectangle?<br>
</li><li>invariant - not able to convert.</li></ul>

<h2>Nested Classes</h2>

<h3>Static Nested Class (non-local)</h3>

Defined within a class.<br>
<br>
<b>Example</b>
<pre><code>class Outer {<br>
    static class StaticNested {<br>
        // class definition<br>
    }<br>
}<br>
</code></pre>

You can define a class or interface as a static member inside another class or interface. A static nested class is as good as a class defined as an outer class with one different - it is physically defined inside another class.<br>
<br>
<b>What you need to know about static nested classes</b>

<ul><li>The accessibility (<code>public</code>, <code>protected</code>, etc.) of the static class is defined by the outer class.<br>
</li><li>The name of the static nested class is expressed with <code>OuterClassName.NestedClassName</code> syntax.<br>
</li><li>When you define an inner class (or interface) inside an interface, the nested class is declared implicitly <code>public</code> and <code>static</code>. This point is easy to remember: any field in an interface is implicitly declared <code>public</code> and <code>static</code>, and static nested classes have this same behaviour.<br>
</li><li>Static nested classes can be declared <code>abstract</code> or <code>final</code>.<br>
</li><li>Static nested classes can extends another class or it can be used as a base class.<br>
</li><li>Static nested classes can have static members. (As you'll see shortly, this statement does not apply to other kinds of nested classes.)<br>
</li><li>Static nested classes can access the members of the outer class (only static members, obviously).<br>
</li><li>The outer class can also access the members (even private members) of the nested class through an object of the nest class. If you don't declare an instance of the nested class, the outer class cannot access nested class elements directly.</li></ul>

<h3>Inner Class (non-local)</h3>

Defined within a class.<br>
<br>
<b>Example</b>
<pre><code>class Outer {<br>
    class Inner {<br>
        //class definition<br>
    }<br>
}<br>
</code></pre>

To create a new instance of a nested inner class you need to use the following syntax:<br>
<code>center = this.new Point(x, y);</code>
The <code>this.</code> is required in order to prefix the object reference of the outer class as every inner class is associated with an instance of the outer class. In other words, an inner class is always associated with an enclosing object.<br>
<br>
<b>Note:</b> You cannot declare static members in an inner class.<br>
<pre><code>class Outer {<br>
    class Inner {<br>
        static int i = 10; //ERROR!<br>
    }<br>
}<br>
</code></pre>

<b>What you need to know about inner classes</b>

<ul><li>The accessibility (<code>public</code>, <code>protected</code>, etc.) of the inner class is defined by the outer class.<br>
</li><li>Just like top level classes an inner class can extend a class or can implement interfaces. Similarly, an inner class can be extended by other classes, and an inner interface can be implemented or extended by other classes or interfaces.<br>
</li><li>Static nested classes can be declared <code>abstract</code> or <code>final</code>.<br>
</li><li>Inner classes can have inner classes, but you'll have a hard time reading or understanding such complex nesting of classes i.e. avoid!</li></ul>

So, what's the difference between a nested static class and an inner class? Well, there may not seem much at first glance, however look a little closer and we can see that the major differences are that inner classes cannot have static members whereas nested static classes can and you don't need an instance of the class containing a nested static in order to create an instance of the nested static, whereas you do need a instance of the class containing a inner class to create an instance of that inner class.<br>
<br>
<h3>Local Inner Class (local)</h3>

Defined within a code block (method, constructor or initialisation block). As such they are not members of an outer class; they are just local to the method or code in which they are defined.<br>
<br>
<b>Example</b>
<pre><code>class Outer {<br>
    void foo {<br>
        class LocalInner {<br>
            // class definition<br>
        }<br>
    }<br>
}<br>
</code></pre>

It is not possible to define interfaces within methods, constructors or initialisation blocks.<br>
<br>
<b>What you need to know about local inner classes</b>

<ul><li>You can create a non-static local class inside a body of code. Interfaces cannot have local classes, and you cannot create local interfaces.<br>
</li><li>Local classes are accessible only from the body of the code in which they class is defined. The local classes are completely inaccessible outside the body of code in which the class is defined.<br>
</li><li>You can extends a class or implement interfaces while defining a local class.<br>
</li><li>A local class can access all the variables available in the body of code in which it is defined. You can pass only final variables to a local inner class.</li></ul>

<h3>Anonymous Inner Class (local)</h3>

Defined within a code block (method, constructor or initialisation block).<br>
<br>
<b>Example</b>
<pre><code>class Outer {<br>
    void foo() {<br>
        return new Object() {<br>
            public String toString() {<br>
                return "anonymous";<br>
            }<br>
        }<br>
    }<br>
}<br>
</code></pre>

<b>What you need to know about anonymous inner classes</b>

<ul><li>Anonymous classes are defined in the <code>new</code> expression itself, so you cannot create multiple objects of an anonymous class.<br>
</li><li>You cannot explicitly extend a class or explicitly implement interfaces when defining an anonymous class.</li></ul>

<h2>Introspection (Annotations/generics/etc.)</h2>

<a href='http://stackoverflow.com/questions/2044446/java-introspection-and-reflection'>Reflection vs. Introspection</a>

Links for working with Annotations:<br>
<ul><li><a href='http://stackoverflow.com/questions/1004022/java-generic-class-determine-type'>Determining the Type of generic implementations</a>
</li><li><a href='http://stackoverflow.com/questions/6593597/java-seek-a-method-with-specific-annotation-and-its-annotation-element'>Identifying methods with a specific Annotation</a>
</li><li><a href='http://stackoverflow.com/questions/4296910/is-it-possible-to-read-the-value-of-a-annotation-in-java'>Reading the value of Annotations</a></li></ul>

<h2>Autoboxing</h2>

Autoboxing is available as of Java 1.5.0 and introduces the automated handling of primitive Java type to their equivalent Wrapper class. <a href='http://java.sun.com/j2se/1.5.0/docs/guide/language/autoboxing.html'>Java 1.5.0 Language Guide</a>

<h2>Imports & Performance</h2>

Did some research and it seems that conventional wisdom suggests that using imports with the wild card character, of the form:<br>
<pre><code>import javax.swing.*;<br>
</code></pre>
does not impact runtime performance. There is however a valid case for specifying exactly the classes you wish to import as detailed in <a href='http://www.javafaq.nu/java-article914.html'>this article</a>.<br>
<br>
<h2>Mutable & Immutable Objects</h2>

Item 15 of Effective Java (2<sup>nd</sup> Ed.) explains the concept of an Immutable object (an object which cannot be mutated) very nicely, the summary is as follows:<br>
<br>
An immutable class is simply a class whose instances cannot be modified. All of the information contained in each instance is provided when it is created and is fixed for the lifetime of the object. Examples include <code>String</code>, the boxed primitive classes, <code>BigInteger</code> & <code>BigDecimal</code>. The 5 rules for achieving immutability are:<br>
<br>
<ol><li>Don't provide any methods (mutators) that modify the object's state.<br>
</li><li>Ensure that the class cannot be extended.<br>
</li><li>Make all fields final.<br>
</li><li>Make all fields private.<br>
</li><li>Ensure exclusive access to any mutable components.</li></ol>

<a href='http://www.javaranch.com/journal/2003/04/immutable.htm'>Java Ranch - Mutable and Immutable Objects</a>

<h2>Generics</h2>

<a href='http://onjava.com/onjava/excerpt/javaian5_chap04/index1.html'>Writing Generic Types &amp; Methods</a>

<h2>Enum Data Types</h2>

<a href='http://www.javapractices.com/topic/TopicAction.do?Id=1'>Enums Summary from javapractices.com</a>

Enums help you restrict the number of acceptable input to a predefined list and do so in a typesafe manner.<br>
<br>
<b>Note:</b> Enum constants must be the first things defined within the definition of an Enum, also note that a constructor in an Enum class can only be specified as private. Enums cannot be local (i.e. they must be declared at the class level).<br>
<br>
<h2>Checked & Unchecked Exceptions</h2>

<ul><li>Checked exceptions in Java extend the <code>java.lang.Exception</code> class.<br>
</li><li>Unchecked exceptions extend the <code>java.lang.RuntimeException</code>.</li></ul>

Generally it is considered bad practice to catch a <code>RuntimeException</code> such as a <code>ClassCastException</code>, as doing so can mask a programming error, there are of course exceptions to this, like catching a <code>NumberFormatException</code> when trying to capture and parse user input.<br>
<br>
<a href='http://tutorials.jenkov.com/java-exception-handling/checked-or-unchecked-exceptions.html'>Java Exception Handling - Checked or Unchecked Exceptions</a>

<h2>D-Zone Ref Cardz</h2>

<a href='http://refcardz.dzone.com/'>D-Zone Ref Cardz</a>

<h2>Challenging Java Questions</h2>

<a href='http://robaustin.wikidot.com/50-java-interview-questions'>Java Interview Questions</a>

<h2>Concurrency, Threading & Lock Contention</h2>

<a href='http://www.vogella.com/articles/JavaConcurrency/article.html'>http://www.vogella.com/articles/JavaConcurrency/article.html</a>

First things first...why is it important to get multi-threading right in your application? Well, a thread is just a lightweight process which has access to your applications memory, introducing more than one thread means that those threads are now sharing access to your applications memory space which means it's crucial to ensure that, that shared memory is always left in a consistent state by each thread, ready to be read safely by the next thread, the procedures outlined below help ensure that this situation is achievable and that your application runs as desired.<br>
<br>
<h3>Java Concurrency in Practice</h3>

<h4>Using Synchronized Collections</h4>

If you use a <code>synchronized</code> collection from the Collections utility class you should take note of the correct iteration idiom, explained in the <a href='http://docs.oracle.com/javase/6/docs/api/java/util/Collections.html#synchronizedCollection(java.util.Collection)'>Javadoc</a>:<br>
<br>
It is imperative that the user manually synchronize on the returned collection when iterating over it:<br>
<br>
<pre><code>  Collection c = Collections.synchronizedCollection(myCollection);<br>
     ...<br>
  synchronized(c) {<br>
      Iterator i = c.iterator(); // Must be in the synchronized block<br>
      while (i.hasNext())<br>
         foo(i.next());<br>
  }<br>
</code></pre>

Failure to follow this advice may result in non-deterministic behaviour.<br>
<br>
<h4>Summary of Part 1</h4>

<ul><li>It's the mutable state, stupid.<br>
<blockquote>All concurrency issues boil down to coordinating access to mutable state. The less mutable state, the easier it is to ensure thread safety.<br>
</blockquote></li><li>Make fields final unless they need to be mutable.<br>
</li><li>Immutable objects are automatically thread safe.<br>
</li><li>Immutable objects simplify concurrent programming tremendously. They are simpler and safer, and can be shared  freely without locking or defensive copying.<br>
</li><li>Encapsulation makes it practical to manage the complexity.<br>
<blockquote>You could write a thread safe program with all data stored in global variables, but why would you want to? Encapsulating data within objects makes it easier to preserve their invariants; encapsulating synchronization within objects makes it easier to comply with their synchronization policy.<br>
</blockquote></li><li>Guard each mutable variable with a lock. Guard all variables in an invariant with the same lock.<br>
</li><li>Hold locks for the duration of compound actions. A program that accesses a mutable variable from multiple threads without synchronization is a broken program.<br>
</li><li>Don't rely on clever reasoning about why you don't need to synchronize.<br>
</li><li>Include thread safety in the design processor explicitly document that your class is not thread safe.<br>
</li><li>Document your synchronization policy.</li></ul>

<h4>Basic facts about old school synchronization</h4>

<ul><li>Only objects—not primitives—can be locked.<br>
</li><li>Locking an array of objects doesn’t lock the individual objects, i.e. just the array is locked<br>
</li><li>A <code>synchronized</code> method can be thought of as equivalent to a <code>synchronized (this) {...}</code> block that covers the entire method (but note that they're represented differently in byte-code).<br>
</li><li>A <code>static</code> method declared <code>synchronized</code> locks the <code>Class</code> object, because there's no instance object to lock.<br>
</li><li>If you need to lock a class object, consider carefully whether you need to do so explicitly, or by using getClass(), <b>because the behaviour of the two approaches will be different in a subclass</b>.<br>
</li><li>Synchronization in an inner class is independent of the outer class (to see why this is so, remember how inner classes are implemented).<br>
</li><li><code>synchronized</code> doesn't form part of the method signature, so it can’t appear on a method declaration in an interface.<br>
</li><li>Unsynchronized methods don't look at or care about the state of any locks, and they can progress while synchronized methods are running.<br>
</li><li>Java’s locks are re-entrant. That means a thread holding a lock that encounters a synchronization point for the same lock (such as a synchronized method calling another synchronized method in the same class) will be allowed to continue.</li></ul>

<h3>Lock Contention</h3>

<a href='http://www.thinkingparallel.com/2007/07/31/10-ways-to-reduce-lock-contention-in-threaded-programs/'>10 Ways to Reduce Lock Contention in Threaded Programs</a>

<h2>Java's Threading Model</h2>

Chapter 4 of The Well Grounded Java Developer begins by discussing Java's Threading Model which is based on two fundamental concepts:<br>
<br>
<ul><li>Shared, visible-by-default mutable state<br>
</li><li>Pre-emptive thread scheduling</li></ul>

They go on the decompose these further:<br>
<br>
<ul><li>Objects can be easily shared between all threads within a process.<br>
</li><li>Objects can be changed ("mutated") by any threads that have a reference to them.<br>
</li><li>The thread scheduler can swap threads on and off cores at any time, more or less.<br>
</li><li>Methods must be able to be swapped out while they’re running (otherwise a method with an infinite loop would steal the CPU forever). This, however, runs the risk of an unpredictable thread swap leaving a method "half-done" and an object in an inconsistent state. There is also the risk of changes made in one thread not being visible in other threads when they need to be. To mitigate these risks, we come to the last point.<br>
</li><li>Objects can be locked to protect vulnerable data.</li></ul>

<h2>Design Principles for Concurrent Code</h2>

Doug Lea gives 4 main principles to keep in mind when designing and writing concurrent code; <b>Safety</b>, <b>Liveness</b>, <b>Performance</b> & <b>Re-usability</b>.<br>
<br>
SAFETY AND CONCURRENT TYPE SAFETY<br>
Safety is about ensuring that object instances remain self-consistent regardless of any other operations that may be happening at the same time.<br>
<br>
LIVENESS<br>
A live system is one in which every attempted activity eventually either progresses or fails.<br>
<br>
PERFORMANCE<br>
Definitions of performance are often pretty woolly, we'll go for: performance being a measure of how much work a system can do with a given amount of resources.<br>
<br>
REUSABILITY<br>
We should ensure that any code we write to support concurrency can be reused else where, this was the point of the <code>java.unit.concurrent</code> library.<br>
<br>
<h2>The Java Memory Model (JMM)</h2>

Understanding a number of key features from the JMM can really help developers understand the JVM and how to code for it.<br>
<br>
<ul><li><i>Happens-Before</i> — This relationship indicates that one block of code fully completes before the other can start.<br>
</li><li><i>Synchronizes-With</i> — This means that an action will synchronize its view of an object with main memory before continuing.</li></ul>

Some developers find it useful to think of <i>Happens-Before</i> and <i>Synchronizes-With</i> as basic conceptual building blocks for understanding Java concurrency. This is analogous with <i>Has-A</i> and <i>Is-A</i> from  world of OO design, but there is no direct technical connection between the two sets of concepts.<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-27%20at%2011.41.37.png' />

The JMM has these main rules:<br>
<ul><li>An unlock operation on a monitor <i>Synchronizes-With</i> later lock operations.<br>
</li><li>A write to a volatile variable <i>Synchronizes-With</i> later reads of the variable.<br>
</li><li>If an action A <i>Synchronizes-With</i> action B, then A <i>Happens-Before</i> B.<br>
</li><li>If A comes before B in program order, within a thread, then A <i>Happens-Before</i> B.</li></ul>

There are additional rules, which are really about sensible behaviour:<br>
<ul><li>The completion of a constructor <i>Happens-Before</i> the finalizer for that object starts to run (i.e. an object has to be fully constructed before it can be finalized).<br>
</li><li>An action that starts a thread <i>Synchronizes-With</i> the first action of the new thread.<br>
</li><li><code>Thread.join()</code> <i>Synchronizes-With</i> the last (and all other) actions in the thread being joined.<br>
</li><li>If X <i>Happens-Before</i> Y and Y <i>Happens-Before</i> Z then X <i>Happens-Before</i> Z (transitivity).</li></ul>

<h2>Caching</h2>

<a href='http://www.cacheonix.com/articles/Caching_for_Java_Applications.htm'>Main Reference</a>

Caching can help to increase application performance and scalability.<br>
<br>
<h3>In a Nut Shell</h3>

A cache is an area of local memory that holds a copy of frequently accessed data that is otherwise expensive to get or compute. A simple caching API would consist of the a <code>get(Object Key)</code> method and a <code>put (Object key, Object value)</code> method.<br>
<br>
A cache works as follows: An application requests data from cache using a key. If the key is not found, the application retrieves the data from a slow data source and puts it into the cache. The next request for a key is serviced from the cache.<br>
<br>
Section 5.6 of Java Concurrency In Practice has a very nice cache implementation using classes from the standard <code>java.util.concurrent</code> package.<br>
<br>
Note that Java 8 will bring with it a standardised approach to caching allowing different caching providers to be plugged in with little of no overhead. See <a href='http://www.oraclejavamagazine-digital.com/javamagazine_open/20130102#pg59'>here</a> for an article.<br>
<br>
<h3>Temporal and Spatial Locality</h3>

<b>Temporal Locality</b><br>The concept that a resource that is referenced at one point in time will be referenced again sometime in the near future.<br>
<br>
<b>Spatial Locality</b><br>The concept that likelihood of referencing a resource is higher if a resource near it was just referenced.<br>
<br>
<b>Note:</b> When implementing a cache it is vital that you do not ignore temporal or spatial locality as doing so would result in everything being cached, this approach will in fact reduce application performance making <i>Cache Them All</i> an anti-pattern. The degradation of performance is caused by the overhead of maintaining a cache without benefiting from reduced cost of access to frequently used data.<br>
<br>
<h3>Cache Eviction Policy</h3>

A cache eviction policy is an algorithm according to which an existing element is removed from a cache when a new element is added. The eviction policy is applied to ensure that the size of the cache does not exceed a maximum size. Least Recently Used (LRU) is one of the most popular among a number of eviction policies. LRU earned its popularity for being the best in capturing temporal and spatial locality of data access.<br>
<br>
<h3>Measuring Cache Performance</h3>

You tend to measure the performance of a cache using the ratio of cache hits to cache misses.<br>
<br>
<h3>Uses</h3>

Object-relational mapping (ORM) frameworks like Hibernate or data mapping (DM) frameworks such as iBatis ofen provide Level-2 caches of data stored in a database. The application only interacts with the framework and this shields it from all the caching complexities.<br>
<br>
<h2>Object Relational Mapping (ORM) Frameworks</h2>

TODO!<br>
<br>
<h2>Not only SQL (NoSQL)</h2>

In short, NoSQL database management systems are useful when working with a huge quantity of data when the data's nature does not require a relational model (i.e. you don't guarantee the ACID principles usually associated with relational databases). The data can be structured, but NoSQL is used when what really matters is the ability to store and retrieve great quantities of data, not the relationships between the elements. Usage examples might be to store millions of key–value pairs in one or a few associative arrays or to store millions of data records.<br>
<br>
There are numerous example of NoSQL solutions available including <a href='http://cassandra.apache.org/'>Apache Cassandra</a>, GemFire, <a href='http://www.mongodb.org/'>MongoDB</a>, <a href='http://www.neo4j.org/learn'>Neo4j</a>.<br>
<br>
<h2>Garbage Collection</h2>

A good introduction/overview of the basics can be found <a href='http://www.javarevisited.blogspot.co.uk/2011/04/garbage-collection-in-java.html'>here</a> on the <a href='http://www.javarevisited.blogspot.co.uk/'>JavaRevisited Blog</a>. Alternatively see <a href='http://www.oracle.com/technetwork/java/javase/tech/index-jsp-140228.html'>here</a> for all the gorey details from Oracle, particularly their <a href='http://www.oracle.com/technetwork/java/javase/gc-tuning-6-140523.html'>GC Tuning</a> page.<br>
<br>
<h3>Memory Leaks in Java</h3>

Although it might not seem obvious at first it is possible for Java code to suffer from memory leaks, Item 6 of Effective Java 2<sup>nd</sup> Ed. covers this topic and defensive strategies to help avoid.<br>
<br>
Generally speaking memory leaks in Java are due to resources (like files or connections) not being closed correctly before they are moved out of scope, this leaves them hanging around uncollected the garbage collector. The example in Item 6 addresses problems when not nulling out references in an array storing a collection of objects, while basic this is an east example to see how memory could be leaked.<br>
<br>
<h2>Logging</h2>

<ul><li><a href='http://logging.apache.org/log4j/1.2/'>log4j</a>
</li><li><a href='http://www.slf4j.org/'>slf4j</a> is a logging abstraction which shields the developer from having to check is a log level is enabled before creating any, otherwise unnecessary, dynamically compiled Strings, this is explained in more detail <a href='http://www.slf4j.org/faq.html#logging_performance'>here</a>. It stands for Simple Logging Façade for Java and allows the user to plus in which logging framework they want to use (e.g. java.util.logging, log4j and logback) at deployment time. A major benefit of slf4j is that your other libraries can use whatever logging framework they like, but the façade will result in all your logs coming out via you chosen deploy time library.</li></ul>

<h3>Declaring Loggers</h3>

A cautionary note when declaring loggers...<br>
Normal practice when declaring a logger is to use something along the lines of:<br>
<br>
<pre><code>private static final LOGGER = Logger.getLogger(MyClass.class);<br>
</code></pre>

This is handy and efficient as it means you'll only ever have one instance of the logger which all instances of the class with use. However this is quite fragile code, if it’s refactored to move into a superclass or sub-class, the explicit class name would cause problems e.g. misleading log messages if you happen to need to log in a class that will be sub-classed, if you need to do this you'll have to have a logger per instance and use a call to <code>getClass()</code> for the logger name.<br>
<br>
<pre><code>private final logger = Logger.getLogger(getClass());<br>
</code></pre>

Alternatively in Java 7 you can use Method Handles to look up the class in context (even a static context):<br>
<br>
<pre><code>Logger lgr = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());<br>
</code></pre>

<h2>Java printing formatting</h2>

<ul><li><a href='http://sharkysoft.com/archive/printf/docs/javadocs/lava/clib/stdio/doc-files/specification.htm'>Java printf formats</a></li></ul>

<h2>Testing</h2>

Apparently nobody has a team of QA's dedicated to their application any more...there are these things called test cases which the developers write based on stories from the business. Below are a few frameworks for driving these tests:<br>
<br>
<h4>TDD</h4>
The theory with Test Driven Development is that you write one test, then you code just enough to get your test to pass, then you go back and write the another test, code enough to pass that and go back to your code and attempt to refactor any commonality, testing again post refactoring.<br>
<ul><li><a href='http://www.junit.org/'>JUnit</a>
</li><li><a href='http://testng.org/doc/index.html'>TestNG</a></li></ul>

<h4>BDD</h4>
<ul><li><a href='http://fitnesse.org/FrontPage'>FitNesse</a>
</li><li><a href='http://jbehave.org/'>jbehave</a>
</li><li><a href='http://www.concordion.org/'>Concordion</a>
</li><li><a href='http://cukes.info/'>Cucumber</a></li></ul>

<h3>Test Doubles & Mocking</h3>
Test double are used to facilitate testing just the code you're actually writing rather than indirectly testing someone else's code like a third parties database implementation. Gerard Meszaros’s simple explanation of a test double is as follows:<br>
<br>
<blockquote>A Test Double (think Stunt Double) is the generic term for any kind of pretend object used in place of a real object for testing purposes.</blockquote>

He goes one to categorise objects into four compartments depending on the extent to which they are implemented:<br>
<br>
<table><thead><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> Dummy       </td><td> An object that is passed around but never used. Typically used to fulfil the parameter list of a method. </td></tr>
<tr><td> Stub        </td><td> An object that always returns the same canned response. May also hold some dummy state. </td></tr>
<tr><td> Fake        </td><td> An actual working implementation (not of production quality or configuration) that can replace the real implementation. </td></tr>
<tr><td> Mock        </td><td> An object that represents a series of expectations and provides canned responses. <i>Also see below.</i> </td></tr></tbody></table>

<h4>Mocking</h4>
Mocking allows you to pinpoint specific functionality while testing, you might have a method to test which uses RefData for instance, instead of making that call to retrieve the ref data you can choose to mock that call out and return a constant value facilitating your expected result for the original test case. The Wikipedia entry for Mocking appears under <a href='http://en.wikipedia.org/wiki/Mock_object'>Mock Object</a>.<br>
<ul><li><a href='http://code.google.com/p/mockito/'>Mockito</a>
</li><li><a href='http://code.google.com/p/powermock/'>PowerMock</a></li></ul>

A quick introduction to Mockito can be found <a href='http://gojko.net/2009/10/23/mockito-in-six-easy-examples/'>here</a>.<br>
<br>
<h2>Continuous Integration (CI)</h2>

A section on various Continuous Integration platforms e.g. TeamCity, Jenkins (& Hudson), <a href='https://confluence.atlassian.com/display/BAMKB/Bamboo+Knowledge+Base+Home'>Bamboo</a> etc. should go here.<br>
<br>
<h2>Application Containers</h2>

A few years ago micro-containers became very popular for Java applications as J2EE started to become the heart of most applications, mainly because it allows you to plug in common components like JDBC, JMS etc. easily.<br>
<br>
<h3>Spring</h3>

<b>Spring handles the infrastructure so you can focus on your application.</b>

<h4>Spring Links</h4>
<ul><li><a href='http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/'>Documentation for 3.1.x</a>
</li><li><a href='http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/beans.html'>The IoC container documentation</a>
</li><li><a href='http://www.springbyexample.org/'>Spring by Example</a></li></ul>

Spring rapidly became the default container of choice allowing you to create beans (simple pojos) and then plug one into the other gradually building up a dependency graph which Spring manages throughout the application's life-cycle ensuring nothing which is still in use in destroyed prematurely i.e. scope management which is also controllable by the user e.g. Singleton or Session.<br>
<br>
Creating beans is usually accomplished using constructor injection or setter injection, constructor injection is usually favoured however it can introduce difficulties when circular dependencies are unavoidable (however generally we should design our way around these types of dependencies).<br>
<br>
The advantages of Spring are many and varied, it allows access to beans externally with JMX, it allows you to configure caching transparently and supports Aspect Oriented Programming - the idea of wrapping your core pojo with various aspects which provide some additional level of functionality with each layer e.g. security, transaction management, logging caching etc.<br>
<br>
<h2>Remote Method Invocation (RMI)</h2>

<a href='http://docs.oracle.com/javase/tutorial/rmi/index.html'>Oracle's RMI Tutorial</a>

The Java Remote Method Invocation (RMI) system allows an object running in one Java virtual machine to invoke methods on an object running in another Java virtual machine. RMI provides for remote communication between programs written in the Java programming language.<br>
<br>
<h2>Serialization</h2>

<a href='http://www.tutorialspoint.com/java/java_serialization.htm'>This is a really simple tutorial on Java Serialization</a> - to be honest I'd be hard pushed to look past <a href='http://code.google.com/p/protobuf/'>Google's protobufs</a> now.<br>
<br>
<h2>What's new in Java 5?</h2>

These headlines have been scraped from <a href='http://en.wikipedia.org/wiki/Java_version_history'>here</a>.<br>
<br>
<ol><li>Annotations - allowing programmers to provide metadata tags to language constructs (classes, methods, etc.)<br>
</li><li>Generics - allowing collections to be statically type checked at compile time and eliminating the need for most typecasts.<br>
</li><li>Autoboxing/unboxing - see <a href='http://code.google.com/p/thesandbox/wiki/Resources#Autoboxing'>above</a>.<br>
</li><li>Enumerations = i.e. the <code>enum</code> keyword<br>
</li><li>Varargs - The last parameter of a method can now be declared using a type name followed by three dots (e.g. <code>void drawtext(String... lines)</code>). In the calling code any number of parameters of that type can be used and they are then placed in an array to be passed to the method, or alternatively the calling code can pass an array of that type.<br>
</li><li>The enhanced for-each loop was added.<br>
</li><li>The semantics of the <code>volatile</code> keyword were changed to ensure that threads never read a volatile variable from a local cache.<br>
</li><li><code>import static ...</code> was added<br>
</li><li>The <code>java.util.concurrent</code> package was added<br>
</li><li>Covariant return types of overridden methods were introduced (see <a href='https://code.google.com/p/thesandbox/wiki/Resources#Covariant_Return_Types'>Covariant Return Types</a> above).</li></ol>

<h2>What's new in Java 6?</h2>

<ol><li>General upgrades and performance upticks across the Java estate, particularly for the Java core and Swing.<br>
</li><li>Improved garbage collection and tuning<br>
</li><li><code>SwingWorker</code> is added as a standard library class</li></ol>

These headlines have been scraped from <a href='http://en.wikipedia.org/wiki/Java_version_history'>here</a>.<br>
<br>
<h2>What's new in Java 7?</h2>

The main source of information for this section was the Well Grounded Java Developer.<br>
<br>
<ol><li><code>String</code> values in <code>switch</code> statements<br>
</li><li>The syntax for declaring numeric literals has been changed so you can use underscore characters <code>_</code> when declaring integers e.g. <code>int x = 1_000_000;</code>, you can also declare numbers explicitly as binary strings e.g. if you wanted <code>y</code> to be 5 you could write <code>int y = 0b101</code>.<br>
</li><li>Exceptions handling has been tidied up which allows you to catch multiple exceptions within the same catch statement.<br>
</li><li>You can also try-with-resources which aims to prevent bugs related to calling <code>close()</code> on a resource (like a <code>FileWriter</code>) incorrectly<br>
</li><li>Diamond syntax which allows you to infer the type of your generic objects based on their declaration.<br>
</li><li>Simplified varagrs method invocation, this allows you to write something like:<br>
<pre><code>public static &lt;T&gt; Collection&lt;T&gt; doSomething(T... entries) {<br>
    ...<br>
}<br>
</code></pre>
<blockquote>which used to case a problem because behind the scenes the compiler would create an array of whatever type you passed in as T, however if it was something like <code>HashMap&lt;String, String&gt;</code> you'd get a warning about type safety, this is no longer reported to the programmer using <code>doSomething(T... entries)</code> but instead to the writer of the method.</blockquote></li></ol>

A summary of these features in code can be found <a href='https://code.google.com/p/thesandbox/wiki/CodeExamples?ts=1350752358&updated=CodeExamples#New_Java_Features_Outline'>here</a>.<br>
<br>
There is also a change to Java's sorting algorithm in Java 7, where they will start to use Timsort.<br>
<br>
<b>New Utility Classes</b>

<ul><li><a href='http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Phaser.html'>Phaser</a> - The Phaser is designed to help overcome a number of issues with CountDownLatch & CyclicBarrier, the most thorough explanation I have found so far is <a href='http://stackoverflow.com/questions/6830904/java-tutorials-explanations-of-jsr166y-phaser'>here</a>, to summarise it's benefits are: Dynamic number of parties, Reusable & Advanceable.<br>
</li><li><a href='http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/TransferQueue.html'>TransferQueue</a> - In a nutshell a TransferQueue is a BlockingQueue in which producers may wait for consumers to receive elements. The documentation explains more but it's also handy to know that this implementation has been written to take into account modern compiler and processor features and can operate with great efficiency. (See also Section 4.3.6 of the Well Grounded Java Developer).<br>
</li><li><a href='http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ForkJoinPool.html'>Fork/Join</a> - Fork/Join is all about automatic scheduling of tasks on a thread pool that is invisible to the user. One key feature of the framework is that it’s expected that these lightweight tasks may well spawn other instances of <a href='http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ForkJoinTask.html'>ForkJoinTask</a>, which will be scheduled by the same thread pool that executed their parent. This pattern is sometimes called <i>divide and conquer</i>. Section 5.4.1 of The Well Grounded Java Developer presents a simple example of Fork/Join. The Fork/Join architecture includes work stealing by default, this prevents one or more threads in the pool sitting idle while another thread works on a larger task. The next section (5.4.3) gives a number of suggestions for tasks that might be applicable for fork/join and includes a check list to aid decision making/planning.</li></ul>

<b>Method Handles</b>

This is covered by section 5.2 of The Well Grounded Java Developer.<br>
<br>
Method handles are a much cleaner way to achieve the same things as reflection does, it can be thought of as a more modern approach to reflection, but without the verbosity, overhead, and rough edges that the Reflection API sometimes displays. Everything resides in the <code>java.lang.invoke</code> package.<br>
<br>
What is a <a href='http://docs.oracle.com/javase/7/docs/api/java/lang/invoke/MethodHandle.html'>MethodHandle</a>? The official answer is that it’s a typed reference to a method (or field, constructor, and so on) that is directly executable. Another way of saying this is that a method handle is an object that represents the ability to call a method safely.<br>
<br>
<b><code>String.intern()</code> behavioural changes</b>

<a href='http://java.dzone.com/articles/thursday-code-puzzler-string'>Explanation</a>.<br>
<br>
To understand how the behaviour of the <code>String.intern()</code> method has changed between Java 6 & Java 7 we first need a little background on how it works in Java 6... In Java 6 the constant pool maintained by <code>String.class</code> is populated with String literals when a class is loaded, so if the classloader finds that a "test" literal is defined in a class it will creates a String with text "test" and call <code>intern()</code> on it, which places it in the constant pool which is located in permgen memory.<br>
<br>
In Java 7, the constant pool population strategy has changed, the constant String pool is no longer located in permgen space. Thus no string pool is populated during the class load process, instead it is populated when the execution runs.<br>
<br>
It is also interesting to have a look under the covers at how the compiler handles Strings, for instance:<br>
<pre><code>"test".length();<br>
</code></pre>
would actually be compiled to:<br>
<pre><code>new String("test").intern().length();<br>
</code></pre>
because "test" is a literal. Similarly the <code>+</code> operator for Strings is handled as outlined in the <a href='.md'>JavaDoc</a> of the String class:<br>
<br>
<blockquote>The Java language provides special support for the string concatenation operator ( <code>+</code> ), and for conversion of other objects to strings. String concatenation is implemented through the <code>StringBuilder</code> (or <code>StringBuffer</code>) class and its append method. String conversions are implemented through the method <code>toString()</code>, defined by Object and inherited by all classes in Java.</blockquote>

So:<br>
<pre><code>String te = "te", st = "st";<br>
String username = te + st;<br>
</code></pre>
compiles to:<br>
<pre><code>new StringBuilder().append(te).append(st).toString();<br>
</code></pre>

<b>The invokedynamic bytecode instruction</b>

The invokedynamic (<a href='http://www.oraclejavamagazine-digital.com/javamagazine_open/20130102#pg50'>demystified here</a>) is designed to give dynamic languages that target the JVM a fighting chance in terms of matching the performance of native Java bytecode. It simplifies the process of executing a method on an object of unknown type at runtime and goes hand in hand in Method Handles. The main aim is to reduce the amount of heavy lifting (caching and "monkey patching") that dynamic language implementers have to do in order to keep the amount of costly reflection to a minimum.<br>
<br>
<h2>What about Java 8?</h2>

<ol><li>Lambda Expression? Closures... this is to do with <a href='http://en.wikipedia.org/wiki/Anonymous_function'>anonymous functions</a>. The Oracle Java Magazine published a couple of articles on the subject recently, the first can be found <a href='http://www.oraclejavamagazine-digital.com/javamagazine_open/20121112#pg35'>here[ and the second [http://www.oraclejavamagazine-digital.com/javamagazine/20130304#pg40 here</a>. They point out that while the Java term for this functional programming concept is Lambda Expressions they are also often referred to as Closures, Function Literals, Anonymous Function or Single Abstract Methods (SAMs). Lambda expressions are being touted as the next step towards writing concurrent code for multi-core CPUs. <br /> In addition to new syntax for Lambda expressions, Project Lambda includes a major upgrade to the collections libraries. The aim of the collections upgrade is to make it much easier to write code that uses internal iterations to support a range of well-known functional programming idioms.<br>
</li><li>Additional language features from project coin (Java SE 7)<br>
</li><li>A new Time and Date API<br>
</li><li>A standard approach to <a href='http://www.oraclejavamagazine-digital.com/javamagazine_open/20130102#pg59'>caching</a>.</li></ol>

<hr />

<h1>The JVM</h1>

<a href='http://blog.jamesdbloom.com/'>This</a> blog covers details of the internals of the JVM & the languages that run on it.<br>
<br>
<hr />

<h1>Groovy</h1>

<a href='http://www.itexto.com.br/devkico/en/?p=39'>Why Groovy?</a>

<h2>Groovy Enums</h2>

<a href='http://mrhaki.blogspot.co.uk/2010/12/groovy-goodness-transform-string-into.html'>Coerce string into the appropriate Enum value at runtime</a>

<h2>Groovy Scripts</h2>

Groovy - method of getting access to a script (not located in your current dir) and using it in your script:<br>
<br>
<pre><code>common = new GroovyShell().parse(new File("/home/me/groovy/scripts/Common.groovy"))<br>
common.invokeMethod("methodName", ["args", "go in an", "array!"])<br>
</code></pre>

This is particularly useful you're using symlinks to locate your Groovy scripts and you need them to run reliably when executed from any directory.<br>
<br>
<h3>Groovyserv</h3>

<a href='http://kobo.github.io/groovyserv/quickstart.html'>Groovyserv</a> is an excellent process for <b>NIX, OSX & Windows which starts a JVM in the background which is constantly ticking over to run your Groovy scripts, this increases the speed on execution by up to 20 times!</b>

<h2>Groovy Strings (G-Strings)</h2>

<ul><li><a href='http://mrhaki.blogspot.co.uk/2009/09/groovy-goodness-padding-strings.html'>Padding Groovy Strings</a></li></ul>

<hr />

<h1>Jython</h1>

Jython is a port of the Python programming language to the JVM, a very comprehensive book can be found <a href='http://www.jython.org/jythonbook/en/1.0/index.html'>here</a> and the Java Magazine has published a series of articles on the topic, <a href='http://www.oraclejavamagazine-digital.com/javamagazine_open/20130304#pg67'>Part 1</a> & <a href='http://www.oraclejavamagazine-digital.com/javamagazine/20130506#pg83'>Part 2</a>.<br>
<br>
<hr />

<h1>Libraries & APIs</h1>

<a href='http://code.google.com/p/thesandbox/wiki/LibrariesAPIs'>http://code.google.com/p/thesandbox/wiki/LibrariesAPIs</a>

<hr />

<h1>Applications</h1>

<h2>Vim</h2>

<ul><li><a href='http://www.worldtimzone.com/res/vi.html'>Vi/Vim Cheatsheet</a>
</li><li><a href='http://vim.wikia.com/wiki/Search_and_replace'>Search &amp; Replace</a> with Vim<br>
</li><li><a href='http://vim.wikia.com/wiki/PuTTY_numeric_keypad_mappings'>PuTTY &amp; Vim - Using the Numpad</a></li></ul>

<hr />

<h1>General Programming Terminology</h1>

<h2>Syntactic Sugar</h2>

<blockquote>A phrase that's sometimes used to describe a language feature is "syntactic sugar". This means that the syntax form is redundant—it already exists in the language—but the syntactic sugar form is provided because it's easier for humans to work with.<br>
As a rule of thumb, a feature referred to as syntactic sugar is removed from the compiler’s representation of the program early on in the compilation process—it's said to have been "desugared" into the basic representation of the same feature.<br>
This makes syntactic sugar changes to a language easier to implement because they usually involve a relatively small amount of work, and only involve changes to the compiler (<code>javac</code> in the case of Java).</blockquote>

This is an excellent summary of Syntactic Sugar from the <a href='http://www.manning.com/evans/'>The Well-Grounded Java Developer</a> book by Ben Evans and Martijn Verburg.<br>
<br>
<hr />

<h1>Interview Questions</h1>

<h2>General approach to Programming Problem Solving</h2>

<ol><li>Make sure you understand the problem.<br>
</li><li>Once you understand the question, try an example.<br>
</li><li>Focus on the algorithm you will use to solve the problem. <b>Resist any temptation to begin coding before you figure out the exact solution!</b>
</li><li>After you’ve figured out your algorithm and how you will implement it, explain your solution to the interviewer.<br>
</li><li>While you code, it’s important to explain what you’re doing.<br>
</li><li>Ask questions when necessary.<br>
</li><li>After you’ve written the code for a problem, immediately verify that the code is correct by tracing through it with an example.<br>
</li><li>Make sure you check your code for all error and special cases, especially boundary conditions.</li></ol>

<h2>Java</h2>

<ul><li><a href='http://www.allapplabs.com/interview_questions/java_interview_questions.htm'>http://www.allapplabs.com/interview_questions/java_interview_questions.htm</a>
</li><li><a href='http://javarevisited.blogspot.co.uk/2011/09/swing-interview-questions-answers-in.html'>Java Swing Questions</a>
</li><li><a href='http://java-success.blogspot.co.uk/'>400+ Java Interview Questions &amp; Answers</a>
</li><li><a href='http://java-interview-material.blogspot.co.uk/'>Java Interview Material Blog</a>
</li><li><a href='http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=303&p_certName=SQ1Z0-851'>Oracle Java Certificate Questions</a> - more in the book of course!<br>
</li><li><a href='http://javarevisited.blogspot.sg/2014/02/top-30-java-phone-interview-questions.html'>30 Java Phone Interview Questions</a></li></ul>

<h2>Logic</h2>

<a href='http://imranontech.com/2007/01/10/why-logic-puzzles-make-good-interview-questions/'>Why logic puzzles make good interview questions</a>

<h2>Bit Twiddling Techniques</h2>

Bit twiddling crops up in interviews every so often so it's <a href='http://graphics.stanford.edu/~seander/bithacks.html'>nice to know about...</a><br />
Also see the Java operators summary <a href='http://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html'>page</a>.<br>
<br>
<h2>My Questions</h2>

<ul><li>Which application is the role for? Where does that fit within the business?<br>
</li><li>What are the main challenges faced by the application/team at the moment?<br>
</li><li>Are there any big projects or changes on the horizon for the firm?<br>
</li><li>What are the long term opportunities with the company?<br>
</li><li>How is career development managed? Are there regular training opportunities?<br>
</li><li>What skills and attributes are most needed to progress within the team / company?<br>
</li><li>Where does the company have offices? Where are the headquarters? What activities take place in each office?<br>
</li><li>Please could I have your business card?</li></ul>

<h2>Things I've been asked</h2>

<ul><li>What is a <a href='#Code_Reviews.md'>Code Review</a>?<br>
</li><li>What is my favourite programming language?<br>
</li><li>What is Inversion of Control and why is it useful?<br>
</li><li>Design a noughts and crosses board game? How would your design need to be changed to allow connect four to be played instead?<br>
</li><li>Design the Oyster card system for the London Underground?<br>
</li><li>Can you write code in Java or C++ to find the power set of a given set. For example if S={a,b} the power set is P={{},{a},{b},{a,b}}.<br>
</li><li>What is you favourite feature of Java? What does it do particularly well?<br>
</li><li>What would you change about Java given the chance.<br>
</li><li><a href='http://stackoverflow.com/questions/1801549/reverse-a-singly-linked-list'>How would you reverse a singly linked list?</a>
</li><li>How could you implement a simple spin lock in Java using an Atomic Integer?</li></ul>

<h2>The Systems I've Worked On</h2>

<b>See the Dropbox document, My Systems.docx</b>

<hr />

<h1>Java EE</h1>

<a href='http://docs.oracle.com/javaee/6/tutorial/doc/docinfo.html'>Jave EE 6 Tutorial</a>

<hr />

<h1>Web Services</h1>

Java Web Services can be thought of as an API on the network. The API itself is defined using the Web Services Description Language (WSDL), specifying what operations you can call on a web service and what the input and output types are, among other things. Messages are typically XML, formatted to comply with the Simple Object Access Protocol (SOAP) schema and these messages are typically sent over HTTP.<br>
<br>
<hr />

<h1>Middleware</h1>

<h2>Tibco</h2>

<a href='http://javarevisited.blogspot.co.uk/2011/05/tibco-tutorials-for-beginners.html'>Tibco Tutorial</a>

<h2>Sonic</h2>

<h2>JMS</h2>

The above Tibco Tutorial contains a page comparing RV & EMS...it turns out that EMS is just an implementation of JMS (Java Message Service). The JMS API supports two models:<br>
<br>
<ul><li>Point-to-point (Queues)<br>
</li><li>Publish and subscribe (Topics)</li></ul>

You need to be quite careful when choosing when to use a JMS Topic or a JMS Queue, despite what you might think you can actually have multiple subscribers on a single Queue however the effect will not be that every subscriber gets a copy of each message, but rather a round-robin/load-balanced effect where each subscriber handles a message. Topics on the other hand deliver a copy of each message to each active subscriber, if you absolutely need guarantees that each subscriber to a topic receives a copy of a message then your best bet is to create a queue for each subscriber and then bridge between the Topic each Queue, JMS providers like EMS allow you create bridges between Topics and Queues manually, provided they are on the same Broker. If you need to bridge between brokers (inter-broker) then you need to create a (bi-directional) route between the two brokers and ensure that the destinations you want to route between are both global.<br>
<br>
The <a href='http://en.wikipedia.org/wiki/Java_Message_Service'>JMS</a> page on Wikipedia has more information on terminology, operations and implementations.<br>
<br>
<hr />

<h1>XML</h1>

<a href='http://www.w3schools.com/xml/default.asp'>eXtensible Markup Language</a>

<h2>SAX & DOM</h2>

SAX - Event Driven, stack based parsing, akin to shift-reduce mechanism employed by bottom-up parsers. <a href='http://en.wikipedia.org/wiki/Simple_API_for_XML'>Wikipedia</a>

DOM - Tree Walking <a href='http://en.wikipedia.org/wiki/Document_Object_Model'>Wikipedia</a>

<a href='http://onjava.com/onjava/2002/06/26/xml.html'>SAX &amp; DOM, the Basics</a>

<a href='http://www.informit.com/library/library.aspx?b=STY_XML_21days'>Teach Yourself XML in 21 Days</a>

<hr />

<h1>JSON</h1>

<a href='http://www.json.org/'>JSON</a> stands for <b>J</b> ava <b>S</b> cript <b>O</b> bject <b>N</b> otation and was designed to be a lightweight data exchange format which is easy for humans to read. JSON is an excellent way to serialise objcets in a simple manner.<br>
<br>
Codehaus's <a href='http://jackson.codehaus.org/'>Jackson</a> is a very popular JSON Processor for Java.<br>
<br>
<hr />

<h1>YAML</h1>

[<a href='http://en.wikipedia.org/wiki/YAML'>http://en.wikipedia.org/wiki/YAML</a>
YAML Ain't Markup Language]<br>
<br>
<hr />

<h1>Simple Object Access Protocol (SOAP)</h1>

<a href='http://en.wikipedia.org/wiki/SOAP'>SOAP</a>, originally defined as Simple Object Access Protocol, is a protocol specification for exchanging structured information in the implementation of Web Services in computer networks. It relies on Extensible Markup Language (XML) for its message format, and usually relies on other Application Layer protocols, most notably Hypertext Transfer Protocol (HTTP) and Simple Mail Transfer Protocol (SMTP), for message negotiation and transmission.<br>
<br>
<hr />

<h1>Regular Expressions</h1>

<h2>General Information on RegEx</h2>

<ul><li><a href='http://www.regular-expressions.info/reference.html'>Reference</a>
</li><li><a href='http://fulmicoton.com/posts/multiregexp/'>Multi RegExp</a></li></ul>

<h2>In Java</h2>

<ul><li><a href='http://docs.oracle.com/javase/tutorial/essential/regex/'>RegEx Tutorial from Oracle</a> - Easily tested on the fly with the RegexTestHarness below:</li></ul>

<pre><code>import java.io.Console;<br>
import java.util.regex.Pattern;<br>
import java.util.regex.Matcher;<br>
<br>
public class RegexTestHarness {<br>
<br>
    public static void main(String[] args){<br>
        Console console = System.console();<br>
        if (console == null) {<br>
            System.err.println("No console.");<br>
            System.exit(1);<br>
        }<br>
        while (true) {<br>
<br>
            Pattern pattern = Pattern.compile(console.readLine("%nEnter your regex: "));<br>
<br>
            Matcher matcher = pattern.matcher(console.readLine("Enter input string to search: "));<br>
<br>
            boolean found = false;<br>
            while (matcher.find()) {<br>
                console.format("I found the text" +<br>
                    " \"%s\" starting at " +<br>
                    "index %d and ending at index %d.%n",<br>
                    matcher.group(),<br>
                    matcher.start(),<br>
                    matcher.end());<br>
                found = true;<br>
            }<br>
            if(!found){<br>
                console.format("No match found.%n");<br>
            }<br>
        }<br>
    }<br>
    <br>
}<br>
<br>
</code></pre>

<h2>Examples</h2>

My regex to match just the date stamp from a log file:<br>
<br>
<pre><code>\[[0-9]*/.*?\]<br>
</code></pre>

e.g. matches: <b>[04/08/13 06:00:05.711 INFO ]</b> Hello, this is a line from my log file<br>
<br>
<hr />

<h1>Cron</h1>

<a href='http://en.wikipedia.org/wiki/Cron'>Cron</a> is the time-based job scheduler for Unix. Using a flexible syntax you can define when a specific command should be executed, the syntax is outlined below:<br>
<pre><code>*    *    *    *    *  command to be executed<br>
┬    ┬    ┬    ┬    ┬<br>
│    │    │    │    └───── day of week (0 - 7) (0 or 7 are Sunday, or use names)<br>
│    │    │    └────────── month (1 - 12)<br>
│    │    └─────────────── day of month (1 - 31)<br>
│    └──────────────────── hour (0 - 23)<br>
└───────────────────────── min (0 - 59)<br>
</code></pre>

A sixth optional parameter for the year is not shown above.<br>
<br>
The <code>crontab</code> command can then be used to add <code>cron</code> jobs to a users cron table which ensures the jobs will be executed as desired.<br>
<br>
<hr />

<h1>Complexity (Big-O)</h1>

Big-O analysis is a form of run-time analysis that measures the efficiency of an algorithm in terms of the time it takes for the algorithm to run as a function of the input size. It's not a formal benchmark, just a simple way to classify algorithms by relative efficiency.<br>
<br>
<a href='http://pages.cs.wisc.edu/~vernon/cs367/notes/3.COMPLEXITY.html'>Complexity &amp; Big-O Notation</a>
<a href='http://stackoverflow.com/questions/2307283/what-does-olog-n-mean-exactly'>Good Explanation of O(log n) from Stackoverflow</a>

<hr />

<h1>Maths</h1>

<a href='http://oakroadsystems.com/math/loglaws.htm'>Logarithms</a>

<h1>Data Structures & Algorithms</h1>

A good alternative to <a href='http://en.wikipedia.org/'>Wikipedia</a> articles on data structures and algorithms is <a href='http://www.algolist.net/'>http://www.algolist.net/</a> which provides examples in Java & C++ along with explanations and analysis.<br>
<br>
<a href='http://so-i-think-i-created-my-first-online-project.googlecode.com/files/Data%20Structures%20and%20Algorithms%20in%20Java%20Fourth%20Edition.pdf?bcsi_scan_4c5c01dba4894524=0&bcsi_scan_filename=Data%20Structures%20and%20Algorithms%20in%20Java%20Fourth%20Edition.pdf'>Data Structures &amp; Algorithms in Java (4th Edition)</a>

<hr />

<h2>Data Structures</h2>

<h3>Trees</h3>

<h4>Depth</h4>
<b>Formal Definition:</b> Let <i>v</i> be a node of a tree <i>T</i>. The depth of <i>v</i> is the number of ancestors of <i>v</i>, excluding <i>v</i> itself. If <i>v</i> is the root, then the depth of <i>v</i> is 0.<br>
<b>Informal Definition:</b> How deep down the tree is the node you're concerned with?<br>
<br>
<h4>Height</h4>
<b>Formal Definition:</b> The height of a node <i>v</i> in a tree <i>T</i> is defined recursively:<br>
<ul><li>If <i>v</i> is an external node, then the height of <i>v</i> is 0<br>
</li><li>Otherwise, the height of <i>v</i> is one plus the maximum height of a child of <i>v</i>.<br>
<b>Informal Definition:</b> So basically how far is <i>v</i> away from it's further descendant which is a leaf (external node).</li></ul>

Adapted from <a href='http://en.wikipedia.org/wiki/Red_black_tree'>Red-Black Tree</a>: Both Red-Black tree (<a href='http://www.ece.uc.edu/~franco/C321/html/RedBlack/redblack.html'>demo</a>) and AVL trees (<a href='http://www.strille.net/works/media_technology_projects/avl-tree_2001/'>demo</a>) support O(log n) search, insertion, and removal. AVLs are more rigidly balanced than red–black trees, leading to slower insertion and removal but faster retrieval. This makes AVLs attractive for data structures that may be built once and loaded without reconstruction, such as language dictionaries (or program dictionaries, such as the opcodes of an assembler or interpreter).<br>
<br>
2-4 Trees (<a href='http://www.cs.unm.edu/~rlpm/499/ttft.html'>demo</a>) are fundamental to understanding red-black trees because the insertion and deletion operations on 2-4 trees are also equivalent to colour-flipping and rotations in red–black trees, despite this 2-4 trees are seldom used in practice. Try walking through the <a href='http://en.wikipedia.org/wiki/2-4_tree#Example'>example</a> from wikipedia of a 2-3-4 tree insertion.<br>
<br>
<b>Differentiating Red-Black Trees</b>

Although AVL trees and (2,4) trees have a number of nice properties, there are some dictionary applications for which they are not well suited. For instance, AVL trees may require many restructure operations (rotations) to be performed after a removal, and (2,4) trees may require many fusing or split operations to be performed after either an insertion or removal. The red-black tree data structure does not have these drawbacks, however, as it requires that only O(1) structural changes be made after an update in order to stay balanced.<br>
<br>
In Java <a href='http://docs.oracle.com/javase/6/docs/api/java/util/TreeMap.html'>TreeMap</a> is an example of a Red-Black tree.<br>
<br>
<h3>Heaps</h3>

The description from Wikipedia for the <a href='http://en.wikipedia.org/wiki/Heap_%28data_structure%29'>Heap</a> data structure is very clear on concise. Essentially, the structure is a binary tree where some form of ordering is used to determine where each node is placed, for instance if you were ordering percentages on a max-heap 100 would be at the top, 1 at the bottom. When you add a node to this type of structure you always fill the next level with empty places from the left to the right, once you add a value you compare it to it's parent and if ordering dictates that the new node should come before it's parent you swap them in the structure (and carry on until you don't need to swap or you reach the root). Similarly if you remove the root you then swap the most recently added item into the root's position and compare the new root with each of it's children, swapping with the most appropriate child e.g. the larger of the two in the case of a max-heap. Again you continue this process until you don't need to swap again or you reach a leaf.<br>
<br>
Heaps are used in Heap Sorts to achieve worst case performance of O(n log n).<br>
<br>
In Java the <a href='http://docs.oracle.com/javase/6/docs/api/java/util/PriorityQueue.html'>Priority Queue</a> is an example of a min-heap.<br>
<br>
<h3>Sets</h3>
A Set stores unique elements.<br>
<br>
<h3>Maps</h3>
In the conventional sense of the word a Map is a data structure which maps a key to a value, each key should map to exactly one value.<br>
<br>
<h4>LinkedHashMap</h4>
Hash table and linked list implementation of the Map interface, with predictable iteration order. This implementation differs from HashMap in that it maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, which is normally the order in which keys were inserted into the map (insertion-order). Note that insertion order is not affected if a key is re-inserted into the map. (A key k is reinserted into a map m if m.put(k, v) is invoked when m.containsKey(k) would return true immediately prior to the invocation.)<br>
<br>
There is also an equiv. for Sets called LinkedHashSet, which could be compared to TreeSet and HashSet.<br>
<br>
<h4>Multi Map</h4>
A multimap is a generalisation of a map or associative array abstract data type in which more than one values may be associated with and returned for a given key. Often multimap is implemented as a map with lists or sets as the map values. Both Apache Commons Collections & Google Collections offer MultiMap implementations.<br>
<br>
<h4>Concurrent Hash Map</h4>

Section 5.2.1 of Java Concurrency In Practice explains the internals of a ConcurrentHashMap at a high level, essentially lock striping is used to allow multiple readers to access the map concurrently and writers are managed on a lock per bucket basis reducing contention across the whole collection. The author(s) note that under normal circumstances there should be little or no overhead of using a ConcurrentHashMap where a Hashtable or synchronized Map was used before, it should be much more scalable as a collection.<br>
<br>
Section 5.6 of Java Concurrency In Practice details using a ConcurrentHashMap to build a result cache for repetitive computationally expensive operations.<br>
<br>
Section 4.3.4 of the Well Grounded Java Developer also explains the internals of a ConcurrentHashMap at a high level.<br>
<br>
<h3>Stacks</h3>

<h3>Lists</h3>

<h3>Queues</h3>
A Queue is a basic Collection in Java that offers 5 additional methods for interacting with the collection in a queue fashion:<br>
<pre><code>    boolean offer(E e); // inserts, if possible<br>
    E poll();           // retrieves and removes head of queue<br>
    E remove();         // like poll() but throws exception<br>
    E peek();           // retrieves head without removing<br>
    E element();        // like peek() but throws exception<br>
</code></pre>

<h4>Concurrent Linked Queue</h4>
<ul><li>Unbounded thread-safe Queue based on linked nodes<br>
</li><li>Elements ordered FIFO<br>
</li><li>Used when many threads share access to a queue<br>
</li><li>The size() method is not constant time (it have to count all elements each time)<br>
</li><li>can often be used as a thread-safe low-contention list</li></ul>

<h4>Priority Queue</h4>
<ul><li>Unbounded priority queue based on priority heap<br>
</li><li>Orders elements according to order specified at construction time (either natural order or via Comparable, or by Comparator)<br>
</li><li>Least element is at the head of the queue (this queue is not stable, thus when two elements are equals their insertion order is not maintained - you would have to provide this mechanism yourself)<br>
</li><li>A normal Priority Queue is not thread-safe, however a Priority Blocking Queue (see below) is.<br>
</li><li>If you need to see the items in the queue in reverse order (i.e. largest first) then just create a reverse comparator and use that for head building.</li></ul>

<h3>Blocking Queues</h3>

We also have the Blocking Queue interface which includes the <code>put()</code> and <code>take()</code> methods which block until space or elements become available (respectively). These types of queue are frequently used in Producer/Consumer scenarios.<br>
<br>
There are various Blocking Queue implementations:<br>
<ul><li><b>ArrayBockingQueue</b> - underlying container is an array<br>
</li><li><b>LinkedBlockingQueue</b> - Linked queues typically have higher throughput than array-based queues but less predictable performance in most concurrent applications.<br>
</li><li><b>DelayQueue</b> - Only delivers after some time-out has expired<br>
</li><li><b>PriorityBlockingQueue</b> - Returns by sort order, smallest elements first<br>
</li><li><b>SynchronousQueue</b> - Queues of size zero, used if you do not want asynchronicity</li></ul>

<h3>Deques</h3>
A Deque is a double‐ended queue that allows efficient insertion and removal from both the head and the tail. Implementations include ArrayDeque and LinkedBlockingDeque.<br>
<br>
Just as blocking queues lend themselves to the producer-consumer pattern, deques lend themselves to a related pattern called work stealing. A producer-consumer design has one shared work queue for all consumers; in a work stealing design, every consumer has its own deque. If a consumer exhausts the work in its own deque, it can steal work from the tail of someone else's deque. Work stealing can be more scalable than a traditional producer-consumer design because workers don't contend for a shared work queue; most of the time they access only their own deque, reducing contention. When a worker has to access another's queue, it does so from the tail rather than the head, further reducing contention.<br>
<br>
Deques are introduced in section 5.3.3 of Java Concurrency in Practice.<br>
<br>
<h3>LRU Cache</h3>

A Least Recently Used Cache is an extension of a LinkedHashMap. Usually, the LinkedHashMap orders elements by insertion order however we can change that to access order in the constructor. See Java Specialist Masters course (slide 640).<br>
<br>
Alternatively you can implement a cache with the LRU eviction policy with a map and a linked list. The map stores cached elements. The linked list keeps tracks of the least recently used cache elements. When a cache element is updated, it is removed from the list and added to the top of the list. The new elements are added to the top of the list as well. If the cache grows bigger than its maximum size, an element is removed from the bottom of the list and from the map. This way the least recently used elements are evicted first.<br>
<br>
<h2>Sorting</h2>

There are lots of different types of sorting algorithms, only some are covered here. A comparison of the space complexity of many of those featured can be found <a href='http://en.wikipedia.org/wiki/Timsort#Performance'>here</a>.<br>
<br>
<h3>Stable vs. Unstable Sorts</h3>
<b>Stable</b> sorting algorithms maintain the relative order of records with equal keys.<br>
<b>Unstable</b> sorting algorithms may change the relative order of records with equal keys, but stable sorting algorithms never do so.<br>
Unstable sorting algorithms can be specially implemented to be stable. One way of doing this is to artificially extend the key comparison, so that comparisons between two objects with otherwise equal keys are decided using the order of the entries in the original data order as a tie-breaker. Remembering this order, however, often involves an additional computational cost.<br>
<br>
<h3>Selection Sort - O(n<sup>2</sup>)</h3>
<b>Basic Idea</b>: Ascend the unsorted array until the find the smallest value in the list, swap that value with the value in the first spot, then search adjust the position you are attempting to fill and search again for the next smallest etc. continuing until you reach the end and everything is sorted.<br>
<br>
Selection sort is sometimes useful for small data sets where memory is limited.<br>
Worst Case Performance: O(n<sup>2</sup>)<br>
Best Case Performance: O(n<sup>2</sup>)<br>
Average Case Performance: O(n<sup>2</sup>)<br>
<br>
<h3>Insertion Sort - O(n<sup>2</sup>)</h3>
<b>Basic Idea</b>: Insertion sort is a simple sorting algorithm that is relatively efficient for small lists and mostly sorted lists, and often is used as part of more sophisticated algorithms. It works by taking elements from the list one by one and inserting them in their correct position into a new sorted list. In arrays, the new list and the remaining elements can share the array's space, but insertion is expensive, requiring shifting all following elements over by one.<br>
<br>
Generally speaking (and despite having same worst case complexity) an Insertion Sort is expected to perform better than a Selection Sort.<br>
<br>
Worst Case Performance: O(n<sup>2</sup>) comparisons, swaps<br>
Best Case Performance: O(n) comparisons, O(1) swaps<br>
Average Case Performance: О(n<sup>2</sup>) comparisons, swaps<br>
<br>
<a href='http://en.wikipedia.org/wiki/Insertion_sort#Comparisons_to_other_sorting_algorithms'>Comparisons</a>

<h3>Bubble Sort - O(n<sup>2</sup>)</h3>
<b>Basic Idea</b>: Bubble sort works by repeatedly stepping through the list to be sorted, comparing each pair of adjacent items and swapping them if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which indicates that the list is sorted.<br>
<br>
Worst Case Performance: O(n<sup>2</sup>)<br>
Best Case Performance: O(n)<br>
Average Case Performance: O(n<sup>2</sup>)<br>
<br>
<h3>Quick Sort - O(n log n) (although not guaranteed)</h3>
<b>Basic Idea</b>: Is similar to merge sort in that is it a divide and conquer algorithm. <a href='http://opendatastructures.org/versions/edition-0.1e/ods-java/11_1_Comparison_Based_Sorti.html#SECTION001412000000000000000'>link</a> The basic idea is to pick a pivot value and move elements around (by swapping them) so that they fall on the correct side of the pivot value.<br>
<br>
As the the Java Arrays API <a href='http://docs.oracle.com/javase/6/docs/api/java/util/Arrays.html#sort(int['>http://docs.oracle.com/javase/6/docs/api/java/util/Arrays.html#sort(int[</a>]) Java uses a tuned quicksort to perform it's sorting, the algorithm offers n*log(n) performance on many data sets that cause other quicksorts to degrade to quadratic performance. That sort I have seen it suggested elsewhere that it uses Merge Sort<br>
<br>
Worst Case Performance: O(n<sup>2</sup>) - although in practice this is very rare behaviour.<br>
Best Case Performance: O(n log n)<br>
Average Case Performance: O(n log n)<br>
NOTE: Quick Sorts space complexity is O(log n) where as Merge Sorts is O(n)<br>
<br>
<h3>Merge Sort - O(n log n) (guaranteed)</h3>
<b>Basic Idea</b>: This is a classic divide and conquer algorithm. Recursively halve your array until each item is on its own, then scan the first two items placing them in the correct order, complete this process with the next two items until each single item has been ordered as a pair (ignore odds), then take the first two pairs of sorted lists and scan both to create a order list of 4 items, keep going until the list of back together.<br>
<br>
Worst Case Performance: O(n log n)<br>
Best Case Performance: O(n log n)<br>
Average Case Performance: O(n log n)<br>
<br>
<h3>Timsort - O(n log n)</h3>
<b>Basic Idea</b>: Timsort is a hybrid sorting algorithm, derived from merge sort and insertion sort, designed to perform well on many kinds of real-world data. The algorithm finds subsets of the data that are already ordered, and uses the subsets to sort the data more efficiently. This is done by merging an identified subset, called a run, with existing runs until certain criteria are fulfilled.<br>
<br>
Although it originated as a search for the Python programming language, as of Java SE 7 Timsort will be used to Array in Java as well.<br>
<br>
Worst Case Performance: O(n log n)<br>
Best Case Performance: O(n)<br>
Average Case Performance: O(n log n)<br>
<br>
<h3>Hash Sort - O(n)</h3>
<b>Basic Idea</b>: Maintain an array of buckets of items which is at least 20-25% larger than your list of items (to reduce clashes) and perform the same calculation on each item to return a numerical representation  of that item such that you can index into the array with that.<br>
<br>
<b>Java Specialist Masters Course</b>
<ul><li>Traditionally (before Java 1.4), the HashMap would contain a sparse array holding pointers to the keys<br>
</li><li>The remainder of dividing hash code by the number of buckets in the sparse array would give the offset<br>
</li><li>When there was a clash, the equals() method was used to ascertain uniqueness (having lots of objects with the same hash code would result in a long list to iterate through)<br>
</li><li>It was important to have a prime number hash map size and to have fairly random distribution of hash codes</li></ul>

<ul><li>Since Java 1.4 the HashMap uses bit masking to put the keys into buckets (thus the size of HashMap is always a power of 2 [2,4,8,16,32,34,128,256,...] - if you size the HashMap differently (e.g. 101) it will automatically be resized to one of these values<br>
</li><li>A big disadvantage with bit masking is that it is far more sensitive to bit patterns than the remainder approach (which was ditched because it is quite an expensive operation)<br>
</li><li>The bits are then being mixed around with re-hashing</li></ul>

<h2>Searching</h2>
<h3>Linear Search - O(n)</h3>
<b>Basic Idea</b>: Just search through the list from the beginning until you find the item you're looking for. With a linear search it tends<b>not to make much difference if the list is sorted or not (although you could have a very clever sort designed to place the most frequently used items at the front of the list).</b>

Worst Case Performance: n<br>
Best Case Performance: 1<br>
Average Case Performance: n/2<br>
<br>
<h3>Binary Search - O(log n)</h3>
<b>Basic Idea</b>: Particularly applicable to the tree structures discussed above. The tree must already be sorted and balanced if you want to achieve O(log n) guaranteed, remember that this take time and power in the first place.<br>
<br>
Worst Case Performance: O(log n)<br>
Best Case Performance: 1<br>
Average Case Performance: ~O(log n)<br>
<br>
<h3>Hash Search - O(1)</h3>
<b>Basic Idea</b>: Index into the list using the hash code you calculate for your item, if there are clashes you might not achieve your constant time target however if you stick to only ever being ~75-80% full then you shouldn't be too far off.<br>
<br>
Hashes are not very efficient in terms of space because by definition you need some empty slots.<br>
<br>
<hr />

<h1>Unified Modelling Language (UML)</h1>

UML is a simple diagramming style which consists of boxes that represent classes and their relationships.<br>
<br>
<h2>Basics</h2>

Each box representing a class is subdivided into 3 sections, the first contains the class name and the package name (if any), the second part lists the class's variables and the bottom lists its methods.<br>
<br>
<ul><li><code>public</code> member variables and methods are prefixed with a <code>+</code>
</li><li><code>protected</code> member variables and methods are prefixed with a <code>-</code>
</li><li><code>private</code> member variables and methods are prefixed with a <code>#</code>
</li><li><code>abstract</code> classes and methods are <i>italicised</i>
</li><li><code>static</code> elements are <u>underlined</u>
</li><li>Methods and Variables can optionally have their types appended following a colon <code>:</code>, similarly parameter type information on methods can also be included</li></ul>

<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-13%20at%2021.24.58.png' />

<h2>Inheritance</h2>

Inheritance is represented using a solid line and a hollow triangular arrow:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-13%20at%2020.57.48.png' />

<h2>Interfaces</h2>

An interface looks much like inheritance, except that the arrow has a dotted line tail, note that the name <code>&lt;&lt;interface&gt;&gt;</code> is shown enclosed within double angle brackets:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-13%20at%2021.10.12.png' />

<h2>Composition</h2>

Much of the time, a useful representation of a class hierarchy must include how objects are contained in other objects. For example, a Company might include one Employee and one Person (perhaps a contractor):<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-13%20at%2021.15.05.png' />

The lines between classes show that there can be 0 to 1 instances of Person in Company and 0 to 1 instances of Employee in Company. If there can be many instances of a class inside another, such as the array of Employees then we represent that object composition as a single line with either an <b>or 0,</b> on it:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202013-02-13%20at%2021.21.13.png' />

<hr />

<h1>Design Patterns</h1>

The <a href='http://www.oodesign.com/'>oodesign.com</a> website goes into detail of many popular design patterns and principles.<br>
<br>
<h2>Singleton Pattern</h2>

The Singleton Pattern is a Creational Pattern.<br>
<br>
A singleton is simply a class that is instantiated exactly once, typically a system component that is intrinsically unique, such as the window manager or file system. The singleton itself must also provide a single point of access for those who depend on it.<br>
<br>
Item 3 of Effective Java 2<sup>nd</sup> Ed. for this one. The Java Specialists Masters course also details the static Holder approach.<br>
<br>
<h2>Observer Pattern</h2>

The Observer Pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependencies are notified and updated automatically.<br>
<br>
Java provides an inbuilt Observer pattern using the <code>Observable</code> class (which you extend as the Object to be observed) and the <code>Observer</code> interface which you implement if you want to be notified of changes in an <code>Observable</code>. Each <code>Observer</code> must be registered with the <code>Observable</code> is observes.<br>
<br>
<a href='http://www.ibm.com/developerworks/library/j-jtp07265/'>Thread Safe Listening</a>

<h2>Factory Method Pattern</h2>

The Factory Pattern (aka Virtual Constructor) is also a Creational Pattern, it defines an interface for creating an object, but let subclasses decide which class to instantiate. The Factory Method lets a class defer instantiations to subclasses.<br>
<br>
Example usage in Java would be the <code>Collection</code> interface extending the <code>Iterable</code> interface...the <code>LinkedList</code> class which implements <code>Collection</code> is responsible for creating a <code>LinkedListItr</code> from it's <code>Iterator iterator()</code> method. Note that the <code>LinkedListItr</code> implements the <code>Iterator</code> interface.<br>
<br>
<h2>Facade</h2>

The intent of the Facade pattern (a Structural Pattern) is to present a simple (unified) interface for interacting with a complex subsystem.<br>
<br>
Examples in Java:<br>
<ul><li>The <code>SwingUtilities</code> class is a facade and has methods such as <code>invokeLater()</code>.<br>
</li><li>The <code>Executors</code> class is also considered a facade for thread pool creation.</li></ul>

<h2>Decorator Pattern</h2>

The Decorator Patten (aka Wrapper or Filter) is a Structural Pattern which allows you to attach additional responsibilities to an object dynamically. Decorator provide a flexible alternative to subclassing for extending functionality.<br>
<br>
The Java Collections framework provides us with an example of this pattern, as does Item 16 of Effective Java 2<sup>nd</sup> Ed., both are quite similar. The Collections example is the <code>Collections.synchronizedXXX(XXX)</code> method which wraps the <code>List</code>, <code>Map</code> or <code>Set</code> passed in with a wrapper that synchronises access to the underlying collection.<br>
<br>
<h2>Visitor Pattern</h2>

The intent of the visitor pattern is to allow you to represent an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.<br>
<br>
The visitor pattern defines two interfaces, the first is the <i>Visitor</i> interface, implementations must define and implement a <code>visit</code> method accepting every class of object which implements the second interface <i>Visitable</i>. The <i>Visitable</i> merely offers an <code>accept</code> method which takes a <i>Visitor</i> implementation, the aim in then to invoke the correct overloading of the <code>visit</code> method in the <i>Visitor</i> by calling something like visitor.visit(this). You can easily add new visitors which do different things when an object of a specific type is visited, however if you implement a new <i>Visitable</i> class then you need to go back and change your <i>Visitor</i> interface and all of it's implementations. This is pretty difficult to understand without a diagram and some example code, both of which can be found <a href='http://www.oodesign.com/visitor-pattern-customers-report-java-sourcecode.html'>here</a>.<br>
<br>
<h2>Inversion of Control (IoC) & Dependency Injection (DI)</h2>

The main idea behind <i>IoC</i> is that instead of your application code being completely in control and deciding when to call objects to go out and do the required work you reverse the situation so that another thread decides when and which object/method of your code is called. The best analogy for this idea is the idea of a command like application vs. a GUI built using a GUI framework e.g. Swing...when implementing a GUI framework like Swing all you have to do is supply the user with a button or input mechanism and implement an event handler for that button, the GUI framework then does all the hard work of determining what has happened and then just calls your action listener to do the business. If the GUI framework didn't do all the hard work for you, you'd have to receive every single user action in the same way and then determine yourself which subroutine you should call based on their input (e.g. like a command line application).<br>
<br>
The general concept behind <i>DI</i> is called IoC, a class should not configure its dependencies but should be configured from outside (<a href='.md'>http://www.vogella.com/articles/DependencyInjection/article.html from here</a>), thus when you code is called (by whatever means) it already has all its dependencies configured so is not required to go out and locate them.<br>
<br>
One of the most common Dependency Injection frameworks is <a href='http://www.springsource.org/'>Spring</a>, although <a href='http://code.google.com/p/google-guice/'>Google's Guice</a> is also very popular. What these frameworks do is collect all the objects your application requires centrally which allows you to build each dependency level in turn. The are five main benefits to <i>DI</i>, these are summarised below:<br>
<br>
<table><thead><th> <b>Benefit</b> </th><th> <b>Description</b> </th><th> <b>Example</b> </th></thead><tbody>
<tr><td> Loose coupling </td><td> Your code is no longer tightly bound to creating the dependencies it needs. Combined with the technique of coding to interfaces, it can also mean that your code is no longer tightly bound to specific implementations of a dependency. </td><td> Instead of your HollywoodService object needing to create its own SpreadsheetAgentFinder, it can have it passed in to it. If you’re coding to interfaces, this means that the HollywoodService can have any type of AgentFinder passed to it. </td></tr>
<tr><td> Testability    </td><td> As an extension to loose coupling, there's a special use case worth mentioning. For the purposes of testing, you can inject a test double as a dependency. </td><td> You can inject a stub ticket pricing service that always returns the same price, as opposed to using the 'real' pricing service, which is external and not always available. </td></tr>
<tr><td> Greater cohesion </td><td> Your code is more focused on performing its task as opposed to dealing with loading and configuring dependencies. A side benefit to this is increased readability. </td><td> Your DAO (Data Access Object) is focused on executing queries and not on looking up JDBC driver details. </td></tr>
<tr><td> Reusable components </td><td> As an extension to loose coupling, your code can be utilized by a wider range of users who can provide their own specific implementations. </td><td> An enterprising software developer could sell you a LinkedIn agent finder. </td></tr>
<tr><td> Lighter code   </td><td> Your code no longer needs to pass dependencies between layers. Dependencies can instead be injected directly at the point they’re required. </td><td> Instead of passing down the JDBC driver details from a service class, you can directly inject the driver at the DAO where it’s really needed. </td></tr></tbody></table>

<h3>Evolution</h3>

The evolution from hard-code classes/objects to classes/objects built using <i>DI</i> can be traced as follows:<br>
<br>
<ol><li>Hard coding<br>
</li><li>The Factory Method, Abstract Factory or Service Locator Patterns<br>
</li><li>Dependency Injection</li></ol>

See chapter 3 of the Well Grounded Java developer for more information.<br>
<br>
If you're still struggling with the concepts the first paragraph of the <a href='http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/beans.html'>Spring IoC documentation</a> does a nice job of breaking things down:<br>
<br>
<blockquote>IoC is also known as dependency injection (DI). It is a process whereby objects define their dependencies, that is, the other objects they work with, only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse, hence the name Inversion of Control (IoC), of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes, or a mechanism such as the Service Locator pattern.</blockquote>

<hr />

<h1>Enterprise Integration/Design Patterns</h1>

<a href='http://www.eaipatterns.com/index.html'>http://www.eaipatterns.com/index.html</a>

<hr />

<h1>Process Methodologies</h1>

<h2>Scrum (Agile Development)</h2>

Scrum is an iterative and incremental agile software development method for managing software projects and product or application development. The original <a href='http://www.scrum.org/Scrum-Guides'>Scrum Guide</a> is available from the home of Scrum and is written by the developers and formalisers or the process. The are also a number of other related articles which are worth reading:<br>
<br>
<ul><li><a href='http://en.wikipedia.org/wiki/Scrum_%28development%29'>Scrum (development) on Wikipedia</a>
</li><li><a href='http://www.scrum.org/Resources/What-is-Scrum'>What is Scrum?</a>
</li><li><a href='http://www.agilemodeling.com/artifacts/userStory.htm'>About User Stories</a>
</li><li><a href='http://en.wikipedia.org/wiki/Planning_poker'>Time Estimation &amp; Planning</a></li></ul>

<h2>Code Reviews</h2>

What should you actually look for?<br>
<br>
<ol><li>Logical bugs<br>
</li><li>Stupid bugs<br>
</li><li>Unreadable code<br>
</li><li>Ugly code that should be refactored</li></ol>

There are may benefits to a code review, primarily in terms of the quality of code because they allow you to spot bugs before the code goes to production. I also like the fact that they prevent knowledge being silo-ed in a single team member, which is particularly beneficial in a large/expanding team or a new project - after all you never when you might be called to fix a problem in another developer's code. The Elastic Leadership blog says that <a href='http://5whys.com/blog/what-should-a-good-code-review-look-and-feel-like.html'>a good code review</a> should:<br>
<ul><li>Present learning opportunities at every turn, even down to the way each developer works e.g. What was the keyboard shortcut?<br>
</li><li>Increase code quality...obviously<br>
</li><li>Only be 5-15 mins long meaning that you can only cover a small part of the code base, this encourages commits to be modular compete (something which is sought after in agile development as in theory you code should always be ready be release if needs be)<br>
</li><li>Not use tools (like <a href='https://www.atlassian.com/software/crucible/overview'>Crucible</a>) to help you because using them misses the point of learning something from someone by interacting with them.</li></ul>

This blog also provides a step-by-step guide which is well worth a read...<a href='http://5whys.com/blog/step-4-start-doing-code-reviews-seriously.html'>here</a>. They sum up code reviews as follows:<br>
<br>
<blockquote>Code reviews are nothing short of a magical process of growing people, code quality and confidence at the same time.</blockquote>

<h2>Code Quality</h2>

<h3>Sonar</h3>

<a href='http://www.sonarsource.org/'>Sonar</a> is an open platform to manage code quality. As such, it covers the 7 axes of code quality:<br>
<ul><li>Architecture & Design<br>
</li><li>Duplications<br>
</li><li>Unit Tests<br>
</li><li>Complexity<br>
</li><li>Potential bugs<br>
</li><li>Coding rules<br>
</li><li>Comments</li></ul>

One Sonar plug in which is definitely worth investiating and installing is <a href='http://checkstyle.sourceforge.net/index.html#Related_Tools'>Checkstyle</a>

<hr />

<h1>The OSI Application Model</h1>

The <a href='http://en.wikipedia.org/wiki/OSI_model'>Open Systems Interconnection</a> (OSI) model is a model used standardise the functions of a communications system in terms of abstraction layers. Similar communication functions are grouped into logical layers. A layer serves the layer above it and is served by the layer below it.<br>
<br>
The 7 layers from top (nearest the user) to bottom are:<br>
<b>Application</b><br>
<b>Presentation</b><br>
<b>Session</b><br>
<b>Transport</b><br>
<b>Network</b><br>
<b>Data Link</b><br>
<b>Physical</b>

Below the are describe from bottom to top...<br>
<br>
<h2>Physical</h2>
The <b>Physical Layer</b> describes the physical properties of the various communications media; as well as the electrical properties and interpreation of the exchanged signals.<br />
Example: this layer defines the size of the Ethernet coaxial cable, the type of the BNC connector used, and the termination method. e.g. IEEE 802.11<br>
<br>
<h2>Data Link</h2>
The <b>Data Link Layer</b> describes the logical organisation of data bits transmitted on a particular medium.<br />
Example: this layer defines the framing, addressing and checksumming of Ethernet packets. e.g. Point-to-Point Protocol (PPP)<br>
<br>
<h2>Network</h2>
The <b>Network Layer</b> describes how a series of exchanges over various data links can deliver data between any two modes in a network.<br>
Example: this layer defines the addressing and routing structure of the Internet. e.g. IP (v4/v6)<br>
<br>
<h2>Transport</h2>
The <b>Transport Layer</b> describes the quality and nature of the data delivery.<br>
Example: this layer defines if and how retransmissions will be used to ensure data delivery. e.g. TCP/UDP<br>
<br>
<h2>Session</h2>
The <b>Session Layer</b> describes the organisation of data sequences larger than the packets handled by lower layers.<br>
Example: the layer describes how request and reply packets are paired in a remote procedure call. e.g. TSL/SSL<br>
<br>
<h2>Presentation</h2>
The <b>Presentation Layer</b> describes the syntax of data being transferred. <br>
Example: this layer describes how floating point numbers can be exchanged between hosts with different math formats. e.g. MIME<br>
<br>
<h2>Application</h2>
The <b>Application Layer</b> describes how real work actually gets done.<br>
Example: this layer would implement file system operations. e.g. HTTP, DHCP, NFS<br>
<br>
<hr />

<h1>Architectures (32-bit vs. 64-bit)</h1>

A 64-bit computer architecture generally has integer and addressing registers that are 64 bits wide, allowing direct support for 64-bit data types and addresses.<br>
<br>
<h2>64-bit Java</h2>

For Java specific 64-bit information see the <a href='http://www.oracle.com/technetwork/java/hotspotfaq-138619.html#64bit_description'>FAQ</a>

A compiled Java program can run on a 32- or 64-bit Java virtual machine without modification. The lengths and precision of all the built-in types are specified by the standard and are not dependent on the underlying architecture. Java programs that run on a 64-bit Java virtual machine have access to a larger address space (this is the primary advantage), this allows for a much larger Java heap size and an increased maximum number of Java Threads, which is needed for certain kinds of large or long-running applications.<br>
<br>
The additional overhead of having native pointers in the system which take up 8 bytes instead of 4 means that a small performance penalty is often observed when porting from 32-bit to 64-bit Java, however the the benefits of being able to address larger amounts of memory might make that it worth it. Any degradation will depend on the amount of pointer accessing your application performs.<br>
<br>
<h3>IMPORTANT</h3>

<b>What it is NOT</b>:  Many Java users and developers assume that a 64-bit implementation means that many of the built-in Java types are doubled in size from 32 to 64.  This is not true.  We did not increase the size of Java integers from 32 to 64 and since Java longs were already 64 bits wide, they didn't need updating.  Array indexes, which are defined in the Java Virtual Machine Specification, are not widened from 32 to 64.  We were extremely careful during the creation of the first 64-bit Java port to insure Java binary and API compatibility so all existing 100% pure Java programs would continue running just as they do under a 32-bit VM.<br>
<br>
<hr />

<h1>Databases</h1>

See <a href='https://code.google.com/p/thesandbox/wiki/Databases'>Databases</a>

<hr />

<h1><code>*</code>NIX - Gems for the Command Line</h1>

<ul><li><a href='http://stackoverflow.com/questions/13489398/delete-files-older-than-10-days-using-shell-script-in-unix'>Using find to delete files older than x days</a>
<pre><code>find . -mtime +10 -type f -delete<br>
</code></pre>
</li><li><a href='http://www.malloc.co/linux/how-to-print-with-color-in-linux-terminal/'>Controlling Linux Shell Text Colour</a> - This is an excellent way to give (Groovy) scripts an extra something.<br>
</li><li><a href='http://www.cyberciti.biz/faq/what-process-has-open-linux-port/'>Ports &amp; Processes</a>
This is a very useful article with lots of information, however more often than not you can get what you need from:<br>
<pre><code>netstat -tulpn<br>
</code></pre>
</li><li><a href='http://netcat.sourceforge.net/'>Netcat</a> is a godsend when attempting to investigate and diagnose firewall issues! Always remember that the direction of a firewall request is relative to the home or safe side of the network, therefore <b>inbound</b> requests come from the DMZ to the internal network and <b>outbound</b> requests leave the internal network destined for the DMZ.<br>
</li><li><a href='http://www.dedoimedo.com/computers/rsync-guide.html'>rsync</a></li></ul>

<h2>Bash</h2>

<h3>Scripts</h3>

<ul><li><a href='http://wiki.bash-hackers.org/scripting/basics'>Bash Hackers Scripting Basics</a></li></ul>

<hr />

<h1>Networking</h1>

<h2>Multicast Addressing</h2>

<a href='http://www.tcpipguide.com/free/t_IPMulticastAddressing.htm'>http://www.tcpipguide.com/free/t_IPMulticastAddressing.htm</a>

<hr />

<h1>Books</h1>

<a href='http://ofps.oreilly.com/titles/9781449323950/index.html'>Spring Data</a> - A book covering various Spring related topics including MongoDB, Neo4J & GemFire.<br>
<br>
<ul><li><a href='http://flylib.com/category/en/'>Fly Lib</a>
</li><li><a href='https://github.com/vhf/free-programming-books/blob/master/free-programming-books.md'>https://github.com/vhf/free-programming-books/blob/master/free-programming-books.md</a>
</li><li><a href='http://www.topfreebooks.org'>http://www.topfreebooks.org</a></li></ul>

<h1>Blogs</h1>

<a href='http://www.javarevisited.blogspot.co.uk/'>Javarevisited</a>

Blog about Java Program Tutorial Example How to, Unix Linux commands, Interview Questions, FIX Protocol, Tibco RV tutorials, Equities trading system, MySQL<br>
<br>
<a href='http://ebookfree4all.blogspot.co.uk/'>http://ebookfree4all.blogspot.co.uk/</a>

<a href='http://books4career.blogspot.co.uk/'>http://books4career.blogspot.co.uk/</a>

<a href='http://5whys.com/'>Elastic Leadership - Notes to a Software Team Leader</a>

<a href='http://agilewarrior.wordpress.com/'>Agile Warrior</a>

<a href='http://martinfowler.com/'>Martin Fowler of ThoughtWorks</a>

<a href='http://www.takipiblog.com'>Takipi Blog</a>
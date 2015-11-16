

# Build & Dependency Management #

  * [Ant](http://ant.apache.org/)
  * [Ivy](http://ant.apache.org/ivy/)
  * [Maven](http://maven.apache.org/)

# Caching #

  * [Ehcache](http://ehcache.org/)
  * GemFire Cache
GemFire is a Java based cache  tutorial [here](http://community.gemstone.com/display/gemfire/GemFire+Tutorial). GemFire is a distributed cache in which each distributed peer is connected to all other peers. A more comprehensive overview can be found [here](http://ofps.oreilly.com/titles/9781449323950/id2325011_09-gemfire.html). Under the GemFire Client/Server architecture each GemFire peer becomes a server which also listens to connections from GemFire clients on a separate port. A GemFire client only connects to a limited number of these cache servers (which it discovers via a locator) but because all the GemFire peers/servers are linked to each other the client has access to the entire data grid.

# Collections #

  * [Colt](http://acs.lbl.gov/software/colt/) (Old!)
  * [Guava](http://code.google.com/p/guava-libraries/)
  * [Trove](http://trove.starlight-systems.com/)

# Command Line Tools (Windows) #

  * [Cygwin](http://www.cygwin.com/) (paired with [Cygwin/X](http://x.cygwin.com/) for X11 goodies)
  * [Hidden Start](http://www.ntwind.com/software/hstart.html) - allows you to run console applications and batch files without any window in the background.
  * [PsTools](http://technet.microsoft.com/en-gb/sysinternals/bb896649.aspx)
  * [Windows PowerShell](http://www.microsoft.com/powershell)

# Complex Event Stream Processing #

  * [Esper](http://esper.codehaus.org/)
> > Esper is an event stream processing (ESP) and event correlation engine (CEP) written in Java. Basically instead of working as a database where you put stuff in to later poll it using SQL queries, Esper works as real time engine that triggers actions when event conditions occur among event streams. A tailored Event Processing Language (EPL) allows registering queries in the engine, using Java objects (POJO, JavaBean) to represent events. A listener class - which is basically also a POJO - will then be called by the engine when the EPL condition is matched as events come in. The EPL allows expressing complex matching conditions that include temporal windows, and join different event streams, as well as filter and sort them.
> > A simple example could be to compute the average stock price of the BEA tick on a sliding window of 30 seconds. Given a StockTick event bean with a price and symbol property and the EPL "select avg(price) from StockTick.win:time(30 sec) where symbol='IBM'", a POJO would get notified as tick come in - and in real world millions of ticks can come in - so there's no way to store them all to later query them using a classical database architecture. Statements can be much more complex, and also be combined together with "followed by" conditions.

# Cryptography #

  * [Bouncy Castle](http://www.bouncycastle.org/)

# Databases #

  * [HSQLDB](http://hsqldb.org/)
  * [Berkeley DB JE](http://www.oracle.com/technetwork/database/berkeleydb/overview/index-093405.html)

Also see [NoSQL](http://code.google.com/p/thesandbox/wiki/LibrariesAPIs#NoSQL)

# Date/Time #

  * [JodaTime](http://joda-time.sourceforge.net/)

Why do we need Joda-Time? [Because](http://www.codefutures.com/simpledateformat-and-thread-safety/)!

## Job Scheduling ##

  * [AutoSys](http://supportconnectw.ca.com/public/autosys/infodocs/autosys_cheatsheet.asp)
  * [Quartz](http://www.quartz-scheduler.org/)

# Design Patterns #

[JPatterns](http://www.jpatterns.org/) - Annotations for clearly specifying design patterns in Java.

# Enterprise Integration #

## Camel ##

[Camel](http://camel.apache.org/)<br>
For a list of all the EIPs that Camel supports, see <a href='http://camel.apache.org/enterprise-integration-patterns.html'>here</a>.<br>
<br>
<h3>Components</h3>

<a href='http://camel.apache.org/jms.html'>JMS Component for Camel</a>

<a href='http://camel.apache.org/quickfix.html'>QuickFixJ</a>

<a href='http://camel.apache.org/restlet.html'>Restlet</a>
<ul><li><a href='http://www.javacodegeeks.com/2013/03/rest-with-apache-camel.html'>http://www.javacodegeeks.com/2013/03/rest-with-apache-camel.html</a>
</li><li><a href='http://www.javacodegeeks.com/2012/05/rest-endpoint-for-integration-using.html'>http://www.javacodegeeks.com/2012/05/rest-endpoint-for-integration-using.html</a></li></ul>

<h1>The FIX Protocol</h1>

The <b>F</b> inancial <b>I</b> nformation E <b>x</b> change Protocol. See <a href='http://javarevisited.blogspot.co.uk/2011/04/fix-protocol-tutorial-for-beginners.html'>here</a> for a series of tutorial on the FIX Protocol.<br>
<br>
<h2>QuickFixJ</h2>

<a href='http://www.quickfixj.org/'>http://www.quickfixj.org/</a>

<h2>Tools & References</h2>

<ul><li><a href='http://www.btobits.com/fixopaedia/index.html'>B2Bits</a>
</li><li><a href='http://www.critchley.biz/js/FIXProtocol.html'>FIX Protocol Translator</a>
</li><li><a href='http://www.fixionary.com/'>Fixionary</a>
</li><li><a href='http://www.fixprotocol.org/specifications/fix4.4fiximate/index.html'>Fiximate for FIX 4.4</a></li></ul>

The FIX Protocol has gradually evolved over the years however not all organisations have managed to upgrade legacy systems to cope with the latest specs. the most common specs. I've found in use are 4.4 and 4.2, 4.2 in particular can be very confusing when it comes to execution reports due in part to the user of ExecTransType<a href='20.md'>20</a> which was later streamlined semantically into ExecType<a href='150.md'>150</a>. A good resource to check examples and behaviours is <a href='http://www.onixs.biz/fix-dictionary/4.2/app_d.html'>Appendix D of the Specification</a> which lists Order State Change Matrices.<br>
<br>
<h1>Google</h1>

<h2>Go Lang</h2>

<a href='http://golang.org'>Go</a> is a complied garbage collected language which is a bit like a cross between C & Java, while still fairly new it is rapidly gaining traction.<br>
<br>
<h2>Protobufs</h2>

<a href='http://code.google.com/p/protobuf/'>Why use anything else?</a>

<h1>Groovy</h1>

<h2>Groovyserv</h2>

<ul><li><a href='http://kobo.github.io/groovyserv/quickstart.html'>Groovyserv</a>
</li><li><a href='http://groovy.github.io/gmaven/groovy-maven-plugin/index.html'>The Groovy Maven Plugin, this allows you to run groovy scripts inside you build</a> e.g. to generate sources</li></ul>

<h1>HTML</h1>

<h2>CSS</h2>

<ul><li><a href='http://www.htmlcodetutorial.com/character_famsupp_208.html'>Setting the font of a whole page</a>
</li><li><a href='http://en.wikipedia.org/wiki/Font_family_%28HTML%29'>HTML Font families</a></li></ul>

<h1>IO</h1>

<ul><li><a href='http://commons.apache.org/proper/commons-io/'>Commons IO</a></li></ul>

<h1>Integrated Development Environments</h1>

<h2>IntelliJ IDEA</h2>

<ul><li><a href='http://www.jetbrains.com/idea/'>IDEA</a> - Information on modifying you environment can be found <a href='http://devnet.jetbrains.com/docs/DOC-181'>here</a>.</li></ul>

<h1>Java Decompilation</h1>

<ul><li><a href='http://www.varaneckas.com/jad/'>JAD</a>
</li><li><a href='http://java.decompiler.free.fr/'>JD - Java Decompiler</a></li></ul>

<h1>JGroups</h1>

<a href='http://www.jgroups.org/overview.html'>JGroups</a> is a Java implementation of reliable multicast.<br>
<br>
<h1>JSON</h1>

<ul><li><a href='http://code.google.com/p/google-gson/'>GSON from Google</a>
</li><li><a href='http://jackson.codehaus.org/'>http://jackson.codehaus.org/</a> a <a href='http://www.json.org/'>JSON</a> Processor</li></ul>

<h1>Middleware</h1>

<h2>JMS</h2>

<a href='http://jpalace.org/docs/tutorials/spring/jms.html'>JMS with Spring Tutorial</a>

<ul><li><a href='http://activemq.apache.org/'>Apache ActiveMQ</a>
</li><li><a href='http://www.tibco.co.uk/products/automation/messaging/enterprise-messaging/enterprise-message-service/default.jsp'>Tibco EMS</a>
</li><li><a href='http://www-01.ibm.com/software/integration/wmq/'>IBM WebSphere MQ</a></li></ul>

<h3>Tooling (Performance & monitoring)</h3>

<ul><li><a href='http://www.hermesjms.com/confluence/display/HJMS/Home'>HermesJMS</a> - used to monitor and manipulate JMS brokers/queues/topics etc. See <a href='http://tibcoadmin.com/tibco/ems/configuring-hermesjms-for-tibco-ems/'>here</a> for a configuration guide for Tibco EMS.<br>
</li><li><a href='https://h20392.www2.hp.com/portal/swdepot/displayProductInfo.do?productNumber=HPJMETER'>HP JMeter</a>
</li><li><a href='http://jmeter.apache.org/'>Apache JMeter</a>
</li><li><a href='http://www.yourkit.com/'>YourKit Java Profiler</a> (they also offer a .NET equiv.)<br>
</li><li>For more Java profiling tools see the list of <a href='http://www.javaperformancetuning.com/resources.shtml#ProfilingToolsFree'>free</a> and <a href='http://www.javaperformancetuning.com/resources.shtml#ProfilingToolsNotFree'>commercial</a> profilers on the <a href='http://www.javaperformancetuning.com/'>Java Performance Tuning</a>
</li><li><a href='http://visualvm.java.net/'>VisualVM</a> - used to connect to you java application over JMX and part of more recent JDK releases, has a wide variety of plugins e.g. <a href='https://kenai.com/projects/btrace/pages/Home'>btrace</a> or <a href='http://visualvm.java.net/saplugin.html'>sapluglin</a> from <a href='http://visualvm.java.net/plugins.html'>http://visualvm.java.net/plugins.html</a> & <a href='http://java.net/projects/tda/downloads/directory/visualvm'>here</a>.<br>
</li><li><a href='http://architects.dzone.com/articles/how-analyze-java-thread-dumps'>How to Analyse Java Thread Dumps</a></li></ul>

<h2>Tibco</h2>

<ul><li><a href='http://www.tibco.co.uk/products/automation/messaging/enterprise-messaging/enterprise-message-service/default.jsp'>Tibco EMS</a>
</li><li><a href='http://www.tibco.co.uk/products/automation/messaging/low-latency/rendezvous/default.jsp'>Tibco Rendezvous</a></li></ul>

<h1>Monitoring</h1>

<ul><li><a href='http://code.google.com/p/grep4j/'>Distributed log file monitoring</a></li></ul>

<h1>NoSQL</h1>

<ul><li><a href='http://cassandra.apache.org/'>Apache Cassandra</a> - Cassandra is similar in to <a href='http://code.google.com/p/thesandbox/wiki/LibrariesAPIs#Caching'>GemFire</a> in some senses, you run a distributed platform that manages replication itself, I <b>think</b> !GemFIre is ACID however where as Cassandra is not.<br>
</li><li><a href='http://www.mongodb.org/'>MongoDB</a>
</li><li>GemFire (also see caching section)<br>
</li><li><a href='http://www.neo4j.org/learn'>Neo4j</a> - a graph based NoSQL database<br>
</li><li><a href='http://redis.io/'>Redis</a></li></ul>

<h1>Open JDK 7 for OS X</h1>

<a href='http://code.google.com/p/openjdk-osx-build/'>openjdk-osx-build</a>

<h1>Object Relational Mapping (ORM)</h1>

<ul><li><a href='http://www.hibernate.org/'>Hibernate</a>
</li><li><a href='http://en.wikipedia.org/wiki/List_of_object-relational_mapping_software#Java'>List of Alternatives</a>
</li><li><a href='http://mybatis.github.io/mybatis-3/'>Mybatis</a> - sits between a full ORM & a general Database layer.</li></ul>

<h1>Reviews</h1>

<ul><li><a href='http://www.atlassian.com/software/crucible/overview'>Crucible from Atlassian</a>
</li><li><a href='http://phabricator.org/'>Phabricator from Facebook</a></li></ul>

<h1>Screenshot Utilities</h1>

<ul><li><a href='http://getgreenshot.org/'>Greenshot</a> (this is only really useful pre-Windows 7)</li></ul>

<h1>SSH</h1>

<ul><li><a href='http://www.jcraft.com/jsch/'>Java Secure Channel by JCraft</a> - there is also a side project to provide JavaDocs <a href='http://epaul.github.io/jsch-documentation/javadoc/'>here</a>.</li></ul>

<h1>Static Analysis</h1>

<ul><li><a href='http://findbugs.sourceforge.net/'>Find Bugs</a>
</li><li><a href='http://www.sonarsource.org/'>Sonar</a></li></ul>

<h1>Testing</h1>

<h2>Mocking</h2>

<ul><li><a href='http://code.google.com/p/mockito/'>Mockito</a> - Intro <a href='http://gojko.net/2009/10/23/mockito-in-six-easy-examples/'>1</a>, <a href='http://refcardz.dzone.com/refcardz/mockito'>2</a>
</li><li><a href='http://code.google.com/p/powermock/'>PowerMock</a> - Used to Mock Statics <a href='http://code.google.com/p/powermock/wiki/MockStatic'>1</a> or <a href='http://java.dzone.com/articles/using-powermock-mock-static'>2</a> and <a href='http://powermock.googlecode.com/files/PowerMockAtOredev.pdf'>Mock the un-testable</a> with it's <a href='http://metlos.wordpress.com/2012/09/14/the-dark-powers-of-powermock/'>Dark Powers</a></li></ul>

<h2>Surefire</h2>

<a href='http://maven.apache.org/surefire/maven-surefire-plugin/'>Surefire</a> is a Maven plugin which is in charge of running tests, you can configure it in a variety of different ways including how it forms and runs your tests, which is very useful if you're running out of PermGen/Heap during testing. More configuration details on that <a href='http://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html'>here</a>.<br>
<br>
<h2>Test/Code Coverage</h2>

<ul><li><a href='http://www.atlassian.com/software/clover/overview'>Clover</a> - Java Code Coverage<br>
</li><li><a href='http://mojo.codehaus.org/cobertura-maven-plugin/index.html'>Cobertura</a> - via the Mojo plugin for Maven<br>
</li><li><a href='http://www.eclemma.org/jacoco/'>JaCoCo</a> - Java Code Coverage (has support for Java 1.7, default coverage engine in Sonar)</li></ul>

<h1>XA</h1>

<b>Note:</b> The whole point of XA is that transactions are global (as opposed to local) which means you can tie actions/events together to make everything atomic. So essentially <i>global</i> transactions enable you to work with multiple transactional resources (e.g. relational databases and message queues) at the same time. A <i>local</i> transaction on the other hand is resource specific, e.g. just a JMS broker or a database.<br>
<br>
The Java Transaction API (<a href='http://en.wikipedia.org/wiki/Java_Transaction_API'>JTA</a>) is JavaEE's implementation of the <a href='http://en.wikipedia.org/wiki/X/Open_XA'>XA</a> standard protocol. JTA is part of the JavaEE specification, which means that any JavaEE-compliant application server must provide JTA support (some lightweight alternatives like Apache Tomcat do not provide it). Using JTA outside a JAVA EE server takes some work to set up because you have to find and use a JTA transaction manager, such as one of these:<br>
<br>
<ul><li><a href='http://www.atomikos.com/'>Atomikos</a>
</li><li><a href='http://jotm.ow2.org/xwiki/bin/view/Main/WebHome'>JOTM</a>
</li><li><a href='http://www.bitronix.be'>Bitronix</a></li></ul>

<h2>Transaction Propagation</h2>

Transaction propagation means that if you have two methods which are both transactional and one calls the other, then method being called can simply use use the existing transaction from the first method. Propagation is defined simply as the spreading of something. <a href='http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/transaction.html'>This</a> page can help.<br>
<br>
<h1>UML</h1>

Generating UML diagrams from Java source code<br>
<ul><li><a href='http://www.umlgraph.org/index.html'>http://www.umlgraph.org/index.html</a></li></ul>

<h1>Version Control</h1>

<ul><li><a href='http://subversion.tigris.org/'>SubVersion</a>
</li><li><a href='http://tortoisesvn.net/'>TortoiseSVN</a>
</li><li><a href='http://git-scm.com/'>Git</a>
</li><li><a href='https://github.com/FredrikNoren/ungit'>ungit</a> - <a href='https://github.com/joyjit/git-jira-hook'>A hook into JIRA for git</a></li></ul>

<h1>Web</h1>

<h2>REST</h2>

<ul><li><a href='http://restlet.org/'>Restlet</a></li></ul>

<h1>XML</h1>

e <b>X</b> tensible <b>M</b> arkup <b>L</b> anguage<br>
<br>
<h2>JAXB (Java API for XML Binding)</h2>

Manipulating XML Pojos <a href='http://javarevisited.blogspot.co.uk/2013/01/jaxb-xml-binding-tutorial-marshalling-unmarshalling-java-object-xml.html'>Tutorial</a>

<h1>YAML</h1>

<ul><li><a href='http://code.google.com/p/snakeyaml/'>SnakeYAML</a>
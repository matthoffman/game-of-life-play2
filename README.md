Conway's Game of Life
=====================

This is a quick sample project implementing [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) 
on top of the [Play 2 "mini" framework](https://github.com/typesafehub/play2-mini). It is client-server: the gameboard
is all HTML and uses JQuery to communicate to a stateless REST server that contains the logic. 

The HTML template is <a href="http://twitter.github.com/bootstrap/">Twitter's Bootstrap</a> project.



How to run this sample
======================

* install sbt 0.11.2  if you do not have it already. You can get it from here: https://github.com/harrah/xsbt/wiki/Getting-Started-Setup

* execute `sbt` and then `compile` to build the project

* execute `sbt` and then 'run' to run the built-in development server

* execute `sbt assembly` to generate a jar distribution 



A few interesting things about this implementation
--------------------------------------------------


The main app is written in Java, the tests are written in Scala.
The app is in Java because:

1. it's more universally understandable, and may be interesting for others.
2. I was kind of curious how Play2 (or at least Play2 Mini) worked with Java, being a natively Scala-based framework.

The tests are in Scala because:

1. Scala is more fun to write
2. It is quicker to write
3. With ScalaTest, it can be done in a way that is at least as readable, and quite possibly more readable, than Java.
This is not always the case for Scala: It's easy to write Scala code that looks like Perl++.
In general, I think anything that makes tests quicker and more fun is A Good Thing, as long as it's still readable.


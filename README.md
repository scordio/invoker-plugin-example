# Testing multiple JDK versions via the Maven Invoker Plugin

[![Github CI status](https://github.com/scordio/invoker-plugin-example/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/scordio/invoker-plugin-example/actions/workflows/main.yml?query=branch%3Amain)

This project demonstrates the usage of the [Maven Invoker Plugin](https://maven.apache.org/plugins/maven-invoker-plugin/)
for tests targeting different JDK versions.

On purpose, this Maven module does not contain any productive or test code, and the only relevant content of the POM is the configuration of the `maven-invoker-plugin`.

## Test Structure

Following the directory layout recommended by the invoker plugin documentation, three integration test groups are defined under the directory `src/it`:
 * `src/it/jdk-8` for tests targeting the JDK 8.
 * `src/it/jdk-11` for tests targeting the JDK 11.
 * `src/it/jdk-16` for tests targeting the JDK 16.

Each group has only one test class with one test method, that uses APIs available only in the corresponding Java versions. Specifically:
* [`Jdk8Test`](src/it/jdk-8/src/test/java/maven/invoker/jdk8/Jdk8Test.java) uses [`LocalDate.now()`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#now--), introduced with Java 8.
* [`Jdk11Test`](src/it/jdk-11/src/test/java/maven/invoker/jdk11/Jdk11Test.java) uses [`String.isBlank()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#isBlank()), introduced with Java 11.
* [`Jdk16Test`](src/it/jdk-16/src/test/java/maven/invoker/jdk16/Jdk16Test.java) uses [`String.stripIndent()`](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/String.html#stripIndent()), introduced with Java 15.

## Test Compilation

Each test group is compiled against the corresponding JDK version, controlled by the [`maven.compiler.release`](https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html#release) property defined in the POM of the group.

## Test Execution

Usually, a Maven project runs integration tests via the `verify` goal.

This example follows the same pattern, triggering the [`install`](https://maven.apache.org/plugins/maven-invoker-plugin/install-mojo.html) and [`run`](https://maven.apache.org/plugins/maven-invoker-plugin/run-mojo.html) goals of the invoker plugin which are bound to the `pre-integration-test` and `integration-test` lifecycle phases, respectively. 

Each test group runs on all the compatible Java versions, i.e:
* JDK 8 tests run on JDK 8, JDK 11 and JDK 16.
* JDK 11 tests run on JDK 11 and JDK 16.
* JDK 16 tests run on JDK 16 only.

This is achieved by creating a test JAR for each group that is shared, which is then used as a dependency together with the [`dependenciesToScan`](https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html#dependenciesToScan) property of the Maven Surefire plugin.

Test JARs must be created before they can be used as dependencies. To achieve that, the test group execution order is controlled with the [`invoker.ordinal`](https://maven.apache.org/plugins/maven-invoker-plugin/run-mojo.html#invokerPropertiesFile) property, having the highest value for the `jdk-8` group and the lowest value for the `jdk-16` group.

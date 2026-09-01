# Maven and Java Run Commands

This document explains how to build and run a Java project using Maven, which is the standard approach for Selenium automation projects.

## 1. Why Maven matters

### What it is
Maven is a build tool used to:
- download dependencies
- compile Java code
- run tests
- package the project

### Why it matters in Selenium
A Selenium Java project depends on libraries like Selenium, JUnit, Apache POI, and other tools. Maven automatically resolves and downloads them from a central repository.

## 2. Check if Java and Maven are installed

### When to use
Run this at the start of any Java project setup.

```powershell
java -version
mvn -v
```

### Why it matters
If Java or Maven is missing, the project cannot compile or run.

## 3. Set environment variables in Windows PowerShell

### When to use
Run this when Java or Maven is installed but not currently on the PATH.

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

$env:MAVEN_HOME = "C:\Program Files\apache-maven-3.9.16"
$env:PATH = "$env:MAVEN_HOME\bin;$env:PATH"
```

### Why it matters
Without these variables configured, the shell cannot find the Java and Maven commands.

## 4. Compile the project

### When to use
Use this to check whether the project builds successfully without running tests.

```powershell
cd "C:\Users\varsh\OneDrive\Desktop\manojProjects\selenium-automation-framework"
mvn clean compile
```

### Why it matters
Compilation verifies that your Java source and imports are valid.

## 5. Run a Java class with Maven

### When to use
Use this when your class has a `main` method and you want to run it directly.

```powershell
mvn exec:java "-Dexec.mainClass=com.example.actions.ManualDragDropDemo"
```

### Why this is the best approach
Maven includes the dependency classpath automatically. This avoids errors such as `NoClassDefFoundError` for Selenium classes.

## 6. Run the test suite

### When to use
Use this to execute test cases in the project.

```powershell
mvn test
```

### Why it matters
This is the standard command for running automated test validation.

## 7. Run a specific test class

### When to use
Use this if you want to run only one test at a time.

```powershell
mvn test -Dtest=MyTest
```

### Why it matters
This is useful when debugging one test or validating one feature quickly.

## 8. Run the compiled class directly with Java

### When to use
This is only useful if you already have the compiled classes and classpath available.

```powershell
java -cp "target/classes;target/dependency/*" com.example.actions.ManualDragDropDemo
```

### Why this is riskier
If dependencies are missing, Java throws class-not-found exceptions.

## 9. Common Maven lifecycle commands

```powershell
mvn clean
mvn compile
mvn test
mvn package
mvn install
```

### Meaning of each
- `clean`: delete previous build output
- `compile`: compile Java source code
- `test`: compile and run tests
- `package`: create a JAR or package
- `install`: place the artifact into the local Maven repo

## 10. Important real-world notes

- This project uses Java 17
- Maven downloads project dependencies automatically
- If a class fails with `NoClassDefFoundError`, usually the runtime classpath is missing dependencies
- Prefer `mvn exec:java` or `mvn test` over direct `java` execution in Selenium projects

## 11. Final takeaway

Maven is essential for Java-based Selenium work because it handles build, dependency resolution, and test execution. For interviews, it is important to explain not only the command syntax, but also the purpose of each lifecycle stage and why dependency management matters.

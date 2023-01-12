### creat jar file
```
jar -cvfm LifeTimeClock.jar manifest.mf *.class
```

### create mave project
```
mvn archetype:generate \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false \
  -DgroupId=com.chronus \
  -DartifactId=MvnJava
```

### build project
 ```
 mvn install (-e -X)
 ```

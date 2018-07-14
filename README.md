# CodeAssignment
CodeAssignment
1. The application can be run as Spring Boot application from IDE like Eclipse or STS or from the project workspace root by running mvn spring-boot:run
2. Used Spring Boot and Spring Dependency Injection concepts
3. Leveraged Spring Boot logback.xml for logging
4. Used Custom Exception
5. Written scalable code to process multiple XML or multiple CSV records
6. For convenience, input files are placed in maven resources folder itself to load as class path resources
7. Output error report files will be written to Operating System specific temp folder. At the same time, it is OS agnostic. 
   [For Windows - run %temp% from Windows + R prompt to navigate to temp folder
    For Unix/Linux - cd /tmp to navigate to temp folder]
8. Though the packaging of application is jar, it cannot be run as a jar at the moment as there are known limitations in an external jar picking the class path resource  	files [which in fact are files inside an archive - JAR].
9. Added auditing logs - To name a few, to log total files/processed files/total records/errored records/time taken to process a file
10. 

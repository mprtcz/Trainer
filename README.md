# Trainer
Java application which allows a user to keep track of any performed activity, storing all data in a SQL database. 
Application performs statistical analysis and presents results in two graphs. 

Application's UI with inserted data:

![picture](https://sc-cdn.scaleengine.net/i/7028f4e81b971b84f91b346bac0971f01.png)

The application stores records of user's activity in a database. Activities are inserted via middle panel `Add new record`, which allows to type activity's name and custom number representing for example repetitions of performed exercise:

![picture](https://sc-cdn.scaleengine.net/i/85f2dca44ab13e9445b93fa9c7ed78191.png)


The list of activities' names which have been already inserted in the database are stored in the list `Exercise names` at the left side of the window. 

![picture](https://sc-cdn.scaleengine.net/i/0898e07f7b5885e5b3a5fb4483cd5a4f2.png)


After clicking on the name it loads to the middle panel of the application.

History of user's activities is displayed on right side list, `Exercise History` which shows activity's name, included number as well as date and time of record's insertion in the database:

![picture](https://sc-cdn.scaleengine.net/i/7fbf7e5a7227336b979f4c94f20cef8c1.png)


The application's statistical analysis of stored data is displayed as two diagrams, Pie Chart representing the percentage of different activities by numbers of reps included in those activities: 

![picture](https://sc-cdn.scaleengine.net/i/ecc2fac67223106d3af8cb451bb582a51.png)

and a bar chart displaying activities in different colors, number of repetitions on Y scale and time of record's insertion on X scale.

![picture](https://sc-cdn.scaleengine.net/i/a4608159ab270b3ff096bc753b59c851.png)

---

To generate a jar file of this project using maven:
  1. after cloning the project, open terminal in the project's main directory and type:

  `mvn package`
  
  2. Next go to generated `target` directory:
  
  `cd target`
  
  3. To run generated jar file enter:
  
  `java -jar Trainer-jar-with-dependencies.jar`

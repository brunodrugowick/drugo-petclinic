# drugo-petclinic
On this repository I developed the well-known Spring Pet Clinic application as per instructions by John Thompson 
on his Spring Framework 5 course on Udemy.

## Try the latest version
The latest stable version of this app is automatically deployed to [https://drugo-petclinic.herokuapp.com/](https://drugo-petclinic.herokuapp.com/).

## Running
To run: 
```
./mvnw spring-boot:run
```

To build and run:
```
./mvnw package
 java -jar petclinic-web/target/*.jar
```

## Developing
The app can create a sample database to use while developing. 
This process is only triggered if you understand the DataLoader.java class.

Otherwise the app will run normally without creating any sample data.
Go ahead and take a look at the mentioned class to understand what you have to do
to have the sample data created for you.
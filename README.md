# TODO_List_Web_Application
This project is aimed at creating a Full-Stack web application which provides a persistent service of creating a personalised todo list.
A registered user can create his own personalised todo list and is also able to delete the tasks that he has completed.

## Architecture
Considering the fact that the 'Todo'list needs to be persistent the list information and the User that it belongs to needs to be saved in a persistent storage database and should be available whenever the user logs into the site.

The architecture of the Web application is as follows:
- MongoDB (Database)
- Java Spring Boot (BackEnd Web Server) **(*refer DBWrapper Folder)**
- Javascript Server (ReactJs) (FrontEnd Web Server) **(*refer todo Folder)**

### Data Modelling (MongoDB)
To maintain persistence of the Users todo list the Web application needs to maintain a record of all users using the service as well as their 'todo' list information. This is information is stored in a single database in mongodb named *'todo'*. 

Within this database are two collections namely:
1) memberInfo
2) memberTasks

The data format of documents within those collections are as follows:
1) memberInfo:
```javascript
{
  "_id": ObjectId("5bb9d8d0dee62919f8dbd4d8"),
  "memberId":"4729693",
  "firstName":"Salil",
  "lastName":"Ajgaonkar",
  "email":"xxx.xxx@xxx.com",
  "password":"ABCDEFGH"
 }
```
*_id* = MongoDB generated Unique Id for the Document<br />
*memberId* = Unique Id for a User (*could be generated using a counter maintained in a persistent cache system like Redis)<br />
*password* = Hashed representation of the Original Password<br />

2) memberTasks
```javascript
{
  "_id" : ObjectId("5bb9f8ee55e3def531b827a6"),
  "memberId" : "4729693",
  "tasks" : [
    { "id" : "122","task" : "Buy tomatoes"},
    {"id" : "34", "task" : "Pickup Kids"},
    {"id" : "654","task" : "Buy Milk"}
  ]
}
```
*memberId* = Indicates to which member the list belongs to.<br />
*tasks* = An array of all the task created by the user each identified by a unique Id, which is later used to delete the specific task once done<br />

**Based on Data management requirements the data can be stored in replica clusters and/or sharded across clusters**

### Server functionality (Java Springboot)
Ideally for security and database access convenience all external services interacting with database should communicate with the database via a wrapper service. The wrapper service (also called db wrapper service) is a service that is not exposed publicly and has a direct connection to the database. All external services that need to interact with the database can do so via authenticating with the db wrapper service using REST API.
The backend web service is created using Spring MVC framework.<br />
**Since no data computation is required for a 'TODO List' Web application there was no point in separating the web service and the db wrapper service. The web service will only act as a data router between the db-wrapper and the frontend. Thus in this project the db-wrapper itself acts as a back end web server to the front end server**

### Front End functionality (Javascript Server using ReactJs)
ReactJS is a component based Javascript Library used for building interactive user interfaces. It is built using reusable components which helps in faster rendering of webpages.

![alt text](https://github.com/SalilAj/TODO_List_Web_Application/blob/master/123.png)

**Currently working on**

### Deployment and Miscellaneous tools
1) The project can be hosted on cloud computing platforms like AWS or Google Cloud for serverless deployment
2) Continuous integration and deployment tools like Jenkins for the backend Java server and CircleCI or Heroku for frontend server can be implemented to prevent manual deployment and code integration issues.
3) Continuous inspection tools like SonarQube can be used to detect code smells and enforce coding standards

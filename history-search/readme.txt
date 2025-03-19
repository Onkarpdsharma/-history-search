Assuming all the required software is installed for the react js frontend to run
GO inside bitcoin directory run following command
install axios for api call
Remove node_modules from the bitcon directory due to size issue,

run

npm install

npm install axios

npm start  --for normal run

##For production build
npm install -g serve
serve -s build

##access the application
http://localhost:3000

-------------------------------------------------------------------------
Assuming required software installed to run backend application jdk 17, maven 3.9.9

Go inside history directory run following command
#due to size issue target folder is removed

build the application

mvn clean build

mvn spring-boot:run

#using frontend url we can access the backend application
#Historic data is available for 2024 -March and 2025 March

--------------------------------------------------------------
CI/CD terraform script available, it is dynamic script which accept the parameter during run belongs to GKE cluster and other info required for deployment. Azure DevOps used to deploy through Azure-deployment.yml

It is located under deploy folder
----------------------------------------
JUnit Test case Implementation
Cucumber Test case Implementation
Log4j Implementation
Swagger documentation is part of the use case

access swagger doc-
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs


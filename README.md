# Priority API Server

API server as defined in the task [here](https://www.notion.so/Tatsam-Hiring-Software-Engineer-Java-Spring-Assignment-f20238a62be44732aacb8f9169449e33)

<br>Live example: https://afternoon-shelf-75180.herokuapp.com/ <br>
## Prerequisites 
The following tools are required to build and run this project<br>
1.  Java8/11
2.  Maven
3. Docker
4. Docker-compose 

## Overview  

The API specification is defined as per Open-API spec in the file **api-spec.yml**<br>

Use **builder.sh** to build and run the project

To bootstrap, Run: `./builder.sh -p -d -c`<br>
The API server will be avaiable at localhost:8080 (it has a nice swagger UI)


## Examples 

1. Get all priority areas in the database( Note: The DB is not filled with any piority area at start. Use the next API to add any priority areas)
   ```curl -X GET "http://localhost:8080/v1/areas" -H  "accept: application/json" ```
2. Add a priority area to the databse
   ``` curl -X POST "http://localhost:8080/v1/areas" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"mycoolpriorityarea\"}" ```

3. Add a user's priorities with satisfaction (To restrict access to admin, Oauth2 or other Auth mechanism can be added)
**Note:** Priority to an area can be assigned from **1** to N(max priority area count).
``` curl -X POST "http://localhost:8080/v1/users" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"priorityAreas\": [    {      \"area\": {        \"name\": \"mycoolpriorityarea\"      },      \"priority\": 1,      \"statisfaction\": \"3\"    }  ],  \"username\": \"cooluser\"}" ```

4. Get a User's priority details by username
   ``` curl -X GET "http://localhost:8080/v1/users/cooluser" -H  "accept: application/json" ```
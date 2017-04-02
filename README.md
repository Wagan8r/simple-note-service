#simple-note-service

To run the service, first clone from GitHub:

    git clone https://github.com/Wagan8r/simple-note-service.git

Start the service on Windows with:

    gradlew bootRun
    
or on MacOS or Linux with:

    ./gradlew bootRun

The service is started at:

    http://localhost:80
    
To use the service, submit requests via curl like below:

    curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Pick up milk!"}' http://localhost/api/notes
    curl -i -H "Content-Type: application/json" -X GET http://localhost/api/notes/1
    curl -i -H "Content-Type: application/json" -X GET http://localhost/api/notes
    curl -i -H "Content-Type: application/json" -X GET http://localhost/api/notes?query=milk
    
Enjoy!
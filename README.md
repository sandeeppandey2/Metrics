# Metrics
## Springboot project to calculate basic metrics

#Steps to run project

1.clone code from repo

2.got to project path

3.run command: mvn spring-boot:run
  
#output

make Get call as below:

1.Request

http://localhost:8087/metrics

2.response

{
"numberOfSuccessfulRequest": 147631,
"numberOfUnsuccessfulRequest": 0,
"totalNumberOfRequest": 147631,
"percentageRequestSuccess": 100,
"averageTime": 6
}

#note
Average response time is for general api not for all api.


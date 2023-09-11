
## Use cases
| ID       | Select                                                            | Iterations | Source                                                                                     |
|:--------:|-------------------------------------------------------------------|-----------:|--------------------------------------------------------------------------------------------|
|   UC1    | SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS |          1 | [TpjwebappsTest.java](src/main/java/com/rds_software/jdbc/performance/TpjwebappsTest.java) |
|   UC10   | SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS |         10 | [TpjwebappsTest.java](src/main/java/com/rds_software/jdbc/performance/TpjwebappsTest.java) |
|   UC50   | SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS |         50 | [TpjwebappsTest.java](src/main/java/com/rds_software/jdbc/performance/TpjwebappsTest.java) |
|  UC300   | SELECT ID, URL, TARGET, JSOPTIONS, ENV_IND, DESCR FROM TPJWEBAPPS |        300 | [TpjwebappsTest.java](src/main/java/com/rds_software/jdbc/performance/TpjwebappsTest.java) |

## Clone repo

```sh
git clone https://github.com/lanarimarco/jdbc-performance.git 
cd jdbc-performance
```

## Config

- create `datasource.properties` in the current dir and set properties:
```properties
user=setme
password=setme
url=jdbc:as400://set-server/csmr40dat
driver=com.ibm.as400.access.AS400JDBCDriver
```

- create jar
```sh
mvn clean package
```

- execute
```sh
java -jar ./target/jdbc-performance.jar
```

## RDS
Client: prsjava.rdspr.rds-software.com  
AS400: as400new.rdspr.rds-software.com  
```
2023-09-11 16:01:05 - Executing: TPJWEBAPPS 1 iterations
2023-09-11 16:01:05 - Duration: 266ms
2023-09-11 16:01:05 - Executing: TPJWEBAPPS 10 iterations
2023-09-11 16:01:05 - Duration: 31ms
2023-09-11 16:01:05 - Executing: TPJWEBAPPS 50 iterations
2023-09-11 16:01:05 - Duration: 141ms
2023-09-11 16:01:05 - Executing: TPJWEBAPPS 300 iterations
2023-09-11 16:01:06 - Duration: 750ms
```

## Servizi logistici
Client: 192.168.29.225  
AS400: 192.168.29.228  
```
2023-09-11 16:15:46 - Executing: TPJWEBAPPS 1 iterations
2023-09-11 16:15:46 - Duration: 94ms
2023-09-11 16:15:46 - Executing: TPJWEBAPPS 10 iterations
2023-09-11 16:15:47 - Duration: 437ms
2023-09-11 16:15:47 - Executing: TPJWEBAPPS 50 iterations
2023-09-11 16:15:48 - Duration: 1594ms
2023-09-11 16:15:48 - Executing: TPJWEBAPPS 300 iterations
2023-09-11 16:15:58 - Duration: 9688ms
```

## Comparison

| Use case | RDS(duration ms) | Servizi logistica (duration ms) |
|:--------:|-----------------:|--------------------------------:|
|   UC1    |              266 |                              94 |
|   UC10   |               31 |                             437 |
|   UC50   |              141 |                            1594 |
|  UC300   |              750 |                            9688 |

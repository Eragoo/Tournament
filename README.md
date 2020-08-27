# Tournament
## Overview
 #### Tech stack:
 - Spring Boot, Data
 - Hibernate, PostgresDB, Flyway
 - Gradle, Swagger
 - Lombok

### Tournament flow:
 1) Create participants
 2) Create tornament with participants
 3) Start a tournament. This operation generates matches with tournament participants. Match generates for each 2 participants 
 4) Play each match and get the winners (losers deletes from tournament)
 5) When in tournament stay half of participants - move to 3 step
 6) In last match you get the winner of tournament

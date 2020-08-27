# Tournament
## Overview
 #### Tech stack:
 - Spring Boot, Data, Security
 - Hibernate, PostgresDB, Flyway
 - Maven, Swagger
 - Lombok, Hibernate JPA model generator

### Tournament flow:
 1) Create participants
 2) Create tornament with participants
 3) Start a tournament. This operation generates matches with tournament participants. Match generates for each 2 participants 
 4) Play each match and get the winners (losers deletes from tournament)
 5) When in tournament stay half of participants - move to 3 step
 6) In last match you get the winner of tournament

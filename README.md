A backend system built using Spring Boot to support head-to-head tournament formats like 1v1, 2v2, 5v5 with dynamic bracket generation and automatic progression.

Problem Statement

To extend an existing battle royale tournament system to support head-to-head formats with:
- Direct opponent matches
- Win/Loss results
- Bracket generation
- Automatic round progression

Features

- Dynamic bracket generation
- Round-wise match creation
- BYE handling for odd teams
- Match result submission
- Automatic next round generation
- Fetch match winner
- Fetch tournament winner
- Global exception handling
- Structured error response

Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok

System Flow

1. Create tournament & teams
2. Generate bracket (Round 1)
3. Submit match results
4. System generates next round when we hit a request
5. Repeat until final
6. Fetch tournament winner

API's

POST   /tournaments/{id}/generate-bracket
POST   /matches/{id}/result
GET    /matches/{id}/winner
POST   /tournaments/{id}/next-round (optional manual)
GET    /tournaments/{id}/winner
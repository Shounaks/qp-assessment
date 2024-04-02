# Grocery Booking API

## There is only so much i can do in 90-120 mins, I have tried my level best to create a proof of concept for this MS.

Design API endpoints
1. Admin Responsibilities:
   - 🟩 Add new grocery items to the system
   - 🟩 View existing grocery items
   - 🟩 Remove grocery items from the system
   - 🟩 Update details (e.g., name, price) of existing grocery items
   - 🟩 Manage inventory levels of grocery items
2. User Responsibilities:
   - 🟩 View the list of available grocery items
   - 🟩 Ability to book multiple grocery items in a single order
Advanced Challenge:
  - 🟧 Containerize the application using Docker for ease of deployment and scaling.
Database:
  - 🟩 Use any relational database of your choice. (MySQL)

# Items Used:
- Spring Boot 3.0.0 (huge peformance gains instead of JDK 8-11 and some new features)
- Spring Security 6.X.X (Cause why not)
- Spring Security Access (for PreAuthorized and method level security)
- Spring Data JPA (Spring Rest/MySQL Connector...)
- Lombok
- SpringFox Swagger Documentation


# Remaining Items/ Improvements:
- Due to lack of time, I couldnt add AOP, User Input Sanitization, Proper logging flow + Formatter, PII Masking, and some other items, that i do generally.
- Currently i have shifted to Local Dockerized MySQL but would prefer something like railway.app or any other service, for demo.
- Would have loved Integration testing WireMock, etc.

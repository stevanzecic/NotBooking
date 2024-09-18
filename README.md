# NotBooking

### Version: 0.1.4

A simple yet effective application for Apartment/Hotel management. Built with Angular 16 and Spring Boot 3.2.9.

# Installation

# Usage

---
---

# Changelog

- 0.0.1 /by Stevan Zecic = _Project initialization_
  - Created the project: Angular 16 project for user application and Spring Boot project for server-side application. Spring Initializr was used to create the Spring Boot project.
- 0.1.0 - _Basic server-side functionality_
  - Added dependencies
  - Connected the database
  - Added JWT authentication
  - New user registration functionality
  - CORS filtering configuration
- 0.1.1 - _Basic user application functionality_
  - Angular project removed and created again
  - NgZorroAntdModule added for NG-ZORRO configuration
  - Basic side navigation menu added
  - Register page added
- 0.1.2 - _Registration_
  - User registration functionality added
- 0.1.3 - _Login API_
  - Login added to NotBooking Spring project
    - AuthController path added
    - AuthenticationRequest and AuthenticationResponse added
    - UserService and UserServiceImpl added
    - JwtUtil fixed
    - WebSecurityConfiguration authentication added
- 0.1.4 - _Login page_
  - Login page added to NotBookingWeb client application
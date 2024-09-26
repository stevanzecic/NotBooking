# NotBooking

### Version: 0.1.7

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
- 0.1.5 - _Admin and Guest modules_
  - register and login URL handling added to auth service
  - localStorage handling added
  - Admin and Guest modules added
  - Navitation panel adaptation based on the role of the logged in user
  - Login page validation changed so it accepts 'admin' as username
  - Added components for admin and guest modules which will be used for different displays
- 0.1.6 - _JWT token handling_
  - Added JWT token handling to the NotBooking application
  - Side menu changes
  - Added icon
- 0.1.7 - _Rooms API_
  - Added Rooms API
    - RoomRepository, RoomsController, RoomDTO and RoomsService added
  - Role based authentication added



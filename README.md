A Spring Boot-based RESTful API designed to manage an organization's employees and departments. This project demonstrates clean architecture patterns, validation mechanisms, unified error handling, and entity-DTO mapping.

src/main/java/com/example/EmployeeOrg/
│
├── EmployeeOrgApplication.java      # Application main entry point
│
├── entities/                        # Database entities (JPA mappings)
│   ├── EmployeeEntity.java
│   └── DepartmentEntity.java
│
├── dto/                             # Data Transfer Objects for API requests/responses
│   ├── EmployeeDTO.java
│   └── DepartmentDTO.java
│
├── repositories/                    # Spring Data JPA repositories
│   ├── EmployeeRepository.java
│   └── DepartmentRepository.java
│
├── service/                         # Business logic layer
│   ├── EmployeeService.java
│   └── DepartmentService.java
│
├── controller/                      # REST controllers (API endpoints)
│   ├── EmployeeController.java
│   └── DepartmentController.java
│
├── configs/                         # Configuration beans
│   └── MapperConfig.java
│
├── advice/                          # Global exception handling and response wrapping
│   ├── ApiResponse.java             # Unified response wrapper
│   ├── ApiErrors.java               # Standardized error payload
│   ├── GlobalExceptionHandler.java  # RestControllerAdvice
│   └── GlobalResponseHandler.java   # ResponseBodyAdvice (wrapper middleware)
│
├── annotations/                     # Custom validation annotations
│   ├── EmployeeRoleValidation.java
│   └── EmployeeRoleValidator.java
│
└── exceptions/                      # Custom business exceptions
    └── ResourceNotFoundException.java
During the analysis of the project, a couple of potential bugs/gaps were identified:


EmployeeOrg - Organization Management REST API
A Spring Boot-based RESTful API designed to manage an organization's employees and departments. This project demonstrates clean architecture patterns, validation mechanisms, unified error handling, and entity-DTO mapping.

🚀 Tech Stack
Framework: Spring Boot 4.0.6 (Java 21)
Database: H2 In-Memory Database (with H2 Console enabled)
Data Access: Spring Data JPA / Hibernate
Object Mapping: ModelMapper (for Entity ↔ DTO conversions)
Validation: Jakarta Validation / Hibernate Validator
Boilerplate Reduction: Project Lombok
📁 Directory & Package Structure
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
🛠️ Key Features & Implementation Highlights
1. Data Validation & Custom Annotation
Input validation is strictly enforced using annotations like @NotBlank, @Size, @Email, @Min, @Max, @Positive, and @PastOrPresent on DTO classes.
A custom annotation @EmployeeRoleValidation along with its validator EmployeeRoleValidator is implemented to ensure employee roles are restricted to either USER or ADMIN.
2. Standardized API Responses
All API responses are structured consistently using the ApiResponse<T> wrapper, which automatically appends a timestamp, data payload, or detailed error information (ApiErrors).
A GlobalExceptionHandler intercept and formats exceptions gracefully:
ResourceNotFoundException maps to 404 Not Found.
MethodArgumentNotValidException maps to 400 Bad Request containing a list of failed fields and constraints.
Generic Exception maps to 500 Internal Server Error.
3. Dynamic Partial Updates (PATCH)
The API supports partial updates (PATCH /employees/{id}) using Java reflection. The EmployeeService utilizes Spring's ReflectionUtils to update only the fields provided in the request body.
🔌 API Endpoints
Employees (/employees)
Method	Endpoint	Description	Request Body
GET	/employees	Retrieve all employees	None
GET	/employees/{id}	Retrieve a specific employee by ID	None
POST	/employees	Create a new employee	EmployeeDTO
PUT	/employees/{id}	Update an existing employee fully	EmployeeDTO
PATCH	/employees/{id}	Partially update an employee	Map of properties
DELETE	/employees/{id}	Delete an employee by ID	None
Departments (/department)
Method	Endpoint	Description	Request Body
GET	/department	Retrieve all departments	None
GET	/department/{id}	Retrieve a specific department by ID	None
POST	/department	Create a new department	DepartmentDTO
PUT	/department/{id}	Update an existing department fully	DepartmentDTO
DELETE	/department/{id}	Delete a department by ID	None
🔍 Codebase Diagnostics & Observations
During the analysis of the project, a couple of potential bugs/gaps were identified:

Incomplete Global Response Wrapper (GlobalResponseHandler.java):
The supports method in GlobalResponseHandler returns false. This prevents the beforeBodyWrite interceptor from executing, meaning success responses are not automatically wrapped inside the ApiResponse wrapper class unless done manually in the controllers.
Unused Query Parameters (EmployeeController.java):
The getEmployees method in the controller accepts @RequestParam(required = false) Integer age and @RequestParam(required = false) String sortBy, but it calls employeeService.getEmployees() without passing these parameters. Therefore, sorting and filtering are currently not functional.
Database Relation Mapping:
EmployeeEntity and DepartmentEntity do not currently have any defined relational mappings (e.g., @ManyToOne or @OneToMany) linking them together. They operate as independent entities.

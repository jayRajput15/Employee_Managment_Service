package com.example.EmployeeOrg.controller;

import com.example.EmployeeOrg.dto.EmployeeDTO;
import com.example.EmployeeOrg.entities.EmployeeEntity;
import com.example.EmployeeOrg.exceptions.ResourceNotFoundException;
import com.example.EmployeeOrg.repositories.EmployeeRepository;
import com.example.EmployeeOrg.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping("/getSecretMessage")
//    public String getSecretMessage() {
//        return "Secret message: asd#hdh@234";
//    }

    //private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name = "employeeID")Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeByID(id);
        return employeeDTO
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseThrow(()->new ResourceNotFoundException("Employee not found!"));
    }
//    @ExceptionHandler(NoSuchElementException.class)
//    public String handleEmployeeNotFound(NoSuchElementException exception){
//        return "Employee not found";
//    }


    @GetMapping

    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(required = false) Integer age , @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid  EmployeeDTO inputEmployee){
       EmployeeDTO savedEmployee =  employeeService.createEmployee(inputEmployee);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO inputEmployee, @PathVariable(name = "employeeId") Long id){
        return ResponseEntity.ok(employeeService.updateEmployee(id,inputEmployee));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable(name = "employeeId") Long id){
      boolean getDeleted = employeeService.deleteEmployee(id);
      if(getDeleted){
         return   ResponseEntity.ok(true);
      }
      return ResponseEntity.notFound().build();
    }

     @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String, Object> updates, @PathVariable(name = "employeeId") Long id){
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployee(id,updates);
        if(employeeDTO == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
     }
}



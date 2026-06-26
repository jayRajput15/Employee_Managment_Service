package com.example.EmployeeOrg.controller;

import com.example.EmployeeOrg.dto.DepartmentDTO;
import com.example.EmployeeOrg.entities.DepartmentEntity;
import com.example.EmployeeOrg.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
   public List<DepartmentDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO inputDepartment){
        DepartmentDTO departmentDTO = departmentService.createdDepartment(inputDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{EmployeeID}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "EmployeeID") Long id){
        DepartmentDTO departmentDTO =  departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @PutMapping("/{EmployeeID}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable(name = "EmployeeID") Long id, @Valid @RequestBody DepartmentDTO inputDepartment){
        DepartmentDTO update = departmentService.updateDepartment(id,inputDepartment);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{EmployeeID}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable(name = "EmployeeID") Long id){
       departmentService.deleteDepartment(id);
       return ResponseEntity.ok().build();
    }
    }


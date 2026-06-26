package com.example.EmployeeOrg.service;

import com.example.EmployeeOrg.dto.DepartmentDTO;
import com.example.EmployeeOrg.entities.DepartmentEntity;
import com.example.EmployeeOrg.exceptions.ResourceNotFoundException;
import com.example.EmployeeOrg.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class)).collect(Collectors.toList());
    }

    public DepartmentDTO createdDepartment(@Valid DepartmentDTO inputDepartment) {
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        return modelMapper.map(departmentEntity,DepartmentDTO.class);
    }


    public DepartmentDTO getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found with id " + id));
        //isDepartmentExist(id);
     return modelMapper.map(departmentEntity,DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(Long id, @Valid DepartmentDTO inputDepartment) {
      isDepartmentExist(id);
      DepartmentEntity departmentEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);
      departmentEntity.setId(id);
      departmentRepository.save(departmentEntity);
      return modelMapper.map(departmentEntity,DepartmentDTO.class);
    }

    public void isDepartmentExist(Long id) {
        boolean exist =  departmentRepository.existsById(id);
        if(!exist){
            throw new ResourceNotFoundException("Department not found with id " + id);
        }
    }

    public void  deleteDepartment(Long id) {
        isDepartmentExist(id);
        departmentRepository.deleteById(id);

    }
}

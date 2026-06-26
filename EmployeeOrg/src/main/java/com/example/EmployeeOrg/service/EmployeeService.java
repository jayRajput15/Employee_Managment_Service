package com.example.EmployeeOrg.service;

import com.example.EmployeeOrg.dto.EmployeeDTO;
import com.example.EmployeeOrg.entities.EmployeeEntity;
import com.example.EmployeeOrg.exceptions.ResourceNotFoundException;
import com.example.EmployeeOrg.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeByID(Long id) {

       // EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);

        return employeeRepository.findById(id).map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getEmployees() {
       List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
       return employeeEntities
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
               .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO inputEmployee) {
       isExistByEmployeeId(id);
    EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);

    employeeEntity.setId(id);
    employeeRepository.save(employeeEntity);
    return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public void isExistByEmployeeId(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist){ throw new ResourceNotFoundException("Employee not found"); }

    }

    public boolean deleteEmployee(Long id) {
        isExistByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployee(Long id, Map<String, Object> updates) {
        isExistByEmployeeId(id);
      EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
      updates.forEach((fields,values)->{
          Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, fields);
          fieldToBeUpdated.setAccessible(true);
          ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, values);
      });
      return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}

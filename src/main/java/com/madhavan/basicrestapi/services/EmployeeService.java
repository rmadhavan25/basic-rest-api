package com.madhavan.basicrestapi.services;


import com.madhavan.basicrestapi.DTO.EmployeeDTO;
import com.madhavan.basicrestapi.entities.EmployeeEntity;
import com.madhavan.basicrestapi.exceptions.ResourceNotFoundException;
import com.madhavan.basicrestapi.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapperService;


    public EmployeeDTO getEmployeeByID(Long employeeID) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).orElse(null);
        if(employeeEntity == null) {
            throw new ResourceNotFoundException("Employee with ID " + employeeID + " not found");
        }
        return mapperService.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> mapperService.map(employeeEntity, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO saveNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity employeeEntity = mapperService.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return mapperService.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeByID(Long employeeID, EmployeeDTO employeeDTO) {
        if(!employeeRepository.existsById(employeeID)) {
            throw new ResourceNotFoundException("Employee with ID " + employeeID + " not found");
        }
        EmployeeEntity employeeEntity = mapperService.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeID);
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return mapperService.map(updatedEmployee, EmployeeDTO.class);
    }

    public Boolean deleteEmployeeByID(Long employeeID) {
        if(employeeRepository.existsById(employeeID)){
            employeeRepository.deleteById(employeeID);
            return true;
        } else {
            throw new ResourceNotFoundException("Employee with ID " + employeeID + " not found");
        }
    }

    public EmployeeDTO updateEmployeePartiallyByID(Long employeeID, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).orElse(null);
        if (employeeEntity == null) {
            throw new ResourceNotFoundException("Employee with ID " + employeeID + " not found");
        }
        if (employeeDTO.getName() != null) {
            employeeEntity.setName(employeeDTO.getName());
        }
        if (employeeDTO.getEmail() != null) {
            employeeEntity.setEmail(employeeDTO.getEmail());
        }
        if (employeeDTO.getAge() != null) {
            employeeEntity.setAge(employeeDTO.getAge());
        }
        if (employeeDTO.getDateOfJoining() != null) {
            employeeEntity.setDateOfJoining(employeeDTO.getDateOfJoining());
        }
        if (employeeDTO.getIsActive() != null) {
            employeeEntity.setIsActive(employeeDTO.getIsActive());
        }
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return mapperService.map(updatedEmployee, EmployeeDTO.class);
    }
}

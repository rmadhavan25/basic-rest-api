package com.madhavan.basicrestapi.controllers;


import com.madhavan.basicrestapi.DTO.EmployeeDTO;
import com.madhavan.basicrestapi.exceptions.ResourceNotFoundException;
import com.madhavan.basicrestapi.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable("id") Long EmployeeID){
        EmployeeDTO responseEmployee = employeeService.getEmployeeByID(EmployeeID);
        return ResponseEntity.ok(responseEmployee);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(value = "age", required = false) Integer employeeAge){
        List<EmployeeDTO> responseEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(responseEmployees);
    }

    @PostMapping(path = "/")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO responseEmployee = employeeService.saveNewEmployee(inputEmployee);
        return new ResponseEntity<>(responseEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByID(@PathVariable("id") Long EmployeeID, @Valid @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO responseEmployee = employeeService.updateEmployeeByID(EmployeeID, employeeDTO);
        return ResponseEntity.ok(responseEmployee);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteEmployeeByID(@PathVariable("id") Long EmployeeID){
        Boolean deleteResponse = employeeService.deleteEmployeeByID(EmployeeID);
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeePartiallyByID(@PathVariable("id") Long EmployeeID, @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeResponse = employeeService.updateEmployeePartiallyByID(EmployeeID, employeeDTO);
        return ResponseEntity.ok(employeeResponse);
    }

}

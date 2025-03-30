package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")

public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


//jdbc:mysql://localhost:3306//crmdb1

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
          //  @RequestBody EmployeeDto dto
          @Valid @RequestBody EmployeeDto dto,
          //for error handling
          BindingResult result
            ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
              EmployeeDto employeeDto = employeeService.addEmployee(dto);
                return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/employee?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestParam long id
    ){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(
            @RequestParam long id,
            @RequestBody EmployeeDto dto
    ){
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/employees?pagesize=3&pageNo=1&sortBy=email&sortDir=desc

    @GetMapping
    //want to get only 5 records per page from 14 records
    public ResponseEntity<List<EmployeeDto> >getEmployees(
    @RequestParam(name="PageSize",required=false,defaultValue ="5" )int pageSize,
    @RequestParam(name="PageNo",required=false,defaultValue ="0" )int pageNo,
    @RequestParam(name="sortBy",required=false,defaultValue ="name" )String sortBy,
     @RequestParam(name="sortDir",required=false,defaultValue ="asc" )String sortDir
    ){
      List<EmployeeDto> employeesDto = employeeService.getEmployee(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);

    }
    //http://localhost:8080/api/v1/employee/employeeId/1
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto>getEmployeeById(
    @PathVariable long empId
    ){
       EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}

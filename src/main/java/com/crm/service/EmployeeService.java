package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    // private Object dto;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        return employeeDto;
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {
        Optional<Employee> opEmp = employeeRepository.findById(id);
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updatedEmployee);
        return employeeDto;

    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
      Sort sort = sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

      Pageable page = PageRequest.of(pageNo, pageSize, sort);
       Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    Employee mapToEntity(EmployeeDto dto) {
        Employee emp = modelMapper.map(dto, Employee.class);
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }

    private EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(emp.getId());
//        dto.setName(emp.getName());
//        dto.setEmailId(emp.getEmailId());
//        dto.setMobile(emp.getMobile());

    }

    //getting the employee by its id
    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Record Not Found with id :" + empId)


        );
        EmployeeDto dto = mapToDto(employee);
        return dto;

//        Optional<Employee> OpEmp = employeeRepository.findById(empId);
//        Employee employee = OpEmp.get();
//        return mapToDto(employee);

    }
}
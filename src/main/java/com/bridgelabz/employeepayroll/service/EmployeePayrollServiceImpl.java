package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService {
    @Autowired
    private EmployeeRepository employeeRepository;

    // method to create an employee
    @Override
    public ResponseDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO);
        log.debug("Employee Data: " + employee.toString());
        employeeRepository.save(employee);
        return new ResponseDTO("Created Employee payroll data successfully", HttpStatus.CREATED.value() , employee);
    }

    // method to get employee data based on id
    @Override
    public ResponseDTO getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
        return new ResponseDTO("Employee fetched successfully", HttpStatus.OK.value(), employee);
    }

    // method to update employee data based on id
    @Override
    public ResponseDTO updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByName(employeeDTO.name);
        if(optionalEmployee.isPresent()) {
            Employee updatedEmployee = optionalEmployee.get();
            updatedEmployee.updateEmployeeData(employeeDTO);
            employeeRepository.save(updatedEmployee);

            return new ResponseDTO("Employee data updated successfully", HttpStatus.OK.value(), updatedEmployee);
        } else {
            throw new EmployeePayrollException("Employee Not Found");
        }
    }

    // method to delete employee based on id
    @Override
    @Transactional
    public ResponseDTO deleteEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found with id: " + employeeId));
        employeeRepository.delete(employee);

        return new ResponseDTO("Employee deleted successfully", HttpStatus.OK.value(), null);
    }

    // method to get all employees
    @Override
    public ResponseDTO getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        if(!allEmployees.isEmpty()) return new ResponseDTO("Employees fetched successfully", HttpStatus.OK.value(), allEmployees);
        else throw new EmployeePayrollException("Employees Not Found");
    }

    // method to get all employees based on department
    @Override
    public ResponseDTO getAllEmployeesByDepartment(String department) {
        List<Employee> employeesByDepartment = employeeRepository.findAllByDepartment(department);
        if(!employeesByDepartment.isEmpty()) return new ResponseDTO("Employees fetched successfully", HttpStatus.OK.value(), employeesByDepartment);
        else throw new EmployeePayrollException("Employees Not Found for this department");
    }
}

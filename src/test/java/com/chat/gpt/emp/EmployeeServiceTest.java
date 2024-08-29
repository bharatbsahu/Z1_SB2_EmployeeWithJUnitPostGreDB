package com.chat.gpt.emp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

public class EmployeeServiceTest {
	
    @InjectMocks
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeRepository employeeRepository;
    
    
	public EmployeeServiceTest() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
    public void testGetAllEmployees() {
		
		Employee employee1 = new Employee();
		employee1.setFirstName("John");
		employee1.setLastName("Doe");
		employee1.setEmail("john.doe@example.com");
		
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));
		
		assertThat(employeeService.getAllEmployees()).hasSize(1);
		
	}
    
    @Test
    public void testGetEmployeeById() {
    	
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertThat(foundEmployee.isPresent()).isTrue();
        assertThat(foundEmployee.get().getFirstName()).isEqualTo("John");
    	
    }
    
    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setEmail("jane.doe@example.com");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertThat(savedEmployee.getFirstName()).isEqualTo("Jane");
    }

    @Test
    public void testDeleteEmployee() {
        Long employeeId = 1L;
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

}

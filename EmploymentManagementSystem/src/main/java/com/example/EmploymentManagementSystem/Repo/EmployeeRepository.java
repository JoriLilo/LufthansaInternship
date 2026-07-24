package com.example.EmploymentManagementSystem.Repo;

import com.example.EmploymentManagementSystem.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {



     List<Employee> findByDepartment(String department);


     List<Employee> findEmployeesBySalaryGreaterThan(BigDecimal salary);


     Optional<Employee> findEmployeesByLastNameContaining(String text);


     List<Employee> findByDepartmentAndSalaryGreaterThanEqual(String department, BigDecimal salary);

     @Query(value = "SELECT e FROM Employee e WHERE e.hireDate >= :hireDate")
     List<Employee> findEmployeeByHireDateAfter(@Param("hireDate") LocalDate hireDate);


     @Query(value = "SELECT * FROM employee  WHERE salary >= :salary",nativeQuery = true)
     List<Employee> findEmployeeBySalaryGreaterThan( @Param("salary") BigDecimal salary);


     Page<Employee> findAll(Pageable pageable);

     List<Employee>  findByOrderBySalaryAsc();

}

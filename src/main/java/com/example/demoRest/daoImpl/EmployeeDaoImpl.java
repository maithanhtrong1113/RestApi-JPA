package com.example.demoRest.daoImpl;

import com.example.demoRest.dao.EmployeeDao;
import com.example.demoRest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager theEntityManager){
        entityManager= theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery= entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees= theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class,theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
       Employee employee= entityManager.merge(theEmployee);
       return employee;
    }

    @Override
    public void deleteById(int theId) {
    Employee theEmployee = entityManager.find(Employee.class,theId);
    entityManager.remove(theEmployee);
    }
}

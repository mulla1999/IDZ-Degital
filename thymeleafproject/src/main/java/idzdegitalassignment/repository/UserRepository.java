package idzdegitalassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idzdegitalassignment.entity.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, Integer>{

}

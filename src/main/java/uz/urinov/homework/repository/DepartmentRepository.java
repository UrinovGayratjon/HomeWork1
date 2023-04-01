package uz.urinov.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{



    boolean existsByName(String name);


}

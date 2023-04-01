package uz.urinov.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.homework.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{


    boolean existsByCorpName(String corpName);

}

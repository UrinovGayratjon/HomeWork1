package uz.urinov.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.homework.entity.Address;
import uz.urinov.homework.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber,Integer id);

}

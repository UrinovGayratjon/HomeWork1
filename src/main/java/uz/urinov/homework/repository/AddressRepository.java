package uz.urinov.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.homework.entity.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    boolean existsByStreetAndHomeNumber(String street,String homeNumber);

    Optional<Address> getByStreetAndHomeNumber(String street, Integer homeNumber);

}

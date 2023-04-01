package uz.urinov.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.urinov.homework.dto.request.RequestWorkerDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Address;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Department;
import uz.urinov.homework.entity.Worker;
import uz.urinov.homework.repository.AddressRepository;
import uz.urinov.homework.repository.CompanyRepository;
import uz.urinov.homework.repository.DepartmentRepository;
import uz.urinov.homework.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer companyId) {

        return workerRepository.findById(companyId).orElse(null);
    }

    public ApiResponse saveWorker(RequestWorkerDto newWorker) {
        if (workerRepository.existsByPhoneNumber(newWorker.getPhoneNumber())) {
            return ApiResponse.builder().isSuccess(false).message("Bunday telefon number mavjud!").build();
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(newWorker.getDepartmentId());

        if (optionalDepartment.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday department mavjud emas").build();
        }


        Address address;
        Optional<Address> optionalAddress = addressRepository.getByStreetAndHomeNumber(newWorker.getStreet(), newWorker.getHomeNumber());

        address = optionalAddress.orElseGet(() ->
                addressRepository.save(
                        Address.builder().homeNumber(newWorker.getHomeNumber()).street(newWorker.getStreet()).build()
                )
        );

        Worker worker = new Worker();

        worker.setName(newWorker.getName());
        worker.setPhoneNumber(newWorker.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(optionalDepartment.get());


        workerRepository.save(worker);

        return ApiResponse.builder().isSuccess(true).message("Success").build();
    }

    public ApiResponse editWorker(Integer workerId, RequestWorkerDto newWorker) {

        Optional<Worker> workerOptional = workerRepository.findById(workerId);

        if (workerOptional.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday worker mavjud emas!").build();
        }


        if (workerRepository.existsByPhoneNumberAndIdNot(newWorker.getPhoneNumber(), workerId)) {
            return ApiResponse.builder().isSuccess(false).message("Bunday telefon number mavjud!").build();
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(newWorker.getDepartmentId());

        if (optionalDepartment.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday department mavjud emas").build();
        }


        Address address;
        Optional<Address> optionalAddress = addressRepository.getByStreetAndHomeNumber(newWorker.getStreet(), newWorker.getHomeNumber());

        address = optionalAddress.orElseGet(() ->
                addressRepository.save(
                        Address.builder().homeNumber(newWorker.getHomeNumber()).street(newWorker.getStreet()).build()
                )
        );

        Worker worker = workerOptional.get();
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(address);
        worker.setPhoneNumber(newWorker.getPhoneNumber());
        worker.setName(newWorker.getName());

        workerRepository.save(worker);
        return ApiResponse.builder().isSuccess(true).message("Success").build();
    }
}

package uz.urinov.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.urinov.homework.dto.request.RequestDepartmentDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Department;
import uz.urinov.homework.repository.CompanyRepository;
import uz.urinov.homework.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer companyId) {

        return departmentRepository.findById(companyId).orElse(null);
    }

    public ApiResponse saveDepartment(RequestDepartmentDto newDepartment) {

        if (departmentRepository.existsByName(newDepartment.getName())) {
            return ApiResponse.builder().isSuccess(false).message("Bunday Department mavjud").build();
        }

        Optional<Company> optionalCompany = companyRepository.findById(newDepartment.getCompanyId());

        if (optionalCompany.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday companiya mavjud emas").build();
        }

        Department department = new Department();
        department.setCompany(optionalCompany.get());
        department.setName(newDepartment.getName());

        departmentRepository.save(department);
        return ApiResponse.builder().isSuccess(true).message("Success!").build();
    }

    public ApiResponse editDepartment(Integer departmentId, RequestDepartmentDto newDepartment) {


        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (optionalDepartment.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday department mavjud emas").build();
        }

        Optional<Company> optionalCompany = companyRepository.findById(newDepartment.getCompanyId());

        if (optionalCompany.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday companiya mavjud emas").build();
        }

        Department department = optionalDepartment.get();
        department.setName(newDepartment.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);


        return ApiResponse.builder().isSuccess(true).message("Success").build();
    }
}

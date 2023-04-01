package uz.urinov.homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.homework.dto.request.RequestCompanyDto;
import uz.urinov.homework.dto.request.RequestDepartmentDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Department;
import uz.urinov.homework.service.CompanyService;
import uz.urinov.homework.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/api/departments")
    public HttpEntity<List<Department>> getAllDepartment() {

        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("/api/departments/{departmentId}")
    public HttpEntity<Department> getDepartment(@PathVariable Integer departmentId) {
       Department department = departmentService.getDepartment(departmentId);
       if (department == null){
           return ResponseEntity.badRequest().build();
       }
        return ResponseEntity.ok(department);
    }

    @PostMapping("/api/departments")
    public HttpEntity<ApiResponse> saveDepartment(@RequestBody RequestDepartmentDto newDepartment) {
        ApiResponse apiResponse = departmentService.saveDepartment(newDepartment);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @PutMapping("/api/departments/{departmentId}")
    public HttpEntity<ApiResponse> editDepartment(@PathVariable Integer departmentId, @RequestBody RequestDepartmentDto newDepartment) {
        ApiResponse apiResponse = departmentService.editDepartment(departmentId,newDepartment);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }


}

package uz.urinov.homework.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.urinov.homework.dto.request.RequestCompanyDto;
import uz.urinov.homework.dto.request.RequestDepartmentDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Department;
import uz.urinov.homework.service.CompanyService;
import uz.urinov.homework.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public HttpEntity<ApiResponse> saveDepartment(@RequestBody  @Valid RequestDepartmentDto newDepartment) {
        ApiResponse apiResponse = departmentService.saveDepartment(newDepartment);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @PutMapping("/api/departments/{departmentId}")
    public HttpEntity<ApiResponse> editDepartment(@PathVariable Integer departmentId,  @Valid  @RequestBody RequestDepartmentDto newDepartment) {
        ApiResponse apiResponse = departmentService.editDepartment(departmentId,newDepartment);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidError(MethodArgumentNotValidException exception){

        Map<String,String> errors = new HashMap();
        exception.getBindingResult().getAllErrors().forEach((error-> {

            String fieldName =( (FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);

        }));
        return errors;
    }


}

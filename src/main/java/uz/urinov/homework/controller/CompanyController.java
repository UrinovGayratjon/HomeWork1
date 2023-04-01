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
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/api/companies")
    public HttpEntity<List<Company>> getAllCompany() {

        return ResponseEntity.ok(companyService.getAllCompany());
    }

    @GetMapping("/api/companies/{companyId}")
    public HttpEntity<Company> getCompany(@PathVariable Integer companyId) {
       Company company = companyService.getCompany(companyId);
       if (company == null){
           return ResponseEntity.badRequest().build();
       }
        return ResponseEntity.ok(company);
    }

    @PostMapping("/api/companies")
    public HttpEntity<ApiResponse> saveCompany(@RequestBody  @Valid RequestCompanyDto newCustomer) {
       ApiResponse apiResponse= companyService.saveCompany(newCustomer);

       if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @PutMapping("/api/companies/{companyId}")
    public HttpEntity<ApiResponse> editCompany(@PathVariable Integer companyId, @RequestBody  @Valid  RequestCompanyDto newCompanyDto) {
       ApiResponse apiResponse = companyService.editCompany(companyId,newCompanyDto);
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

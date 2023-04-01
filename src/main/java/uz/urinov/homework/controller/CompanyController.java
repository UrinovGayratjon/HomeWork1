package uz.urinov.homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.homework.dto.request.RequestCompanyDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.service.CompanyService;

import java.util.List;

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
    public HttpEntity<ApiResponse> saveCompany(@RequestBody RequestCompanyDto newCustomer) {
       ApiResponse apiResponse= companyService.saveCompany(newCustomer);

       if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @PutMapping("/api/companies/{companyId}")
    public HttpEntity<ApiResponse> editCompany(@PathVariable Integer companyId, @RequestBody RequestCompanyDto newCompanyDto) {
       ApiResponse apiResponse = companyService.editCompany(companyId,newCompanyDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }


}

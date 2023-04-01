package uz.urinov.homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.homework.dto.request.RequestCompanyDto;
import uz.urinov.homework.dto.request.RequestWorkerDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Worker;
import uz.urinov.homework.service.CompanyService;
import uz.urinov.homework.service.WorkerService;

import java.util.List;

@RestController
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping("/api/workers")
    public HttpEntity<List<Worker>> getAllWorker() {

        return ResponseEntity.ok(workerService.getAllWorker());
    }

    @GetMapping("/api/workers/{workerId}")
    public HttpEntity<Worker> getWorker(@PathVariable Integer workerId) {
       Worker company = workerService.getWorker(workerId);
       if (company == null){
           return ResponseEntity.badRequest().build();
       }
        return ResponseEntity.ok(company);
    }

    @PostMapping("/api/workers")
    public HttpEntity<ApiResponse> saveWorker(@RequestBody RequestWorkerDto newCustomer) {
        ApiResponse apiResponse = workerService.saveWorker(newCustomer);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @PutMapping("/api/workers/{workerId}")
    public HttpEntity<ApiResponse> editWorker(@PathVariable Integer workerId, @RequestBody RequestWorkerDto newCustomer) {
        ApiResponse apiResponse = workerService.editWorker(workerId,newCustomer);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }


}

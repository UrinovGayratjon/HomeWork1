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
import uz.urinov.homework.dto.request.RequestWorkerDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.entity.Worker;
import uz.urinov.homework.service.CompanyService;
import uz.urinov.homework.service.WorkerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public HttpEntity<ApiResponse> saveWorker(@RequestBody  @Valid  RequestWorkerDto newCustomer) {
        ApiResponse apiResponse = workerService.saveWorker(newCustomer);
        if (apiResponse.isSuccess()){
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @PutMapping("/api/workers/{workerId}")
    public HttpEntity<ApiResponse> editWorker(@PathVariable Integer workerId, @Valid  @RequestBody RequestWorkerDto newCustomer) {
        ApiResponse apiResponse = workerService.editWorker(workerId,newCustomer);
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

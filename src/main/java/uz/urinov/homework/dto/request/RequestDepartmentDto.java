package uz.urinov.homework.dto.request;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.urinov.homework.entity.Company;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDepartmentDto {

    @NotNull(message = "name error")
    private String name;

    @NotNull(message = "companyId Error")
    private Integer companyId;

}

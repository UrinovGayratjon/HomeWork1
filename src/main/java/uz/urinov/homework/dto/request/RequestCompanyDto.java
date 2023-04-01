package uz.urinov.homework.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import uz.urinov.homework.entity.Address;
import uz.urinov.homework.service.CompanyService;
@Data
public class RequestCompanyDto {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;
}

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

    @NotNull(message = "Corp name error")
    private String corpName;

    @NotNull(message = "directorName  error")
    private String directorName;

    @NotNull(message = "street  error")
    private String street;

    @NotNull(message = "homeNumber  error")
    private Integer homeNumber;
}

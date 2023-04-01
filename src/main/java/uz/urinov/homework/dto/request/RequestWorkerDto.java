package uz.urinov.homework.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWorkerDto {

    @NotNull(message = "Name error")
    private String name;

    @NotNull(message = "phoneNumber error")
    private String phoneNumber;

    @NotNull(message = "street error")
    private String street;

    @NotNull(message = "homeNumber error")
    private Integer homeNumber;

    @NotNull(message = "departmentId error")
    private Integer departmentId;


}

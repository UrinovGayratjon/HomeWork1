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

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;

    @NotNull
    private Integer departmentId;


}

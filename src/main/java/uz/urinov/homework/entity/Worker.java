package uz.urinov.homework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;


    @Column(unique = true, nullable = false)
    private String phoneNumber;



    @ManyToOne
    private Address address;


    @ManyToOne
    private Department department;


}

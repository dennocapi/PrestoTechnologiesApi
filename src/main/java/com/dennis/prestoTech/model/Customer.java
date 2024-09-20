package com.dennis.prestoTech.model;

import jakarta.persistence.*;
        import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "emailAddress"),
        @UniqueConstraint(columnNames = "identificationNumber"),
        @UniqueConstraint(columnNames = "phoneNumber")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String identificationNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String residentialAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String emailAddress;

    private Boolean isDeleted = false;

}

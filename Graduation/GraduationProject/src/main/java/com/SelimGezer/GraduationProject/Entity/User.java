package com.SelimGezer.GraduationProject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Data
@Entity
@Table(name = "User")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long identificationNumber;

    @Column(length = 50)
    private String fullname;

    private BigDecimal monthlyIncome;

    @Column(length = 13)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private BigDecimal guaranteePrice;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Credit credit;

    public User(Long identificationNumber, String fullname, BigDecimal monthlyIncome, String phoneNumber, Date birthday,BigDecimal guaranteePrice) {
        this.identificationNumber = identificationNumber;
        this.fullname = fullname;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.guaranteePrice=guaranteePrice;
    }

    public Optional<BigDecimal> getGuaranteePrice() {
        return Optional.ofNullable(guaranteePrice);
    }
}

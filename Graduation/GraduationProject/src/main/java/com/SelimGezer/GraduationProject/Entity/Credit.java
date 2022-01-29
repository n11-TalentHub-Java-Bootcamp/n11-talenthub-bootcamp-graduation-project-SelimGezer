package com.SelimGezer.GraduationProject.Entity;

import com.SelimGezer.GraduationProject.Enum.CreditResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Credit")
@Getter
@Setter
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_foreignKey"))
    private User user;

    private BigDecimal creditLimit;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CreditResult creditResult;


    public Credit(User user, BigDecimal creditLimit, CreditResult creditResult) {
        this.user = user;
        this.creditLimit = creditLimit;
        this.creditResult = creditResult;
    }
}

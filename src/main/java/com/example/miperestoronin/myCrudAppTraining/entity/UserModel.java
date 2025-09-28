
package com.example.miperestoronin.myCrudAppTraining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private UserModel user;

    @OneToMany(mappedBy = "user") // ← имя поля в PhoneNumber
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();


    @OneToMany(mappedBy = "user") // ← имя поля в EmailModel
    private List<EmailModel> emails = new ArrayList<>();

}
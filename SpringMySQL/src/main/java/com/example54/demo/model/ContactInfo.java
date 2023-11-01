package com.example54.demo.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_info")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @SequenceGenerator(name = "student_generator",allocationSize = 1)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "phone", nullable = false)
    private String phone;


    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    public ContactInfo() {
    }

    public ContactInfo(Long id, String email, String phone, String address, String city, String state) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @OneToOne(mappedBy = "contactInfo", optional = false)
    private Student student;


}

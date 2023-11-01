package com.example54.demo.repositoriy.ContactInfoRepo;

import com.example54.demo.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoJpaRepository extends JpaRepository<ContactInfo,Long> {
}

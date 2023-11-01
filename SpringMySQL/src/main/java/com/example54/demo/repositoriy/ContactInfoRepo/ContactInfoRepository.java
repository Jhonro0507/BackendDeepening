package com.example54.demo.repositoriy.ContactInfoRepo;

import com.example54.demo.model.ContactInfo;
import com.example54.demo.model.Student;

import java.util.List;

public interface ContactInfoRepository {
    List<ContactInfo> findAll();
    ContactInfo findById(Long id);
    ContactInfo create(ContactInfo contactInfo);
    ContactInfo update(Long id, ContactInfo contactInfo);
    Boolean delete(Long id);
}

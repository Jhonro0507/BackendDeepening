package com.example54.demo.service.ContactInfoService;

import com.example54.demo.model.ContactInfo;

import java.util.List;

public interface ContactInfoService {
    List<ContactInfo> findAll();
    ContactInfo findById(Long id);
    ContactInfo create(ContactInfo contactInfo);
    ContactInfo update(Long id, ContactInfo contactInfo);
    Boolean deleteContactInfo(Long id);
}

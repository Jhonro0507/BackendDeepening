package com.example54.demo.controller.ContactInfoController;

import com.example54.demo.model.ContactInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactInfoController {
    List<ContactInfo> getAllContactInfo();
    ResponseEntity<ContactInfo> getContactInfoById(Long id);
    ResponseEntity<ContactInfo> createContactInfo(ContactInfo contactInfo);
    ResponseEntity<ContactInfo> updateContactInfo(Long id, ContactInfo contactInfo);
    ResponseEntity<Void> deleteContactInfo(Long id);


}

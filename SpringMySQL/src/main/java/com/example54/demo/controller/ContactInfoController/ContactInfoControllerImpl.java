package com.example54.demo.controller.ContactInfoController;

import com.example54.demo.model.ContactInfo;
import com.example54.demo.model.Student;
import com.example54.demo.repositoriy.ContactInfoRepo.ContactInfoRepositoryImpl;
import com.example54.demo.service.ContactInfoService.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/contactInfo")
public class ContactInfoControllerImpl implements ContactInfoController{
    private ContactInfoService contactInfoService;
    @Autowired
    public ContactInfoControllerImpl(ContactInfoService contactInfoService){
        this.contactInfoService=contactInfoService;
    }

    @GetMapping()
    public List<ContactInfo> getAllContactInfo() {
        return contactInfoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfoById(@PathVariable Long id) {
        ContactInfo contactInfo = contactInfoService.findById(id);
        if (contactInfo != null){
            return new ResponseEntity<>(contactInfo, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping()
    public ResponseEntity<ContactInfo> createContactInfo(@RequestBody ContactInfo contactInfo) {
        ContactInfo contactInfoCreated = contactInfoService.create(contactInfo);
        return new ResponseEntity<>(contactInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable Long id, @RequestBody ContactInfo contactInfo) {
        ContactInfo contactInfoUpdated = contactInfoService.update(id,contactInfo);
        if (contactInfoUpdated != null){
            return new ResponseEntity<>(contactInfoUpdated, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long id) {
        boolean success = contactInfoService.deleteContactInfo(id);
        if (success){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

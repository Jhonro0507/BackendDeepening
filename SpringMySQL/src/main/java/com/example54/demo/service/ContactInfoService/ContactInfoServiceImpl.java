package com.example54.demo.service.ContactInfoService;

import com.example54.demo.model.ContactInfo;
import com.example54.demo.repositoriy.ContactInfoRepo.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactInfoServiceImpl implements ContactInfoService{
    private ContactInfoRepository contactInfoRepository;
    @Autowired
    public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository){
        this.contactInfoRepository = contactInfoRepository;
    }
    @Override
    public List<ContactInfo> findAll() {
        return contactInfoRepository.findAll();
    }

    @Override
    public ContactInfo findById(Long id) {
        return contactInfoRepository.findById(id);
    }

    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        return contactInfoRepository.create(contactInfo);
    }

    @Override
    public ContactInfo update(Long id, ContactInfo contactInfo) {
        return contactInfoRepository.update(id,contactInfo);
    }

    @Override
    public Boolean deleteContactInfo(Long id) {
        return contactInfoRepository.delete(id);
    }
}

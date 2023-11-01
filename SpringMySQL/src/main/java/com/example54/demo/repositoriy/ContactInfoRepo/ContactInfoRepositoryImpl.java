package com.example54.demo.repositoriy.ContactInfoRepo;

import com.example54.demo.model.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ContactInfoRepositoryImpl implements ContactInfoRepository{
    private ContactInfoJpaRepository contactInfoJpaRepository;
    @Autowired
    public ContactInfoRepositoryImpl (ContactInfoJpaRepository contactInfoJpaRepository){
        this.contactInfoJpaRepository = contactInfoJpaRepository;
    }
    @Override
    public List<ContactInfo> findAll() {
        return contactInfoJpaRepository.findAll().size()>0 ? contactInfoJpaRepository.findAll():null;
    }

    @Override
    public ContactInfo findById(Long id) {
        return contactInfoJpaRepository.findById(id).isPresent() ? contactInfoJpaRepository.findById(id).get():null;
    }

    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        return contactInfoJpaRepository.save(contactInfo);
    }

    @Override
    public ContactInfo update(Long id, ContactInfo contactInfo) {
        ContactInfo contactInfoFound = findById(id);
        if (contactInfoFound != null){
            contactInfoFound.setAddress(contactInfo.getAddress());
            contactInfoFound.setCity(contactInfo.getCity());
            contactInfoFound.setEmail(contactInfo.getEmail());
            contactInfoFound.setState(contactInfo.getState());
            contactInfoFound.setPhone(contactInfo.getPhone());
            contactInfoFound.setStudent(contactInfo.getStudent());
            return contactInfoJpaRepository.save(contactInfoFound);
        }else{
            return null;
        }
    }


    @Override
    public Boolean delete(Long id) {
        ContactInfo contactInfoFound = findById(id);
        if (contactInfoFound != null){
            contactInfoJpaRepository.delete(contactInfoFound);
            return true;
        }else{
            return false;
        }
    }
}

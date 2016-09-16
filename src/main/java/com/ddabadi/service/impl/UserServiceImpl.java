package com.ddabadi.service.impl;

import com.ddabadi.domain.User;
import com.ddabadi.domain.repository.UserRepository;
import com.ddabadi.dto.UserAuthDto;
import com.ddabadi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUserId(String userId) {
        Iterator<User> users = repository.findByUserId(userId).iterator();

        if(users.hasNext()){
            return users.next();
        }else{
            return null;
        }

    }


    @Override
    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public User update(Long idUpdate, User user) {
        User userUpdate = repository.findOne(idUpdate);
        userUpdate.setNama(user.getNama());
        if(user.getPassword()==""){
            // tidak update password
        }else {
            userUpdate.setPassword(user.getPassword());
        }
        userUpdate.setGudang(user.getGudang());
        userUpdate.setUserId(user.getUserId());
        userUpdate.setStatus(user.getStatus());
        return repository.saveAndFlush(userUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getByNama(String nama, int hal, int jumlah) {
        PageRequest pageRequest= new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"nama");
        return repository.findByNamaLike("%" + nama + "%", pageRequest);
    }

    @Override
    public Boolean auth(UserAuthDto authDto) {
        Boolean isAuth = false;

        List<User> users = repository.findByUserId(authDto.getUserName());
        if(users.iterator().hasNext()){
            User user = users.iterator().next();
            if(user.getPassword().equals(authDto.getPasskey())){
                isAuth=true;
            }else{
                //NOT VALID AUTH
            }
        }
        //User user = repository.

        return isAuth;
    }



}

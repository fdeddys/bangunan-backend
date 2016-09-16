package com.ddabadi.service;

import com.ddabadi.domain.User;
import com.ddabadi.dto.UserAuthDto;
;
import org.springframework.data.domain.Page;

/**
 * Created by deddy on 5/26/16.
 */
public interface UserService {
    User getById(Long id);
    User getByUserId(String userId);
    User save(User user);
    User update(Long idUpdate, User user);
    Page<User> getByNama(String nama, int hal, int jumlah);
    Boolean auth(UserAuthDto authDto);
}

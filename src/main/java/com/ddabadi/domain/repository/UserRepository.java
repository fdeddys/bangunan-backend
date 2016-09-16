package com.ddabadi.domain.repository;

import com.ddabadi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    //@Query(value = "select new com.ddabadi.dto.UserDto(u.id, u.userId, u.nama) from User u where u.nama like :cariNama")
    Page<User> findByNamaLike(String nama, Pageable pageable);
    //Page<User> findByNamaLike(@Param("cariNama") String nama, Pageable pageable);

    List<User> findByUserId(String userId);

}

package com.ddabadi.web;

import com.ddabadi.domain.User;
import com.ddabadi.dto.UserAuthDto;
import com.ddabadi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by deddy on 5/26/16.
 */

@RestController
@RequestMapping(value = "api/user",
                produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/nama/{nama}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    Page<User> getByNamaPage(@PathVariable("nama")String nama,
                             @PathVariable("hal")int hal,
                             @PathVariable("jumlah")int jumlah){
        String cariNama;

        if(nama.equals("--")){
            cariNama="%";
        }else{
            cariNama=nama.trim();
        }

        return userService.getByNama(cariNama, hal, jumlah);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.GET)
    User  getByNama(@PathVariable("id")Long id){

        return userService.getById(id);
    }

    ///userId/' + userId
    @RequestMapping(value = "/userId/{userId}",
                    method = RequestMethod.GET)
    User  getByUserId(@PathVariable("userId")String userId){

        return userService.getByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    User  save(@RequestBody User user){

        return userService.save(user);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT)
    User  save(@PathVariable("id")Long id,
               @RequestBody User user){

        return userService.update(id, user);
    }

    //auth
    @RequestMapping(value = "auth",
            method = RequestMethod.POST)
    Boolean  loginAuth(@RequestBody UserAuthDto userAuthDto){

        return userService.auth(userAuthDto);
    }

}

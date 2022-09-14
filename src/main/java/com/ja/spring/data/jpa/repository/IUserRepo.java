package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    //From usuario where username = ?
    //Derived Query
    User findOneByUsername(String username);
}

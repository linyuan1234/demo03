package com.example.demo.service;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;


    public Account getAcctByid(Integer id){

        return accountMapper.getAcc(id);
    }

}

package com.mishcma.gradesubmission.service;


import com.mishcma.gradesubmission.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
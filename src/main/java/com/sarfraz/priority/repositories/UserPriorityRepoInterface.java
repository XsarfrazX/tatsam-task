package com.sarfraz.priority.repositories;

import com.sarfraz.priority.entity.UserPriority;

import java.util.Optional;

public interface UserPriorityRepoInterface {

    Optional<UserPriority> getUserPriority(String userName);
    boolean saveUserPriority(UserPriority userPriority);
}

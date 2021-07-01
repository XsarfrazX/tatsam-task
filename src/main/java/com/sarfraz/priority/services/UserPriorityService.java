package com.sarfraz.priority.services;

import com.sarfraz.priority.entity.UserPriority;
import com.sarfraz.priority.model.User;
import com.sarfraz.priority.repositories.UserPriorityRepoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPriorityService {

    private static final Logger log = LoggerFactory.getLogger(UserPriorityService.class);

    @Autowired
    UserPriorityRepoInterface userPriorityRepoInterface;

    /**
     *
     * @param userPriorities: User with priority for areas and satisfaction
     * @return http response code
     */
    Integer addUserPriority(User userPriorities) {

        UserPriority userPriority = new UserPriority(userPriorities.getUsername(), userPriorities.getPriorityAreas());

        boolean exists = userPriorityRepoInterface.getUserPriority(userPriority.getUserName()).isPresent();
        if(exists) {
            log.error("User with username: {} already has prioties set", userPriorities.getUsername());
            return 422;
        }

        boolean isSaved = userPriorityRepoInterface.saveUserPriority(userPriority);

        if(isSaved) {
            log.info("User's: {} priorities saved successfully", userPriorities.getUsername());
            return 201;
        }

        else {
            log.error("Error while saving User priorities to DB: {}" , userPriorities.toString());
            return 500;
        }

    }

    Optional<User> getUser(String userName) {
        Optional<UserPriority> savedUserPriority = userPriorityRepoInterface.getUserPriority(userName);

        if(savedUserPriority.isPresent()) {
            log.info("Got userriority details from DB: {}", savedUserPriority.toString());
            User userDto = new User().username(savedUserPriority.get().getUserName())
                    .priorityAreas(savedUserPriority.get().getAreaPriorityList());
            return Optional.of(userDto);
        }

        log.error("Did not find user priority record with username: {}", userName);
        return Optional.empty();

    }


}

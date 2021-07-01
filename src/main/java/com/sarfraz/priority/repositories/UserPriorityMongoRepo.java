package com.sarfraz.priority.repositories;

import com.sarfraz.priority.entity.UserPriority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@ConditionalOnProperty(name="database", havingValue = "mongodb")
@Repository
public class UserPriorityMongoRepo implements UserPriorityRepoInterface{
    private static final Logger log = LoggerFactory.getLogger(UserPriorityMongoRepo.class);
    private static final String USER_NAME_FIELD = "userName";

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        log.trace("Entering UserPriorityMongoRepo init");
        log.trace("DB name: {}, \n Collections {}, ",
                mongoTemplate.getDb().getName(), mongoTemplate.getCollectionNames());
    }

    @Override
    public Optional<UserPriority> getUserPriority(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(USER_NAME_FIELD).is(userName));
        log.info("Finding User priorities with username: {}", userName);

        return Optional.ofNullable(mongoTemplate.findOne(query, UserPriority.class));

    }

    @Override
    public boolean saveUserPriority(UserPriority userPriority) {

        log.info("In saveUserPriority: Saving User with priorities: {}", userPriority.toString());
        UserPriority savedUserPriority = mongoTemplate.insert(userPriority);
        if(!savedUserPriority.getUserName().equals(userPriority.getUserName())) {
            log.error(" Problem in inserted to MongoDB UserPriority: {}", userPriority.toString());
            return false;
        }
        return true;
    }


}

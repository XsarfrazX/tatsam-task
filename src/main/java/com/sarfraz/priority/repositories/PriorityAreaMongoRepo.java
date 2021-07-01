package com.sarfraz.priority.repositories;

import com.sarfraz.priority.entity.PriorityArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(name="database", havingValue = "mongodb")
@Repository
public class PriorityAreaMongoRepo implements  PriorityAreaRepoInterface{

    private static final Logger log = LoggerFactory.getLogger(PriorityAreaMongoRepo.class);
    private static final String NAME_FIELD = "name";

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        log.trace("Entering PriorityAreaMongoRepo init");
        log.trace("DB name: {}, \n Collections {}, ",
                mongoTemplate.getDb().getName(), mongoTemplate.getCollectionNames());
    }

    @Override
    public Optional<List<PriorityArea>> getAreaList() {

        return Optional.ofNullable(mongoTemplate.findAll(PriorityArea.class));
    }

    @Override
    public boolean saveArea(PriorityArea priorityArea) {

        log.info("In SaveArea inserting: {}", priorityArea.toString());
        PriorityArea priorityAreaSavedInDb = mongoTemplate.insert(priorityArea);

        if(!priorityAreaSavedInDb.getName().equals(priorityArea.getName())) {
            log.error(" Problem inserting Priority Area: {}", priorityArea.toString());
            return false;
        }

        log.info("Priority Area inserted in DB");
        return true;
    }

    @Override
    public boolean findArea(PriorityArea priorityArea) {
        Query query = new Query(Criteria.where(NAME_FIELD).is(priorityArea.getName()));

        log.info("Finding Priority Area: {}", priorityArea.toString());
        Optional<PriorityArea> priorityAreaInDB = Optional.ofNullable(mongoTemplate.findOne(query, PriorityArea.class));

        if(priorityAreaInDB.isPresent()) {
            log.info("Found Priority Area");
            return true;
        }
        log.info("Priority Area not found in DB");
        return false;

    }
}

package com.sarfraz.priority.services;


import com.sarfraz.priority.entity.PriorityArea;
import com.sarfraz.priority.model.Area;
import com.sarfraz.priority.repositories.PriorityAreaRepoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriorityAreaService {

    private static final Logger log = LoggerFactory.getLogger(PriorityAreaService.class);

    @Autowired
    private PriorityAreaRepoInterface priorityAreaRepoInterface;

    public Optional<List<Area>> getAllPriorityAreas() {

        Optional<List<PriorityArea>> priorityAreas = priorityAreaRepoInterface.getAreaList();
        if(priorityAreas.isPresent()) {
            List<Area> priorityAreaDTO = new ArrayList<>();
            // Mongo collection to DTO
            for(PriorityArea priorityArea: priorityAreas.get()) {
                priorityAreaDTO.add(new Area().name(priorityArea.getName()));

            }
            if(priorityAreaDTO.size() == 0) {
                log.error("Priority area count is 0");
                return Optional.empty();
            }
            log.info("Found priority areas in DB with count: {}", priorityAreaDTO.size());
            return Optional.of(priorityAreaDTO);

        }

        else {
            log.error("No Priority Areas found in DB");
            return Optional.empty();
        }


    }


    /**
     *
     * @param area Priority area to set
     * @return type is Httpreponse in Integer
     */
    public Integer addPriorityArea(Area area) {
        PriorityArea priorityAreaToSave = new PriorityArea(area.getName());

        boolean isPresent = priorityAreaRepoInterface.findArea(priorityAreaToSave);
        if(isPresent) {
            log.error("Priority area: {} is already present", area.getName());
            return 422;
        }

            boolean isInserted = priorityAreaRepoInterface.saveArea(priorityAreaToSave);
            if(isInserted) {
                log.info("Saved Area: {} in DB", area.toString());
                return 201;
            }
            log.info("Error saving Area: {} in DB", area.toString());

            return 500;


    }
}

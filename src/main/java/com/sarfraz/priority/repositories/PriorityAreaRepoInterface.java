package com.sarfraz.priority.repositories;

import com.sarfraz.priority.entity.PriorityArea;

import java.util.List;
import java.util.Optional;

public interface PriorityAreaRepoInterface {

    Optional<List<PriorityArea>> getAreaList();
    boolean saveArea(PriorityArea priorityArea);
    boolean findArea(PriorityArea priorityArea);
}

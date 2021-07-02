package com.sarfraz.priority.api;

import com.sarfraz.priority.entity.UserPriority;
import com.sarfraz.priority.model.Area;
import com.sarfraz.priority.model.AreaPriority;
import com.sarfraz.priority.services.PriorityAreaService;
import com.sarfraz.priority.services.UserPriorityService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;

import com.sarfraz.priority.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.websocket.server.PathParam;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-01T20:49:10.499858+05:30[Asia/Calcutta]")
@Controller
@RequestMapping("${openapi.tatsamPriority.base-path:/v1}")
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);
    private final NativeWebRequest request;

    @Autowired
    private UserPriorityService userPriorityService;

    @Autowired
    private PriorityAreaService priorityAreaService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * Post a user Priorities 
     */
    @Override
    public ResponseEntity<String> usersPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user) {

        Map<Boolean,String> isBodyValid = validateBody(user);
        if(isBodyValid.containsKey(false)) {
            return new ResponseEntity<String>(isBodyValid.get(false), HttpStatus.BAD_REQUEST);
        }

        Integer responseCode = userPriorityService.addUserPriority(user);
        if( responseCode == 201) {
            String successMessage = "User priority data successfully inserted";
            log.info(successMessage);
            return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);

        }
        else if( responseCode == 422) {
            String errorMessage = "User: " + user.toString() +" is already present";
            log.error(errorMessage);
            return new ResponseEntity<String>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String errorMessage = "Server error while saveing to DB";
        log.error(errorMessage);
        return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * Get a user priorities by username in path variable
     */
    @Override
    public ResponseEntity<User> usersUsernameGet(@PathVariable String username) {

        Optional<User> savedUser = userPriorityService.getUser(username);
        if(savedUser.isPresent()) {
            log.info("User priority details are present");
            return new ResponseEntity<User>(savedUser.get(), HttpStatus.OK);
        }
        String errorMessage = "No record found for user " + username;
        log.error(errorMessage);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //TODO: CHeck for duplicate priotity and area
    private Map<Boolean, String> validateBody(User user) {
        String errorMsg ="";
        Map<Boolean,String> result = new HashMap<>();

        // Check if username is null or empty
        if(user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            errorMsg = "Username is invalid";
            log.error(errorMsg);
            result.put(false,errorMsg);
            return result;

        }
        // Check if array of priority areas is null or empty
        if(user.getPriorityAreas() == null || user.getPriorityAreas().isEmpty()) {
            errorMsg = "No priority area set by user";
            log.error(errorMsg);
            result.put(false,errorMsg);
            return result;


        }
        // Get all priority areas present in DB
        List<Area> savedAreas = priorityAreaService.getAllPriorityAreas().isPresent() ? priorityAreaService.getAllPriorityAreas().get() :
                new ArrayList<>();

        Integer priorityAreaCount = savedAreas.size();

        // Get names of priority areas
        List<String> savedPriorityAreaNames = savedAreas.stream().map(area -> area.getName()).collect(Collectors.toList());

        // If the array of priority area's in user request is more than "Priority areas" present in DB, user has provided an invalid area
        if(user.getPriorityAreas().size() > priorityAreaCount)
        {
            errorMsg = "Priority areas set are more than what is present in DB";
            log.error(errorMsg);
            result.put(false,errorMsg);
            return result;
        }

        for(AreaPriority userPriorityArea: user.getPriorityAreas()) {
            // Check if area of priority is null or empty
            if(userPriorityArea.getArea() == null || userPriorityArea.getArea().getName() == null || userPriorityArea.getArea().getName().isEmpty()) {
                errorMsg = "User priority Area name is null";
                log.error(errorMsg);
                result.put(false,errorMsg);
                return result;

            }

            // If an priority area which isnt present in priotity area db is present, it is invalid
            if(!savedPriorityAreaNames.contains(userPriorityArea.getArea().getName())) {
                errorMsg = "Area of priority: " + userPriorityArea.getArea().getName() + " is not present in DB";
                log.error(errorMsg);
                result.put(false,errorMsg);
                return result;

            }

            // Priority of an area is either null, or invalid i.e. < 1 or > max priority which can be set
            if(userPriorityArea.getPriority() == null || userPriorityArea.getPriority() < 1 || userPriorityArea.getPriority() > priorityAreaCount) {
                errorMsg = "Priority of area " + userPriorityArea.getArea().getName() + "is invalid";
                log.error(errorMsg);
                result.put(false,errorMsg);
                return  result;

            }
        }


        // Check for duplicate priority area names and priorities
        List<Integer> priorities = user.getPriorityAreas().stream().map(area -> area.getPriority()).collect(Collectors.toList());
        List<String> priorityAreas = user.getPriorityAreas().stream().map(area -> area.getArea().getName()).collect(Collectors.toList());
        if(priorities.size() != new HashSet<Integer>(priorities).size()) {
            errorMsg = "Duplicate priority values";
            log.error(errorMsg);
            result.put(false, errorMsg);
            return result;
        }
        if(priorityAreas.size() != new HashSet<String >(priorityAreas).size()) {
            errorMsg = "Duplicate priority area names";
            log.error(errorMsg);
            result.put(false, errorMsg);
            return result;
        }


        result.put(true, "valid");
        return result;




    }

}

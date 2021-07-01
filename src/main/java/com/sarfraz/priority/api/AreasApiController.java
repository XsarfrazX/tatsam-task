package com.sarfraz.priority.api;
import com.sarfraz.priority.entity.PriorityArea;
import com.sarfraz.priority.model.Area;
import com.sarfraz.priority.services.PriorityAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-01T20:49:10.499858+05:30[Asia/Calcutta]")
@Controller
@RequestMapping("${openapi.tatsamPriority.base-path:/v1}")
public class AreasApiController implements AreasApi {

    private static final Logger log = LoggerFactory.getLogger(AreasApiController.class);
    private final NativeWebRequest request;

    @Autowired
    private PriorityAreaService priorityAreaService;

    @org.springframework.beans.factory.annotation.Autowired
    public AreasApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    /**
     * List all the Priority Areas
     */
    @Override
    public ResponseEntity<List<Area>> areasGet() {

        Optional<List<Area>> priorityAreas = priorityAreaService.getAllPriorityAreas();

        if(priorityAreas.isPresent()) {
            return new ResponseEntity<List<Area>>( priorityAreas.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Add a priority area to DB
     */
    @Override
    public ResponseEntity<String> areasPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Area area) {

        if(area == null || area.getName() == null || area.getName().isEmpty()) {
            String errorMessage = "Request body is not valid";
            log.error(errorMessage);
            return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Integer isAdded = priorityAreaService.addPriorityArea(area);

        if(isAdded == 201) {
            String successMessaage = "Priority Area " + area.getName() + " successfully added!!";
            log.info(successMessaage);
            return new ResponseEntity<String>(successMessaage, HttpStatus.CREATED);
        }
        else if(isAdded == 422) {
            String errorMessage = "Priority Area " + area.getName() + "is already present";
            log.error(errorMessage);
            return new ResponseEntity<String>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);


        }
        else {
            String errorMessage = "Server error while inserting";
            log.error(errorMessage);
            return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }



}

package com.sarfraz.priority.api;
import com.sarfraz.priority.model.Area;
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

    private final NativeWebRequest request;

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
        Area a = new Area();

        return ResponseEntity.ok(Arrays.asList(a));
    }

    /**
     * Add a priority area to DB
     */
    @Override
    public ResponseEntity<String> areasPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Area area) {
        return  ResponseEntity.ok("hi");

    }

}

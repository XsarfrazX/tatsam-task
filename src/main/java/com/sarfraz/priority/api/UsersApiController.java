package com.sarfraz.priority.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
import javax.websocket.server.PathParam;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-01T20:49:10.499858+05:30[Asia/Calcutta]")
@Controller
@RequestMapping("${openapi.tatsamPriority.base-path:/v1}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;

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
        return ResponseEntity.ok("hi");

    }

    /**
     * Get a user priorities by username in path variable
     */
    @Override
    public ResponseEntity<User> usersUsernameGet(@PathVariable String username) {
        User u = new User();
        u.setUsername(username);
        return  ResponseEntity.ok(u);
    }

}

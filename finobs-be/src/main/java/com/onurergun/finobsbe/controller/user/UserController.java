package com.onurergun.finobsbe.controller.user;

import com.onurergun.finobsbe.application.user.UserApp;
import com.onurergun.finobsbe.application.user.UserDto;
import com.onurergun.finobsbe.common.APIResponseBody;
import com.onurergun.finobsbe.common.APIResponseBodyError;
import com.onurergun.finobsbe.common.AbstractAPIResponseBody;
import com.onurergun.finobsbe.infrastructure.user.UserDao;
import com.onurergun.finobsbe.infrastructure.user.UserDtoMapper;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${finobs.api-prefix}" + "users")
public class UserController {

  private final UserApp userService;

  private final UserDtoMapper userDtoMapper;

  public UserController(UserApp userService, UserDtoMapper userDtoMapper) {
    this.userService = userService;
    this.userDtoMapper = userDtoMapper;
  }

  @GetMapping
  public ResponseEntity<AbstractAPIResponseBody> getAllUsers(HttpServletRequest request) {
    APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
    responseBody.setMessage("All users listed");

    List<UserDao> users = userService.getAllUsers();
    List<UserDto> usersDto = users.stream().map((user) -> userDtoMapper.userToUserDto(user)).collect(Collectors.toList());

    responseBody.setResponseData(usersDto);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<AbstractAPIResponseBody> createUser(
      HttpServletRequest request,
      @Valid @RequestBody UserDto userDto) {

    request.setAttribute("userDto", userDto);

    UserDao user = userDtoMapper.userDtoToUser(userDto);
    UserDao createdUser = userService.createUser(user);

    APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
    responseBody.setMessage("User created");
    fillResponseBodyRequestData(responseBody, userDto);

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<AbstractAPIResponseBody> createUser(
          HttpServletRequest request,
          @Valid @RequestBody CreateUserRequest createUserRequest) {

    request.setAttribute("userDto", createUserRequest);

    UserDto userDto = userDtoMapper.createUserRequestToUserDto(createUserRequest);
    UserDto createdUser = userService.createUser(userDto);

    APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
    responseBody.setMessage("User created");
    fillResponseBodyRequestData(responseBody, userDto);

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<APIResponseBodyError> handleValidationExceptions(
          HttpServletRequest request,
          MethodArgumentNotValidException ex) {

    APIResponseBodyError errorBody = new APIResponseBodyError(request.getServletPath());
    errorBody.setMessage("Invalid value(s) encountered");
    fillResponseBodyRequestData(errorBody, (UserDto)ex.getTarget());

    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errorBody.addUpdateErrorParam(fieldName, errorMessage);
            });

    return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<APIResponseBodyError> handleDataIntegrityViolationExceptions(
          HttpServletRequest request,
          DataIntegrityViolationException ex) {

    APIResponseBodyError errorBody = new APIResponseBodyError(request.getServletPath());
    fillResponseBodyRequestData(errorBody, (UserDto)request.getAttribute("userDto"));

    String mostSpecificCauseMessage = ex.getMostSpecificCause().getMessage();
    String[] parsedCauseMessage = mostSpecificCauseMessage.split("Detail:");
    String message = parsedCauseMessage[0];
    if (parsedCauseMessage.length > 1)
    {
      message = parsedCauseMessage[1];
    }
    errorBody.setMessage(message);

    if (ex.getCause() instanceof ConstraintViolationException constraintViolationEx) {
      String constraintName = constraintViolationEx.getConstraintName();

      if (constraintName.equals(UserDao.UNIQUE_CONSTRAINT_USERNAME)) {
        errorBody.addUpdateErrorParam("userName", "in use");
      }
      else if (constraintName.equals(UserDao.UNIQUE_CONSTRAINT_EMAIL)) {
        errorBody.addUpdateErrorParam("email", "in use");
      }
      else {
        String errorMessage = constraintViolationEx.getMessage();
        errorBody.addUpdateErrorParam(constraintName, errorMessage);
      }
    }
    return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
  }

  private void fillResponseBodyRequestData(AbstractAPIResponseBody responseBody, UserDto userDto) {
    responseBody.addUpdateRequestDataParam("firstName", userDto.getFirstName());
    responseBody.addUpdateRequestDataParam("lastName", userDto.getLastName());
    responseBody.addUpdateRequestDataParam("userName", userDto.getUserName());
    responseBody.addUpdateRequestDataParam("email", userDto.getEmail());
    responseBody.addUpdateRequestDataParam("password", userDto.getPassword());
  }
}

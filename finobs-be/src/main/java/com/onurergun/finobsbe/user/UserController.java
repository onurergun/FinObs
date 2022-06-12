package com.onurergun.finobsbe.user;

import com.onurergun.finobsbe.common.APIResponseBody;
import com.onurergun.finobsbe.common.APIResponseBodyError;
import com.onurergun.finobsbe.common.AbstractAPIResponseBody;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${apiPrefix}" + "users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserDtoUserMapper userMapper;

  @GetMapping
  public ResponseEntity<AbstractAPIResponseBody> getAllUsers(HttpServletRequest request) {
    APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
    responseBody.setMessage("All users listed");

    List<User> users = userService.getAllUsers();
    List<UserDto> usersDto = users.stream().map((user) -> userMapper.userToUserDto(user)).collect(Collectors.toList());

    responseBody.setResponseData(usersDto);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<AbstractAPIResponseBody> createUser(
      HttpServletRequest request, @Valid @RequestBody UserDto userDto) {

    request.setAttribute("userDto", userDto);

    User user = userMapper.userDtoToUser(userDto);
    User createdUser = userService.createUser(user);

    APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
    responseBody.setMessage("User created");
    fillResponseBodyRequestData(responseBody, userDto);

    return new ResponseEntity(responseBody, HttpStatus.OK);
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

    return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
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

    if (ex.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException constraintViolationEx = (ConstraintViolationException)ex.getCause();
      String constraintName = constraintViolationEx.getConstraintName();

      if (constraintName.equals(User.UNIQUE_CONSTRAINT_USERNAME)) {
        errorBody.addUpdateErrorParam("userName", "in use");
      }
      else if (constraintName.equals(User.UNIQUE_CONSTRAINT_EMAIL)) {
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

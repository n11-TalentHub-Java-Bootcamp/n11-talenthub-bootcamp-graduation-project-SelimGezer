package com.SelimGezer.GraduationProject.Controller;


import com.SelimGezer.GraduationProject.Dtos.UserRequestDto;
import com.SelimGezer.GraduationProject.Dtos.UserResponceDto;
import com.SelimGezer.GraduationProject.Dtos.UserUpdateDto;
import com.SelimGezer.GraduationProject.Service.EntityService.UserEntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/users")
@Api(value = "User Api documentation")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @ApiOperation(value = "New user adding method")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponceDto saveUser( @Valid @RequestBody UserRequestDto userRequestDto){
        return userEntityService.saveUser(userRequestDto);
    }

    @ApiOperation(value = "All user list method")
    @GetMapping()
    public List<UserResponceDto> getAllUser(){
        return userEntityService.getAllUser();
    }

    @ApiOperation(value = "Get user by identification number method")
    @GetMapping("/{identificationNumber}")
    public UserResponceDto getUserByIdentificationNumber(@PathVariable("identificationNumber") Long identificationNumber){
        return userEntityService.getUserByIdentificationNumber(identificationNumber);
    }

    @ApiOperation(value = "Update user by identification number method")
    @PutMapping("/{identificationNumber}")
    public UserResponceDto updateUserByIdentificationNumber(@PathVariable("identificationNumber") Long identificationNumber, @Valid @RequestBody UserUpdateDto userUpdateDto){
        return userEntityService.updateUserByIdentificationNumber(identificationNumber,userUpdateDto);
    }

    @ApiOperation(value = "Delete user by identification number method")
    @DeleteMapping("/{identificationNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByIdentificationNumber(@PathVariable("identificationNumber") Long identificationNumber){
        userEntityService.deleteUserByIdentificationNumber(identificationNumber);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}

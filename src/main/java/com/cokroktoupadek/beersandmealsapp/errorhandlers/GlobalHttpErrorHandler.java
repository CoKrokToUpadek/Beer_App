package com.cokroktoupadek.beersandmealsapp.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BeerNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(BeerNotFoundException exception){
        return new ResponseEntity<>("Beer with specified parameters does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BeerDbIsEmptyException.class)
    public ResponseEntity<Object> handleEmptyBeerDb(BeerDbIsEmptyException exception){
        return new ResponseEntity<>("Beer Db is empty", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MealDbIsEmptyException.class)
    public ResponseEntity<Object> handleEmptyMealDb(MealDbIsEmptyException exception){
        return new ResponseEntity<>("Meal Db is empty", HttpStatus.BAD_REQUEST);
    }
}

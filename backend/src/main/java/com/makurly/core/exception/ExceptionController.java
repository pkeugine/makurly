package com.makurly.core.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionDto> notFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto("해당 상품이 존재하지 않습니다."));
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<ExceptionDto> alreadyExist(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto("해당 이름의 유저는 이미 존재합니다."));
    }

    @ExceptionHandler({UserNotExistException.class})
    public ResponseEntity<ExceptionDto> userNotExist(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto("해당 유저 정보가 없습니다."));
    }
}

package com.in.pathshala.onlineBookStore.Dto.exceptions;

public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException(String msg) {
        super(msg);
    }
}

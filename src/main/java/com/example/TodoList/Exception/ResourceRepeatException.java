package com.example.TodoList.Exception;

public class ResourceRepeatException extends RuntimeException{
    public ResourceRepeatException(String message){
        super(message);
    }
}

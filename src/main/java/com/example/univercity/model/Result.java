package com.example.univercity.model;

import lombok.Data;

@Data
public class Result {

    private boolean success;
    private String message;
    private Object data;
    private String exception;

    public Result(boolean success, String message, Exception exception) {
        this.success = success;
        this.message = message;
        this.exception = exception.getMessage();
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result() {

    }

    public Result success(Object data) {
        return new Result(true, "success", data);
    }

    public Result error(Exception e) {
        return new Result(false, "fail", e);
    }

    public Result delete(){
        return new Result(true,"deleted success");
    }


}

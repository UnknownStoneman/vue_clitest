package com.example.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

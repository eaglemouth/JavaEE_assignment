package edu.whu.demo.exception;

import lombok.Data;

@Data
public class ControllerException extends Exception{
    public final static int INPUT_ERROR = 101;
    public final static int DELETE_ERROR = 102;
    public final static int UPDATE_ERROR = 103;
    public final static int FIND_ERROR = 104;


    int code;
    public ControllerException(int code, String message){
        super(message);
        this.code=code;
    }
}

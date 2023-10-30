package edu.whu.demo.exception;

import edu.whu.demo.domain.Product;

public class ProductAdminException extends Exception{
    public ProductAdminException(String message){
        super(message);
    }
}

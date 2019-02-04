package com.yang.exceptions;

public class ShopOperationException extends RuntimeException{

    private static final long serialVersionUID = 8547853499906896407L;

    public ShopOperationException(String msg){
        super(msg);
    }
}

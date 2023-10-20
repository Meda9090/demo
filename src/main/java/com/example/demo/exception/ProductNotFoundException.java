package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException { //Исключение «Продукт не найден»

    public ProductNotFoundException() {
        super();
    }

    // Constructor
    public ProductNotFoundException(String message) {
        super(message);
    }
}


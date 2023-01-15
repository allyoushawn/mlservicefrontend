package com.allyoushawn.mlservicefrontend;

import lombok.Data;

@Data
public class Greeting {

    private String id = "request_123";
    private String content = "This is very good.";
    private String result;
    private String service;

}

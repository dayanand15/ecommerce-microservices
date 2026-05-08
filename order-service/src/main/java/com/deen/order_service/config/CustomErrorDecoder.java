//package com.deen.order_service.config;
//
//import com.deen.order_service.exception.UserNotFoundException;
//import feign.Response;
//import feign.codec.ErrorDecoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomErrorDecoder implements ErrorDecoder {
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//
//        if (response.status() == 404) {
//            return new UserNotFoundException("User not found");
//        }
//
//        if (response.status() == 400) {
//            return new RuntimeException("Bad request from service");
//        }
//
//        return new RuntimeException("Feign client error");
//    }
//}
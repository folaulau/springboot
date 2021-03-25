package com.lovemesomecoding.restassured;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

class UserTests {

    @BeforeEach
    public void setup() {

    }

    @Test
    void test_post() throws URISyntaxException {
        System.out.println("test");

        Response response = when().post(new URI("https://jsonplaceholder.typicode.com/todos/1")).andReturn();

        System.out.println(response.asString());
    }

    @Test
    void test_get() throws URISyntaxException {
        System.out.println("test");

        Response response = when().get(new URI("https://jsonplaceholder.typicode.com/todos/1")).andReturn();

        System.out.println(response.asString());
    }

}

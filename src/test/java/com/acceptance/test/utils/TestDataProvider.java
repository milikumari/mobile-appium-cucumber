package com.acceptance.test.utils;

import com.acceptance.test.testDataModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TestDataProvider {

    public TestDataProvider(){
    }


    private   User user;
    public User getUser(){

        User user = new User();
        String testEnv= System.getProperty("test.env");
        ObjectMapper mapper = new ObjectMapper();
        try {
            user= mapper.readValue(new File(System.getProperty("user.dir")+"/src/test/resources/testData/"+testEnv+"/testData.json"),User.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

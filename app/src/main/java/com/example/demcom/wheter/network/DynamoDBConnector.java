package com.example.demcom.wheter.network;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by Demcom on 4/29/2017.
 */
public class DynamoDBConnector {
    private static DynamoDBConnector ourInstance = new DynamoDBConnector();
    private CognitoCachingCredentialsProvider cognitoCredentialsProvider;
    public static DynamoDBConnector getInstance() {
        return ourInstance;
    }


    private DynamoDBConnector() {
    }


    public void initCredentials(){

    }

    public void getWeatherInformation(){


    }
}

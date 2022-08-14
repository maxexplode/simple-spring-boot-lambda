package org.example;

import com.amazonaws.services.dynamodbv2.local.main.CommandLineInput;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Inventory;
import org.junit.BeforeClass;
import org.junit.Test;

public class InventoryDataTest {
    static DynamoDBProxyServer dynamoDBProxyServer;

    @BeforeClass
    public static void setup() throws Exception
    {
        String port = "8000";
        dynamoDBProxyServer = ServerRunner.createServer(new CommandLineInput(
                new String[]{"-inMemory", "-port", port} ));

        dynamoDBProxyServer.start();
    }

    @Test
    public void testInsert() throws JsonProcessingException {
        Inventory inventory
                 = new Inventory();
        inventory.setInventoryId(123L);
        inventory.setItemName("abc");
        inventory.setItemQuantity(5);
        inventory.setItemPrice(50.5);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(inventory));

    }
}

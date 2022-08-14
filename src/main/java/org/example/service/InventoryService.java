package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Inventory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private static final DynamoDbEnhancedClient DB_ENHANCED_CLIENT =
            DynamoDbEnhancedClient.create();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final DynamoDbTable<Inventory> inventory = DB_ENHANCED_CLIENT
            .table("inventory", TableSchema.fromBean(Inventory.class));

    public Inventory getItem(Long id)
    {
        Inventory key = new Inventory();
        key.setInventoryId(id);
        return inventory.getItem(key);
    }

    public List<Inventory> getItems()
    {
        return inventory.scan().items().stream().collect(Collectors.toList());
    }

    public void creteItem(Inventory item)
    {
        try {
            System.out.println("item : " + OBJECT_MAPPER.writeValueAsString(item));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        inventory.putItem(item);
    }

}

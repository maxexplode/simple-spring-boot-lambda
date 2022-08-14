package org.example.controller;

import org.example.domain.Inventory;
import org.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable Long id) {
        return inventoryService.getItem(id);
    }

    @GetMapping
    public List<Inventory> getInventoryList() {
        return inventoryService.getItems();
    }

    @PostMapping
    public void createInventory(@RequestBody Inventory item) {
        inventoryService.creteItem(item);
    }


}

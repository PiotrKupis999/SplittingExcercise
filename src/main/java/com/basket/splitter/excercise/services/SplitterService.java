package com.basket.splitter.excercise.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SplitterService {

    private Map<String, List<String>> productDeliveryMap;

    public SplitterService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.productDeliveryMap = mapper.readValue(new File("src/main/resources/config.json"), Map.class);
    }

    public Map<String, List<String>> splitBasket(List<String> items) {

        Map<String, List<String>> deliveryGroups = GetSortedDeliveryGroups(items);


        Map<String, List<String>> optimatedGroup = new HashMap<>();
        String maxKey = null;

        while (items.size() != 0){

            int maxSet = 0;
            //find type of delivery with max amount of products
            for (String deliveryOption : deliveryGroups.keySet()){
                if (deliveryGroups.get(deliveryOption).size() > maxSet){
                    maxKey = deliveryOption;
                    maxSet = deliveryGroups.get(deliveryOption).size();
                }
            }

            //put the biggest one to hashmap
            optimatedGroup.put(maxKey, deliveryGroups.get(maxKey));

            //remove all used products from main list
            for (String product : deliveryGroups.get(maxKey)){
                items.remove(product);
            }

            //sort the main list
            deliveryGroups = GetSortedDeliveryGroups(items);
        }

        return optimatedGroup;
    }

    //grouping the products by type of delivery
    public Map<String, List<String>> GetSortedDeliveryGroups(List<String> items) {
        Map<String, List<String>> deliveryGroups = new HashMap<>();

        for (String item : items) {
            List<String> deliveryOptions = productDeliveryMap.get(item);
            if (deliveryOptions != null) {
                for (String deliveryOption : deliveryOptions) {
                    // check if the delivery option already exists in the deliveryGroups map
                    // if not, add it with an empty list
                    List<String> groupItems = deliveryGroups.computeIfAbsent(deliveryOption, k -> new ArrayList<>());

                    // add the current item to the list of items for the current delivery option
                    groupItems.add(item);
                }
            }
        }

        return deliveryGroups;
    }
}

package com.basket.splitter.excercise;

import com.basket.splitter.excercise.services.SplitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitterServiceTest {

    private SplitterService splitterService;

    @BeforeEach
    void setUp() throws IOException {
        splitterService = new SplitterService();
    }

    @Test
    void testSplitBasket() {
        // test data
        List<String> items = new ArrayList<String>();
        items.add("Cookies Oatmeal Raisin");
        items.add("Cheese Cloth");
        items.add("English Muffin");

        // expectation
        Map<String, List<String>> expectedLayout = new HashMap<>();
        expectedLayout.put("Parcel locker", Arrays.asList("Cookies Oatmeal Raisin", "Cheese Cloth", "English Muffin"));


        // test the method
        Map<String, List<String>> optimalLayout = splitterService.splitBasket(items);

        // result
        assertEquals(expectedLayout, optimalLayout);
    }

    @Test
    void testSplitBasket2() {
        // test data
        List<String> items = new ArrayList<String>();
        items.add("Sugar - Cubes");
        items.add("Otomegusa Dashi Konbu");
        items.add("Flour - Buckwheat, Dark");
        items.add("Salt - Rock, Course");

        // expectation
        Map<String, List<String>> expectedLayout = new HashMap<>();

        expectedLayout.put("Courier", Arrays.asList("Otomegusa Dashi Konbu", "Flour - Buckwheat, Dark", "Salt - Rock, Course"));

        Map<String, List<String>> expectedGroups = new HashMap<>();
        expectedGroups.put("Same day delivery", Arrays.asList("Sugar - Cubes"));
        expectedGroups.put("Courier", Arrays.asList("Otomegusa Dashi Konbu", "Flour - Buckwheat, Dark", "Salt - Rock, Course"));

        // test the method
        Map<String, List<String>> deliveryGroups = splitterService.splitBasket(items);

        // result
        assertEquals(expectedGroups, deliveryGroups);
    }




}
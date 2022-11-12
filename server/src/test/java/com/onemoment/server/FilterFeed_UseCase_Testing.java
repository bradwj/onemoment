package com.onemoment.server;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterFeed_UseCase_Testing {
    //
    @Test
    void filter_TC1() {
        //all values valid
        assertEquals( FilterFeed.sort("new"), "Feed Filtered Successfully");
    }

    @Test
    void filter_TC2() {
        //Invalid filter
        assertEquals( FilterFeed.sort("smelly"), "An error occurred when attempting to apply the filter");

    }
}
package com.onemoment.server;

package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
class AdminGUITest {
    AdminGUI gui;
    Report testReport1;
    Report testReport2;
    @BeforeEach
    void setUp() {
        gui = new AdminGUI();
        // Test users
        User testUser1 = new User("JackBlack002", "TenaciousD53");
        User testUser2 = new User("KingKrill", "seafoodFtw15");

        // Reports for the test users
        testReport1 = new Report(testUser1, "Bullying/Harassment");
        testReport2 = new Report(testUser2, "Inappropriate content");

        // Adding test users to database
        DBMgr.addUser(testUser1);
        DBMgr.addUser(testUser2);

        // Adding test reports to database
        DBMgr.addReport(testReport1);
        DBMgr.addReport(testReport2);


    }

    @AfterEach
    void tearDown() {
        // Clearing users
        DBMgr.removeUser("JackBlack002");
        DBMgr.removeUser("KingKrill");
    }

    @Test
    void viewReports() {
        // Test case to ensure reports in database are the same as ones returned by viewReports
        ArrayList<Report> reports = new ArrayList<Report>();
        reports.add(testReport1);
        reports.add(testReport2);
        assertEquals(reports.get(0), gui.viewReports().get(0));
        assertEquals(reports.get(1), gui.viewReports().get(1));
    }

    @Test
    void banTemporarily() {
        // Testing case for banning JackBlack002 for 1 day
        assertEquals("User JackBlack002 has been successfully banned for 86400 seconds", gui.banTemporarily(testReport1, 86400));
        // Test case for invalid ban length
        assertEquals("Invalid ban length", gui.banTemporarily(testReport2, -1500));
    }

    @Test
    void banPermanently() {
        // Test case for banning JackBlack002 permanently
        assertEquals("User JackBlack002 has been successfully permanently banned", gui.banPermanently(testReport1));
    }
}
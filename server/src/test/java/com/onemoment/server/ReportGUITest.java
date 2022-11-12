package com.onemoment.server;

package org.example;

import static org.junit.jupiter.api.Assertions.*;

class ReportGUITest {
    ReportGUI gui;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gui = new ReportGUI();
        DBMgr.addUser(new User("JackBlack002", "TenaciousD53"));
        DBMgr.addUser(new User("KingKrill", "seafoodFtw15"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        DBMgr.removeUser("JackBlack002");
        DBMgr.removeUser("KingKrill");
    }

    @org.junit.jupiter.api.Test
    void report() {
        assertEquals("Thank you for your report of JackBlack002", gui.report("JackBlack002", "Bullying/Harassment"));
        assertEquals("User does not exist", gui.report("FakeUser", "Inappropriate content"));
        assertEquals("User does not exist", gui.report("", "Inappropriate content"));
        assertEquals("The reason for the report cannot be blank", gui.report("KingKrill", ""));
    }
}
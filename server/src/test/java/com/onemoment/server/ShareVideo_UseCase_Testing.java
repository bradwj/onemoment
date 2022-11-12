package com.onemoment.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShareVideo_UseCase_Testing {
    //
    @Test
    void share_TC1() {
        //all values valid
        assertEquals( ShareVideo.share(true, true, true), "Video Shared Successfully");
    }

    @Test
    void share_TC2() {
        //Invalid access
        assertEquals( ShareVideo.share(false, false, true), "You do not have access to the video");

    }

    @Test
    void share_TC3() {
        //corrupted video
        assertEquals( ShareVideo.share(false, true, false), "The video is unavailable, or may be corrupted");

    }
}
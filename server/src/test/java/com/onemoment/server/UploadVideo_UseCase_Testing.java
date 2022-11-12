package com.onemoment.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UploadVideo_UseCase_Testing {

    @Test
    void uploadVideo_TC1() {
        // all values valid
        int duration = 10; // video duration is 10 seconds
        assertEquals(
                UploadVideo.uploadVideo(new Video(duration * 1000, "test"), true),
                UploadVideo.CONFIRMATION_MSG
        );
    }

    @Test
    void uploadVideo_TC2() {
        // invalid privacy setting
        int duration = 10; // video duration is 10 seconds
        assertEquals(
                UploadVideo.uploadVideo(new Video(duration * 1000, "test"), null),
                UploadVideo.INVALID_PRIVACY_SETTING_MSG
        );
    }

    @Test
    void uploadVideo_TC3() {
        // invalid video duration
        int duration = 60; // video duration is 60 seconds
        assertEquals(
                UploadVideo.uploadVideo(new Video(duration * 1000, "test"), false),
                UploadVideo.INVALID_VIDEO_DURATION_MSG
        );
    }

    @Test
    void uploadVideo_TC5() {
        // exceptional video upload (video is null)
        assertEquals(
                UploadVideo.uploadVideo(null, true),
                UploadVideo.INVALID_VIDEO_UPLOAD_MSG
        );
    }
}

package com.onemoment.server;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import com.onemoment.server.models.User;
import com.onemoment.server.service.VideoService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

public class UploadVideo_UseCase_Testing {

    /*so for 10 minute video, data used will be
2160p video : 20000 *60*10/8/1024 = 1465 MB
1440p video : 8913 *60*10/8/1024 = 653 MB
1080p video : 3774*60*10/8/1024 = 277 MB
720p video : 3000*60*10/8/1024 = 220 MB
480p video : 1000*60*10/8/1024 = 73 MB
360p video : 721*60*10/8/1024 = 53 MB
240p video : 377*60*10/8/1024 = 27.6 MB
144p video : 80*60*10/8/1024 = 5.8 MB */
VideoService vService;
User u;
User nullUser;

MultipartFile file;
MultipartFile longFile;
MultipartFile nullFile;

@BeforeEach   
void setUp() {
    vService = new VideoService();
    u = new User(UUID.randomUUID(), "userName", "password123", "123-456-7890", new Date(), "biography");
    nullUser = null;
    nullFile = null;


    //********************    NEED TO FIX   *************************************/
    //ADD a multipartfile????????????????????????? -> video under a minute?
    file = null;
    //ADD a multipartfile??????????????????????? -> video over a minute?
    longFile = null;
}


    @Test
    void uploadVideo_TC1() throws Exception {
        // all values valid
        long validLength = 45000000;  //1080p video -> 27700000 bytes for 1 minute video -> 4616666 bytes for 10 seconds
        assertEquals(
            vService.saveVideo(u, file, "first uploaded video", true), "Video successfully saved"
        );
    }

    @Test
    void uploadVideo_TC2() throws Exception {
        // identical name
        vService.saveVideo(u, file, "identicalName", true);
        assertEquals(
            vService.saveVideo(u, file, "identicalName", true), "A video already exists with this name"

        );
    }

    @Test
    void uploadVideo_TC3() throws Exception {
        // invalid video duration
        long invalidLength = 27700001;
        assertEquals(
            vService.saveVideo(u, longFile, "long video", true), "This video is too large"
        );
    }

    @Test
    void uploadVideo_TC5() throws Exception {
        // exceptional video upload (user is null)
        assertEquals(
             vService.saveVideo(nullUser, file, "name", false), "User is null"
        );
    }

    @Test
    void uploadVideo_TC6() throws Exception {
        // exceptional video upload (file is null)
        assertEquals(
             vService.saveVideo(u, nullFile, "name2", false), "File is null"
        );
    }

    @Test
    void uploadVideo_TC7() throws Exception {
        // exceptional video upload (name is empty)
        assertEquals(
             vService.saveVideo(u, file, "", false), "Name is empty"
        );
    }
}

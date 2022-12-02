package com.onemoment.server.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onemoment.server.models.User;
import com.onemoment.server.models.Video;
import com.onemoment.server.models.Videos;
import com.onemoment.server.service.VideoService;

@RestController
@RequestMapping
public class VideoController {
    private VideoService vService;

    @PostMapping
    public ResponseEntity<String> saveVideo(@RequestParam("user") User u, @RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws Exception {
        vService.saveVideo(u, file, name);
        return ResponseEntity.ok("Video saved successfully");
    }

    @GetMapping("{name}")
    public ResponseEntity<ByteArrayResource> getVideoByname(@PathVariable("name") String name) throws Exception {
        return ResponseEntity.ok(new ByteArrayResource(vService.getVideo(name).getData()));
    }

    @GetMapping("{user}")
    public List<Response> getUsersVideoList(@RequestParam("user") User u) {
        List<Response> userList = new ArrayList<>();
        for(Video v: vService.getAllVideos().getUsersVideoList(u)) {
            userList.addAll((Collection<? extends Response>) new ByteArrayResource(v.getData()));
        }
        return userList;
    }

    @GetMapping("all")
    public List<Response> getVideoList() {
        List<Response> videoList = new ArrayList<>();
        for(Video v: vService.getAllVideos().getVideoList()) {
            videoList.addAll((Collection<? extends Response>) new ByteArrayResource(v.getData()));
        }
        return videoList;
    }
    

}

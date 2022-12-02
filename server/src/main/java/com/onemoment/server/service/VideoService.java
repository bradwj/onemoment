package com.onemoment.server.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.onemoment.server.models.User;
import com.onemoment.server.models.Video;
import com.onemoment.server.models.Videos;
import com.onemoment.server.repositories.VideoRepository;

@Repository
public class VideoService {
    private VideoRepository repo;

    public Video getVideo(String name) throws Exception {
        Video v = repo.getVideoWithName(name);
        if(v == null) {
            throw new Exception();
        }
        return v;
    }

    public Videos getAllVideos() {
        return repo.getAllVideos();
    }

    public List<Video> getUsersVideoList(User u) {
        return repo.getUsersVideoList(u);
    }

    public void saveVideo(User u, MultipartFile file, String name) throws Exception {
        Video newVideo = new Video(u, name, file.getBytes());
        if(repo.getVideoWithName(name) != null) {
            throw new Exception();
        }
        repo.addVideo(newVideo);
    }

    

}

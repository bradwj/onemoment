package com.onemoment.server.repositories;


import com.onemoment.server.models.User;
import com.onemoment.server.models.Users;
import com.onemoment.server.models.Video;
import com.onemoment.server.models.Videos;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class VideoRepository {

    private static Videos list = new Videos();

    public Videos getAllVideos() {
        return list;
    }

    public void addVideo(Video v) {
        list.getVideoList()
                .add(v);
    }

    public Video getVideoWithName(String name) {
        return list.getVideoWithName(name);
    }

    public List<Video> getUsersVideoList(User u) {
        return list.getUsersVideoList(u);
    }

}

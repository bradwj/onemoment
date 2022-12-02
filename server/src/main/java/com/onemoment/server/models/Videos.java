package com.onemoment.server.models;

import java.util.ArrayList;
import java.util.List;

public class Videos {

    private List<Video> videoList;

    //returns an arraylist of videos
    public List<Video> getVideoList() {
        if(videoList == null) {
            videoList = new ArrayList<>();
        }
        return videoList;
    }

    //returns an arraylist of videos from one specified user
    public List<Video> getUsersVideoList(User u) {
        List<Video> userVideos= new ArrayList<>();
        for(Video v : videoList) {
            if(v.getUser().equals(u)) {
                userVideos.add(v);
            }
        }
        return userVideos;
    }

    //returns the video with specified name
    public Video getVideoWithName(String name) {
        for(Video v : videoList) {
            if(v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }
}


package com.khaled.rwayat3beer;

/**
 * Created by khaled on 16/08/2017.
 */

public class StoryGetter {
    private String StoryTitle;

    public StoryGetter(String StoryTitle){
        this.setStoryTitle(StoryTitle);
    }

    public String getStoryTitle() {
        return StoryTitle;
    }

    public void setStoryTitle(String storyTitle) {
        StoryTitle = storyTitle;
    }
}

package com.musicmentor.musicmentor.request;

import com.musicmentor.musicmentor.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQuizRequest {
    public String title;
    public String description;
    public User owner;
}

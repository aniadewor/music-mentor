package com.musicmentor.musicmentor.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQuizRequest {
    public String title;
    public String description;
    public Integer ownerId;
}

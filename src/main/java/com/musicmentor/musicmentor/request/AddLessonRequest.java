package com.musicmentor.musicmentor.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLessonRequest {
    private String title;
    private String description;
    private String content;
    private String className;
}



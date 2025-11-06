package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class FileService {
    private final LessonRepository lessonRepository;

    public Lesson saveFile(MultipartFile file) throws IOException {
        Lesson lesson = new Lesson();
        lesson.setImageName(file.getOriginalFilename());
        lesson.setImage(file.getBytes());
        return lessonRepository.save(lesson);
    }
    public Lesson getFile(Integer lessonId){
    return lessonRepository.findById(lessonId).orElseThrow(()-> new RuntimeException("File Not Found"));}
}

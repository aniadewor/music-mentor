package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    private final String uploadDir = "/Users/annadewor/Downloads/musicmentor/src/main/java/com/musicmentor/musicmentor/uploads";
    private final FileService fileService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        return ResponseEntity.ok("File uploaded successfully: " + filePath.toAbsolutePath());
    }
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam ("file")MultipartFile file) throws IOException {
        fileService.saveFile(file);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer lessonId) {
       Lesson file =  fileService.getFile(lessonId);
       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename = \""+ file.getImageName() +"\"").contentType(MediaType.IMAGE_JPEG).body(file.getImage());
    }
}
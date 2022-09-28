package com.accountmanagement.practice.controller;

import com.accountmanagement.practice.dto.FileResponse.FileResponse;
import com.accountmanagement.practice.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
public class fileController {

    private static final Logger logger = LoggerFactory.getLogger(fileController.class);

    private final FileService fileService;
    @Value("${project.image}")
    private String path;

    public fileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image) throws IOException {
        String filename = null;
        String url = ServletUriComponentsBuilder.
                fromCurrentContextPath().path("/media/").
                path(Objects.requireNonNull(image.getOriginalFilename())).toUriString();
        try {
            filename = this.fileService.uploadImages(path, image);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("couldn't upload file because File has the same data");
            return new ResponseEntity<>(new FileResponse(null, null, "couldn't upload file"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(filename, url, "File SuccessFully Uploaded"), HttpStatus.OK);
    }


    @GetMapping(value = "/media/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void loadFileAsResource(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.loadFileAsResource(path, fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}

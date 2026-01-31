package com.imageservice.host.controller;

import com.imageservice.domain.entities.Image;
import com.imageservice.infrastructure.repository.ImageContentStore;
import com.imageservice.infrastructure.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageContentController {

    private final ImageRepository imageRepository;
    private final ImageContentStore contentStore;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(@PathVariable("imageId") UUID id, @RequestParam("image") MultipartFile image)
            throws IOException {
        Image img = imageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        img.setContentMimeType(image.getContentType());
        contentStore.setContent(img, image.getInputStream());
        imageRepository.save(img);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("imageId") UUID id) {
        Image img = imageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        InputStream imgContent = contentStore.getContent(img);
        if (imgContent == null) {
            throw new EntityNotFoundException("Нет изображения связанного с этой сущностью");
        }

        InputStreamResource inputStreamResource = new InputStreamResource(imgContent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(img.getContentLength());
        headers.set("Content-Type", img.getContentMimeType());

        return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
    }
}

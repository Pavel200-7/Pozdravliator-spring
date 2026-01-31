package com.imageservice.service.image;

import com.imageservice.domain.entities.Image;
import com.imageservice.infrastructure.repository.ImageRepository;
import com.imageservice.service.image.data.request.CreateImageCRequest;
import com.imageservice.service.image.data.request.DeleteImageCRequest;
import com.imageservice.service.image.data.response.CreateImageCResponse;
import com.imageservice.service.image.data.response.DeleteImageCResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Autowired private ImageRepository repository;

    @Override
    public CreateImageCResponse createImage(CreateImageCRequest request) {
        Image image = Image.builder()
                .id(request.getId())
                .build();
        repository.save(image);
        return new CreateImageCResponse(true);
    }

    @Override
    public DeleteImageCResponse deleteImage(DeleteImageCRequest request) {
        Image image = repository.findById(request.getId())
                .orElseThrow(EntityNotFoundException::new);
        repository.delete(image);
        return new DeleteImageCResponse(true);
    }
}

package com.imageservice.infrastructure.repository;

import com.imageservice.domain.entities.Image;
import org.springframework.content.commons.store.ContentStore;

public interface ImageContentStore extends ContentStore<Image, String> {
}

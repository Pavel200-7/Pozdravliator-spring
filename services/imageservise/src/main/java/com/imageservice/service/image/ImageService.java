package com.imageservice.service.image;

import com.imageservice.service.image.data.request.*;
import com.imageservice.service.image.data.response.*;

public interface ImageService {
    public CreateImageCResponse createImage(CreateImageCRequest request);
    public DeleteImageCResponse deleteImage(DeleteImageCRequest request);
}

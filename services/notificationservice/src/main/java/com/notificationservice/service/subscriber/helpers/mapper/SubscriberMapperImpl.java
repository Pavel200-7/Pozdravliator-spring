package com.notificationservice.service.subscriber.helpers.mapper;

import com.notificationservice.domain.entities.Subscriber;
import com.notificationservice.service.subscriber.data.request.GetSubscribersQRequest;
import com.notificationservice.service.subscriber.data.response.GetSubscribersQResponse;
import com.notificationservice.service.subscriber.data.response.data.SubscriberData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscriberMapperImpl implements SubscriberMapper {
    @Override
    public GetSubscribersQResponse toResponse(Page<Subscriber> page, GetSubscribersQRequest request) {
        List<SubscriberData> items = page.getContent().stream()
                .map(i -> this.toSubscriberData(i))
                .toList();
        int offset = request.getPage() * request.getPageSize();
        return GetSubscribersQResponse.builder()
                .items(items)
                .page(request.getPage())
                .pageSize(request.getPageSize())
                .hasNext(page.hasNext())
                .hasPrev(page.hasPrevious())
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public SubscriberData toSubscriberData(Subscriber entity) {
        return SubscriberData.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .build();
    }
}

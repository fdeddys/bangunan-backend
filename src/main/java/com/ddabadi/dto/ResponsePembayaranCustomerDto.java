package com.ddabadi.dto;

import java.util.List;

/**
 * Created by deddy on 6/25/16.
 */
public class ResponsePembayaranCustomerDto {

    private List<PembayaranCustomerDto> content;
    private Long totalElements;

    public List<PembayaranCustomerDto> getContent() {
        return content;
    }

    public void setContent(List<PembayaranCustomerDto> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}

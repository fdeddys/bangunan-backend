package com.ddabadi.dto;

import java.util.List;

/**
 * Created by deddy on 6/12/16.
 */
public class ResponsePembayaranSupplierDto {

    private List<PembayaranSupplierDto> content;
    private Long totalElements;

    public List<PembayaranSupplierDto> getContent() {
        return content;
    }

    public void setContent(List<PembayaranSupplierDto> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}

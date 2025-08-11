package com.sera.mybooks.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.mybooks.domain.enumeration.ReadStatus;

@JsonIgnoreProperties
public class BookUpdateStatusRequest {

    @JsonProperty(required = true)
    private ReadStatus status;

    public ReadStatus getStatus() {
        return this.status;
    }
}

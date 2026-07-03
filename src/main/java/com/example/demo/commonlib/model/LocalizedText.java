package com.example.demo.commonlib.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalizedText implements Serializable {

    private String ru;

    @JsonAlias("kz")
    private String kk;

    private String en;

    public boolean hasRu() {
        return ru != null && !ru.trim().isEmpty();
    }

}
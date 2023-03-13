package com.mpfleet.fleet.models;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class CommonObject extends BaseEntity {
    private String description;
    private String details;
}

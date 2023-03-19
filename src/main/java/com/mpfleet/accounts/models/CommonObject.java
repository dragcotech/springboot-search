package com.mpfleet.accounts.models;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class CommonObject extends BaseEntity {
    private String description;
    private String details;
}

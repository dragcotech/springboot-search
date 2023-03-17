package com.mpfleet.hr.models;

import com.mpfleet.fleet.models.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@MappedSuperclass
public abstract class CommonObject extends BaseEntity {
    private String description;
    private String details;
}

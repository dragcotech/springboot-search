package com.mpfleet.security.models;

import com.mpfleet.commons.CommonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends CommonObject {

    // for test purposes, we actually don't need the constructor
    public Role(String description, String details) {
        super(description, details);
    }
}

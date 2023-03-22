package com.mpfleet.security.models;

import com.mpfleet.commons.CommonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends CommonObject {
}

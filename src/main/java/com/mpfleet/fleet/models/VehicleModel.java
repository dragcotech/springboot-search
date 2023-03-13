package com.mpfleet.fleet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Entity
@Table(name = "vehicle_model")
public class VehicleModel extends CommonObject {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleModel that = (VehicleModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

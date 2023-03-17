package com.mpfleet.hr.models;

import com.mpfleet.admin.models.CommonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "job_title")
public class JobTitle extends CommonObject {

}

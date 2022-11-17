package com.epg.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Data
public class Program {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String channelId;
    private String imageUrl;
    private String title;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

}

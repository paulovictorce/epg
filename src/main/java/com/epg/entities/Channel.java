package com.epg.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Channel {

    @Id
    @GeneratedValue(generator="channel-uuid")
    @GenericGenerator(name="channel-uuid", strategy = "uuid")
    private String id;
    private String name;
    private Integer position;
    private String category;


}

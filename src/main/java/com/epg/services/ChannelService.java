package com.epg.services;

import com.epg.entities.Channel;
import com.epg.repositories.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ResponseEntity<List<Channel>> getAll() {
        List<Channel> channelList = channelRepository.findAll();
        return new ResponseEntity<>(channelList, HttpStatus.OK);
    }

    public ResponseEntity<Channel> save(Channel channel) {
        return new ResponseEntity<>(channelRepository.save(channel), HttpStatus.CREATED);
    }



}

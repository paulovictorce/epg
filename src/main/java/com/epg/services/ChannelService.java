package com.epg.services;

import com.epg.entities.Channel;
import com.epg.repositories.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    private ResponseEntity validationChannelFailResponse = new ResponseEntity<>(
            "The category, name and position fields must be informed", HttpStatus.BAD_REQUEST);

    public ResponseEntity<List<Channel>> getAll() {
        List<Channel> channelList = channelRepository.findAll();
        return new ResponseEntity<>(channelList, HttpStatus.OK);
    }

    public ResponseEntity save(Channel channel) {

        if (validateChannel(channel)) {
            return validationChannelFailResponse;
        }

        return new ResponseEntity<>(channelRepository.save(channel), HttpStatus.CREATED);
    }

    private boolean validateChannel(Channel channel) {

        if (StringUtils.isEmpty(channel.getName()) || StringUtils.isEmpty(channel.getCategory()) || channel.getPosition() == null) {
            return true;
        }

        return false;

    }



}

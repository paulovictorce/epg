package com.epg.services;


import com.epg.entities.Channel;
import com.epg.repositories.ChannelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChannelServiceTest {

    @InjectMocks
    private ChannelService channelService;

    @Mock
    private ChannelRepository channelRepository;

    private static final Channel CHANNEL = new Channel();
    private static final Channel INVALID_CHANNEL = new Channel();

    private static final List<Channel> CHANNEL_LIST = new ArrayList<>();

    private static final List<Channel> EMPTY_CHANNEL_LIST = new ArrayList<>();

    @BeforeAll
    static void setupMocks() {

        CHANNEL.setCategory("category");
        CHANNEL.setId("a100");
        CHANNEL.setName("Channel Test");
        CHANNEL.setPosition(10);

        CHANNEL_LIST.add(CHANNEL);

    }

    @Test
    void shouldNotCreateChannelWithoutMandatoryInformation() {
        Mockito.when(channelRepository.save(INVALID_CHANNEL))
                .thenReturn(INVALID_CHANNEL);
        ResponseEntity response = channelService.save(INVALID_CHANNEL);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldCreateChannelWithMandatoryInformation() {
        Mockito.when(channelRepository.save(CHANNEL))
                .thenReturn(CHANNEL);
        ResponseEntity response = channelService.save(CHANNEL);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnAllChannels() {
        Mockito.when(channelRepository.findAll())
                .thenReturn(CHANNEL_LIST);
        ResponseEntity response = channelService.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnWithoutErrorWhenChannelListIsEmpty() {
        Mockito.when(channelRepository.findAll())
                .thenReturn(EMPTY_CHANNEL_LIST);
        ResponseEntity response = channelService.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }




}

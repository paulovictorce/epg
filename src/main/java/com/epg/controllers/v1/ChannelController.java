package com.epg.controllers.v1;

import com.epg.entities.Channel;
import com.epg.services.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping("/create")
    public ResponseEntity<Channel> save(@RequestBody Channel channel) {
        return channelService.save(channel);
    }

    @GetMapping
    public ResponseEntity<List<Channel>> getAll() {
        return channelService.getAll();
    }

}

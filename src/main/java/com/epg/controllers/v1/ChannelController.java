package com.epg.controllers.v1;

import com.epg.entities.Channel;
import com.epg.services.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.controller.version}")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> getAll() {
        return channelService.getAll();
    }

    @PostMapping("/channels")
    public ResponseEntity<Channel> save(@RequestBody Channel channel) {
        return channelService.save(channel);
    }

}

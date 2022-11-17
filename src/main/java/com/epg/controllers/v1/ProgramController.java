package com.epg.controllers.v1;

import com.epg.entities.Program;
import com.epg.services.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("/create")
    public ResponseEntity save(@RequestBody Program program) {
        return programService.save(program);
    }

    @GetMapping("/channel-id/{channelId}")
    public ResponseEntity getByChannelId(@PathVariable String channelId) {
        return programService.getByChannelId(channelId);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {
        return programService.getById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return programService.delete(id);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Program program) {
        return programService.update(id, program);
    }
}

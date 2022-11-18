package com.epg.services;

import com.epg.entities.Channel;
import com.epg.entities.Program;
import com.epg.repositories.ChannelRepository;
import com.epg.repositories.ProgramRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProgramServiceTest {

    @InjectMocks
    private ProgramService programService;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private ChannelRepository channelRepository;

    private static final Program PROGRAM = new Program();
    private static final Program INVALID_PROGRAM = new Program();

    private static final Channel CHANNEL = new Channel();
    private static final Channel INVALID_CHANNEL = new Channel();

    private static final List<Program> PROGRAM_LIST = new ArrayList<>();

    @BeforeAll
    static void setupMocks() {

        CHANNEL.setCategory("category");
        CHANNEL.setId("a100");
        CHANNEL.setName("Channel Test");
        CHANNEL.setPosition(10);

        PROGRAM.setId("a100");
        PROGRAM.setDescription("Program Description");
        PROGRAM.setTitle("Title");
        PROGRAM.setImageUrl("Image URL");
        PROGRAM.setChannelId(CHANNEL.getId());
        PROGRAM.setStartTime(LocalTime.NOON);
        PROGRAM.setEndTime(LocalTime.MIDNIGHT);

        PROGRAM_LIST.add(PROGRAM);

    }

    @Test
    void shouldNotCreateProgramWithoutMandatoryInformation() {
        Mockito.when(programRepository.save(INVALID_PROGRAM))
                .thenReturn(INVALID_PROGRAM);
        ResponseEntity response = programService.save(INVALID_PROGRAM);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldCreateProgramWithMandatoryInformation() {
        Mockito.when(programRepository.save(PROGRAM))
                .thenReturn(PROGRAM);

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.of(CHANNEL));

        ResponseEntity response = programService.save(PROGRAM);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldNotCreateProgramWhenChannelIsNotFounded() {
        Mockito.when(programRepository.save(PROGRAM))
                .thenReturn(PROGRAM);

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.empty());

        ResponseEntity response = programService.save(PROGRAM);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldCreateProgramWhenChannelIsFounded() {
        Mockito.when(programRepository.save(PROGRAM))
                .thenReturn(PROGRAM);

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.of(CHANNEL));

        ResponseEntity response = programService.save(PROGRAM);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldNotUpdateProgramWhenChannelIsNotFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.of(PROGRAM));

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.empty());

        ResponseEntity response = programService.update(PROGRAM.getId(), PROGRAM);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldUpdateProgramWhenChannelIsFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.of(PROGRAM));

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.of(CHANNEL));

        ResponseEntity response = programService.update(PROGRAM.getId(), PROGRAM);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotUpdateProgramWhenProgramIsNotFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.empty());

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.of(CHANNEL));

        ResponseEntity response = programService.update(PROGRAM.getId(), PROGRAM);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldUpdateProgramWhenProgramIsFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.of(PROGRAM));

        Mockito.when(channelRepository.findById(CHANNEL.getId()))
                .thenReturn(Optional.of(CHANNEL));

        ResponseEntity response = programService.update(PROGRAM.getId(), PROGRAM);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotDeleteProgramWhenProgramIsNotFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.empty());


        ResponseEntity response = programService.delete(PROGRAM.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldDeleteProgramWhenProgramIsFounded() {
        Mockito.when(programRepository.findById(PROGRAM.getId()))
                .thenReturn(Optional.of(PROGRAM));

        ResponseEntity response = programService.delete(PROGRAM.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnProgramListByChannelWhenProgramsAreNotFounded() {
        Mockito.when(programRepository.findAll())
                .thenReturn(PROGRAM_LIST);
        ResponseEntity response = programService.getByChannelId("aaaa");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnProgramListByChannelWhenProgramsAreFounded() {
        Mockito.when(programRepository.findAll())
                .thenReturn(PROGRAM_LIST);
        ResponseEntity response = programService.getByChannelId(CHANNEL.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}

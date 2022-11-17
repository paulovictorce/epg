package com.epg.services;

import com.epg.entities.Channel;
import com.epg.entities.Program;
import com.epg.repositories.ChannelRepository;
import com.epg.repositories.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ChannelRepository channelRepository;

    private ResponseEntity channelNotFoundedResponse = new ResponseEntity<>("The informed channel doesn't exists on our database!" +
            " Please, inform the correct channel! The program was not saved.",
            HttpStatus.BAD_REQUEST);

    private ResponseEntity programNotFoundedResponse = new ResponseEntity<>("Program not founded!",
            HttpStatus.NOT_FOUND);

    public ResponseEntity<List<Program>> getAll() {
        List<Program> programList = programRepository.findAll();
        return new ResponseEntity<>(programList, HttpStatus.OK);
    }

    public ResponseEntity getById(String programId) {

        Optional<Program> programOptional = programRepository.findById(programId);
        if (!programOptional.isPresent()) {
            return programNotFoundedResponse;
        }
        return new ResponseEntity<>(programOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity getByChannelId(String channelId) {

        List<Program> programList = programRepository.findAll();

        List<Program> programListAux = programList.stream()
                .filter(program -> program.getChannelId().equals(channelId))
                .collect(Collectors.toList());

        if (programListAux.isEmpty()) {
            return new ResponseEntity<>("Programs not founded!",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(programListAux, HttpStatus.OK);
    }

    public ResponseEntity save(Program program) {

        if (validateProgramChannel(program)) {
            return channelNotFoundedResponse;
        }

        return new ResponseEntity<>(programRepository.save(program), HttpStatus.CREATED);
    }

    public ResponseEntity update(String programId, Program program) {

        Optional<Program> programOptional = programRepository.findById(programId);

        if (!programOptional.isPresent()) {
            return programNotFoundedResponse;
        }

        if (validateProgramChannel(program)) {
            return channelNotFoundedResponse;
        }

        program.setId(programOptional.get().getId());

        return new ResponseEntity<>(programRepository.save(program), HttpStatus.OK);
    }

    public ResponseEntity delete(String programId) {

        Optional<Program> programOptional = programRepository.findById(programId);

        if (!programOptional.isPresent()) {
            return programNotFoundedResponse;
        }

        programRepository.delete(programOptional.get());

        return new ResponseEntity<>("The program " + programId + " has been deleted", HttpStatus.OK);
    }

    private boolean validateProgramChannel(Program program) {

        Optional<Channel> channelOptional = channelRepository.findById(program.getChannelId());

        if (channelOptional.isPresent()) {
            return false;
        }

        return true;
    }
}

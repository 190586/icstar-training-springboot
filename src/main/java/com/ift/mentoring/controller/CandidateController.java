package com.ift.mentoring.controller;

import com.ift.mentoring.model.Candidate;
import com.ift.mentoring.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public ResponseEntity<Iterable<Candidate>> getCandidate() {
        Iterable<Candidate> candidates = candidateRepository.findAll();
        return new ResponseEntity<>(candidates,HttpStatus.OK);
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> postCandidate(@RequestBody Candidate candidate){
        try {
            candidateRepository.save(new Candidate(candidate.getName()));
            return  new ResponseEntity<>(candidate,HttpStatus.CREATED);
        }catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> putCandidate(@PathVariable(required = true) Long id, @RequestBody Candidate newCandidate) {
        Optional<Candidate> optCandidate = candidateRepository.findById(id);
        if(optCandidate.isPresent()) {
            Candidate oldCandidate = optCandidate.get();
            oldCandidate.setName(newCandidate.getName());
           return new ResponseEntity<>(candidateRepository.save(oldCandidate),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable(required = true) Long id) {
        try {
            candidateRepository.deleteById(id);
           return new ResponseEntity<>("Candidate has been deleted",HttpStatus.OK);
        }catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

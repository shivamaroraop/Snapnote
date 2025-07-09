package com.shivam.firstproject.controllers;

import java.util.*;

import com.shivam.firstproject.entity.JournalEntry;
import com.shivam.firstproject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/journal")
public class JournalentryinfoV2 {

    @Autowired
private JournalEntryService journalEntryService;

@GetMapping
public ResponseEntity<?> getall(){
    List<JournalEntry>ans=journalEntryService.getallentry();
    if(ans==null)return new ResponseEntity<>("No journal entries found",HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(ans,HttpStatus.OK);
}

@PostMapping
    public ResponseEntity<?> addjournalentry(@RequestBody JournalEntry journalentry){
    try{
        journalentry.setDate(LocalDateTime.now());
        journalEntryService.saveentry(journalentry);
        return new ResponseEntity<>(journalentry,HttpStatus.CREATED);
    }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/id/{myid}")
    public ResponseEntity<?> getjournalentrybyid(@PathVariable ObjectId myid){
        Optional<JournalEntry> entry= journalEntryService.getentrybyid(myid);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

@DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deletejournalentry(@PathVariable ObjectId myid){
     journalEntryService.deleteentrybyid(myid);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@PatchMapping("/id/{myid}")
    public ResponseEntity<?> updatejournalentry(@RequestBody JournalEntry journalentry, @PathVariable ObjectId myid){
        Optional<JournalEntry> old=journalEntryService.getentrybyid(myid);
        if(old.isPresent()){
            journalEntryService.updateentry(old.get(),journalentry);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

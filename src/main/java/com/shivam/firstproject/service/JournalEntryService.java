package com.shivam.firstproject.service;

import com.shivam.firstproject.Repo.JournalEntryRepo;
import com.shivam.firstproject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalentryrepo;

    public void saveentry(JournalEntry entry) {
        journalentryrepo.save(entry);
    }
    public List<JournalEntry> getallentry(){
        return journalentryrepo.findAll();
    }
    public Optional<JournalEntry> getentrybyid(ObjectId id) {
        return journalentryrepo.findById(id);
    }
    public void deleteentrybyid(ObjectId id) {
         journalentryrepo.deleteById(id);
    }
    public void updateentry(JournalEntry old, JournalEntry newentry) {
        if(newentry.getTitle()!=null && !newentry.getTitle().isEmpty()) {old.setTitle(newentry.getTitle());}
        if(newentry.getData()!=null && !newentry.getData().isEmpty()) {old.setData(newentry.getData()); }
        journalentryrepo.save(old);
    }
}

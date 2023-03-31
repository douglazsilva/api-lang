package com.aidtecnologia.api.lang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LangController {

    @Autowired
    private LangRepository repository;

    @GetMapping("/langs")
    public List<Lang> getLangs() {
        return repository.findByOrderByRanking();
    }

    @GetMapping("/langs/{id}")
    public Lang getLang(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/langs")
    public ResponseEntity<Lang> postLang(@RequestBody Lang lang) {
        return new ResponseEntity<>(repository.save(lang), HttpStatus.CREATED);
    }

    @PutMapping("/langs/{id}")
    public Lang putLang(@PathVariable String id, @RequestBody Lang lang) {
        // repository.findById(id)
        // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        lang.setId(id);
        return repository.save(lang);
    }

    @DeleteMapping("/langs/{id}")
    public void deleteLang(@PathVariable String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}

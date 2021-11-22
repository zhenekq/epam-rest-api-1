package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        List<Tag> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable int id){
        Tag tag = tagService.getTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        tagService.createTag(tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag, @PathVariable int id){
        tagService.updateTag(tag, id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable int id){
        Tag tag = tagService.deleteTagById(id);
        System.out.println(tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}

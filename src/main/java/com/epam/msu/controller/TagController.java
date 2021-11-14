package com.epam.msu.controller;

import com.epam.msu.dto.TagDto;
import com.epam.msu.entity.Tag;
import com.epam.msu.service.TagService;
import com.epam.msu.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /*@PatchMapping
    public ResponseEntity<Tag> updateTag(@RequestParam String name){

    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable int id){
        Tag tag = tagService.deleteTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}

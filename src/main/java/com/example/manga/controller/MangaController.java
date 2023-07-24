package com.example.manga.controller;

import com.example.manga.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MangaController {
    private final MangaService mangaService;
    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping("/manga/{description}")
    public String getManga(@PathVariable String description) {
        return mangaService.getManga(description);
    }
}

package com.yorha.controller;

import com.yorha.dto.MoodDTO;
import com.yorha.service.MoodService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class MoodController {
    private final MoodService moodService;

    public MoodController(@Qualifier("MoodServiceImpl") MoodService moodService) {
        this.moodService = moodService;
    }

    @RequestMapping("/findAll2")
    public String findAll(Model model){
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods",moodDTOList);
        return "mood";
    }
}

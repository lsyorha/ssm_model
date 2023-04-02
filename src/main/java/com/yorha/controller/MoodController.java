package com.yorha.controller;

import com.yorha.dto.MoodDTO;
import com.yorha.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class MoodController {
    @Resource
    private final MoodService moodService;

    public MoodController(@Autowired MoodService moodService) {
        this.moodService = moodService;
    }


    @RequestMapping("/findAll2")
    public String findAll(Model model){
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods",moodDTOList);
        return "mood";
    }

    @RequestMapping("/{moodId}/praise")
    public String praise(Model model, @PathVariable Integer moodId,Integer userId){

        boolean isPraise = moodService.praiseMood(userId, moodId);

        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods",moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";

    }

}

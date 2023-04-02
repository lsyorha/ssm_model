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
import java.util.Random;

@Controller
@RequestMapping("/mood")
public class MoodController {
    @Resource
    private final MoodService moodService;

    public MoodController(@Autowired MoodService moodService) {
        this.moodService = moodService;
    }


    @RequestMapping("/findAll")
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

    @RequestMapping("/{moodId}/praiseForRedis")
    public String praiseForRedis(Model model, @PathVariable Integer moodId,Integer userId){
//      随机生成id
        Random random = new Random();
        userId = Integer.valueOf(random.nextInt(100) + "");

        boolean isPraise = moodService.praiseMoodForRedis(userId, moodId);
//      查询所有说说
        List<MoodDTO> moodDTOList = moodService.findAllForRedis();
        model.addAttribute("moods",moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";

    }
}

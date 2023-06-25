package com.etraveli.controller.preference;

import com.etraveli.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PreferenceListController {
    @Autowired
    private PreferenceService preferenceService;

    @RequestMapping(value = "/listPreferences", method = RequestMethod.GET)
    public String findAllPreferences(Model model) {
        model.addAttribute("preferences", preferenceService.findAll());
        return "preferenceList";
    }
}

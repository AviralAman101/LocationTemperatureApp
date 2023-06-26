package com.etraveli.controller.preference;

import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import com.etraveli.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("preference")
public class PreferenceDeleteController {
    @Autowired
    private PreferenceService preferenceService;

    @RequestMapping(value = "/deletePreference/{id}/{city}/{state}", method = RequestMethod.GET)
    public String selectForDelete(@PathVariable Long id, @PathVariable String city,@PathVariable String state, Model model) {
        PreferenceID preferenceID = new PreferenceID();
        preferenceID.setCity(city);
        preferenceID.setUserId(id);
        preferenceID.setState(state);
        model.addAttribute("preference", preferenceService.findById(preferenceID));
        return "preferenceDelete";
    }

    @RequestMapping(value = "/deletePreferenceFailed", method = RequestMethod.GET)
    public String deletePreferenceFailed() {
        return "preferenceDelete";
    }

    @RequestMapping(value = "/deletePreference", method = RequestMethod.POST)
    public String delete(@ModelAttribute Preference preference, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        String message = null;
        String viewName = null;
        try {
            preferenceService.delete(preference);
            message = "Preference deleted.For User id :" + preference.getUserId();
            viewName = "redirect:/mvc/listPreferences";
            sessionStatus.setComplete();
        } catch (Exception ex) {
            message = "Preference delete failed.";
            viewName = "redirect:/mvc/deletePreferenceFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }
}

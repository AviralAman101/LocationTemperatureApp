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

    @RequestMapping(value = "/deletePreference/{id}/{city}", method = RequestMethod.GET)
    public String selectForDelete(@PathVariable Long id, @PathVariable String city, Model model) {
        PreferenceID preferenceID = new PreferenceID();
        preferenceID.setCity(city);
        preferenceID.setUserId(id);
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
            PreferenceID preferenceID = new PreferenceID();
            preferenceID.setUserId(preference.getUserId());
            preferenceID.setCity(preference.getCity());
            preferenceService.delete(preferenceID);
            message = "Preference deleted.For User id :" + preferenceID.getUserId();
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

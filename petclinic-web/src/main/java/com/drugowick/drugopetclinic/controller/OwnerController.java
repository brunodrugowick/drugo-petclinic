package com.drugowick.drugopetclinic.controller;

import com.drugowick.drugopetclinic.converters.OwnerToOwnerCommand;
import com.drugowick.drugopetclinic.converters.commands.OwnerCommand;
import com.drugowick.drugopetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService, OwnerToOwnerCommand ownerToOwnerCommand) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @Transactional
    @RequestMapping("/{id}")
    public String showOwner(@PathVariable String id, Model model) {

        Long idLong = Long.parseLong(id);
        OwnerCommand ownerCommand = ownerService.findCommandById(idLong);
        model.addAttribute("owner", ownerCommand);

        model.addAttribute("currentId", idLong);
        model.addAttribute("previousId", idLong - 1l);
        model.addAttribute("nextId", idLong + 1l);

        return "owners/ownerDetails";
    }
}

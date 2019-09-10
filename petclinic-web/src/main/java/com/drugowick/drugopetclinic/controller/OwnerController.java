package com.drugowick.drugopetclinic.controller;

import com.drugowick.drugopetclinic.converters.OwnerToOwnerCommand;
import com.drugowick.drugopetclinic.converters.commands.OwnerCommand;
import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService  ownerService;
    private final OwnerToOwnerCommand ownerToOwnerCommand;

    public OwnerController(OwnerService ownerService, OwnerToOwnerCommand ownerToOwnerCommand, OwnerToOwnerCommand ownerToOwnerCommand1) {
        this.ownerService = ownerService;
        this.ownerToOwnerCommand = ownerToOwnerCommand1;
    }

    /**
     * Prevents binding on "id" field...
     *      because reasons (?)
     *      one cannot have ID on their post data (?)
     *
     * @param webDataBinder
     */
    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @Transactional
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        OwnerCommand ownerCommand = ownerToOwnerCommand.convert(owner);

        // allow parameterless GET request for /owners to return all records
        if (owner.getFirstName() == null || owner.getFirstName() == "") {
            Set<Owner> results = ownerService.findAll();
            Set<OwnerCommand> resultsCommand = new HashSet<>();
            results.forEach(ownerResults -> resultsCommand.add(ownerToOwnerCommand.convert(ownerResults)));
            model.addAttribute("selections", resultsCommand);
            return "owners/ownersList";
        }

        //find owners by last name
        Set<OwnerCommand> results = ownerService.findAllByFirstNameContaining(owner.getFirstName());

        if (results.isEmpty()) {
            result.rejectValue("firstName", "notFound", "not found");
            model.addAttribute(owner);
            return "owners/findOwners";
        } else if (results.size() == 1) {
            ownerCommand = results.iterator().next();
            return "redirect:/owners/" + ownerCommand.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }

    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
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

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwner";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwner";
        } else {
            Owner savedOwner = ownerService.save(owner);
            System.out.println(owner.getId() + " " + savedOwner.getId());
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable("ownerId") Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwner";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@PathVariable("ownerId") Long ownerId, @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwner";
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}

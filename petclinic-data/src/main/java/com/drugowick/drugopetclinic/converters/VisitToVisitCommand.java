package com.drugowick.drugopetclinic.converters;

import com.drugowick.drugopetclinic.converters.commands.VisitCommand;
import com.drugowick.drugopetclinic.model.Visit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VisitToVisitCommand implements Converter<Visit, VisitCommand> {
    @Override
    public VisitCommand convert(Visit visit) {
        if (visit == null) {
            return null;
        }

        VisitCommand visitCommand = new VisitCommand();
        visitCommand.setDate(visit.getDate());
        visitCommand.setDescription(visit.getDescription());

        return visitCommand;
    }
}

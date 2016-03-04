package uk.gov.ofwat.fountain.domain;

import uk.gov.ofwat.fountain.domain.run.Run;

/**
 * Created by james.toddington on 24/04/2014.
 */
public class RunPlaceHolder extends Run {

    public static final RunPlaceHolder RUN_PLACE_HOLDER = new RunPlaceHolder(0, "NO_RUN_CODE", "NO_RUN_NAME", "NO_RUN_DESCRIPTION", AgendaPlaceHolder.AGENDA_PLACE_HOLDER);

    private RunPlaceHolder(long id, String code, String name, String description, Agenda agenda){
        super();
        this.setId((int)id);
        this.setCode(code);
        this.setName(name);
        this.setDescription(description);
        this.setAgenda(agenda);
    }
}

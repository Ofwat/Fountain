package uk.gov.ofwat.fountain.domain;

public class AgendaPlaceHolder extends Agenda {

    public static final AgendaPlaceHolder AGENDA_PLACE_HOLDER = new AgendaPlaceHolder(0, "NO_AGENDA_CODE", "NO_AGENDA_NAME", "NO_AGENDA_DESCRIPTION");

    private AgendaPlaceHolder(long id, String code, String name, String description){
        super();
        this.setId((int)id);
        this.setCode(code);
        this.setName(name);
        this.setDescription(description);
    }

}

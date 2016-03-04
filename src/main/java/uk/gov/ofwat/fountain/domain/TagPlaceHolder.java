package uk.gov.ofwat.fountain.domain;

import uk.gov.ofwat.fountain.domain.tag.Tag;

public class TagPlaceHolder extends Tag {

    public static final TagPlaceHolder TAG_PLACE_HOLDER = new TagPlaceHolder(-1l,"NO_TAG_NAME", "NO_TAG_DISPLAY_NAME");

    private TagPlaceHolder(Long id, String name, String displayName){
        super();
        this.setId(id);
        this.setName(name);
        this.setDisplayName(displayName);
    }

}


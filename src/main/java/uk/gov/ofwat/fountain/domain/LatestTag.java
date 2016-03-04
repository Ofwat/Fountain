package uk.gov.ofwat.fountain.domain;

import uk.gov.ofwat.fountain.domain.tag.Tag;

/**
 * Created by james.toddington on 24/04/2014.
 */
public class LatestTag extends Tag {

    public static final LatestTag LATEST_TAG = new LatestTag(0l,"Latest", "Latest");

    private LatestTag(Long id, String name, String displayName){
        super();
        this.setId(id);
        this.setName(name);
        this.setDisplayName(displayName);
    }

}


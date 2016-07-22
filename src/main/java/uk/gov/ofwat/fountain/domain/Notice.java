package uk.gov.ofwat.fountain.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adam Edgar on 21/07/2016.
 */
@XmlRootElement(name = "notice")
@XmlType(propOrder = { "type" , "notice", "additionalInformation", "timeStamp"})
public class Notice {

    private String type;
    private String notice;
    private String additionalInformation;
    private String timeStamp;

    public Notice(String type, String notice, String additionalInformation) {
        this(type, notice, additionalInformation, (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()));
    }
    public Notice(String type, String notice, String additionalInformation, String timeStamp) {
        this.type = type;
        this.notice = notice;
        this.additionalInformation   = additionalInformation;
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

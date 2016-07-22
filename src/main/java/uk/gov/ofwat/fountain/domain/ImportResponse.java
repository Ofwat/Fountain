package uk.gov.ofwat.fountain.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Edgar on 21/07/2016.
 */
@XmlRootElement(name = "importResponse")
@XmlType(propOrder = { "success" , "notices", "uploadFileName"})
public class ImportResponse {

    private boolean success;
    private List<Notice> notices = new ArrayList<Notice>();
    private String uploadFileName;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void addNotice(Notice notice) {
        this.notices.add(notice);
    }

    public void addNotices(List<Notice> notices) {
        this.notices.addAll(notices);
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}

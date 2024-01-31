package framework.utils.testrail;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class CaseModel {
    @JsonAlias("case_id")
    int caseId;

    @JsonAlias("status_id")
    int statusId;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonAlias("comment")
    String comment;
}

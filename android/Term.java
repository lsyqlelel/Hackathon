package team.dmqqd.chengjitong;

import java.util.List;

public class Term {
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    private String termName;
    private List<String> subject;
}

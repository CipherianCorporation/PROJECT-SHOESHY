package com.edu.graduationproject.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MailInfo {
    String from = "ThaiHiep";
    String to;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    List<File> files = new ArrayList<>();

    public MailInfo(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public MailInfo() {
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String[] getCc() {
        return this.cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return this.bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<File> getFiles() {
        return this.files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public MailInfo from(String from) {
        setFrom(from);
        return this;
    }

    public MailInfo to(String to) {
        setTo(to);
        return this;
    }

    public MailInfo cc(String[] cc) {
        setCc(cc);
        return this;
    }

    public MailInfo bcc(String[] bcc) {
        setBcc(bcc);
        return this;
    }

    public MailInfo subject(String subject) {
        setSubject(subject);
        return this;
    }

    public MailInfo body(String body) {
        setBody(body);
        return this;
    }

    public MailInfo files(List<File> files) {
        setFiles(files);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MailInfo)) {
            return false;
        }
        MailInfo mailInfo = (MailInfo) o;
        return Objects.equals(from, mailInfo.from) && Objects.equals(to, mailInfo.to) && Objects.equals(cc, mailInfo.cc)
                && Objects.equals(bcc, mailInfo.bcc) && Objects.equals(subject, mailInfo.subject)
                && Objects.equals(body, mailInfo.body) && Objects.equals(files, mailInfo.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, cc, bcc, subject, body, files);
    }

    @Override
    public String toString() {
        return "{" +
                " from='" + getFrom() + "'" +
                ", to='" + getTo() + "'" +
                ", cc='" + getCc() + "'" +
                ", bcc='" + getBcc() + "'" +
                ", subject='" + getSubject() + "'" +
                ", body='" + getBody() + "'" +
                ", files='" + getFiles() + "'" +
                "}";
    }

}

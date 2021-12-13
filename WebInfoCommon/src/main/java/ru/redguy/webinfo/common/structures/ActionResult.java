package ru.redguy.webinfo.common.structures;

public class ActionResult {
    private final boolean success;

    private String comment;

    public ActionResult(boolean success) {
        this.success = success;
    }

    public ActionResult setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public boolean isSuccess() {
        return success;
    }
}

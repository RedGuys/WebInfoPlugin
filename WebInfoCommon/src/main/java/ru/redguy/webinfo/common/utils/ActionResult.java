package ru.redguy.webinfo.common.utils;

public class ActionResult {
    private final boolean success;

    private boolean unsupported;

    private String comment;

    public ActionResult(boolean success) {
        this.success = success;
    }

    public ActionResult setUnsupported(boolean unsupported) {
        this.unsupported = unsupported;
        return this;
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

    public boolean isUnsupported() {
        return unsupported;
    }
}

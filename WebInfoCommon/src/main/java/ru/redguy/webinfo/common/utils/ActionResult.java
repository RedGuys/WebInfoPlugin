package ru.redguy.webinfo.common.utils;

public class ActionResult {
    private final boolean success;

    private boolean unsupported;

    public ActionResult(boolean success) {
        this.success = success;
    }

    public ActionResult(boolean success, boolean unsupported) {
        this.success = success;
        this.unsupported = unsupported;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isUnsupported() {
        return unsupported;
    }
}

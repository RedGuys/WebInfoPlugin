package ru.redguy.miniwebserver.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class WebRequest {
    private boolean canceled = false;
    private HashMap<String, ArrayList<Object>> arguments = new HashMap<>();

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public HashMap<String, ArrayList<Object>> getArguments() {
        return arguments;
    }

    public void setArguments(HashMap<String, ArrayList<Object>> arguments) {
        this.arguments = arguments;
    }
}

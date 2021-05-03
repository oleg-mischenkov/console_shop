package com.mishchenkov.command;

import com.mishchenkov.context.ApplicationContext;

import java.util.Map;
import java.util.TreeMap;

public class CommandHolder {

    private final Map<Integer, Command> holder;
    private final ApplicationContext context;

    public CommandHolder(ApplicationContext context) {
        holder = new TreeMap<>();
        this.context = context;
    }

    public void setCommand(Integer key, Command command) {
        holder.put(key, command);
    }

    public Command getCommand(Integer key) {
        return holder.get(key);
    }

    public Map<Integer, Command> getAllCommand() {
        return holder;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public boolean isCommandExist(Integer key) {
        return holder.containsKey(key);
    }
}

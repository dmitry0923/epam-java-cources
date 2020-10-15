package com.epam.university.java.project.core.state.machine.domain;

import java.util.LinkedList;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
public class StatefulEntityImpl<STATE, EVENT> implements StatefulEntity<STATE, EVENT> {
    private StateMachineDefinition<STATE, EVENT> stateMachineDefinition;

    @Override
    public STATE getState() {
        LinkedList<StateMachineState<STATE, EVENT>> stack
                = (LinkedList<StateMachineState<STATE, EVENT>>) stateMachineDefinition.getStates();
        return stack.pop().getFrom();
    }

    @Override
    public void setState(STATE state) {
        LinkedList<StateMachineState<STATE, EVENT>> stack
                = (LinkedList<StateMachineState<STATE, EVENT>>) stateMachineDefinition.getStates();
        stack.pop().setFrom(state);
    }

    @Override
    public StateMachineDefinition<STATE, EVENT> getStateMachineDefinition() {
        return stateMachineDefinition;
    }

    @Override
    public void setStateMachineDefinition(StateMachineDefinition<STATE, EVENT> definition) {
        this.stateMachineDefinition = definition;
    }
}

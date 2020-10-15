package com.epam.university.java.project.core.state.machine.domain;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
public class StateMachineDefinitionImpl<STATE, EVENT> implements StateMachineDefinition<STATE,EVENT> {
    private Collection<StateMachineState<STATE, EVENT>> stateMachineStateCollection
            = new LinkedList<>();
    private Class<? extends StateMachineEventHandler> stateMachineEventHandler;
    private EVENT startEvent;
    private STATE startState;

    @Override
    public EVENT getStartEvent() {
        return startEvent;
    }

    @Override
    public STATE getStartState() {
        return startState;
    }

    @Override
    public void setStartEvent(EVENT event) {
        this.startEvent = event;
    }

    @Override
    public void setStartState(STATE state) {
        this.startState = state;
    }

    @Override
    public Collection<StateMachineState<STATE, EVENT>> getStates() {
        return stateMachineStateCollection;
    }

    @Override
    public void addState(StateMachineState<STATE, EVENT> state) {
        stateMachineStateCollection.add(state);
    }

    @Override
    public Class<? extends StateMachineEventHandler> getHandlerClass() {
        return stateMachineEventHandler;
    }

    @Override
    public void setHandlerClass(Class<? extends StateMachineEventHandler> handlerClass) {
        this.stateMachineEventHandler = handlerClass;
    }
}

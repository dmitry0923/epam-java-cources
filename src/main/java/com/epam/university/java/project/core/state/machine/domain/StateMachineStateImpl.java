package com.epam.university.java.project.core.state.machine.domain;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
public class StateMachineStateImpl<TYPE, EVENT> implements StateMachineState<TYPE, EVENT> {
    private String methodToCall;
    private EVENT event;
    private TYPE from;
    private TYPE to;

    @Override
    public TYPE getFrom() {
        return from;
    }

    @Override
    public void setFrom(TYPE state) {
        this.from = state;
    }

    @Override
    public TYPE getTo() {
        return to;
    }

    @Override
    public void setTo(TYPE state) {
        this.to = state;
    }

    @Override
    public EVENT getOn() {
        return event;
    }

    @Override
    public void setOn(EVENT event) {
        this.event = event;
    }

    @Override
    public String getMethodToCall() {
        return methodToCall;
    }

    @Override
    public void setMethodToCall(String method) {
        this.methodToCall = method;
    }
}

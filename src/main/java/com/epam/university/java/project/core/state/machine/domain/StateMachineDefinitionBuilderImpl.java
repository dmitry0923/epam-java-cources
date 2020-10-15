package com.epam.university.java.project.core.state.machine.domain;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
public class StateMachineDefinitionBuilderImpl<STATE, EVENT> implements StateMachineDefinitionBuilder<STATE, EVENT> {
    private StateMachineDefinition<STATE, EVENT> stateMachineDefinition;

    @Override
    public StateMachineDefinition<STATE, EVENT> build() {
        stateMachineDefinition = new StateMachineDefinitionImpl<>();
        return stateMachineDefinition;
    }

    @Override
    public StateMachineDefinitionBuilder<STATE, EVENT> addState(STATE from,
                                                                STATE to, EVENT on, String method) {
        StateMachineState stateMachineState = new StateMachineStateImpl();
        stateMachineState.setFrom(from);
        stateMachineState.setTo(to);
        stateMachineState.setMethodToCall(method);
        stateMachineDefinition.addState(stateMachineState);
        return this;
    }

    @Override
    public StateMachineDefinitionBuilder<STATE, EVENT> addState(STATE from,
                                                                STATE to, EVENT on) {
        StateMachineState stateMachineState = new StateMachineStateImpl();
        stateMachineState.setFrom(from);
        stateMachineState.setTo(to);
        stateMachineDefinition.addState(stateMachineState);
        return this;
    }
}

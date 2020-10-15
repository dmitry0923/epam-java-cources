package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionBuilderImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
public class StateMachineManagerImpl implements StateMachineManager {
    private final StateMachineDefinitionBuilderImpl stateMachineDefinitionBuilder;

    public StateMachineManagerImpl() {
        this.stateMachineDefinitionBuilder = new StateMachineDefinitionBuilderImpl();
    }

    @Override
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        JAXBContext jc;
        Unmarshaller unmarshaller = null;
        Definition definition = null;
        Class<?> clazz  = null;
        StateMachineDefinition stateMachineDefinition = stateMachineDefinitionBuilder.build();


        try {
            jc = JAXBContext.newInstance(Definition.class);
            unmarshaller = jc.createUnmarshaller();
            definition = (Definition) unmarshaller.unmarshal(resource.getFile());


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        stateMachineDefinition.setStartState(definition.getStartState());
        stateMachineDefinition.setStartEvent(definition.getStartEvent());

        try {
            clazz = Class.forName(definition.getHandler());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            clazz = (Class<?>) Class.forName(definition.getClass().getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return stateMachineDefinition;
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity, StateMachineDefinition<S, E> definition) {
        return null;
    }

    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        return null;
    }
}

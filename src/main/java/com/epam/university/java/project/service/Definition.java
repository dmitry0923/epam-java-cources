package com.epam.university.java.project.service;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
@XmlRootElement(name = "definition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Definition {
    @XmlAttribute(name = "startEvent")
    private String startEvent;
    @XmlAttribute(name = "startState")
    private String startState;
    @XmlAttribute(name = "handler")
    private String handler;

    @XmlElement(name = "transition", type = Transition.class)
    private List<Transition> transitions;

    public String getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(String startEvent) {
        this.startEvent = startEvent;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}

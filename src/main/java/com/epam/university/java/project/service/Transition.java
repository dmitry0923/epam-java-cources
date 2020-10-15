package com.epam.university.java.project.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author Dmitry Novikov 13-Oct-20.
 */
@XmlRootElement(name = "transition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transition {
    @XmlAttribute(name = "from")
    private String from;
    @XmlAttribute(name = "to")
    private String to;
    @XmlAttribute(name = "on")
    private String on;
    @XmlAttribute(name = "call")
    private String call;
}

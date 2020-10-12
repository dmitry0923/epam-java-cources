package com.epam.university.java.project.core.cdi.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beans")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeansRoot {
    @XmlElement(name = "bean", type = BeanDefinitionImpl.class)
    private List<BeanDefinition> lisOfBeans;

    public List<BeanDefinition> getLisOfBeans() {
        return lisOfBeans;
    }

    public void setLisOfBeans(List<BeanDefinition> lisOfBeans) {
        this.lisOfBeans = lisOfBeans;
    }
}

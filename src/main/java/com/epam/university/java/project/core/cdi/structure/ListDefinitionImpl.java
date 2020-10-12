package com.epam.university.java.project.core.cdi.structure;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistry;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author Dmitry Novikov 11-Oct-20.
 */
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListDefinitionImpl implements ListDefinition {
    @XmlElement(name = "value", type = ListItemDefinitionImpl.class)
    private Collection<ListItemDefinition> items;

    @Override
    public Collection<ListItemDefinition> getItems() {
        return items;
    }

    @Override
    public void setItems(Collection<ListItemDefinition> items) {
        this.items = items;
    }
}

package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.project.core.cdi.io.Resource;
import java.io.File;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Author Dmitry Novikov 08-Oct-20.
 */
public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    @Override
    public int loadBeanDefinitions(Resource resource) {
//        int numberOfLoadedDefinitions;
//        JAXBContext jc = JAXBContext.newInstance(PersonImpl.class);
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//
//
//        Person person = (Person) unmarshaller.unmarshal(resource.getFile());
//
//        if ()
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }
}

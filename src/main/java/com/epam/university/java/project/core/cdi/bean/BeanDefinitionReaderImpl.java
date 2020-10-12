package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Author Dmitry Novikov 08-Oct-20.
 */
public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        int numberOfLoadedBeans = 0;
        try {
            // берем только корневой класс куда входят все остальные зависимости
            JAXBContext jc = JAXBContext.newInstance(BeansRoot.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            BeansRoot beans = (BeansRoot) unmarshaller.unmarshal(resource.getFile());
            List<BeanDefinition> beanDefinitionList = beans.getLisOfBeans();

            if (beanDefinitionList.isEmpty()) {
                throw new RuntimeException();
            }

            for (BeanDefinition bdf: beanDefinitionList
                 ) {
                beanDefinitionRegistry.addBeanDefinition(bdf);
                numberOfLoadedBeans++;
            }

            return numberOfLoadedBeans;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return numberOfLoadedBeans;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        int numberOfLoadedBeanDefinitions = 0;
        for (Resource resource: resources
             ) {
            numberOfLoadedBeanDefinitions += loadBeanDefinitions(resource);
        }
        return numberOfLoadedBeanDefinitions;
    }
}

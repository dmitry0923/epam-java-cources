package com.epam.university.java.project.core.cdi.context;

/**
 * Author Dmitry Novikov 09-Oct-20.
 */
public class ApplicationContextFactoryImpl implements ApplicationContextFactory {
    @Override
    public ApplicationContext newInstance() {
        return new ApplicationContextImpl();
    }
}

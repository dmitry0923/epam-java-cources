package com.epam.university.java.core.task055;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Task055Impl implements Task055 {
    @Override
    public ProcessingContext createContext(String path) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ProcessingContextImpl.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        final String filePath = getClass().getResource(path).getFile();
        return (ProcessingContextImpl) unmarshaller.unmarshal(new File(filePath));
    }
}

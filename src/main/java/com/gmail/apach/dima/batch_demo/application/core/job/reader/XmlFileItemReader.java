package com.gmail.apach.dima.batch_demo.application.core.job.reader;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public abstract class XmlFileItemReader<T> extends StaxEventItemReader<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setFragmentRootElementNames(getFragmentRootElements());
        setUnmarshaller(marshaller());
        super.afterPropertiesSet();
    }

    @Override
    protected void doOpen() throws Exception {
        setResource(getResource());
        super.doOpen();
    }

    private Jaxb2Marshaller marshaller() {
        final var marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(getBoundedClass());
        return marshaller;
    }

    protected abstract Class<?> getBoundedClass();

    protected abstract String[] getFragmentRootElements();

    protected abstract Resource getResource() throws Exception;
}

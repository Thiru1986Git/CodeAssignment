package com.cts.application.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

//XML Service class for JAXB Marshalling and UnMarshalling 
@Service
public class XMLService {

	/**
	 * This method is used to create Unmarshaller for the given class
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public Unmarshaller getUnmarshaller(Class<?> c) throws JAXBException {
		JAXBContext jaxbContext = getJAXBContext(c);
		return jaxbContext.createUnmarshaller();
	}

	/**
	 * This method is used to create Marshaller for the given class
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public Marshaller getMarshaller(Class<?> c) throws JAXBException {
		JAXBContext jaxbContext = getJAXBContext(c);
		return jaxbContext.createMarshaller();
	}

	/**
	 * This method is used to get JAXBContext for the given class
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public JAXBContext getJAXBContext(Class<?> c) throws JAXBException {
		return JAXBContext.newInstance(c);
	}
}

package com.kaveinga.audit;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateListenerConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private HibernateListener userAuditListener;

	@PostConstruct
	public void registerListeners() {
		
//		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//	        throw new NullPointerException("factory is not a hibernate factory");
//	    }
		
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

		EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
		
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_COMMIT_UPDATE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.SAVE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.MERGE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.UPDATE).appendListener(userAuditListener);
		registry.getEventListenerGroup(EventType.POST_LOAD).appendListener(userAuditListener);
		
	}

}

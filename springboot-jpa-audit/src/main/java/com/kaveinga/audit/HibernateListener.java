package com.kaveinga.audit;

import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.DeleteEvent;
import org.hibernate.event.spi.DeleteEventListener;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.MergeEventListener;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kaveinga.account.Account;
import com.kaveinga.user.User;
import com.kaveinga.user.UserAuditService;
import com.kaveinga.utility.ObjectUtils;

@Component
public class HibernateListener implements SaveOrUpdateEventListener, PersistEventListener, DeleteEventListener,
PostUpdateEventListener, MergeEventListener, PreInsertEventListener, PostInsertEventListener, PreUpdateEventListener,
FlushEventListener, PostDeleteEventListener,
PostLoadEventListener{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserAuditService userAuditService;
	
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		log.info("onPreInsert(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
		return false;
	}

	@Override
	public void onMerge(MergeEvent event) throws HibernateException {
		log.info("onMerge(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
	}

	@Override
	public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
		log.info("onMerge(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
	}

	@Override
	public void onDelete(DeleteEvent event) throws HibernateException {
		log.info("onDelete(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getObject()));
	}

	@Override
	public void onDelete(DeleteEvent event, Set transientEntities) throws HibernateException {
		log.info("onDelete(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getObject()));
	}

	@Override
	public void onPersist(PersistEvent event) throws HibernateException {
		log.info("onPersist(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getObject()));
	}

	@Override
	public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
		log.info("onPersist(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getObject()));
		log.info(createdAlready.toString());
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		log.info("requiresPostCommitHanding(..)");
		log.info("ENTITY NAME: {}",persister.getEntityName());
		return false;
	}


	@Override
	public void onPostInsert(PostInsertEvent event) {
		log.info("onPostInsert(..)");
		log.info("ENTITY ID: {}",event.getId());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		log.info("onPreUpdate(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
		return false;
	}


	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		log.info("onPostUpdate(..)");
		Object entity = event.getEntity();
		log.info("ENTITY ID: {}",event.getId());
		log.info("ENTITY: {}",ObjectUtils.toJson(entity));
		
		if(entity.getClass().equals(User.class)) {
			log.debug("user update");
			User user = (User)event.getEntity();
			userAuditService.audit(user);
		}else if(entity.getClass().equals(Account.class)) {
			log.debug("account update");
		}else {
			log.debug("class not matched");
		}
	}

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		log.info("onSaveOrUpdate(..)");
		log.info("ENTITY NAME: {}",event.getEntityName());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
	}

	@Override
	public void onFlush(FlushEvent event) throws HibernateException {
		log.info("onFlush(..)");
	}

	
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		log.info("onPostDelete(..)");
		Object entity = event.getEntity();
		log.info("ENTITY ID: {}",event.getId());
		log.info("ENTITY: {}",ObjectUtils.toJson(entity));
		
		if(entity.getClass().equals(User.class)) {
			log.debug("user delete");
			User user = (User)event.getEntity();
			userAuditService.audit(user);
		}else if(entity.getClass().equals(Account.class)) {
			log.debug("account delete");
		}else {
			log.debug("class not matched");
		}
	}

	@Override
	public void onPostLoad(PostLoadEvent event) {
		log.info("onPostLoad(..)");
		log.info("ENTITY ID: {}",event.getId());
		log.info("ENTITY: {}",ObjectUtils.toJson(event.getEntity()));
	}
	
}

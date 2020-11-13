package com.example.main.config;

import org.hibernate.envers.RevisionListener; //encargado de hacer las revisiones

import com.example.main.entities.audit.Revision;

public class CustomRevisionListener implements RevisionListener {

	public void newRevision(Object revisionEntity) {
		final Revision revision = (Revision) revisionEntity;
	}
	
}

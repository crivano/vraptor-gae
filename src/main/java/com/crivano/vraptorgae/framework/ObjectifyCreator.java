package com.crivano.vraptorgae.framework;

import javax.annotation.PostConstruct;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

@Component
@RequestScoped
public class ObjectifyCreator
    implements ComponentFactory<Objectify> {

    private final ObjectifyFactory factory;
    private Objectify ofy;

    public ObjectifyCreator(ObjectifyFactory factory) {
        this.factory = factory;
    }

    @PostConstruct
    public void create() {
        //ofy = factory.begin();
    }

    public Objectify getInstance() {
        return ObjectifyService.ofy();
    }
}
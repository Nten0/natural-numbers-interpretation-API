package core;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import core.controller.PhoneNumberInterpretationController;

public class PhoneNumberInterpretationApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public PhoneNumberInterpretationApplication() {
        singletons.add(new PhoneNumberInterpretationController());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}


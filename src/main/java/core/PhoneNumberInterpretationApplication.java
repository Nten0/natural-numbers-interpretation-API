package core;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import core.service.PhoneNumberInterpretationService;

public class PhoneNumberInterpretationApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public PhoneNumberInterpretationApplication() {
        singletons.add(new PhoneNumberInterpretationService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}


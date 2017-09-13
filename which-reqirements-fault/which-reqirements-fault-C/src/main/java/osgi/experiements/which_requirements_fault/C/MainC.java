package osgi.experiements.which_requirements_fault.C;

import aQute.bnd.annotation.headers.ProvideCapability;
import aQute.bnd.annotation.headers.RequireCapability;

@ProvideCapability(ns = "contract")
@RequireCapability(ns = "anotherMissingContract")
public class MainC {

}

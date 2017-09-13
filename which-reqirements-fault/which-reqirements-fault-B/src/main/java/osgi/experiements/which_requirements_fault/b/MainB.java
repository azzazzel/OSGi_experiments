package osgi.experiements.which_requirements_fault.b;

import aQute.bnd.annotation.headers.ProvideCapability;
import aQute.bnd.annotation.headers.RequireCapability;

@RequireCapability (ns="missingContract")
@ProvideCapability(ns="contract")
public class MainB {

}

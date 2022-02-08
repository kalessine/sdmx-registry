package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.DataStructureReference;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Dataflow.class)
public class Dataflow_ { 

    public static volatile SingularAttribute<Dataflow, Long> dataflow;
    public static volatile SingularAttribute<Dataflow, Name> name;
    public static volatile SingularAttribute<Dataflow, Annotated> annotations;
    public static volatile SingularAttribute<Dataflow, String> agencyid;
    public static volatile SingularAttribute<Dataflow, String> id;
    public static volatile SingularAttribute<Dataflow, String> version;
    public static volatile SingularAttribute<Dataflow, DataStructureReference> structure;

}
package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Dataflow;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DataStructureReference.class)
public class DataStructureReference_ { 

    public static volatile ListAttribute<DataStructureReference, Dataflow> dataflowList;
    public static volatile SingularAttribute<DataStructureReference, String> agencyid;
    public static volatile SingularAttribute<DataStructureReference, Long> refid;
    public static volatile SingularAttribute<DataStructureReference, String> id;
    public static volatile SingularAttribute<DataStructureReference, String> version;

}
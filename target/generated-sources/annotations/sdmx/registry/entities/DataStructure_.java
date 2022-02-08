package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.DataStructureComponent;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DataStructure.class)
public class DataStructure_ { 

    public static volatile ListAttribute<DataStructure, DataStructureComponent> dataStructureComponentList;
    public static volatile SingularAttribute<DataStructure, Name> name;
    public static volatile SingularAttribute<DataStructure, Long> datastructure;
    public static volatile SingularAttribute<DataStructure, Annotated> annotations;
    public static volatile SingularAttribute<DataStructure, String> agencyid;
    public static volatile SingularAttribute<DataStructure, String> id;
    public static volatile SingularAttribute<DataStructure, String> version;

}
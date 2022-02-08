package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.DataStructureComponent;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(ConceptReference.class)
public class ConceptReference_ { 

    public static volatile ListAttribute<ConceptReference, DataStructureComponent> dataStructureComponentList;
    public static volatile SingularAttribute<ConceptReference, String> conceptid;
    public static volatile SingularAttribute<ConceptReference, String> agencyid;
    public static volatile SingularAttribute<ConceptReference, Long> refid;
    public static volatile SingularAttribute<ConceptReference, String> id;
    public static volatile SingularAttribute<ConceptReference, String> version;

}
package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.DataStructureComponent;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(CodelistReference.class)
public class CodelistReference_ { 

    public static volatile ListAttribute<CodelistReference, DataStructureComponent> dataStructureComponentList;
    public static volatile SingularAttribute<CodelistReference, String> agencyid;
    public static volatile SingularAttribute<CodelistReference, Long> refid;
    public static volatile SingularAttribute<CodelistReference, String> id;
    public static volatile SingularAttribute<CodelistReference, String> version;

}
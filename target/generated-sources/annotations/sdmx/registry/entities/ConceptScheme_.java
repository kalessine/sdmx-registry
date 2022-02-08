package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.Concept;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(ConceptScheme.class)
public class ConceptScheme_ { 

    public static volatile SingularAttribute<ConceptScheme, Long> conceptschemeid;
    public static volatile ListAttribute<ConceptScheme, Concept> conceptList;
    public static volatile SingularAttribute<ConceptScheme, Name> name;
    public static volatile SingularAttribute<ConceptScheme, Annotated> annotations;
    public static volatile SingularAttribute<ConceptScheme, String> agencyid;
    public static volatile SingularAttribute<ConceptScheme, String> id;
    public static volatile SingularAttribute<ConceptScheme, String> version;

}
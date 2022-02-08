package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.ConceptScheme;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Concept.class)
public class Concept_ { 

    public static volatile SingularAttribute<Concept, Long> concept;
    public static volatile SingularAttribute<Concept, ConceptScheme> conceptScheme;
    public static volatile SingularAttribute<Concept, Name> name;
    public static volatile SingularAttribute<Concept, String> conceptid;
    public static volatile SingularAttribute<Concept, Annotated> annotations;

}
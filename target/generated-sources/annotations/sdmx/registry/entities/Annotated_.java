package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotation;
import sdmx.registry.entities.Code;
import sdmx.registry.entities.Codelist;
import sdmx.registry.entities.Concept;
import sdmx.registry.entities.ConceptScheme;
import sdmx.registry.entities.DataStructure;
import sdmx.registry.entities.DataStructureComponent;
import sdmx.registry.entities.Dataflow;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Annotated.class)
public class Annotated_ { 

    public static volatile ListAttribute<Annotated, Annotation> annotationList;
    public static volatile ListAttribute<Annotated, DataStructureComponent> dataStructureComponentList;
    public static volatile ListAttribute<Annotated, Dataflow> dataflowList;
    public static volatile SingularAttribute<Annotated, String> padfield;
    public static volatile ListAttribute<Annotated, Codelist> codelistList;
    public static volatile ListAttribute<Annotated, Concept> conceptList;
    public static volatile ListAttribute<Annotated, ConceptScheme> conceptSchemeList;
    public static volatile SingularAttribute<Annotated, Long> id;
    public static volatile ListAttribute<Annotated, Code> codeList;
    public static volatile ListAttribute<Annotated, DataStructure> dataStructureList;

}
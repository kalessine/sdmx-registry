package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Code;
import sdmx.registry.entities.Codelist;
import sdmx.registry.entities.Concept;
import sdmx.registry.entities.ConceptScheme;
import sdmx.registry.entities.DataStructure;
import sdmx.registry.entities.Dataflow;
import sdmx.registry.entities.NameText;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Name.class)
public class Name_ { 

    public static volatile ListAttribute<Name, NameText> nameTextList;
    public static volatile ListAttribute<Name, Dataflow> dataflowList;
    public static volatile ListAttribute<Name, Codelist> codelistList;
    public static volatile ListAttribute<Name, Concept> conceptList;
    public static volatile SingularAttribute<Name, String> en;
    public static volatile ListAttribute<Name, ConceptScheme> conceptSchemeList;
    public static volatile SingularAttribute<Name, Long> id;
    public static volatile ListAttribute<Name, Code> codeList;
    public static volatile ListAttribute<Name, DataStructure> dataStructureList;

}
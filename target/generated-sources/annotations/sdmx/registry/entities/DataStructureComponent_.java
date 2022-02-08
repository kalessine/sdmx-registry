package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.AttributeRelationshipType;
import sdmx.registry.entities.CodelistReference;
import sdmx.registry.entities.ConceptReference;
import sdmx.registry.entities.ConceptSchemeReference;
import sdmx.registry.entities.DataStructure;
import sdmx.registry.entities.DataStructureComponentPK;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DataStructureComponent.class)
public class DataStructureComponent_ { 

    public static volatile SingularAttribute<DataStructureComponent, String> identifier;
    public static volatile SingularAttribute<DataStructureComponent, AttributeRelationshipType> attributerelationshiptype;
    public static volatile SingularAttribute<DataStructureComponent, String> assignmentstatus;
    public static volatile SingularAttribute<DataStructureComponent, CodelistReference> codelistenumeration;
    public static volatile SingularAttribute<DataStructureComponent, ConceptSchemeReference> conceptschemeenumeration;
    public static volatile SingularAttribute<DataStructureComponent, Annotated> annotations;
    public static volatile SingularAttribute<DataStructureComponent, DataStructure> dataStructure;
    public static volatile SingularAttribute<DataStructureComponent, Integer> type;
    public static volatile SingularAttribute<DataStructureComponent, ConceptReference> conceptidentity;
    public static volatile SingularAttribute<DataStructureComponent, DataStructureComponentPK> dataStructureComponentPK;

}
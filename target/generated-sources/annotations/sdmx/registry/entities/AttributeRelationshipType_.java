package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.AttributeRelationshipAttachGroups;
import sdmx.registry.entities.DataStructureComponent;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(AttributeRelationshipType.class)
public class AttributeRelationshipType_ { 

    public static volatile ListAttribute<AttributeRelationshipType, DataStructureComponent> dataStructureComponentList;
    public static volatile SingularAttribute<AttributeRelationshipType, Boolean> attachgroups;
    public static volatile SingularAttribute<AttributeRelationshipType, Long> id;
    public static volatile SingularAttribute<AttributeRelationshipType, String> primarymeasurereference;
    public static volatile SingularAttribute<AttributeRelationshipType, Boolean> empty;
    public static volatile SingularAttribute<AttributeRelationshipType, String> attachgroup;
    public static volatile ListAttribute<AttributeRelationshipType, AttributeRelationshipAttachGroups> attributeRelationshipAttachGroupsList;

}
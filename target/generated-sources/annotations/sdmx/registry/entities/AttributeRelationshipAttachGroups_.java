package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.AttributeRelationshipAttachGroupsPK;
import sdmx.registry.entities.AttributeRelationshipType;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(AttributeRelationshipAttachGroups.class)
public class AttributeRelationshipAttachGroups_ { 

    public static volatile SingularAttribute<AttributeRelationshipAttachGroups, AttributeRelationshipAttachGroupsPK> attributeRelationshipAttachGroupsPK;
    public static volatile SingularAttribute<AttributeRelationshipAttachGroups, String> attachgroup;
    public static volatile SingularAttribute<AttributeRelationshipAttachGroups, AttributeRelationshipType> attributeRelationshipType;

}
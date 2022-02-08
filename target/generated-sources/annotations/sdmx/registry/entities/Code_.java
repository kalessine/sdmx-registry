package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.CodePK;
import sdmx.registry.entities.Codelist;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Code.class)
public class Code_ { 

    public static volatile SingularAttribute<Code, CodePK> codePK;
    public static volatile SingularAttribute<Code, Codelist> codelist1;
    public static volatile SingularAttribute<Code, String> parentCode;
    public static volatile SingularAttribute<Code, Name> name;
    public static volatile SingularAttribute<Code, Annotated> annotations;

}
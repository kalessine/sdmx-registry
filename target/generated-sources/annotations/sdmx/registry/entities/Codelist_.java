package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.Code;
import sdmx.registry.entities.Name;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Codelist.class)
public class Codelist_ { 

    public static volatile SingularAttribute<Codelist, Long> codelist;
    public static volatile SingularAttribute<Codelist, Name> name;
    public static volatile SingularAttribute<Codelist, Annotated> annotations;
    public static volatile SingularAttribute<Codelist, String> agencyid;
    public static volatile SingularAttribute<Codelist, String> id;
    public static volatile ListAttribute<Codelist, Code> codeList;
    public static volatile SingularAttribute<Codelist, String> version;

}
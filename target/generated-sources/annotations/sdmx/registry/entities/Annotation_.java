package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotated;
import sdmx.registry.entities.AnnotationText;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Annotation.class)
public class Annotation_ { 

    public static volatile ListAttribute<Annotation, AnnotationText> annotationTextList;
    public static volatile SingularAttribute<Annotation, Long> id;
    public static volatile SingularAttribute<Annotation, String> annotationid;
    public static volatile SingularAttribute<Annotation, String> title;
    public static volatile SingularAttribute<Annotation, String> type;
    public static volatile SingularAttribute<Annotation, String> url;
    public static volatile SingularAttribute<Annotation, Annotated> annotated;

}
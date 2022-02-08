package sdmx.registry.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sdmx.registry.entities.Annotation;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-02-06T18:45:21", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(AnnotationText.class)
public class AnnotationText_ { 

    public static volatile SingularAttribute<AnnotationText, Annotation> annotation;
    public static volatile SingularAttribute<AnnotationText, String> text;
    public static volatile SingularAttribute<AnnotationText, Long> id;
    public static volatile SingularAttribute<AnnotationText, String> lang;

}
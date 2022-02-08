/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import java.util.ArrayList;
import java.util.List;
import sdmx.common.AnnotationType;
import sdmx.common.Annotations;
import sdmx.common.TextType;
import sdmx.registry.entities.Annotation;
import sdmx.registry.entities.AnnotationText;
import java.util.Collections;
import javax.persistence.EntityManager;

/**
 *
 * @author James
 */
public class AnnotationsUtil {
      public static sdmx.registry.entities.Annotated toDatabaseAnnotations(EntityManager em,Annotations annots) {
          if( annots==null ) return null;
          sdmx.registry.entities.Annotated at = new sdmx.registry.entities.Annotated();
          List<sdmx.registry.entities.Annotation> dbAnnots = new ArrayList<>(annots.size());
          for(int i=0;i<annots.size();i++) {
              sdmx.registry.entities.Annotation an = toDatabaseAnnotation(em,annots.getAnnotation(i));
              an.setAnnotated(at);
              dbAnnots.add(an);
          }
          at.setAnnotationList(dbAnnots);
          em.persist(at);
          return at;
      }
      public static sdmx.registry.entities.Annotation toDatabaseAnnotation(EntityManager em,AnnotationType annot) {
          if( annot == null ) return null;
          sdmx.registry.entities.Annotation dbAnnot = new sdmx.registry.entities.Annotation();
          dbAnnot.setTitle(annot.getAnnotationTitle());
          dbAnnot.setAnnotationid(annot.getId());
          dbAnnot.setType(annot.getAnnotationType());
          dbAnnot.setUrl(annot.getAnnotationUrl());
          List<sdmx.registry.entities.AnnotationText> texts = new ArrayList<>();
          for(int i=0;i<annot.getAnnotationText().size();i++) {
              sdmx.registry.entities.AnnotationText txt = toDatabaseAnnotationText(annot.getAnnotationText().get(i));
              txt.setAnnotation(dbAnnot);
              texts.add(txt);
              }
          dbAnnot.setAnnotationTextList(texts);
          em.persist(dbAnnot);
          return dbAnnot;
      }
      public static sdmx.registry.entities.AnnotationText toDatabaseAnnotationText(TextType tt) {
          sdmx.registry.entities.AnnotationText at = new sdmx.registry.entities.AnnotationText();
          at.setText(tt.getText());
          at.setLang(tt.getLang());
          return at;
      }
      public static Annotations toSDMXAnnotation(sdmx.registry.entities.Annotated annot) {
          if( annot == null ) return null;
          Annotations an = new Annotations();
          //an.setAnnotations(new ArrayList<AnnotationType>());
          for(sdmx.registry.entities.Annotation an2:annot.getAnnotationList()){
              an.getAnnotations().add(toSDMXAnnotationType(an2));
          }
          return an;
      }

    public static sdmx.common.AnnotationType toSDMXAnnotationType(sdmx.registry.entities.Annotation an) {
        AnnotationType annot = new AnnotationType();
        annot.setAnnotationTitle(an.getTitle());
        annot.setAnnotationType(an.getType());
        annot.setAnnotationUrl(an.getUrl());
        for(AnnotationText text:an.getAnnotationTextList()){
            annot.addAnnotationText(new TextType(text.getLang(),text.getText()));
        }
        return annot;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.LocalCodeRef;
import sdmx.commonreferences.LocalItemReference;
import sdmx.commonreferences.NCNameID;
import sdmx.commonreferences.types.ItemSchemePackageTypeCodelistType;
import sdmx.commonreferences.types.ItemTypeCodelistType;
import sdmx.registry.entities.Code;
import sdmx.registry.entities.Concept;
import sdmx.registry.entities.ConceptScheme;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.concept.ConceptType;

/**
 *
 * @author James
 */
public class ConceptUtil {
    public static Concept findDatabaseConcept(EntityManager em,String agency,String csid, String version,String id) {
        try{
        Query q = em.createQuery("select c from Concept c where c.conceptPK.agencyid=:agency and c.conceptPK.id=:csid and c.conceptPK.version=:version and c.conceptPK.conceptid=:id");
        //System.out.println("Find:"+agency+":"+csid+":"+version+":"+id);
        q.setParameter("agency", agency);
        q.setParameter("csid", csid);
        q.setParameter("version", version);
        q.setParameter("id", id);
        return (sdmx.registry.entities.Concept)q.getSingleResult();
        }catch(Exception e) { 
            return null; }
    }
    public static Concept createDatabaseConcept(EntityManager em, ConceptScheme cs, ConceptType c) {
        sdmx.registry.entities.Concept ct = new sdmx.registry.entities.Concept();
        ct.setConceptScheme(cs);
        ct.setConceptid(c.getId().toString());
        ct.setAnnotations(AnnotationsUtil.toDatabaseAnnotations(em,c.getAnnotations()));
        List<sdmx.registry.entities.Concept> concepts = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            concepts.add(ConceptUtil.createDatabaseConcept(em,cs,c.getConcept(i)));
        }
        NameUtil.setName(em,ct, c);
        return ct;
    }

    public static ConceptType toSDMXConcept(Concept c) {
        ConceptType cd = new ConceptType();
        cd.setAnnotations(AnnotationsUtil.toSDMXAnnotation(c.getAnnotations()));
        cd.setNames(NameUtil.toSDMXName(c.getName()));
        cd.setId(new NCNameID(c.getConceptid()));
        return cd;
    }
}

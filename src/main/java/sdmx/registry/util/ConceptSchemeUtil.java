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
import sdmx.commonreferences.NCNameID;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.registry.entities.Concept;
import sdmx.registry.entities.ConceptScheme;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;

/**
 *
 * @author James
 */
public class ConceptSchemeUtil {
    public static sdmx.registry.entities.ConceptScheme createDatabaseConceptScheme(EntityManager em,ConceptSchemeType c) {
        sdmx.registry.entities.ConceptScheme cs = new sdmx.registry.entities.ConceptScheme();
        cs.setAgencyid(c.getAgencyID().toString());
        cs.setId(c.getId().toString());
        cs.setVersion(c.getVersion().toString());
        cs.setAnnotations(AnnotationsUtil.toDatabaseAnnotations(em,c.getAnnotations()));
        List<sdmx.registry.entities.Concept> concepts = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            concepts.add(ConceptUtil.createDatabaseConcept(em,cs,c.getConcept(i)));
        }
        NameUtil.setName(em,cs, c);
        cs.setConceptList(concepts);
        em.persist(cs);
        em.flush();
        em.refresh(cs);
        return cs;
    }    

    public static ConceptScheme findDatabaseConceptScheme(EntityManager em, String agency, String id, String version) {
        try{
        Query q = em.createQuery("select c from ConceptScheme c where c.agencyid=:agency and c.id=:id and c.version=:version");
        q.setParameter("agency", agency);
        q.setParameter("id", id);
        q.setParameter("version", version);
        return (sdmx.registry.entities.ConceptScheme)q.getSingleResult();
        }catch(Exception ex) { 
            return null; }
    }
    public static List<sdmx.registry.entities.ConceptScheme> searchConceptScheme(EntityManager em, String agency, String id, String version) {
        if ("*".equals(version) && "all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select c from ConceptScheme c");
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.version=:version");
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(id)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.agencyid=:agency");
            q.setParameter("agency", agency);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(agency)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.id=:id");
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("*".equals(version)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.agencyid=:agency and c.id=:id");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("all".equals(id)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.agencyid=:agency and c.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else if ("all".equals(agency)) {
            Query q = em.createQuery("select c from ConceptScheme c where c.id==:id and c.version=:version");
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        } else {
            Query q = em.createQuery("select c from ConceptScheme c where c.agencyid=:agency and c.id=:id and c.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.ConceptScheme>) q.getResultList();
        }
    }
  public static ConceptSchemeType toSDMXConceptSchemeType(sdmx.registry.entities.ConceptScheme c) {
        ConceptSchemeType cl = new ConceptSchemeType();
        cl.setNames(NameUtil.toSDMXName(c.getName()));
        cl.setAgencyID(new NestedNCNameID(c.getAgencyid()));
        cl.setId(new NCNameID(c.getId()));
        cl.setVersion(new Version(c.getVersion()));
        List<sdmx.registry.entities.Concept> concepts = c.getConceptList();
        List<ItemType> items = new ArrayList<ItemType>();
        for (Concept cd : concepts) {
            items.add(ConceptUtil.toSDMXConcept(cd));
        }
        cl.setItems(items);
        return cl;
    }

    public static List<ConceptSchemeType> toSDMXConceptSchemeTypes(List<sdmx.registry.entities.ConceptScheme> cls) {
        List<ConceptSchemeType> result = new ArrayList<>();
        for (sdmx.registry.entities.ConceptScheme c : cls) {
            result.add(toSDMXConceptSchemeType(c));
        }
        return result;
    }
    public static void stub(ConceptSchemeType cs) {
        cs.setItems(null);
    }
}

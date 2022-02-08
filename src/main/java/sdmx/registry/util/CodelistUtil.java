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
import sdmx.commonreferences.NCNameID;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.registry.entities.Codelist;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.datastructure.DataStructureType;

/**
 *
 * @author James
 */
public class CodelistUtil {

    public static sdmx.registry.entities.Codelist createDatabaseCodelist(EntityManager em, CodelistType c) {
        sdmx.registry.entities.Codelist cl = new sdmx.registry.entities.Codelist();
        cl.setAgencyid(c.getAgencyID().toString());
        cl.setId(c.getId().toString());
        cl.setVersion(c.getVersion().toString());
        cl.setAnnotations(AnnotationsUtil.toDatabaseAnnotations(em,c.getAnnotations()));
        em.persist(cl);
        em.flush();
        em.refresh(cl);
        List<sdmx.registry.entities.Code> codes = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            codes.add(CodeUtil.createDatabaseCode(em, cl, c.getCode(i)));
        }
        NameUtil.setName(em, cl, c);
        cl.setCodeList(codes);
        return cl;
    }

    public static Codelist findDatabaseCodelist(EntityManager em, String agency, String id, String version) {
        try {
            Query q = em.createQuery("select c from Codelist c where c.agencyid=:agency and c.id=:id and c.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (sdmx.registry.entities.Codelist) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<sdmx.registry.entities.Codelist> searchCodelist(EntityManager em, String agency, String id, String version) {
        if ("*".equals(version) && "all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select c from Codelist c");
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select c from Codelist c where c.version=:version");
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(id)) {
            Query q = em.createQuery("select c from Codelist c where c.agencyID=:agency");
            q.setParameter("agency", agency);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(agency)) {
            Query q = em.createQuery("select c from Codelist c where c.id=:id");
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("*".equals(version)) {
            Query q = em.createQuery("select c from Codelist c where c.agencyID=:agency and c.id=:id");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("all".equals(id)) {
            Query q = em.createQuery("select c from Codelist c where c.agencyID=:agency and c.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else if ("all".equals(agency)) {
            Query q = em.createQuery("select c from Codelist c where c.id=:id and c.version=:version");
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        } else {
            Query q = em.createQuery("select c from Codelist c where c.agencyID=:agency and c.id=:id and c.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Codelist>) q.getResultList();
        }
    }

    public static CodelistType toSDMXCodelist(sdmx.registry.entities.Codelist c) {
        CodelistType cl = new CodelistType();
        cl.setNames(NameUtil.toSDMXName(c.getName()));
        cl.setAgencyID(new NestedNCNameID(c.getAgencyid()));
        cl.setId(new NCNameID(c.getId()));
        cl.setVersion(new Version(c.getVersion()));
        List<sdmx.registry.entities.Code> codes = c.getCodeList();
        List<ItemType> items = new ArrayList<ItemType>();
        for (sdmx.registry.entities.Code cd : codes) {
            items.add(CodeUtil.toSDMXCode(cd));
        }
        cl.setItems(items);
        return cl;
    }

    public static List<CodelistType> toSDMXCodelist(List<sdmx.registry.entities.Codelist> cls) {
        List<CodelistType> result = new ArrayList<>();
        for (sdmx.registry.entities.Codelist c : cls) {
            result.add(toSDMXCodelist(c));
        }
        return result;
    }
    public static void stub(CodelistType cl) {
        cl.setItems(null);
    
    }
}

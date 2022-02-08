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
import sdmx.registry.entities.Codelist;
import sdmx.registry.entities.Dataflow;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.dataflow.DataflowType;

/**
 *
 * @author James
 */
public class DataflowUtil {
    public static sdmx.registry.entities.Dataflow createDatabaseDataflow(EntityManager em, DataflowType df) {
        sdmx.registry.entities.DataStructureReference ref = DataStructureReferenceUtil.toDatabaseDataStructureReference(em, df.getStructure());
        sdmx.registry.entities.Dataflow df1 = new sdmx.registry.entities.Dataflow();
        df1.setAgencyid(df.getAgencyID().toString());
        df1.setId(df.getId().toString());
        df1.setVersion(df.getVersion().toString());
        df1.setAnnotations(AnnotationsUtil.toDatabaseAnnotations(em,df.getAnnotations()));
        NameUtil.setName(em, df1, df);
        df1.setStructure(ref);
        return df1;
    }

    public static Dataflow findDataflow(EntityManager em, String agency, String id, String version) {
        try {
            Query q = em.createQuery("select d from Dataflow d where d.agencyid=:agency and d.id=:id and d.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (sdmx.registry.entities.Dataflow) q.getSingleResult();
        } catch (Exception ex) {
            //ex.printStackTrace();
            return null;
        }
    }

    public static List<sdmx.registry.entities.Dataflow> searchDataflow(EntityManager em, String agency, String id, String version) {
        if ("*".equals(version) && "all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select d from Dataflow d");
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select d from Dataflow d where d.version=:version");
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(id)) {
            Query q = em.createQuery("select d from Dataflow d where d.agencyid=:agency");
            q.setParameter("agency", agency);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(agency)) {
            Query q = em.createQuery("select d from Dataflow d where d.id=:id");
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("*".equals(version)) {
            Query q = em.createQuery("select d from Dataflow d where d.agencyid=:agency and d.id=:id");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("all".equals(id)) {
            Query q = em.createQuery("select d from Dataflow d where d.agencyid=:agency and d.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else if ("all".equals(agency)) {
            Query q = em.createQuery("select d from Dataflow d where d.id=:id and d.id=:id");
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        } else {
            Query q = em.createQuery("select d from Dataflow d where d.agencyid=:agency and d.id=:id and d.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.Dataflow>) q.getResultList();
        }
    }

    public static DataflowType toSDMXDataflow(sdmx.registry.entities.Dataflow df) {
        if( df == null ) return null;
        DataflowType df1 = new DataflowType();
        df1.setNames(NameUtil.toSDMXName(df.getName()));
        df1.setAgencyID(new NestedNCNameID(df.getAgencyid()));
        df1.setId(new NCNameID(df.getId()));
        df1.setVersion(new Version(df.getVersion()));
        df1.setStructure(DataStructureReferenceUtil.toSDMXDataStructureReference(df.getStructure()));
        return df1;
    }
}

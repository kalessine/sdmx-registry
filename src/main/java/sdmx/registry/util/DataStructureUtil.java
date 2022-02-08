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
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.registry.entities.DataStructureComponent;
import sdmx.structure.base.Component;
import sdmx.structure.datastructure.AttributeListType;
import sdmx.structure.datastructure.AttributeType;
import sdmx.structure.datastructure.DataStructureComponents;
import sdmx.structure.datastructure.DataStructureType;
import sdmx.structure.datastructure.DimensionListType;
import sdmx.structure.datastructure.DimensionType;
import sdmx.structure.datastructure.MeasureDimensionType;
import sdmx.structure.datastructure.MeasureListType;
import sdmx.structure.datastructure.PrimaryMeasure;
import sdmx.structure.datastructure.TimeDimensionType;

/**
 *
 * @author James
 */
public class DataStructureUtil {

    public static sdmx.registry.entities.DataStructure createDatabaseDataStructure(EntityManager em, DataStructureType ds) {
        sdmx.registry.entities.DataStructure ds2 = new sdmx.registry.entities.DataStructure();
        ds2.setAgencyid(ds.getAgencyID().toString());
        ds2.setId(ds.getId().toString());
        if ("".equals(ds.getVersion().toString())) {
            ds2.setVersion("1.0");
        } else {
            ds2.setVersion(ds.getVersion().toString());
        }
        ds2.setAnnotations(AnnotationsUtil.toDatabaseAnnotations(em,ds.getAnnotations()));
        NameUtil.setName(em, ds2, ds);
        List<sdmx.registry.entities.DataStructureComponent> comps = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < ds.getDataStructureComponents().getDimensionList().getDimensions().size(); i++) {
            comps.add(DimensionUtil.toDatabaseDimension(em, ds, ds.getDataStructureComponents().getDimensionList().getDimensions().get(i), position++));
        }
        if (ds.getDataStructureComponents().getDimensionList().getTimeDimension() != null) {
            comps.add(DimensionUtil.toDatabaseDimension(em, ds, ds.getDataStructureComponents().getDimensionList().getTimeDimension(), position++));
        }
        if (ds.getDataStructureComponents().getDimensionList().getMeasureDimension() != null) {
            comps.add(DimensionUtil.toDatabaseDimension(em, ds, ds.getDataStructureComponents().getDimensionList().getMeasureDimension(), position++));
        }
        for (int i = 0; i < ds.getDataStructureComponents().getAttributeList().size(); i++) {
            comps.add(DimensionUtil.toDatabaseDimension(em, ds, ds.getDataStructureComponents().getAttributeList().getAttribute(i), position++));
        }
        comps.add(DimensionUtil.toDatabaseDimension(em, ds, ds.getDataStructureComponents().getMeasureList().getPrimaryMeasure(), position++));
        ds2.setDataStructureComponentList(comps);
        return ds2;
    }

    public static sdmx.registry.entities.DataStructure findDataStructure(EntityManager em, String agency, String id, String version) {
        try {
            Query q = em.createQuery("select d from DataStructure d where d.agencyid=:agency and d.id=:id and d.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            sdmx.registry.entities.DataStructure d=null;
            List<sdmx.registry.entities.DataStructure> list = q.getResultList();
            if(list.size()==0)return null;
            return list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<sdmx.registry.entities.DataStructure> searchDataStructure(EntityManager em, String agency, String id, String version) {
        if ("*".equals(version) && "all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select d from DataStructure d");
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("all".equals(id) && "all".equals(agency)) {
            Query q = em.createQuery("select d from DataStructure d where d.version=:version");
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(id)) {
            Query q = em.createQuery("select d from DataStructure d where d.agencyid=:agency");
            q.setParameter("agency", agency);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("*".equals(version) && "all".equals(agency)) {
            Query q = em.createQuery("select d from DataStructure d where d.id=:id");
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("*".equals(version)) {
            Query q = em.createQuery("select d from DataStructure d where d.id=:id and d.agencyid=:agency");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("all".equals(id)) {
            Query q = em.createQuery("select d from DataStructure d where d.version=:version and d.agencyid=:agency");
            q.setParameter("agency", agency);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else if ("all".equals(agency)) {
            Query q = em.createQuery("select d from DataStructure d where d.id=:id and d.version=:version");
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        } else {
            Query q = em.createQuery("select d from DataStructure d where d.agencyid=:agency and d.id=:id and d.version=:version");
            q.setParameter("agency", agency);
            q.setParameter("id", id);
            q.setParameter("version", version);
            return (List<sdmx.registry.entities.DataStructure>) q.getResultList();
        }
    }
    public static DataStructureType toSDMXDataStructure(sdmx.registry.entities.DataStructure d) {
        DataStructureType struct = new DataStructureType();
        struct.setAgencyID(new NestedNCNameID(d.getAgencyid()));
        struct.setId(new IDType(d.getId()));
        struct.setVersion(new Version(d.getVersion()));
        struct.setNames(NameUtil.toSDMXName(d.getName()));
        struct.setAnnotations(AnnotationsUtil.toSDMXAnnotation(d.getAnnotations()));
        List<DataStructureComponent> dcl=d.getDataStructureComponentList();
        if( struct.getDataStructureComponents()==null ) {
            struct.setDataStructureComponents(new DataStructureComponents());
        }
        for(DataStructureComponent dc:dcl){
            Component comp = DimensionUtil.toSDMXDimension(dc);
            if( comp instanceof DimensionType ) {
                if( struct.getDataStructureComponents().getDimensionList()==null ) {
                    struct.getDataStructureComponents().setDimensionList(new DimensionListType());
                }
                struct.getDataStructureComponents().getDimensionList().getDimensions().add((DimensionType)comp);
            }
            if( comp instanceof AttributeType ) {
                if( struct.getDataStructureComponents().getAttributeList()==null ) {
                    struct.getDataStructureComponents().setAttributeList(new AttributeListType());
                }
                struct.getDataStructureComponents().getAttributeList().getAttributes().add((AttributeType)comp);
            }
            if( comp instanceof PrimaryMeasure ) {
                if( struct.getDataStructureComponents().getMeasureList()==null ) {
                    struct.getDataStructureComponents().setMeasureList(new MeasureListType());
                }
                struct.getDataStructureComponents().getMeasureList().setPrimaryMeasure((PrimaryMeasure)comp);
            }
            if( comp instanceof MeasureDimensionType ) {
                if( struct.getDataStructureComponents().getDimensionList()==null ) {
                    struct.getDataStructureComponents().setDimensionList(new DimensionListType());
                }
                struct.getDataStructureComponents().getDimensionList().setMeasureDimension((MeasureDimensionType)comp);
            }
            if( comp instanceof TimeDimensionType ) {
                if( struct.getDataStructureComponents().getDimensionList()==null ) {
                    struct.getDataStructureComponents().setDimensionList(new DimensionListType());
                }
                struct.getDataStructureComponents().getDimensionList().setTimeDimension((TimeDimensionType)comp);
            }
            
        }
        return struct;
    }
    public void stub(DataStructureType dst) {
        dst.setDataStructureComponents(null);
    }
}

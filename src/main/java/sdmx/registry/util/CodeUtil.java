/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.LocalCodeRef;
import sdmx.commonreferences.LocalItemReference;
import sdmx.commonreferences.types.ItemSchemePackageTypeCodelistType;
import sdmx.commonreferences.types.ItemTypeCodelistType;
import sdmx.commonreferences.types.ObjectTypeCodelistType;
import sdmx.commonreferences.types.PackageTypeCodelistType;
import sdmx.registry.entities.Code;
import sdmx.registry.entities.CodePK;
import sdmx.registry.entities.Concept;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.codelist.CodelistType;

/**
 *
 * @author James
 */
public class CodeUtil {

    public static sdmx.registry.entities.Code findDatabaseCode(EntityManager em, String agency, String csid, String version, String id) {
        try {
            Query q = em.createQuery("select c from Code c where c.codePK.agencyid=:agency and c.codePK.codelistID=:csid and c.codePK.version=:version and c.codePK.id=:id");
            //System.out.println("Find:" + agency + ":" + csid + ":" + version + ":" + id);
            q.setParameter("agency", agency);
            q.setParameter("csid", csid);
            q.setParameter("version", version);
            q.setParameter("id", id);
            return (sdmx.registry.entities.Code) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public static sdmx.registry.entities.Code createDatabaseCode(EntityManager em, sdmx.registry.entities.Codelist cl, CodeType c) {
        sdmx.registry.entities.Code code = new sdmx.registry.entities.Code();
        NameUtil.setName(em, code, c);
        if (c.getParent() != null) {
            code.setParentCode(c.getParent().getId().toString());
        }
        CodePK pk = new CodePK();
        pk.setCodelist(cl.getCodelist());
        pk.setId(c.getId().toString());
        code.setCodePK(pk);
        return code;
    }

    public static CodeType toSDMXCode(Code c) {
        CodeType cd = new CodeType();
        cd.setAnnotations(AnnotationsUtil.toSDMXAnnotation(c.getAnnotations()));
        cd.setNames(NameUtil.toSDMXName(c.getName()));
        cd.setId(new IDType(c.getCodePK().getId()));
        if (c.getParentCode() != null) {
            sdmx.commonreferences.LocalCodeRef ref = new LocalCodeRef(new IDType(c.getParentCode()), ItemTypeCodelistType.CODE, ItemSchemePackageTypeCodelistType.CODELIST);
            cd.setParent(new LocalItemReference(ref));
        }
        return cd;
    }
}

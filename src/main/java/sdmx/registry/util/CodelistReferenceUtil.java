/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import javax.persistence.EntityManager;
import sdmx.commonreferences.CodelistReference;
import sdmx.commonreferences.ConceptReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.ItemSchemeReferenceBase;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;

/**
 *
 * @author James
 */
public class CodelistReferenceUtil {
    public static sdmx.registry.entities.CodelistReference toDatabaseCodelistReference(EntityManager em,sdmx.commonreferences.CodelistReference enumeration) {
         sdmx.registry.entities.CodelistReference ref = new sdmx.registry.entities.CodelistReference();
         ref.setAgencyid(enumeration.getAgencyId().toString());
         ref.setId(enumeration.getMaintainableParentId().toString());
         ref.setVersion(enumeration.getVersion().toString());
         em.persist(ref);
         em.flush();
         em.refresh(ref);
         /*sdmx.registry.entities.Codelist c = CodelistUtil.findDatabaseCodelist(em,enumeration.getAgencyId().toString(),enumeration.getMaintainableParentId().toString(),enumeration.getVersion().toString());
         if(c !=null ) {
         ref.setCodelist(c);
         c.getCodelistReferenceList().add(ref);
         em.persist(ref);
         em.flush();
         em.refresh(ref);
         }else{
             System.out.println("Target Codelist is null!!"+":"+enumeration.getAgencyId()+":"+enumeration.getId()+":"+enumeration.getVersion());
             enumeration.dump();
             return null;
         }*/
         return ref;
    }    

    public static ItemSchemeReferenceBase toSDMXCodelistReference(sdmx.registry.entities.CodelistReference clr) {
       return CodelistReference.create(new NestedNCNameID(clr.getAgencyid()),new IDType(clr.getId()),new Version(clr.getVersion()));
    }
}

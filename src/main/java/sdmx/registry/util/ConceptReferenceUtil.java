/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import javax.persistence.EntityManager;
import sdmx.commonreferences.ConceptReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;

/**
 *
 * @author James
 */
public class ConceptReferenceUtil {

    public static sdmx.registry.entities.ConceptReference toDatabaseConceptreference(EntityManager em, ConceptReference conceptIdentity) {
        sdmx.registry.entities.ConceptReference ref = new sdmx.registry.entities.ConceptReference();
        ref.setAgencyid(conceptIdentity.getAgencyId().toString());
        ref.setId(conceptIdentity.getMaintainableParentId().toString());
        ref.setVersion(conceptIdentity.getVersion().toString());
        ref.setConceptid(conceptIdentity.getId().toString());
        em.persist(ref);
        em.flush();
        em.refresh(ref);
        /*sdmx.registry.entities.Concept c = ConceptUtil.findDatabaseConcept(em, conceptIdentity.getAgencyId().toString(), conceptIdentity.getMaintainableParentId().toString(), conceptIdentity.getVersion().toString(), conceptIdentity.getId().toString());
        if (c != null) {
            ref.setConcept(c);

            return ref;
        } else {
            System.out.println("target concept is null!!!"+conceptIdentity.getAgencyId()+":"+conceptIdentity.getId()+":"+conceptIdentity.getVersion());
            conceptIdentity.dump();
            return null;
        }
        */
        return ref;
    }
    public static ConceptReference toSDMXReference(sdmx.registry.entities.ConceptReference con) {
        return ConceptReference.create(new NestedNCNameID(con.getAgencyid()), new IDType(con.getId()), new Version(con.getVersion()), new IDType(con.getConceptid()));
    }
}

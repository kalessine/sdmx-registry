/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import sdmx.common.TextType;
import sdmx.registry.entities.Dataflow;
import sdmx.registry.entities.DataStructure;
import sdmx.registry.entities.Name;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.concept.ConceptType;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structure.datastructure.DataStructureType;

/**
 *
 * @author James
 */
public class NameUtil {

    public static void setName(EntityManager em, DataStructure ds2, DataStructureType ds) {
        sdmx.registry.entities.Name name = toDatabaseName(em, ds.getNames());
        ds2.setName(name);
    }

    public static void setName(EntityManager em, Dataflow df1, DataflowType df) {
        sdmx.registry.entities.Name name = toDatabaseName(em, df.getNames());
        df1.setName(name);
    }

    public static void setName(EntityManager em, sdmx.registry.entities.ConceptScheme conceptScheme, ConceptSchemeType cl) {
        sdmx.registry.entities.Name name = toDatabaseName(em, cl.getNames());
        conceptScheme.setName(name);
    }

    public static void setName(EntityManager em, sdmx.registry.entities.Concept concept, ConceptType cl) {
        sdmx.registry.entities.Name name = toDatabaseName(em, cl.getNames());
        concept.setName(name);
    }

    public static void setName(EntityManager em, sdmx.registry.entities.Codelist codeList, CodelistType cl) {
        sdmx.registry.entities.Name name = toDatabaseName(em, cl.getNames());
        codeList.setName(name);
    }

    public static void setName(EntityManager em, sdmx.registry.entities.Code code, CodeType cl) {
        sdmx.registry.entities.Name name = toDatabaseName(em, cl.getNames());
        code.setName(name);
    }

    public static sdmx.registry.entities.Name toDatabaseName(EntityManager em, List<sdmx.common.Name> names) {
        sdmx.registry.entities.Name nm = new sdmx.registry.entities.Name();
        List<sdmx.registry.entities.NameText> result = new ArrayList<>();
        em.persist(nm);
        em.flush();
        em.refresh(nm);
        for (int i = 0; i < names.size(); i++) {
            result.add(toDatabaseNameText(nm, names.get(i)));
        }
        nm.setNameTextList(result);
        em.merge(nm);
        return nm;
    }

    public static sdmx.registry.entities.NameText toDatabaseNameText(sdmx.registry.entities.Name nam, sdmx.common.Name name) {
        sdmx.registry.entities.NameText nm = new sdmx.registry.entities.NameText();
        sdmx.registry.entities.NameTextPK pk = new sdmx.registry.entities.NameTextPK();
        pk.setId(nam.getId());
        nm.setText(name.getText());
        if ("en".equals(name.getLang())) {
            nam.setEn(name.getText());
        }
        pk.setLang(name.getLang());
        nm.setNameTextPK(pk);
        return nm;
    }

    public static List<sdmx.common.Name> toSDMXName(sdmx.registry.entities.Name name) {
        List<sdmx.common.Name> result = new ArrayList<sdmx.common.Name>();
        for (sdmx.registry.entities.NameText nt : name.getNameTextList()) {
            result.add(new sdmx.common.Name(nt.getNameTextPK().getLang(), nt.getText()));
        }
        return result;
    }

}

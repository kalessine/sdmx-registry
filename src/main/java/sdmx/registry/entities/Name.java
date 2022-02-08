/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "Name", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Name.findAll", query = "SELECT n FROM Name n"),
    @NamedQuery(name = "Name.findById", query = "SELECT n FROM Name n WHERE n.id = :id")})
public class Name implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Lob
    @Column(name = "en", length = 65535)
    private String en;
    @OneToMany(mappedBy = "name")
    private List<Concept> conceptList;
    @OneToMany(mappedBy = "name")
    private List<Codelist> codelistList;
    @OneToMany(mappedBy = "name")
    private List<DataStructure> dataStructureList;
    @OneToMany(mappedBy = "name")
    private List<Code> codeList;
    @OneToMany(mappedBy = "name")
    private List<ConceptScheme> conceptSchemeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "name")
    private List<NameText> nameTextList;
    @OneToMany(mappedBy = "name")
    private List<Dataflow> dataflowList;

    public Name() {
    }

    public Name(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @XmlTransient
    public List<Concept> getConceptList() {
        return conceptList;
    }

    public void setConceptList(List<Concept> conceptList) {
        this.conceptList = conceptList;
    }

    @XmlTransient
    public List<Codelist> getCodelistList() {
        return codelistList;
    }

    public void setCodelistList(List<Codelist> codelistList) {
        this.codelistList = codelistList;
    }

    @XmlTransient
    public List<DataStructure> getDataStructureList() {
        return dataStructureList;
    }

    public void setDataStructureList(List<DataStructure> dataStructureList) {
        this.dataStructureList = dataStructureList;
    }

    @XmlTransient
    public List<Code> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }

    @XmlTransient
    public List<ConceptScheme> getConceptSchemeList() {
        return conceptSchemeList;
    }

    public void setConceptSchemeList(List<ConceptScheme> conceptSchemeList) {
        this.conceptSchemeList = conceptSchemeList;
    }

    @XmlTransient
    public List<NameText> getNameTextList() {
        return nameTextList;
    }

    public void setNameTextList(List<NameText> nameTextList) {
        this.nameTextList = nameTextList;
    }

    @XmlTransient
    public List<Dataflow> getDataflowList() {
        return dataflowList;
    }

    public void setDataflowList(List<Dataflow> dataflowList) {
        this.dataflowList = dataflowList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Name)) {
            return false;
        }
        Name other = (Name) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Name[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Annotated", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annotated.findAll", query = "SELECT a FROM Annotated a"),
    @NamedQuery(name = "Annotated.findById", query = "SELECT a FROM Annotated a WHERE a.id = :id"),
    @NamedQuery(name = "Annotated.findByPadfield", query = "SELECT a FROM Annotated a WHERE a.padfield = :padfield")})
public class Annotated implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "padfield", length = 1)
    private String padfield;
    @OneToMany(mappedBy = "annotations")
    private List<Concept> conceptList;
    @OneToMany(mappedBy = "annotations")
    private List<Codelist> codelistList;
    @OneToMany(mappedBy = "annotations")
    private List<DataStructure> dataStructureList;
    @OneToMany(mappedBy = "annotations")
    private List<Code> codeList;
    @OneToMany(mappedBy = "annotated")
    private List<Annotation> annotationList;
    @OneToMany(mappedBy = "annotations")
    private List<DataStructureComponent> dataStructureComponentList;
    @OneToMany(mappedBy = "annotations")
    private List<ConceptScheme> conceptSchemeList;
    @OneToMany(mappedBy = "annotations")
    private List<Dataflow> dataflowList;

    public Annotated() {
    }

    public Annotated(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPadfield() {
        return padfield;
    }

    public void setPadfield(String padfield) {
        this.padfield = padfield;
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
    public List<Annotation> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<Annotation> annotationList) {
        this.annotationList = annotationList;
    }

    @XmlTransient
    public List<DataStructureComponent> getDataStructureComponentList() {
        return dataStructureComponentList;
    }

    public void setDataStructureComponentList(List<DataStructureComponent> dataStructureComponentList) {
        this.dataStructureComponentList = dataStructureComponentList;
    }

    @XmlTransient
    public List<ConceptScheme> getConceptSchemeList() {
        return conceptSchemeList;
    }

    public void setConceptSchemeList(List<ConceptScheme> conceptSchemeList) {
        this.conceptSchemeList = conceptSchemeList;
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
        if (!(object instanceof Annotated)) {
            return false;
        }
        Annotated other = (Annotated) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Annotated[ id=" + id + " ]";
    }
    
}

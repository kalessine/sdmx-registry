/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "Concept", catalog = "registry", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"agencyID", "id", "version", "conceptid"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concept.findAll", query = "SELECT c FROM Concept c"),
    @NamedQuery(name = "Concept.findByConcept", query = "SELECT c FROM Concept c WHERE c.concept = :concept"),
    @NamedQuery(name = "Concept.findByConceptid", query = "SELECT c FROM Concept c WHERE c.conceptid = :conceptid")})
public class Concept implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "concept", nullable = false)
    private Long concept;
    @Basic(optional = false)
    @Column(name = "conceptid", nullable = false, length = 100)
    private String conceptid;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumns({
        @JoinColumn(name = "agencyID", referencedColumnName = "agencyid", nullable = false),
        @JoinColumn(name = "id", referencedColumnName = "id", nullable = false),
        @JoinColumn(name = "version", referencedColumnName = "version", nullable = false)})
    @ManyToOne(optional = false)
    private ConceptScheme conceptScheme;
    @JoinColumn(name = "name", referencedColumnName = "id")
    @ManyToOne
    private Name name;

    public Concept() {
    }

    public Concept(Long concept) {
        this.concept = concept;
    }

    public Concept(Long concept, String conceptid) {
        this.concept = concept;
        this.conceptid = conceptid;
    }

    public Long getConcept() {
        return concept;
    }

    public void setConcept(Long concept) {
        this.concept = concept;
    }

    public String getConceptid() {
        return conceptid;
    }

    public void setConceptid(String conceptid) {
        this.conceptid = conceptid;
    }

    public Annotated getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotated annotations) {
        this.annotations = annotations;
    }

    public ConceptScheme getConceptScheme() {
        return conceptScheme;
    }

    public void setConceptScheme(ConceptScheme conceptScheme) {
        this.conceptScheme = conceptScheme;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (concept != null ? concept.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concept)) {
            return false;
        }
        Concept other = (Concept) object;
        if ((this.concept == null && other.concept != null) || (this.concept != null && !this.concept.equals(other.concept))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Concept[ concept=" + concept + " ]";
    }
    
}

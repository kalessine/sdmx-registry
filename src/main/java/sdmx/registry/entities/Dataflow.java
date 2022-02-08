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
@Table(name = "Dataflow", catalog = "registry", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"agencyid", "id", "version"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dataflow.findAll", query = "SELECT d FROM Dataflow d"),
    @NamedQuery(name = "Dataflow.findByAgencyid", query = "SELECT d FROM Dataflow d WHERE d.agencyid = :agencyid"),
    @NamedQuery(name = "Dataflow.findById", query = "SELECT d FROM Dataflow d WHERE d.id = :id"),
    @NamedQuery(name = "Dataflow.findByVersion", query = "SELECT d FROM Dataflow d WHERE d.version = :version"),
    @NamedQuery(name = "Dataflow.findByDataflow", query = "SELECT d FROM Dataflow d WHERE d.dataflow = :dataflow")})
public class Dataflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "agencyid", nullable = false, length = 100)
    private String agencyid;
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false, length = 50)
    private String version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dataflow", nullable = false)
    private Long dataflow;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumn(name = "structure", referencedColumnName = "refid", nullable = false)
    @ManyToOne(optional = false)
    private DataStructureReference structure;
    @JoinColumn(name = "name", referencedColumnName = "id")
    @ManyToOne
    private Name name;

    public Dataflow() {
    }

    public Dataflow(Long dataflow) {
        this.dataflow = dataflow;
    }

    public Dataflow(Long dataflow, String agencyid, String id, String version) {
        this.dataflow = dataflow;
        this.agencyid = agencyid;
        this.id = id;
        this.version = version;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getDataflow() {
        return dataflow;
    }

    public void setDataflow(Long dataflow) {
        this.dataflow = dataflow;
    }

    public Annotated getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotated annotations) {
        this.annotations = annotations;
    }

    public DataStructureReference getStructure() {
        return structure;
    }

    public void setStructure(DataStructureReference structure) {
        this.structure = structure;
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
        hash += (dataflow != null ? dataflow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dataflow)) {
            return false;
        }
        Dataflow other = (Dataflow) object;
        if ((this.dataflow == null && other.dataflow != null) || (this.dataflow != null && !this.dataflow.equals(other.dataflow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Dataflow[ dataflow=" + dataflow + " ]";
    }
    
}

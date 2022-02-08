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
@Table(name = "AttributeRelationshipType", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AttributeRelationshipType.findAll", query = "SELECT a FROM AttributeRelationshipType a"),
    @NamedQuery(name = "AttributeRelationshipType.findById", query = "SELECT a FROM AttributeRelationshipType a WHERE a.id = :id"),
    @NamedQuery(name = "AttributeRelationshipType.findByEmpty", query = "SELECT a FROM AttributeRelationshipType a WHERE a.empty = :empty"),
    @NamedQuery(name = "AttributeRelationshipType.findByAttachgroup", query = "SELECT a FROM AttributeRelationshipType a WHERE a.attachgroup = :attachgroup"),
    @NamedQuery(name = "AttributeRelationshipType.findByAttachgroups", query = "SELECT a FROM AttributeRelationshipType a WHERE a.attachgroups = :attachgroups"),
    @NamedQuery(name = "AttributeRelationshipType.findByPrimarymeasurereference", query = "SELECT a FROM AttributeRelationshipType a WHERE a.primarymeasurereference = :primarymeasurereference")})
public class AttributeRelationshipType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "empty")
    private Boolean empty;
    @Column(name = "attachgroup", length = 200)
    private String attachgroup;
    @Column(name = "attachgroups")
    private Boolean attachgroups;
    @Column(name = "primarymeasurereference", length = 200)
    private String primarymeasurereference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeRelationshipType")
    private List<AttributeRelationshipAttachGroups> attributeRelationshipAttachGroupsList;
    @OneToMany(mappedBy = "attributerelationshiptype")
    private List<DataStructureComponent> dataStructureComponentList;

    public AttributeRelationshipType() {
    }

    public AttributeRelationshipType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public String getAttachgroup() {
        return attachgroup;
    }

    public void setAttachgroup(String attachgroup) {
        this.attachgroup = attachgroup;
    }

    public Boolean getAttachgroups() {
        return attachgroups;
    }

    public void setAttachgroups(Boolean attachgroups) {
        this.attachgroups = attachgroups;
    }

    public String getPrimarymeasurereference() {
        return primarymeasurereference;
    }

    public void setPrimarymeasurereference(String primarymeasurereference) {
        this.primarymeasurereference = primarymeasurereference;
    }

    @XmlTransient
    public List<AttributeRelationshipAttachGroups> getAttributeRelationshipAttachGroupsList() {
        return attributeRelationshipAttachGroupsList;
    }

    public void setAttributeRelationshipAttachGroupsList(List<AttributeRelationshipAttachGroups> attributeRelationshipAttachGroupsList) {
        this.attributeRelationshipAttachGroupsList = attributeRelationshipAttachGroupsList;
    }

    @XmlTransient
    public List<DataStructureComponent> getDataStructureComponentList() {
        return dataStructureComponentList;
    }

    public void setDataStructureComponentList(List<DataStructureComponent> dataStructureComponentList) {
        this.dataStructureComponentList = dataStructureComponentList;
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
        if (!(object instanceof AttributeRelationshipType)) {
            return false;
        }
        AttributeRelationshipType other = (AttributeRelationshipType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.AttributeRelationshipType[ id=" + id + " ]";
    }
    
}

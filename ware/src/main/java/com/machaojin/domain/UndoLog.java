package com.machaojin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;
/**
 * 【请填写功能名称】对象 undo_log
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Alias("UndoLog")
public class UndoLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long branchId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String context;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String rollbackInfo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long logStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date logCreated;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date logModified;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ext;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBranchId(Long branchId) 
    {
        this.branchId = branchId;
    }

    public Long getBranchId() 
    {
        return branchId;
    }
    public void setXid(String xid) 
    {
        this.xid = xid;
    }

    public String getXid() 
    {
        return xid;
    }
    public void setContext(String context) 
    {
        this.context = context;
    }

    public String getContext() 
    {
        return context;
    }
    public void setRollbackInfo(String rollbackInfo) 
    {
        this.rollbackInfo = rollbackInfo;
    }

    public String getRollbackInfo() 
    {
        return rollbackInfo;
    }
    public void setLogStatus(Long logStatus) 
    {
        this.logStatus = logStatus;
    }

    public Long getLogStatus() 
    {
        return logStatus;
    }
    public void setLogCreated(Date logCreated) 
    {
        this.logCreated = logCreated;
    }

    public Date getLogCreated() 
    {
        return logCreated;
    }
    public void setLogModified(Date logModified) 
    {
        this.logModified = logModified;
    }

    public Date getLogModified() 
    {
        return logModified;
    }
    public void setExt(String ext) 
    {
        this.ext = ext;
    }

    public String getExt() 
    {
        return ext;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("branchId", getBranchId())
            .append("xid", getXid())
            .append("context", getContext())
            .append("rollbackInfo", getRollbackInfo())
            .append("logStatus", getLogStatus())
            .append("logCreated", getLogCreated())
            .append("logModified", getLogModified())
            .append("ext", getExt())
            .toString();
    }
}
